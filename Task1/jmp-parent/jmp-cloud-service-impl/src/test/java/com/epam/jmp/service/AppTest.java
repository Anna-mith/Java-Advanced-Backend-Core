package com.epam.jmp.service;

import com.epam.jmp.bank.impl.BankImpl;
import com.epam.jmp.dto.*;
import com.epam.jmp.service.api.exceptions.SubscriptionNotFoundException;
import com.epam.jmp.service.api.service.Service;
import com.epam.jmp.service.impl.ServiceImpl;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    private final List<BankCard> bankcards = getMockBankCards();
    private final Service service = new ServiceImpl();

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    public void testSubscribe() {
        bankcards.forEach(service::subscribe);
        var subscriptions = service.getSubscriptions();
        bankcards.forEach(card -> assertTrue(subscriptions.containsValue(card)));
        var bankCardNumbers = bankcards.stream()
                .map(BankCard::getNumber)
                .collect(Collectors.toUnmodifiableList());
        var subCardNumbers = subscriptions.keySet().stream()
                .map(Subscription::getBankcard)
                .collect(Collectors.toUnmodifiableList());
        assertTrue(bankCardNumbers.containsAll(subCardNumbers));
    }

    public void testGetSubscriptionByBankCardNumber() {
        var card = bankcards.get(0);
        bankcards.forEach(service::subscribe);
        var subByCardNumber = service.getSubscriptionByBankCardNumber(card.getNumber());
        var expectedSubscription = getKeyByValue(service.getSubscriptions(), card);
        assertEquals(expectedSubscription, subByCardNumber.orElseThrow());

        var cardNumber = "0000000000";
        var expectedMessage = "Subscription not found for bank card number: " + cardNumber;
        var subByCardNumberNonExistent = service.getSubscriptionByBankCardNumber(cardNumber);
        try {
            subByCardNumberNonExistent.orElseThrow(SubscriptionNotFoundException::new);
        } catch (SubscriptionNotFoundException exc) {
            assertTrue(exc.getMessage(cardNumber).contains(expectedMessage));
        }
    }

    public void testGetAllUsers() {
        bankcards.forEach(service::subscribe);
        var users = service.getAllUsers();
        var expectedUsers = User.getMockUsers();
        assertTrue(expectedUsers.containsAll(users));
    }

    public void testGetAllSubscriptionsByCondition() {
        bankcards.forEach(service::subscribe);
        var card = bankcards.get(2);
        var subsByCondition = service.getAllSubscriptionsByCondition(sub -> sub.getBankcard().equals(card.getNumber()));
        assertEquals(1, subsByCondition.size());
        assertEquals(card.getNumber(), subsByCondition.get(0).getBankcard());
    }


    public void testGetAverageUsersAge() {
        bankcards.forEach(service::subscribe);
        var expectedAverageAge = 41.0;
        var averageUsersAge = service.getAverageUsersAge();
        assertEquals(expectedAverageAge, averageUsersAge);
    }

    public void testIsPayableUser() {
        var users = User.getMockUsers();
        for (var user : users) {
            if (user.getName().equals("John")) {
                assertFalse(Service.isPayableUser(user));
            } else {
                assertTrue(Service.isPayableUser(user));
            }
        }
    }

    private Subscription getKeyByValue(Map<Subscription, BankCard> subscriptions, BankCard card) {
        for (var entry : subscriptions.entrySet()) {
            if (entry.getValue().getNumber().equals(card.getNumber())) {
                return entry.getKey();
            }
        }

        return null;
    }

    private static List<BankCard> getMockBankCards() {
        var users = User.getMockUsers();
        var bankCards = new ArrayList<BankCard>();
        var bank = new BankImpl();
        for (var i = 0; i < users.size(); i++) {
            var user = users.get(i);
            if (i % 2 == 0) {
                var card = bank.createBankCard(user, BankCardType.CREDIT);
                bankCards.add(card);
            } else {
                var card = bank.createBankCard(users.get(i), BankCardType.DEBIT);
                bankCards.add(card);
            }
        }
        return bankCards;
    }
}
