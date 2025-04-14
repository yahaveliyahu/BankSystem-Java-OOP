package FinalProject;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Scanner s = new Scanner(System.in);
    //private static final Bank bank = new Bank();
    private final static String[] MENU = {

            " Auto-fill accounts with customers",
            " Add a new account",
            " Add a customer to an existing account",
            " Display all accounts",
            " Display profit accounts only",
            " Display accounts by type",
            " Show annual profit for a specific account",
            " Show total annual profit for the bank",
            " Show the highest profit Checking Account",
            " Check profit VIP status for a business account",
            " Print management fee",
            " Exit Program"
    };

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        String choice;
        do {

            choice = showMenu();

            switch (choice) {
                case "1":
                    AccountsFactory.autoFillAccounts();
                    System.out.println("Accounts auto-filled with customers.");

                    break;
                case "2":
                    AddNewAccount();
                    break;
                case "3":
                    AddClient();
                    break;
                case "4":
                    displayAllAccountsByAccountNumber();
                    break;
                case "5":
                    displayProfitAccounts();
                    break;
                case "6":
                    displayAccountsByType();
                    break;
                case "7":
                    showAnnualProfitByAccountNumber();
                    break;
                case "8":
                    showAnnualProfitFromAllAccounts();
                    break;
                case "9":
                    findMostProfitableCheckingAccount();
                    break;
                case "10":
                    checkProfitVIP();
                    break;
                case "11":
                    printManagementFees();
                    break;
                case "e":
                    System.out.println("Exiting Program, GOODBYE!...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
                    break;

            }
        }
        while (!choice.equals("e"));
    }

    private static String showMenu() {
        System.out.println("\n--- Bank Management Menu ---");
        for (int i = 0; i < MENU.length; i++) {
            if (i == MENU.length - 1) {
                System.out.println("e/E) " + MENU[i]);
            } else {
                System.out.println(i + 1 + ") " + MENU[i]);
            }
        }
        System.out.println("Enter your chose:");
        return s.next().toLowerCase();
        //if you choose 'E' it will change it to 'e'
    }

    public static void AddNewAccount() {//press 2
        String accountNumber = "1", managerName, choice, clientName, type;
        Date openAccountDate = new Date();
        int bankNumber, numOfClients, clientRank, depositPayment, yearsOfSavings, yearsOfMortgage, monthlyPayment;
        double businessRevenue;
        long credit, balance, originalMortgageAmount;
        Account[] accounts = Bank.getAccounts();
        System.out.println("Do you want to choose an account number or get an automatic one?(Enter Manual/Auto)");
        choice = s.next();
        while (!choice.equalsIgnoreCase("Manual") && !choice.equalsIgnoreCase("Auto")) {
            System.out.println("Invalid input. Please enter a valid type (Manual/Auto): ");
            choice = s.next();
        }
        if (choice.equalsIgnoreCase("Auto")) {
            if (accounts == null) {
                accountNumber = AccountsFactory.generateRandomAccountNumber(8);//if there is no existing accounts set a random account number
            } else {
                for (int i = accounts.length - 1; i > 0; i--) {
                    if ((accounts[i] != null)) {
                        accountNumber = accounts[i].getAccountNumber();//get the last existing account number
                        break;
                    }
                }
                accountNumber = AccountsFactory.newAutoAccountNumber(accountNumber);
            }
        } else {//the user chose to insert an account number manually
            while (true) {
                try {
                    System.out.println("Enter an account number: ");
                    accountNumber = s.next();
                    //if (accounts != null) {
                    for (Account account : accounts) {
                        if (account != null) {
                            if (account.getAccountNumber().equals(accountNumber)) {
                                throw new DuplicationException(accountNumber);
                            }
                        }
                    }
                    break; // Exit the while loop if there is no exceptions
                    //}
                } catch (DuplicationException e) {
                    System.out.println(e.getMessage());
                }

            }
        }
        while (true) {
            try {
                System.out.println("Enter a Bank Number: ");
                bankNumber = s.nextInt();
                break;//if the number is valid, proceed to the next thing
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                s.next();
            }
        }

        while (true) {
            try {
                System.out.println("Enter the Balance of the account: ");
                balance = s.nextLong();
                break;//if the number is valid, proceed to the next thing
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                s.next();
            }
        }

        System.out.println("Enter the name of the Manager who approved the opening of the account: : ");
        managerName = s.next();
        while (true) {
            try {
                System.out.println("How many people are on the account?: ");
                numOfClients = s.nextInt();
                if (numOfClients < 1) {
                    throw new IllegalArgumentException("It is not possible for the number of clients to be less than 1.");
                } else {
                    break;//if the number is valid, proceed to the next thing
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number of weight (over 0).");
                s.next();
            }
        }
        Client[] clients1 = new Client[numOfClients];
        for (int i = 0; i < numOfClients; i++) {

            System.out.println("Enter the client Name: ");
            clientName = s.next();
            while (true) {
                try {
                    System.out.println("Enter the client Rank: ");
                    clientRank = s.nextInt();
                    if (!(clientRank >= 0) || !(clientRank <= 10)) {
                        throw new IllegalArgumentException("the rank must be between 0 to 10.");
                    } else {
                        break;//if the number is valid, proceed to the next thing
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number of weight (over 0).");
                    s.next();
                }
            }

            Client c = new Client(clientName, clientRank);
            clients1[i] = c;
        }
        System.out.println("Which account do you want to open?(SavingsAccount/CheckingAccount/MortgageAccount)");
        type = s.next();
        while (!type.equalsIgnoreCase("CheckingAccount") && !type.equalsIgnoreCase("SavingsAccount") &&
                !type.equalsIgnoreCase("MortgageAccount")) {
            System.out.println("Invalid input. Please enter a valid account type (SavingsAccount/CheckingAccount/MortgageAccount): ");
            type = s.next();
        }
        if (type.equalsIgnoreCase("CheckingAccount")) {

            while (true) {
                try {
                    System.out.println("Enter the Credit amount: ");
                    credit = s.nextLong();
                    break;//if the number is valid, proceed to the next thing
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number of weight (over 0).");
                    s.next();
                }
            }
            System.out.println("Which type of CheckingAccount?(Regular/business)");
            type = s.next();
            while (!type.equalsIgnoreCase("Regular") && !type.equalsIgnoreCase("business")) {
                System.out.println("Invalid input. Please enter a valid account type (Regular/business): ");
                type = s.next();
            }
            if (type.equalsIgnoreCase("business")) {
                System.out.println("Enter the business Revenue: ");
                businessRevenue = s.nextDouble();
                BusinessCheckingAccount business = new BusinessCheckingAccount(accountNumber, openAccountDate, bankNumber, balance, managerName, clients1, "BusinessCheckingAccount", credit, businessRevenue);
                AccountsFactory.addAccount(business);
            } else {
                RegularCheckingAccount regular = new RegularCheckingAccount(accountNumber, openAccountDate, bankNumber, balance, managerName, clients1, "RegularCheckingAccount", credit);
                AccountsFactory.addAccount(regular);
            }
        } else if (type.equalsIgnoreCase("SavingsAccount")) {
            while (true) {
                try {
                    System.out.println("Enter the amount of money you want to deposit : ");
                    depositPayment = s.nextInt();
                    if (depositPayment < 0) {
                        throw new IllegalArgumentException("It is not possible for the amount to be less than 0.");
                    } else if (depositPayment > balance) {
                        throw new IllegalArgumentException("The deposit amount can not be bigger than the balance");
                    } else {
                        break;//if the number is valid, proceed to the next thing
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    s.next();
                }
            }
            while (true) {
                try {
                    System.out.println("Enter number of years you want to deposit the money: ");
                    yearsOfSavings = s.nextInt();
                    if (yearsOfSavings < 0) {
                        throw new IllegalArgumentException("It is not possible for the amount to be less than 0.");
                    } else {
                        break;//if the number is valid, proceed to the next thing
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    s.next();
                }
            }
            SavingsAccount savingsAccount = new SavingsAccount(accountNumber, openAccountDate, bankNumber, balance, managerName, clients1, "SavingsAccount", depositPayment, yearsOfSavings);
            AccountsFactory.addAccount(savingsAccount);
        } else {//MortgageAccount
            while (true) {
                try {
                    System.out.println("Enter the original Mortgage Amount : ");
                    originalMortgageAmount = s.nextLong();
                    if (originalMortgageAmount < 0) {
                        throw new IllegalArgumentException("It is not possible for the amount to be less than 0.");
                    } else {
                        break;//if the number is valid, proceed to the next thing
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    s.next();
                }
            }
            while (true) {
                try {
                    System.out.println("Enter number of years you want Mortgage to be: ");
                    yearsOfMortgage = s.nextInt();
                    if (yearsOfMortgage < 0) {
                        throw new IllegalArgumentException("It is not possible for the amount to be less than 0.");
                    } else {
                        break;//if the number is valid, proceed to the next thing
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    s.next();
                }
            }
            while (true) {
                try {
                    System.out.println("Enter the Monthly Payments: ");
                    monthlyPayment = s.nextInt();
                    if (monthlyPayment < 0) {
                        throw new IllegalArgumentException("It is not possible for the amount to be less than 0.");
                    } else {
                        break;//if the number is valid, proceed to the next thing
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    s.next();
                }
            }
            MortgageAccount mortgageAccount = new MortgageAccount(accountNumber, openAccountDate, bankNumber, balance, managerName, clients1, "MortgageAccount", originalMortgageAmount, yearsOfMortgage, monthlyPayment);
            AccountsFactory.addAccount(mortgageAccount);
        }
    }

    private static void AddClient() {//press 3
        String accountNumber, clientName;
        int clientRank = 0;
        boolean existingAccount = false, existingClient = false;
        while (true) {
            try {
                System.out.println("Enter an account number: ");
                accountNumber = s.next();
                //if (accounts != null) {
                for (Account account : Bank.getAccounts()) {
                    if (account != null) {
                        if (account.getAccountNumber().equals(accountNumber)) {
                            existingAccount = true;
                            break;
                        }
                    }
                }
                if (!existingAccount) {
                    throw new IllegalArgumentException("Account not found.");
                }
                break; // Exit the while loop if there is no exceptions
                //}
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
        Account account = Bank.findAccountByNumber(accountNumber);

        System.out.println("Enter client name: ");
        clientName = s.next();

        while (true) {
            try {
                System.out.println("Enter the client Rank: ");
                clientRank = s.nextInt();
                if (!(clientRank >= 0) || !(clientRank <= 10)) {
                    throw new IllegalArgumentException("the rank must be between 0 to 10.");
                } else {
                    break;//if the number is valid, proceed to the next thing
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number of weight (over 0).");
                s.next();
            }
        }
        assert account != null;
        if (account.getClients() != null) {
            for (Client client : account.getClients()) {
                if ((client != null) && (client.getClientName().equals(clientName)) && (client.getClientRank() == clientRank)) {
                    System.out.println("Client already exists in this account.");
                    existingClient = true;
                    break;
                }
            }
        }
        if (!existingClient) {//if the client isn't already linked to the account, add him to the account
            Client newClient = new Client(clientName, clientRank);
            account.addClient(newClient);
            System.out.println("Client added to account " + accountNumber);
            System.out.println("Client added successfully.");
        }

    }

    public static void displayAllAccountsByAccountNumber() {//press 4

        Account[] existingAccounts = Arrays.stream(Bank.getAccounts())// יצירת מערך זמני שכולל רק חשבונות שאינם null
                .filter(Objects::nonNull)
                .toArray(Account[]::new);
        if (existingAccounts.length != 0) {

            //Arrays.sort(existingAccounts, Comparator.comparing(Account::getType).thenComparing(Account::getAccountNumber));
            existingAccounts = Bank.organizeAccountsByAccountNumber(existingAccounts);
            System.out.println("Displaying all accounts by account number:");
            for (Account account : existingAccounts) {
                if (account != null) {
                    //System.out.println(account.toString());
                    System.out.println("Type: " + account.getType());
                    System.out.println("Details: Account Number: " + account.getAccountNumber() +
                            ", Type: " + account.getType() + ", Balance: " + account.getBalance());
                    for (Client client : account.getClients()) {
                        if (client != null) {
                            System.out.println(client.toString());
                        }
                    }
                    System.out.println(account.toString());
                    System.out.println("--------------------");
                }
            }
        } else {
            System.out.println("There is no existing accounts");
        }
    }

    private static void displayProfitAccounts() {//press 5
        Account[] profitAccounts = new Account[Bank.getAccounts().length - Bank.getSavingsAccountsCount()];
        int count = 0;
        for (Account account : Bank.getAccounts()) {//insert to a new array that has no SavingsAccounts
            if (account != null && !Objects.equals(account.getType(), "SavingsAccount")) {
                profitAccounts[count] = account;
                count++;
            }
        }
        profitAccounts = Bank.organizeProfitAccounts(profitAccounts);
        for (Account account : profitAccounts) {
            if (account != null) {
                System.out.println("Type: " + account.getType());
                System.out.println("Details: Account Number: " + account.getAccountNumber() +
                        ", Type: " + account.getType() + ", Balance: " + account.getBalance());
                for (Client client : account.getClients()) {
                    if (client != null) {
                        System.out.println(client.toString());
                    }
                }
                System.out.println(account.toString());
                System.out.println("--------------------");
            }
        }


    }

    private static void displayAccountsByType() {//press 6
        String type;
        int count = 0;
        Account[] accounts = Arrays.copyOf(Bank.getAccounts(), Bank.getAccounts().length);
        Account[] newAccount = new Account[accounts.length];
        System.out.println("Which account type do you want to see?(SavingsAccount/MortgageAccount/CheckingAccount/RegularCheckingAccount/BusinessCheckingAccount)");
        type = s.next();
        while (!type.equalsIgnoreCase("CheckingAccount") &&
                !type.equalsIgnoreCase("SavingsAccount") &&
                !type.equalsIgnoreCase("MortgageAccount") &&
                !type.equalsIgnoreCase("RegularCheckingAccount") &&
                !type.equalsIgnoreCase("BusinessCheckingAccount")) {
            System.out.println("Invalid input. Please enter a valid account type (SavingsAccount/CheckingAccount/MortgageAccount): ");
            type = s.next();
        }
        if (type.equalsIgnoreCase("MortgageAccount")) {
            for (Account account : accounts) {
                if ((account instanceof MortgageAccount)) {
                    newAccount[count] = account;
                    count++;
                }
            }
        } else if (type.equalsIgnoreCase("SavingsAccount")) {
            for (Account account : accounts) {
                if ((account instanceof SavingsAccount)) {
                    newAccount[count] = account;
                    count++;
                }
            }
        } else if (type.equalsIgnoreCase("CheckingAccount")) {
            for (Account account : accounts) {
                if ((account instanceof CheckingAccount)) {
                    newAccount[count] = account;
                    count++;
                }
            }
        } else if (type.equalsIgnoreCase("RegularCheckingAccount")) {
            for (Account account : accounts) {
                if ((account instanceof RegularCheckingAccount)) {
                    newAccount[count] = account;
                    count++;
                }
            }
        } else { // (type.equalsIgnoreCase("BusinessCheckingAccount"))
            for (Account account : accounts) {
                if ((account instanceof BusinessCheckingAccount)) {
                    newAccount[count] = account;
                    count++;
                }
            }
        }
        newAccount = Arrays.stream(newAccount)// מערך שכולל רק חשבונות שאינם null
                .filter(Objects::nonNull)
                .toArray(Account[]::new);
        newAccount = Bank.organizeAccountsByAccountNumber(newAccount);
        System.out.println("Displaying accounts by " + type + " type:");
        for (Account account : newAccount) {
            if (account != null) {
                System.out.println("Type: " + account.getType());
                System.out.println("Details: Account Number: " + account.getAccountNumber() +
                        ", Type: " + account.getType() + ", Balance: " + account.getBalance());
                for (Client client : account.getClients()) {
                    if (client != null) {
                        System.out.println(client.toString());
                    }
                }
                System.out.println(account.toString());
                System.out.println("--------------------");
            }
        }
    }

    private static void showAnnualProfitByAccountNumber() {// press 7
        String accountNumber;
        Account account;
        System.out.println("Enter an existing account number that you want to see the annual profit that the Bank gets from this account");
        accountNumber = s.next();
        while (true) {
            if (Bank.findAccountByNumber(accountNumber) == null) {
                System.out.println("We couldn't found the requested AccountNumber, please enter an existing account number that you want to see the annual profit that the Bank gets from this account");
                accountNumber = s.next();
            } else {
                account = Bank.findAccountByNumber(accountNumber);
                break;
            }
        }
        assert account != null;
        System.out.println("Type: " + account.getType() + " Account number: " + accountNumber);
        System.out.println("The profit from this account is: " + Bank.ProfitFromAccountNumber(accountNumber));
    }

    private static void showAnnualProfitFromAllAccounts() { // press 8
        double totalProfit = 0;
        System.out.println("Displaying the annual profit from all accounts in the bank:");

        for (Account account : Bank.getAccounts()) {
            totalProfit += Bank.ProfitFromAccountNumber(account.getAccountNumber());
        }

        System.out.println("The total annual profit from all accounts is: " + totalProfit + " NIS");
    }

    private static void findMostProfitableCheckingAccount() { // press 9
        float maxProfit = 0;
        Account mostProfitableAccount = null;

        for (Account account : Bank.getAccounts()) {
            // Checking if the account is of type CheckingAccount or its subclasses
            if (account instanceof RegularCheckingAccount regularChecking) {
                // Profit calculation for a regular account
                float profit = regularChecking.getBalance() * Bank.getRateDifference();
                if (profit > maxProfit) {
                    maxProfit = profit;
                    mostProfitableAccount = account;
                }
            } else if (account instanceof BusinessCheckingAccount businessChecking) {
                // Profit calculation for a business account
                // Skip VIP customers
                if (Bank.checkVIP(businessChecking)) {
                    continue;
                }
                float profit = (businessChecking.getBalance() * Bank.getRateDifference()) + 3000;
                if (profit > maxProfit) {
                    maxProfit = profit;
                    mostProfitableAccount = account;
                }
            }
        }

        if (mostProfitableAccount != null) {
            System.out.printf("The most profitable checking account is %s with an annual profit of %.2f%nNIS",
                    mostProfitableAccount.getAccountNumber(), maxProfit);
        } else {
            System.out.println("No profitable checking accounts found.");
        }
    }

    private static void checkProfitVIP() {//press 10
        String accountNumber;

        while (true) {
            try {
                System.out.println("Enter an existing account number of BusinessCheckingAccount that you want to see the annual profit that the Bank gets from this account");
                accountNumber = s.next();
                if (Bank.findAccountByNumber(accountNumber) == null) {
                    throw new IllegalArgumentException("We couldn't found the requested AccountNumber");
                }
                if (!(Bank.findAccountByNumber(accountNumber) instanceof BusinessCheckingAccount)) {
                    throw new IllegalArgumentException("This AccountNumber is not a BusinessCheckingAccount");
                } else {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        if (Bank.checkVIP((BusinessCheckingAccount) Objects.requireNonNull(Bank.findAccountByNumber(accountNumber)))) {
            System.out.println("This bank currently profit " + ((BusinessCheckingAccount) Objects.requireNonNull(Bank.findAccountByNumber(accountNumber))).profit(Bank.getRateDifference()) + " from this account");
            System.out.println("it could profit " + Bank.checkProfitVIP((BusinessCheckingAccount) Objects.requireNonNull(Bank.findAccountByNumber(accountNumber))) + " from this account if it weren't a VIP account");
        } else {
            System.out.println("This BusinessCheckingAccount isn't a VIP account");
        }
    }

    public static void printManagementFees() { // press 11 + section 27
        float managementFee = 0;
        System.out.println("Management fees for each account:");

        for (Account account : Bank.getAccounts()) {
            System.out.println("Type: " + account.getType());
            if (account instanceof MortgageAccount mortgage) {
                float fee = mortgage.getOriginalMortgageAmount() * 0.1f;
                managementFee += fee;

                System.out.printf("Account number: %s, Fee: %.2f (10%% of the original mortgage amount)\n",
                        mortgage.getAccountNumber(), fee);
            } else if (account instanceof BusinessCheckingAccount business) {
                float fee = 1000;
                managementFee += fee;
                System.out.printf("Account: %s, Fee: %.2f (Flat rate for business accounts)\n",
                        business.getAccountNumber(), fee);
            } else {
                System.out.printf("Account: %s, No management fees applied\n",
                        account.getAccountNumber());
            }
            System.out.println("--------------------");
        }

        System.out.printf("Total management fees collected: %.2f NIS", managementFee);
    }

}