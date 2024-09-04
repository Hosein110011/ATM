package org.example;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class BankDatabase {

    private Account[] accounts;

    public BankDatabase() {
        // here we have two accounts for testing...
        accounts = new Account[2];
        accounts[0] = new Account(14234, 67566, 2000.0, 4444);
        accounts[1] = new Account(97844, 15376, 6000.0, 1111);
    }

    private Account getAccount(int accountNumber) {
        AtomicReference<Account> wantedAccount = new AtomicReference<>(null);
        Arrays.stream(accounts).forEach(a -> { if (a.getAccountNumber() == accountNumber) wantedAccount.set(a);});
        return wantedAccount.get();
    }

    public boolean authenticateUser(int userAccountNumber, int userPIN) {
        Account userAccount = getAccount(userAccountNumber);
        if (userAccount != null) {
            return userAccount.validatePIN(userPIN);
        }
        else {
            return false;
        }
    }

    public double getAvailableBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getAvailableBalance();
    }

    public double getTotalBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getTotalBalance();
    }

    public void credit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).credit(amount);
    }

    public void debit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).debit(amount);
    }
}
