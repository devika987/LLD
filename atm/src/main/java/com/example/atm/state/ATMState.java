package com.example.atm.state;

import com.example.atm.model.Card;

public abstract class ATMState {

    public abstract void insertCard(Card card);
    public abstract void ejectCard(Card card);
    public abstract void enterPin(Card card, String pin);
    public abstract void selectTransactionType(Card card, String transactionType, int amount);

}
