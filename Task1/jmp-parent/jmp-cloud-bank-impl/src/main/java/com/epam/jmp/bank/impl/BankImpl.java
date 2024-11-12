package com.epam.jmp.bank.impl;

import com.epam.bank.api.service.Bank;
import com.epam.jmp.bank.factory.BankCardFactory;
import com.epam.jmp.dto.*;

import java.util.random.RandomGenerator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BankImpl implements Bank {
    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        var limit = 250000.00;
        return switch (cardType) {
            case CREDIT:
                yield new CreditBankCard(generateCardNumber(), user, limit);
            case DEBIT:
                yield new DebitBankCard(generateCardNumber(), user, limit);
        };
    }

    @Override
    public BankCard createBankCardWithMethodRef(User user, BankCardType cardType) {
        var limit = 250000.00;
        BankCardFactory factoryImpl = switch (cardType) {
            case CREDIT -> CreditBankCard::new;
            case DEBIT -> DebitBankCard::new;
        };
        return factoryImpl.create(generateCardNumber(), user, limit);
    }

    private String generateCardNumber() {
        var random = RandomGenerator.getDefault();

        return IntStream.range(0, 16)
                .mapToObj(i -> String.valueOf(random.nextInt(10)))
                .collect(Collectors.joining());
    }
}
