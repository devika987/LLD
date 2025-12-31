package com.example.atm.state;

import com.example.atm.service.ATMSystem;

public class IdleState extends ATMState {

    @Override
    public void insertCard(com.example.atm.model.Card card) {
        System.out.println("Card inserted. Please enter your PIN.");
        // Transition to CardInsertedState
        ATMSystem.getInstance().setAtmState(new CardInsertedState());
        ATMSystem.getInstance().setCurrentCard(card);
    }

    @Override
    public void ejectCard(com.example.atm.model.Card card) {
        System.out.println("No card to eject.");
    }

    @Override
    public void enterPin(com.example.atm.model.Card card, String pin) {
        System.out.println("No card inserted. Please insert your card first.");
    }

    @Override
    public void selectTransactionType(com.example.atm.model.Card card, String transactionType, int amount) {
        System.out.println("No card inserted. Please insert your card first.");
    }

}
