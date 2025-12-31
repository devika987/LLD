package com.example.atm.service;

import com.example.atm.cor.CashDispenser;
import com.example.atm.enums.TransactionType;
import com.example.atm.model.Account;
import com.example.atm.model.Card;
import com.example.atm.model.Transaction;

import java.util.List;
import java.util.Map;

public class BankingService {

    public final Map<String, Account> accountMap;
    public final Map<String, Card> cardMap;
    public final Map<String, String> cardAccountMap;

    public final CashDispenser cashDispenser;

    public final Map<String, List<Transaction>> accountTransactionsMap;

    public BankingService(Map<String, Account> accountMap, Map<String, Card> cardMap, Map<String, String> cardAccountMap, CashDispenser cashDispenser, Map<String, List<Transaction>> accountTransactionsMap) {
        this.accountMap = accountMap;
        this.cardMap = cardMap;
        this.cardAccountMap = cardAccountMap;
        this.cashDispenser = cashDispenser;
        this.accountTransactionsMap = accountTransactionsMap;
    }



    public boolean withdraw(String accountNumber, int amount) {

        if(accountMap.containsKey(accountNumber)) {
            Account account = accountMap.get(accountNumber);
            if (account.getBalance() >= amount) {
                if (cashDispenser.canDispense(amount)) {
                    cashDispenser.dispense(amount);
                    account.setBalance(account.getBalance() - amount);
                    List<Transaction> transactions = accountTransactionsMap.getOrDefault(accountNumber, new java.util.ArrayList<>());
                    transactions.add(new Transaction(account, TransactionType.WITHDRAWAL, amount, java.time.LocalDateTime.now().toString()));
                    accountTransactionsMap.put(accountNumber, transactions);
                    return true;
                } else {
                    return false;
                }
            } else if (account.getBalance() < amount) {
                System.out.println("Insufficient balance");
            }
        }
        return false;
    }

    public boolean deposit(String accountNumber, int amount) {
        if(accountMap.containsKey(accountNumber)) {
            Account account = accountMap.get(accountNumber);
            account.setBalance(account.getBalance() + amount);
            List<Transaction> transactions = accountTransactionsMap.getOrDefault(accountNumber, new java.util.ArrayList<>());
            transactions.add(new Transaction(account, TransactionType.DEPOSIT, amount, java.time.LocalDateTime.now().toString()));
            accountTransactionsMap.put(accountNumber, transactions);
            return true;
        }
        return false;
    }

    public boolean validatePin(String accountNumber, String pin) {

        return true;
    }

    public int checkBalance(String accountNumber) {
        if(accountMap.containsKey(accountNumber)) {
            Account account = accountMap.get(accountNumber);
            return account.getBalance();
        }
        return 0;
    }


    public Card getCardByCardNumber(String cardNumber) {
        return cardMap.get(cardNumber);
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return accountMap.get(accountNumber);
    }

    public void createAccount(Account account) {
        accountMap.put(account.getAccountNumber(), account);
    }

    public void createCard(Card card) {
        cardMap.put(card.getCardNumber(), card);
        }

        public void linkCardToAccount(Card card, Account account) {
        cardAccountMap.put(card.getCardNumber(), account.getAccountNumber());
        }






}
