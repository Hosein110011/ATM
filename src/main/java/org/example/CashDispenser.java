package org.example;

public class CashDispenser {

    private final static int INITIAL_COUNT = 500;
    private int count;

    public CashDispenser() {
        // set count attribute to default
        count = INITIAL_COUNT;
    }

    public void dispenseCash(int amount) {

        // number of $20 bills required
        int billsRequired = amount / 20;
        count -= billsRequired;
    }

    public boolean isSufficientCashAvailable(int amount) {
        int billsRequired = amount / 20;
        if (count > billsRequired) {
            return true;
        }
        else {
            return false;
        }
    }

}
