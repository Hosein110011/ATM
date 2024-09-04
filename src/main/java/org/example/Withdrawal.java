package org.example;

import java.util.Scanner;

public class Withdrawal extends Transaction {

    private int amount;
    private Keypad keypad;
    private CashDispenser cashDispenser;

    private final static int CANCELED = 6;

    public Withdrawal(int accountNumber, Screen screen, BankDatabase bankDatabase, Keypad keypad, CashDispenser cashDispenser) {
        super(accountNumber, screen, bankDatabase);
        this.keypad = keypad;
        this.cashDispenser = cashDispenser;
    }

    @Override
    public void execute() {
        boolean cashDispensed = false;
        double availableBalance;

        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        do {
            // get the provided amount from user
            amount = displayMenuOfAmounts();

            // if user didn't cancel
            if (amount != CANCELED) {

                availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());

                if (amount <= availableBalance) {
                    if (cashDispenser.isSufficientCashAvailable(amount)){
                        bankDatabase.debit(getAccountNumber(), amount);
                        cashDispenser.dispenseCash(amount);
                        cashDispensed = true;

                        screen.displayMessageLine("\nYour cash has been dispensed. please take your cash now.");
                    }
                    else {
                        // cash dispenser does not have enough cash
                        screen.displayMessageLine("\nInsufficient cash available in the ATM");
                        screen.displayMessageLine("\nPlease choose a smaller amount.");
                    }
                }
            } else {
                screen.displayMessageLine("\ncancelling transactions...");
                return; // -> main menu
            }
        } while(!cashDispensed);

    }

    private int displayMenuOfAmounts() {
        int userChoice = 0;
        Screen screen = getScreen();

        // array of default amounts in menu
        int[] amounts = {0, 20, 40, 60, 100, 200};

        while(userChoice == 0) {
            screen.displayMessageLine("\nWithdrawal Menu : ");
            screen.displayMessageLine("1 - $20");
            screen.displayMessageLine("2 - $40");
            screen.displayMessageLine("3 - $60");
            screen.displayMessageLine("4 - $100");
            screen.displayMessageLine("5 - $200");
            screen.displayMessageLine("6 - Cancel Transaction");

            int input = keypad.getInput();

            switch (input){
                case 1 :
                case 2 :
                case 3 :
                case 4 :
                case 5 :
                    userChoice = amounts[input];
                    break;
                case CANCELED:
                    userChoice = CANCELED;
                    break;
                default:
                    screen.displayMessageLine("\nInvalid selection.");
                    screen.displayMessageLine("Try again.");
            }
        }
        return userChoice;
    }
}
