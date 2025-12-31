package com.example.atm;

import com.example.atm.model.Card;
import com.example.atm.service.ATMSystem;
import com.example.atm.state.IdleState;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtmApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmApplication.class, args);

		ATMSystem atmSystem = ATMSystem.getInstance();

		Card card1 = atmSystem.getBankingService().getCardByCardNumber("123456");

		atmSystem.setCurrentCard(card1);
		atmSystem.getAtmState().insertCard(card1);
		atmSystem.getAtmState().enterPin(card1, "1234");
		atmSystem.getAtmState().selectTransactionType(card1, "withdraw", 233);
		atmSystem.getAtmState().ejectCard(card1);



		Card card2 = atmSystem.getBankingService().getCardByCardNumber("654321");
		atmSystem.setAtmState(new IdleState());

		atmSystem.setCurrentCard(card2);
		atmSystem.getAtmState().insertCard(card2);
		atmSystem.getAtmState().enterPin(card1, "4321");
		atmSystem.getAtmState().selectTransactionType(card1, "withdraw", 200);
		atmSystem.getAtmState().ejectCard(card2);

	}

}
