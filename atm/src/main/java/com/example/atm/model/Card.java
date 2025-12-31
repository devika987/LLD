package com.example.atm.model;

import lombok.Data;

@Data
public class Card {

    private final String cardNumber;
    private String pin;
    private final String accountNumber;
    private final String expirationDate;
    private final String cardHolderName;
    private final String cvv;

    public Card(String cardNumber, String pin, String accounNumber, String expirationDate, String cardHolderName, String cvv) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.accountNumber = accounNumber;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
    }
}
