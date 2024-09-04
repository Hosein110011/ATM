package org.example;

public class Deposit extends Transaction {

    private double amount;
    private Keypad keypad;
    private DepositSlot depositSlot;
    private final static int CANCELED = 0;

    public Deposit(int accountNumber, Screen screen, BankDatabase bankDatabase, DepositSlot depositSlot, Keypad keypad) {
        super(accountNumber, screen, bankDatabase);
        this.depositSlot = depositSlot;
        this.keypad = keypad;
    }

    @Override
    public void execute() {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        // get deposit amount from user
        amount = promptForDepositAmount();
        if (amount != CANCELED) {
            screen.displayMessage("\nPlease insert a deposit envelope containing ");
            screen.displayDollarAmount(amount);
            screen.displayMessage(".");

            // receive deposit envelope
            boolean envelopeReceived = depositSlot.isEnvelopReceived();

            if (envelopeReceived) {
                screen.displayMessageLine("\nYour envelope has been received.");
                screen.displayMessage("NOTE: The money just deposited will not be available until we verify the amount");
                screen.displayMessage("of any enclosed cash and your checks clear.");

                bankDatabase.credit(getAccountNumber(), amount);
            } else {
                screen.displayMessageLine("\nYou did not insert an envelope");
                screen.displayMessageLine("So, the ATM has canceled your transaction.");
            }
        } else {
            screen.displayMessageLine("\ncanceling transaction...");
        }
    }

    private double promptForDepositAmount() {
        Screen screen = getScreen();
        screen.displayMessage("\nPlease enter a deposit amount in CENTS (or 0 to cancel): ");
        int input = keypad.getInput();
        if (input == CANCELED) return CANCELED;
        else {
            return (double) input / 100; // exchange to dollar
        }
    }
}
