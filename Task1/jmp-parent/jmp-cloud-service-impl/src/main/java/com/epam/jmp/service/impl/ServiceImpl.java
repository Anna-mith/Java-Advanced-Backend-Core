package com.epam.jmp.service.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.service.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ServiceImpl implements Service {
    private final Map<Subscription, BankCard> subscriptions;

    public ServiceImpl() {
        subscriptions = new HashMap<>();
    }

    @Override
    public void subscribe(BankCard bankCard) {
        var subscription = new Subscription(bankCard.getNumber(), LocalDate.now());
        subscriptions.put(subscription, bankCard);
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return subscriptions.keySet().stream()
                .filter(sub -> sub.getBankcard().equals(cardNumber))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return subscriptions.values().stream()
                .map(BankCard::getUser)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return subscriptions.keySet().stream()
                .filter(predicate)
                .collect(Collectors.toUnmodifiableList());
    }

    public Map<Subscription, BankCard> getSubscriptions() {
        return subscriptions;
    }
}
