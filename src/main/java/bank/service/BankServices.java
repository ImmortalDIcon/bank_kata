package bank.service;

import bank.model.Operation;

import java.util.List;

public interface BankServices {

    /**
     * Find an account by IBAN and make a deposit to it
     * @param iban
     * @param amount
     * @return
     */
    public boolean makeDeposit(String iban, double amount);

    /**
     * Find an account by IBAN and make a withdrawal from it
     * @param iban
     * @param wording
     * @param amount
     * @return
     */
    public boolean makeWithdrawal(String iban, String wording, double amount);


    /**
     * Find an account by IBAN and get it's bank statement
     * @param iban
     * @return
     */
    public List<Operation> getBankStatement(String iban);
}
