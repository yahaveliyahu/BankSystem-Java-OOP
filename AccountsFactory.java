package FinalProject;

import java.util.Date;
import java.util.Random;

public class AccountsFactory extends Bank {
    public AccountsFactory() {
    }

    // A function to automate the creation of accounts
    public static void autoFillAccounts() {
        Date openDate = new Date(); // General opening date
        int balance = 20; // Example initial balance
        String managerName = "Pini Shlomi"; // Example admin name
        int bankNumber = 12;
        String accountNumber = generateRandomAccountNumber(8); // Generate 8-digit account number

        // Added 4 business accounts
        int credit = 5000; // Example credit
        int businessRevenue = 10500000;//Example businessRevenue
        for (int i = 0; i < 4; i++) {
            addAccount(new BusinessCheckingAccount(accountNumber + i, openDate, bankNumber, balance, managerName, generateClients("Checking"), "BusinessCheckingAccount", credit, businessRevenue));
            businessRevenue -= 500000;//Creates different businessRevenue
        }
        // Added 4 current accounts
        accountNumber = generateRandomAccountNumber(8); // Generate 8-digit account number
        for (int i = 0; i < 4; i++) {
            addAccount(new RegularCheckingAccount(accountNumber + i, openDate, bankNumber, balance, managerName, generateClients("Checking"), "RegularCheckingAccount", credit));
        }

        // Added 4 mortgage type accounts
        accountNumber = generateRandomAccountNumber(8); // Generate 8-digit account number
        int originalMortgageAmount = 200000; // Example mortgage amount
        int yearsOfMortgage = 20; // Examples of mortgage years
        int monthlyPayment = 1500; // Monthly payment example
        for (int i = 0; i < 4; i++) {
            addAccount(new MortgageAccount(accountNumber + i, openDate, bankNumber, balance, managerName, generateClients("Mortgage"), "MortgageAccount", originalMortgageAmount, yearsOfMortgage, monthlyPayment));
            originalMortgageAmount -= 30000; // Creates different mortgage amount

        }

        // Added 4 savings type accounts
        accountNumber = generateRandomAccountNumber(8); // Generate 8-digit account number
        for (int i = 0; i < 4; i++) {
            int depositPayment = 500; // Monthly payment for savings example
            int yearsOfSavings = 5; // Example savings years
            addAccount(new SavingsAccount(accountNumber + i, openDate, bankNumber, balance, managerName, generateClients("Savings"), "SavingsAccount", depositPayment, yearsOfSavings));
        }
        System.out.println("Accounts filled with initial data.");
    }

    // A function that adds an account to the array
    public static void addAccount(Account account) {
        if (getAccounts() != null) {
            if (getAccounts()[getAccounts().length - 1] != null) {
                expandAccountsArray();//adds 2 new places in the array
            }
            Account[] account1 = getAccounts();
            if ((getAccounts()[getAccounts().length - 2] == null)) {
                account1[getAccounts().length - 2] = account;
            } else {
                account1[getAccounts().length - 1] = account;
            }
            setAccounts(account1);
        }
    }

    // Expanding the array if there is not enough space
    private static void expandAccountsArray() {
        Account[] newAccounts = new Account[getAccounts().length + 2];
        System.arraycopy(getAccounts(), 0, newAccounts, 0, getAccounts().length);
        setAccounts(newAccounts);
    }

    // Create two customers per account
    private static Client[] generateClients(String accountType) {
        Client[] clients = new Client[2]; // Each account will start with 2 customers
        clients[0] = new Client(" Client 1", 10);
        clients[1] = new Client(" Client 2", 10);
        return clients;
    }

    public static String newAutoAccountNumber(String str) {
        int strLength = str.length();
        int num = Integer.parseInt(str);//convert the string number to int
        num += 1;
        str = num + "";
        return "0".repeat(strLength - str.length()) + str;
        //if the account number has 0 in the beginning,
        //it will not disappear when we convert the string to int
    }

    public static String generateRandomAccountNumber(int length) {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < length; i++) {
            accountNumber.append(random.nextInt(10)); // Append a random digit (0-9)
        }
        return accountNumber.toString();
    }
}
