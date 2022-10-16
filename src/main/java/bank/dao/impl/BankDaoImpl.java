package bank.dao.impl;



import bank.dao.BankDao;
import bank.model.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class BankDaoImpl implements BankDao {
    public List<BankAccount> bankAccounts;

    /**
     * Init data
     */
    public BankDaoImpl(){
        bankAccounts = new ArrayList<>();
    }

    public BankDaoImpl(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    /**
     * Methode to get an account data by IBAN
     */
    @Override
    public BankAccount getBankAccount(String iban) {

        return bankAccounts.stream().filter(bankAccount -> bankAccount.getIban().equals(iban)).findFirst().orElse(null);

    }

    /**
     * Update the account data in the data game
     */
    @Override
    public void updateBankAccount(BankAccount bankAccount) {
        this.bankAccounts.forEach(account -> {
            if(account.getIban().equals(bankAccount.getIban())) {
                account = bankAccount;
                return;
            }
        });
    }


}
