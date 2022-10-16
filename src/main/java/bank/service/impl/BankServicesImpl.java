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

    public BankServicesImpl() {
        bankDao = new BankDaoImpl();
    }

    public BankServicesImpl(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    /**
     * Find an account by IBAN and make a deposit to it
     * @param iban
     * @param amount
     * @return
     */
    @Override
    public boolean makeDeposit(String iban, double amount) {
        BankAccount account = bankDao.getBankAccount(iban);
        if(account==null || amount<=0) return false;
        account.addAmount(amount);
        account.getOperations().add(new Operation(new Date(),"Deposit", OperationType.CREDIT,amount));
        bankDao.updateBankAccount(account);
        return true;
    }
}
