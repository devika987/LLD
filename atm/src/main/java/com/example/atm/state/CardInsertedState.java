package com.example.atm.state;

import com.example.atm.model.Card;
import com.example.atm.service.ATMSystem;

public class CardInsertedState extends ATMState {

    @Override
    public void insertCard(Card card) {
        System.out.println("Card is already inserted.");
    }

    @Override
    public void ejectCard(Card card) {
        System.out.println("Ejecting card...");
        ATMSystem.getInstance().setAtmState(new IdleState());
    }

    @Override
    public void enterPin(Card card, String pin) {
        System.out.println("PIN entered: " + pin);
        if(ATMSystem.getInstance().validatePin(card, pin)) {
            System.out.println("PIN is correct. You can now select a transaction.");
            ATMSystem.getInstance().setAtmState(new PinEnteredState());
        } else {
            System.out.println("Incorrect PIN. Ejecting card...");
            ATMSystem.getInstance().setAtmState(new IdleState());
        }
    }

    @Override
    public void selectTransactionType(Card card, String transactionType, int amount) {
        System.out.println("Please enter PIN before selecting transaction type.");
    }
}
