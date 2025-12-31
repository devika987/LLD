package com.example.atm.cor;

import static java.lang.Math.min;

public abstract class CashDispenser {

    public CashDispenser nextDispenser;

    public int numberOfNotes;

    public int denomination;

    public CashDispenser(int denomination, int numberOfNotes) {
        this.denomination = denomination;
        this.numberOfNotes = numberOfNotes;

    }




    public boolean canDispense(int amount) {
        if(amount % denomination == 0) {
            int numOfNotes = min(amount / denomination, numberOfNotes);
            int remainder = amount - (numOfNotes * denomination);
            if(remainder == 0) {
                return true;
            } else if(nextDispenser != null) {
                return nextDispenser.canDispense(remainder);
            } else {
                return false;
            }

        }
        System.out.println("Cannot dispense amount: " + amount + " with denomination: " + denomination);
        return false;
    }

    public boolean dispense(int amount) {

        if(amount % denomination == 0) {

            int numOfNotes = amount / denomination;
            int remainder = amount % denomination;

            System.out.println("Dispensing " + numOfNotes + " notes of " + denomination);

            if(remainder != 0 && nextDispenser != null) {
                return nextDispenser.dispense(remainder);
            }
            return true;
        }

        System.out.println("Cannot dispense amount: " + amount + " with denomination: " + denomination);
        return false;

    }

    public void setNextDispenser(CashDispenser nextDispenser) {
        this.nextDispenser = nextDispenser;
    }

}
