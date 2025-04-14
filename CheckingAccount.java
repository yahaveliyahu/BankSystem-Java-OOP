package FinalProject;

import java.util.Date;

public class CheckingAccount extends Account{
    private long credit;

    public CheckingAccount(String accountNumber, Date openAccountDate,int bankNumber, long balance, String managerName, Client[] clients,String type,long credit) {
        super(accountNumber,openAccountDate, bankNumber, balance, managerName, clients,type);
        this.credit=credit;
    }
//    public CheckingAccount(CheckingAccount other) {
//        super(other.getAccountNumber(), other.getOpenAccountDate(), other.getBankNumber(), other.getBalance(), other.getManagerName(), other.getClients(), other.getType() );
//        this.credit=other.credit;
//    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

}
