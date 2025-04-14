package FinalProject;

import java.util.Arrays;

public class Bank {
    private static Account[] accounts = new Account[2];//Starting length of the array
    private static float rateDifference = 0.1f;//a default number for rate difference;

    public Bank() {
    }


    public static Account[] getAccounts() {
        return accounts;
    }

    public static void setAccounts(Account[] account) {
        accounts = account;
    }

    public static float getRateDifference() {
        return rateDifference;
    }

    public void setRateDifference(float rateDifference) {
        Bank.rateDifference = rateDifference;
    }

    public static Boolean checkVIP(BusinessCheckingAccount account) {//section 26 + press 10
        return account.getRevenueBusiness() >= 10000000 && account.getClientsRateAvg() == 10;
    }

    public static float checkProfitVIP(BusinessCheckingAccount account) {//section 26 + press 10
        BusinessCheckingAccount newAccount = new BusinessCheckingAccount(account);
        Client [] newClients=new Client[account.getClients().length];
        for (int i=0;i< newClients.length;i++){
            newClients[i]=new Client(newAccount.getClients()[i]);
        }
        for (Client c : newClients) {
            c.setClientRank(0);
        }
        newAccount.setClients(newClients);
        return newAccount.profit(rateDifference);
    }

    public static Account findAccountByNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account != null && account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public static int getSavingsAccountsCount() {
        int count = 0;
        for (Account account : accounts) {
            if (account instanceof SavingsAccount) {
                count++;
            }
        }
        return count;
    }

    public static Account[] organizeAccountsByAccountNumber(Account[] existingAccounts) {//press 4/6
        Account[] newAccountsArray = Arrays.copyOf(existingAccounts, existingAccounts.length);
        Account[] organizedArray = new Account[existingAccounts.length];
        int counter = 0, place = 0, minAccountNumber = 1000000000;//all account number has only 8 digits so 9 digits will be bigger
        float rateDifference = getRateDifference();
        while (counter != newAccountsArray.length) {//insert each account in the new array organized by profit from the highest to the lowest
            for (int i = 0; i < newAccountsArray.length; i++) {
                if (newAccountsArray[i] != null) {
                    if (Integer.parseInt(newAccountsArray[i].getAccountNumber()) < minAccountNumber) {
                        assert newAccountsArray[0] != null;
                        minAccountNumber = Integer.parseInt(newAccountsArray[i].getAccountNumber());
                        place = i;
                    }
                }
            }
            organizedArray[counter] = newAccountsArray[place];//insert the current highest profit account
            counter++;
            minAccountNumber = 1000000000;//all account number has only 8 digits so 9 digits will be bigger
            newAccountsArray[place] = null;//delete the current highest account from the array
        }
        return organizedArray;
    }

    public static Account[] organizeProfitAccounts(Account[] profitAccounts) {//press 5
        Account[] newProfitAccounts = Arrays.copyOf(profitAccounts, profitAccounts.length);
        Account[] organizedArray = new Account[profitAccounts.length];
        float max = 0;
        int counter = 0, place = 0;
        float rateDifference = getRateDifference();
        while (counter != newProfitAccounts.length) {//insert each account in the new array organized by profit from the highest to the lowest
            for (int i = 0; i < newProfitAccounts.length; i++) {
                if (newProfitAccounts[i] instanceof MortgageAccount) {
                    if (((MortgageAccount) newProfitAccounts[i]).profit(rateDifference) > max) {
                        max = ((MortgageAccount) newProfitAccounts[i]).profit(rateDifference);
                        place = i;
                    }
                } else if (newProfitAccounts[i] instanceof RegularCheckingAccount) {
                    if (((RegularCheckingAccount) newProfitAccounts[i]).profit(rateDifference) > max) {
                        max = ((RegularCheckingAccount) newProfitAccounts[i]).profit(rateDifference);
                        place = i;
                    }
                } else if (newProfitAccounts[i] instanceof BusinessCheckingAccount) {
                    if (((BusinessCheckingAccount) newProfitAccounts[i]).profit(rateDifference) > max) {
                        max = ((BusinessCheckingAccount) newProfitAccounts[i]).profit(rateDifference);
                        place = i;
                    }
                }
            }
            organizedArray[counter] = newProfitAccounts[place];//insert the current highest profit account
            counter++;
            max = 0;
            newProfitAccounts[place] = null;//delete the highest account from the array
        }
        return organizedArray;
    }

    public static double ProfitFromAccountNumber(String accountNumber) {// press 7
        for (Account account : accounts) {
            if (account != null) {
                if (account.getAccountNumber().equals(accountNumber)) {

                    return switch (account) {
                        case BusinessCheckingAccount businessCheckingAccount ->
                                businessCheckingAccount.profit(rateDifference);
                        case RegularCheckingAccount regularCheckingAccount ->
                                regularCheckingAccount.profit(rateDifference);
                        case MortgageAccount mortgageAccount -> mortgageAccount.profit(rateDifference);
                        default -> //(account instanceof SavingsAccount)
                                0;
                    };
                }
            }
        }
        return 0;
    }

}
