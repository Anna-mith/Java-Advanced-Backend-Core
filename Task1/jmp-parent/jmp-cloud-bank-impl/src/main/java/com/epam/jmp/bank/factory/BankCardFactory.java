package com.epam.jmp.bank.factory;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.User;

@FunctionalInterface
public interface BankCardFactory {
    BankCard create(String cardNumber, User user, double limit);
}