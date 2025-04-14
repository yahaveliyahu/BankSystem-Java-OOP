package FinalProject;

public class DuplicationException extends Exception {
    private final String duplicateAccountNumber;

    public DuplicationException(String duplicateAccountNumber) {
        super("Account number " + duplicateAccountNumber + " already exists.");
        this.duplicateAccountNumber = duplicateAccountNumber;
    }

    public String getDuplicateAccountNumber() {
        return duplicateAccountNumber;
    }
}
