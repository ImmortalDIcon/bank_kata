package bank.service.impl;


import bank.dao.BankDao;
import bank.dao.impl.BankDaoImpl;
import bank.model.BankAccount;
import bank.model.Operation;
import bank.model.OperationType;
import bank.service.BankServices;

import java.util.Date;

public class BankServicesImpl implements BankServices {

    private BankDao bankDao;
    private static String UNKNOWN_WITHDRAWAL_WORDING = "Unknown withdrawal";

    public BankServicesImpl() {
        bankDao = new BankDaoImpl();
    }

    public BankServicesImpl(BankDao bankDao) {
        this.bankDao = bankDao;
    }


    @Override
    public boolean makeDeposit(String iban, double amount) {
        BankAccount account = bankDao.getBankAccount(iban);
        if(account==null || amount<=0) return false;
        account.addAmount(amount);
        account.getOperations().add(new Operation(new Date(),"Deposit", OperationType.CREDIT,amount));
        bankDao.updateBankAccount(account);
        return true;
    }

    @Override
    public boolean makeWithdrawal(String iban, String wording,double amount) {
        BankAccount account = bankDao.getBankAccount(iban);
        if(account==null || amount>=0 || !canMakeWithdrawal(account,amount)) return false;
        if(wording==null || wording.isEmpty()) wording = UNKNOWN_WITHDRAWAL_WORDING;
        account.addAmount(amount);
        account.getOperations().add(new Operation(new Date(),wording, OperationType.DEBIT,amount));
        bankDao.updateBankAccount(account);
        return true;
    }

    /**
     * Check if can make a withdrawal from a bank account
     * @param account
     * @param amountToWithdrawal
     * @return true can makeWithdrawal or false if it's impossible
     */
    private boolean canMakeWithdrawal(BankAccount account, double amountToWithdrawal){
        if(account.getBalance()+ account.getOverdraftLimit()<= amountToWithdrawal) return true;
        return false;
    }
}
