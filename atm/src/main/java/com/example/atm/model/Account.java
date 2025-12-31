package com.example.atm.model;

import lombok.Data;

@Data
public class Account {
    private final String accountNumber;
    private int balance;

    public Account(String accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
