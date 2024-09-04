package org.example;

import java.util.Scanner;

public class Keypad {

    private Scanner input;

    public Keypad() {
        input = new Scanner(System.in);
    }

    // return an integer value entered by user
    public int getInput() {
        return input.nextInt();
    }


}
