package bank.model;

import java.util.Date;
import java.util.List;

public class BankAccount {
    public BankAccount(String iban, double balance, double overdraftLimit, Date createDate, List<Operation> operations) {
        this.iban = iban;
        this.balance = balance;
        this.overdraftLimit = overdraftLimit;
        this.createDate = createDate;
        this.operations = operations;
    }

    public BankAccount(){

    }

    private String iban;
    private double balance;
    private double overdraftLimit;
    private Date createDate;
    private List<Operation> operations;

    public String getIban() {
        return iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public void addAmount(double amount) {
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "iban='" + iban + '\'' +
                ", balance=" + balance +
                ", overdraftLimit=" + overdraftLimit +
                ", createDate=" + createDate +
                ", operations=" + operations +
                '}';
    }
}
