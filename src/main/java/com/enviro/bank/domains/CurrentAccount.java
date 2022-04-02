package com.enviro.bank.domains;


import com.enviro.bank.AccountService;
import com.enviro.bank.SystemDB;
import com.enviro.bank.exceptions.AccountNotFoundException;
import com.enviro.bank.exceptions.WithdrawalAmountTooLargeException;

import java.math.BigDecimal;

//Inheritance and Implementation
//CurrentAccount child of Account class
public class CurrentAccount extends Account implements AccountService {

    private BigDecimal overdraft;

    public CurrentAccount(int id, String accountNum, BigDecimal balance, BigDecimal overdraft, String customerNum) {
        super(id, accountNum, balance, customerNum);
        // Condition: limit of overdraft must be less than or equal to 100000
        if (overdraft.compareTo(BigDecimal.valueOf(100000)) <= 0) {
            this.overdraft = overdraft;
        } else {
            //We could have thrown an exception but it is not mentioned in the requirement, hence the warning
            System.out.println("WARNING :: The overdraft limit must be R100000 or less");
        }
    }

    public BigDecimal getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(BigDecimal overdraft) {
        this.overdraft = overdraft;
    }

    @Override
    //method to calculate total amount of withdraw
    public void withdraw(String accountNum, BigDecimal amountToWithdraw) {
        //Convert a parent class to a child class
        CurrentAccount account = (CurrentAccount) SystemDB.ACCOUNTS.get(accountNum);

        if (account == null) {
            throw new AccountNotFoundException(String.format("Account with Account Number => %s is not found in the database",
                    accountNum));
        }

        if (amountToWithdraw.compareTo(account.getBalance()) > 0) {
            BigDecimal remaining = amountToWithdraw.subtract(account.getBalance());

            if (remaining.compareTo(account.getOverdraft()) > 0) {
                try {
                    throw new WithdrawalAmountTooLargeException(String.format("You do not have sufficient funds. You can only withdraw %s or less",
                            account.getOverdraft()));
                } catch (WithdrawalAmountTooLargeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                this.setBalance(account.getBalance().subtract(amountToWithdraw));
            }
        } else {
            BigDecimal result = account.getBalance().subtract(amountToWithdraw);
            this.setBalance(result);
        }
    }
}
