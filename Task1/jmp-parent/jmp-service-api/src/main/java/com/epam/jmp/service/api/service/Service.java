package com.epam.jmp.service.api.service;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate);

    Map<Subscription, BankCard> getSubscriptions();

    default double getAverageUsersAge() {
        return getAllUsers().stream()
                .map(Service::getUserAge)
                .mapToDouble(i -> i).average().orElseThrow();
    }

    static boolean isPayableUser(User user) {
        return getUserAge(user) >= 18;
    }

    private static long getUserAge(User user) {
        return user.getBirthday().until(LocalDate.now(), ChronoUnit.YEARS);
    }
}
