package FinalProject;

import java.util.Date;

public class RegularCheckingAccount extends CheckingAccount{
    public RegularCheckingAccount(String accountNumber, Date openAccountDate,int bankNumber, long balance, String managerName, Client[] clients,String type,long credit) {
        super(accountNumber,openAccountDate, bankNumber, balance, managerName, clients,type,credit);
    }
    public float profit(float rateDifference){
        return getCredit()*rateDifference;
    }
    @Override
    public String toString() {
        return "Credit= "+getCredit();
    }
}
