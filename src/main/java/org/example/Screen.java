package org.example;

public class Screen {

    // display message
    public void displayMessage(String message) {
        System.out.print(message);
    }

    public void displayMessageLine(String message) {
        System.out.println(message);
    }

    // display dollar amount
    public void displayDollarAmount(double amount) {
        System.out.printf("$%,.2f", amount);
    }

}
