package FinalProject;

import java.util.Arrays;
import java.util.Date;

public class Account {
    private final String accountNumber;
    private final Date openAccountDate;
    private int bankNumber;
    private long balance;
    private String managerName;
    private Client[] clients;
    private String type;

    public Account(String accountNumber, Date openAccountDate, int bankNumber, long balance, String managerName, Client[] clients, String type) {
        this.accountNumber = accountNumber;
        this.openAccountDate = openAccountDate;
        this.bankNumber = bankNumber;
        this.balance = balance;
        this.managerName = managerName;
        this.clients = clients;
        this.type = type;
    }

    public Account(Account other) {
        this.accountNumber =other.accountNumber;
        this.openAccountDate =other.openAccountDate;
        this.bankNumber = other.bankNumber;
        this.balance =other.balance;
        this.managerName =other.managerName;
        this.clients =other.clients;
        this.type = other.type;
    }

    @Override
    public String toString() {
        return "accountNumber='" + accountNumber + '\'' +
                ", openAccountDate=" + openAccountDate +
                ", bankNumber=" + bankNumber +
                ", balance=" + balance +
                ", managerName='" + managerName + '\'' +
                ", clients=" + Arrays.toString(clients) +
                ", type='" + type + '\'';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(int bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Date getOpenAccountDate() {
        return openAccountDate;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    public float getClientsRateAvg() {
        int count = 0;
        float sum = 0;
        for (Client c : clients) {
            if (c == null) {
                continue;
            }
            count++;
            sum += c.getClientRank();
        }
        return sum / count;
    }

    public void addClient(Client newClient) {


        // Checking if the array is full - if so, it should be expanded
        for (int i = 0; i < clients.length; i++) {
            if (clients[i] == null) {
                clients[i] = newClient;
                break;
            }
        }
        // If there is no room in the array, we will increase the array and add the new client to the last available place
        if (clients[clients.length - 1] != null) {
            clients = Arrays.copyOf(clients, clients.length + 2);//adds 2 new places in the array
        }
        if ((clients[clients.length - 2] == null)) {
            clients[clients.length - 2] = newClient;
        } else {
            clients[clients.length - 1] = newClient;
        }
        setClients(clients);
    }

}
