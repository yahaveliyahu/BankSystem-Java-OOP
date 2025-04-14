package FinalProject;

import java.util.Date;

public class SavingsAccount extends Account{
    private long depositPayment;
    private int yearsOfSavings;

    public SavingsAccount(String accountNumber, Date openAccountDate,int bankNumber, long balance, String managerName, Client[] clients,String type,long depositPayment,int yearsOfSavings) {
        super(accountNumber,openAccountDate, bankNumber, balance, managerName, clients,type);
        this.depositPayment=depositPayment;
        this.yearsOfSavings=yearsOfSavings;
    }

    public long getDepositPayment() {
        return depositPayment;
    }

    public void setDepositPayment(int depositPayment) {
        this.depositPayment = depositPayment;
    }

    public int getYearsOfSavings() {
        return yearsOfSavings;
    }

    public void setYearsOfSavings(int yearsOfSavings) {
        this.yearsOfSavings = yearsOfSavings;
    }
    public float profit(float rateDifference){
        return 0;
    }

    @Override
    public String toString() {
        return "DepositPayment= " + depositPayment +
                ",YearsOfSavings= " + yearsOfSavings;
    }
}
