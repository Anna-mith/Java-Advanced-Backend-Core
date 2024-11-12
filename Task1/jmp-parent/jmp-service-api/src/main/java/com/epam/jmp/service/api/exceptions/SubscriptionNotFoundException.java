package com.epam.jmp.service.api.exceptions;

public class SubscriptionNotFoundException extends RuntimeException {
    public String getMessage(String cardNumber) {
        return "Subscription not found for bank card number: " + cardNumber + "\n" + super.getMessage();
    }
}
