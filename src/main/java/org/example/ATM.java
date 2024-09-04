package org.example;

public class ATM {

    private boolean userAuthenticated;
    private int currentAccountNumber;
    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private BankDatabase bankDatabase;

    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;

    public ATM(){
        userAuthenticated = false;
        currentAccountNumber = 0;
        screen = new Screen();
        keypad = new Keypad();
        cashDispenser = new CashDispenser();
        depositSlot = new DepositSlot();
        bankDatabase = new BankDatabase();

        run();
    }

    public void run() {
        boolean inProcess = true;
        while(inProcess) {
            while(!userAuthenticated) {
                screen.displayMessageLine("\nWelcome");
                authenticateUser();

                performTransactions();

                currentAccountNumber = 0;
                screen.displayMessageLine("\nThank You!\nGoodbye");
                userAuthenticated = true;
                inProcess = false;
            }
        }
    }

    private void authenticateUser() {
        screen.displayMessage("\nPlease enter your account number : ");
        int accountNumber = keypad.getInput();
        screen.displayMessage("\nEnter your PIN : ");
        int pin = keypad.getInput();

        userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);

        if (userAuthenticated) {
            currentAccountNumber = accountNumber;
        }
        else{
            screen.displayMessageLine("Invalid Account Number or PIN.");
            screen.displayMessageLine("Please Try again.");
        }
    }

    private void performTransactions() {

        Transaction currentTransaction = null;
        boolean userExited = false;

        while(!userExited){
            int mainMenuSelection = displayMainMenu();
            var executor = createTransaction(mainMenuSelection);
            try {
                if (executor != null) {
                    executor.execute();
                } else {
                    break;
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

    }

    private int displayMainMenu() {
        screen.displayMessageLine("\nMain Menu :");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Exit\n");
        screen.displayMessage("Enter a choice : ");
        return keypad.getInput();
    }

    private Transaction createTransaction(int type){
        Transaction temp = null;
        boolean tryFlag = true;
        while (tryFlag) {
            switch (type) {
                case BALANCE_INQUIRY:
                    temp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
                    tryFlag = false;
                    break;
                case WITHDRAWAL:
                    temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
                    tryFlag = false;
                    break;
                case DEPOSIT:
                    temp = new Deposit(currentAccountNumber, screen, bankDatabase, depositSlot, keypad);
                    tryFlag = false;
                    break;
                case EXIT:
                    tryFlag = false;
                    break;
                default:
                    System.out.println("Please enter a valid number!");
                    type = keypad.getInput();
            }
        }
        return temp;
    }
}
