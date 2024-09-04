package org.example;

public class BalanceInquiry extends Transaction {

    public BalanceInquiry(int accountNumber, Screen screen, BankDatabase bankDatabase) {
        super(accountNumber, screen, bankDatabase);
    }

    @Override
    public void execute() {
        // get references to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        // get the available balance for the account involved
        double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());

        // get total balance of the account
        double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());

        // display the balance information on the screen
        screen.displayMessageLine("\nBalance Information : ");
        screen.displayMessage(" - Available Balance : ");
        screen.displayDollarAmount(availableBalance);
        screen.displayMessage("\n - Total Balance : ");
        screen.displayDollarAmount(totalBalance);
        screen.displayMessageLine("");
    }
}
