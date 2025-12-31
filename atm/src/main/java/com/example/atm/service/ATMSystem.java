package com.example.atm.service;

import com.example.atm.cor.*;
import com.example.atm.enums.TransactionType;
import com.example.atm.model.Account;
import com.example.atm.model.Card;
import com.example.atm.state.ATMState;
import com.example.atm.state.IdleState;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@AllArgsConstructor
@Data
public class ATMSystem {

    private final BankingService bankingService;
    private CashDispenser cashDispenser;
    private static ATMSystem instance;

    private ATMState atmState;

    private Card currentCard;

    private ATMSystem() {
        CashDispenser cashDispenser2000 = new Dispenser2000(10);
        CashDispenser cashDispenser500 = new Dispenser500(10);
        CashDispenser cashDispenser200 = new Dispenser200(10);
        CashDispenser cashDispenser100 = new Dispenser100(10);

        this.cashDispenser = cashDispenser100;
        cashDispenser100.setNextDispenser(cashDispenser200);
        cashDispenser200.setNextDispenser(cashDispenser500);
        cashDispenser500.setNextDispenser(cashDispenser2000);

        Account account1 = new Account("123456", 300);
        Account account2 = new Account("654321", 500);
        this.bankingService = new BankingService(new HashMap<>(), new HashMap<>(), new HashMap<>(), cashDispenser, new HashMap<>());

        bankingService.createAccount(account1);
        bankingService.createAccount(account2);
        Card card1 = new Card("123456", "1111", "123456", "12/25", "John Doe", "123");
        Card card2 = new Card("654321", "2222", "654321", "11/24", "Jane Smith", "456");

        bankingService.createCard(card1);
        bankingService.createCard(card2);

        bankingService.linkCardToAccount(card1, account1);
        bankingService.linkCardToAccount(card2, account2);

        this.atmState = new IdleState();

    }

    public static ATMSystem getInstance() {
        if (instance == null) {
            instance = new ATMSystem();
        }
        return instance;
    }

    public boolean performOperation(TransactionType transactionType, String accountNumber, int amount) {
        switch (transactionType) {
            case WITHDRAWAL:
                return bankingService.withdraw(accountNumber, amount);
            case DEPOSIT:
                return bankingService.deposit(accountNumber, amount);
            default:
                throw new IllegalArgumentException("Invalid transaction type");
        }
    }

    public int getBalance(String accountNumber) {
        return bankingService.checkBalance(accountNumber);
    }
    public void setAtmState(ATMState atmState) {
        this.atmState = atmState;
    }


    public boolean validatePin(Card card, String pin) {
        return bankingService.validatePin(card.getAccountNumber(), pin);
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }



    public void selectTransactionType(Card card, String transactionType, int amount) {
        atmState.selectTransactionType(card, transactionType, amount);
    }


}
