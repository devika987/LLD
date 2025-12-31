package com.example.atm.model;

import com.example.atm.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {

    private final Account account;
    private final TransactionType type;
    private final double amount;
    private final LocalDateTime timestamp;

    public Transaction(Account account, TransactionType type, double amount, String timestamp) {
        this.account = account;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }
}
