package bank.dao;


import bank.model.BankAccount;

public interface BankDao {
    public BankAccount getBankAccount(String iban);
    public void updateBankAccount(BankAccount bankAccount);
}
