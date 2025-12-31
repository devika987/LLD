package com.example.atm.state;

import com.example.atm.enums.TransactionType;
import com.example.atm.model.Card;
import com.example.atm.service.ATMSystem;

import java.sql.SQLOutput;

public class PinEnteredState extends ATMState{
    @Override
    public void insertCard(Card card) {
        System.out.println("Card is already inserted.");

    }

    @Override
    public void ejectCard(Card card) {
        System.out.println("Ejecting card...");
        ATMSystem.getInstance().setCurrentCard(null);
        ATMSystem.getInstance().setAtmState(new IdleState());

    }

    @Override
    public void enterPin(Card card, String pin) {
        System.out.println("PIN is already entered.");

    }

    @Override
    public void selectTransactionType(Card card, String transactionType, int amount) {
        switch (transactionType.toLowerCase()) {
            case "withdraw" -> {
                if(ATMSystem.getInstance().performOperation( TransactionType.WITHDRAWAL, card.getAccountNumber(), amount)) {
                    System.out.println("Please collect your cash: " + amount);
                } else {
                    System.out.println("Insufficient balance or ATM funds.");
                }
            }
            case "deposit" -> {
                if(ATMSystem.getInstance().performOperation(TransactionType.DEPOSIT, card.getAccountNumber(), amount)) {
                    System.out.println("Amount deposited: " + amount);
                } else {
                    System.out.println("Deposit failed.");
                }
            }
            case "balance" -> {
                int balance = ATMSystem.getInstance().getBalance(card.getAccountNumber());
                System.out.println("Current balance: " + balance);
            }
            default -> System.out.println("Invalid transaction type.");
        }

    }
}
