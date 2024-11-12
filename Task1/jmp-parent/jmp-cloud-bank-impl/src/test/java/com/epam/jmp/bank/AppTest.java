package com.epam.jmp.bank;

import com.epam.bank.api.service.Bank;
import com.epam.jmp.bank.impl.BankImpl;
import com.epam.jmp.dto.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {

    private final Bank bank = new BankImpl();
    private final List<User> users = User.getMockUsers();

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

    public void testCreateBankCard() {
        for (var i = 0; i < users.size(); i++) {
            var user = users.get(i);
            if (i % 2 == 0) {
                var card = bank.createBankCard(user, BankCardType.CREDIT);
                assertEquals(card.getUser(), user);
                assertTrue(card instanceof CreditBankCard);
            } else {
                var card = bank.createBankCard(users.get(i), BankCardType.DEBIT);
                assertEquals(card.getUser(), user);
                assertTrue(card instanceof DebitBankCard);
            }
        }
    }

    public void testBankCardWithMethodRef() {
        for (var i = 0; i < users.size(); i++) {
            var user = users.get(i);
            if (i % 2 == 0) {
                var card = bank.createBankCardWithMethodRef(user, BankCardType.CREDIT);
                assertEquals(card.getUser(), user);
                assertTrue(card instanceof CreditBankCard);
            } else {
                var card = bank.createBankCardWithMethodRef(users.get(i), BankCardType.DEBIT);
                assertEquals(card.getUser(), user);
                assertTrue(card instanceof DebitBankCard);
            }
        }
    }
}
