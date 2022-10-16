package bank.service;

import bank.dao.BankDao;
import bank.dao.impl.BankDaoImpl;
import bank.model.BankAccount;
import bank.service.impl.BankServicesImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankServiceTest {
    private BankServices bankServicesTest;
    private List<BankAccount> bankAccounts;


    @BeforeEach
    public void initBank(){
        bankAccounts = new ArrayList<>();

        bankAccounts.add(new BankAccount("FR11111111",100,200,new Date(),new ArrayList<>()));
        bankAccounts.add(new BankAccount("FR22222222",300,0,new Date(),new ArrayList<>()));
        bankAccounts.add(new BankAccount("FR33333333",1000,100,new Date(),new ArrayList<>()));
        bankAccounts.add(new BankAccount("FR44444444",500,200,new Date(),new ArrayList<>()));
        bankAccounts.add(new BankAccount("FR55555555",800,200,new Date(),new ArrayList<>()));
        bankAccounts.add(new BankAccount("FR66666666",10000,1000,new Date(),new ArrayList<>()));

        BankDao bankDao = new BankDaoImpl(bankAccounts);
        bankServicesTest = new BankServicesImpl(bankDao);
    }

    @AfterEach
    public void rollbackAccountValue(){
        bankAccounts.get(3).setBalance(500);
    }
    @Test
    public void makeDepositTest() {
        boolean deposit1 = bankServicesTest.makeDeposit("FR44444444", 200);
        boolean deposit2 = bankServicesTest.makeDeposit("FR44444444", -200);
        boolean deposit3 = bankServicesTest.makeDeposit("FR88888888", 200);

        assertEquals(deposit1, true);
        assertEquals(deposit2, false);
        assertEquals(deposit3, false);
        assertEquals(bankAccounts.get(3).getBalance(),700);
    }

    @Test
    public void makeWithdrawalTest() {
        boolean wd1 = bankServicesTest.makeWithdrawal("FR44444444", null,200);
        boolean wd2 = bankServicesTest.makeWithdrawal("FR44444444", null,-200);
        boolean wd3 = bankServicesTest.makeWithdrawal("FR88888888", null,200);

        assertEquals(wd1, false);
        assertEquals(wd2, true);
        assertEquals(wd3, false);
        assertEquals(bankAccounts.get(3).getBalance(),300);
    }
}
