package org.example;

public class Account {

    // account number
    private int accountNumber;

    // PIN for authentication
    private int pin;

    // available + pending deposit
    private double availableBalance;

    // Account constructor initializes attribute
    private double totalBalance;

    public Account(int accountNumber, double totalBalance, double availableBalance, int pin) {
        this.accountNumber = accountNumber;
        this.totalBalance = totalBalance;
        this.availableBalance = availableBalance;
        this.pin = pin;
    }

    // return true if pin is matched with userPIN
    public boolean validatePIN(int userPIN) {
        if (userPIN == pin) {
            return true;
        }
        else {
            return false;
        }
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    // credits an amount to the account
    public void credit(double amount) {
        totalBalance += amount;
    }

    public void debit(double amount) {
        availableBalance -= amount;
        totalBalance -= amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}


