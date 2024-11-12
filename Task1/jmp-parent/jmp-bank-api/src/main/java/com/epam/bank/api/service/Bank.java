package com.epam.bank.api.service;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType cardType);

    BankCard createBankCardWithMethodRef(User user, BankCardType cardType);
}
