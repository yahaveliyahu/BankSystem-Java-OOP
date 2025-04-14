package FinalProject;

public class Client {
    private String clientName;
    private int clientRank;

    public Client(String clientName, int clientRank) {
        this.clientName = clientName;
        this.clientRank = clientRank;
    }

    public Client(Client client) {
        this.clientName = client.clientName;
        this.clientRank = client.clientRank;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getClientRank() {
        return clientRank;
    }

    public void setClientRank(int clientRank) {
        this.clientRank = clientRank;
    }

    @Override
    public String toString() {
        return "clientName='" + clientName + '\'' +
                ", clientRank=" + clientRank ;
    }
}

