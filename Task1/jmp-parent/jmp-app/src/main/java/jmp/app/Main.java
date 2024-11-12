package jmp.app;

import com.epam.jmp.bank.impl.BankImpl;
import com.epam.jmp.dto.*;
import com.epam.jmp.service.impl.ServiceImpl;

public class Main {
    public static void main(String[] args) {
        //demonstration getAllSubscriptionsByCondition() method
        var bank = new BankImpl();
        var service = new ServiceImpl();
        var users = User.getMockUsers();
        for (var i = 0; i < users.size(); i++) {
            var user = users.get(i);
            if (i % 2 == 0) {
                var card = bank.createBankCardWithMethodRef(user, BankCardType.CREDIT);
                service.subscribe(card);
            } else {
                var card = bank.createBankCardWithMethodRef(users.get(i), BankCardType.DEBIT);
                service.subscribe(card);
            }
        }

        var expectedSub = service.getSubscriptions().
                entrySet().stream().findAny().orElseThrow();
        System.out.println("Expected Card Number " + expectedSub.getKey());
        System.out.print("Card Number By Condition ");
        service.getAllSubscriptionsByCondition(sub -> sub.getBankcard().equals(expectedSub.getValue().getNumber()))
                .forEach(System.out::println);
    }
}