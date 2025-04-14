package FinalProject;

import java.util.Date;

public class BusinessCheckingAccount extends CheckingAccount implements Cloneable {
    private double businessRevenue;

    public BusinessCheckingAccount(String accountNumber, Date openAccountDate, int bankNumber, long balance, String managerName, Client[] clients, String type, long credit, double revenueBusiness) {
        super(accountNumber, openAccountDate, bankNumber, balance, managerName, clients, type, credit);
        this.businessRevenue = revenueBusiness;
    }

    public BusinessCheckingAccount(BusinessCheckingAccount other) {
        super(other.getAccountNumber(), other.getOpenAccountDate(), other.getBankNumber(), other.getBalance(), other.getManagerName(), other.getClients(), other.getType(), other.getCredit());
        this.businessRevenue = other.businessRevenue;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public double getRevenueBusiness() {
        return businessRevenue;
    }

    public void setRevenueBusiness(float revenueBusiness) {
        this.businessRevenue = revenueBusiness;
    }

    @Override
    public String toString() {
        return "Credit= " + getCredit() + " BusinessRevenue= " + businessRevenue;
    }

    public float profit(float rateDifference) {
        if (businessRevenue >= 10000000 && getClientsRateAvg() == 10) {
            return 0;
        } else {
            return getCredit() * rateDifference + 3000;//commission
        }
    }
}
