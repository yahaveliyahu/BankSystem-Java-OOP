package FinalProject;

import java.util.Date;

public class MortgageAccount extends Account{
private long originalMortgageAmount;
private int yearsOfMortgage;
private int monthlyPayment;

    public MortgageAccount(String accountNumber, Date openAccountDate,int bankNumber, long balance, String managerName, Client[] clients,String type,long originalMortgageAmount, int yearsOfMortgage,int monthlyPayment) {
        super(accountNumber,openAccountDate, bankNumber, balance, managerName, clients,type);
        this.monthlyPayment=monthlyPayment;
        this.originalMortgageAmount=originalMortgageAmount;
        this.yearsOfMortgage=yearsOfMortgage;
    }

    public long getOriginalMortgageAmount() {
        return originalMortgageAmount;
    }

    public void setOriginalMortgageAmount(int originalMortgageAmount) {
        this.originalMortgageAmount = originalMortgageAmount;
    }

    public int getYearsOfMortgage() {
        return yearsOfMortgage;
    }

    public void setYearsOfMortgage(int yearsOfMortgage) {
        this.yearsOfMortgage = yearsOfMortgage;
    }

    public int getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(int monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
    public float profit(float rateDifference){
        float sumPerYear=(originalMortgageAmount*0.8f)/yearsOfMortgage;
        return sumPerYear*rateDifference;
    }

    @Override
    public String toString() {
        return "OriginalMortgageAmount= " + originalMortgageAmount +
                ", YearsOfMortgage= " + yearsOfMortgage +
                ", MonthlyPayment= " + monthlyPayment ;
    }
}
