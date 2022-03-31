package com.enviro.bank.domains;

import com.enviro.bank.AccountService;
import com.enviro.bank.SystemDB;
import com.enviro.bank.exceptions.AccountNotFoundException;
import com.enviro.bank.exceptions.WithdrawalAmountTooLargeException;

import java.math.BigDecimal;

public class SavingsAccount extends Account implements AccountService {

    public SavingsAccount(int id, String accountNum, BigDecimal balance, String customerNum) {
        super(id, accountNum, balance, customerNum);
        //If condition in case the balance is less than R1000
        if (balance.compareTo(BigDecimal.valueOf(1000)) < 0) {
            System.out.println("WARNING :: The balance minimum value must be R1000");
        }
    }

    @Override
    //Method to calculate total amount of withdraw
    public void withdraw(String accountNum, BigDecimal amountToWithdraw) {
        Account account = SystemDB.ACCOUNTS.get(accountNum);

        if (account == null) {
            throw new AccountNotFoundException(String.format("Account with Account Number => %s is not found in the database", accountNum));
        }

        if (amountToWithdraw.compareTo(account.getBalance()) > 0) {
            try {
                throw new WithdrawalAmountTooLargeException(String.format("You do not have sufficient funds. You can only withdraw %s or less", account.getBalance()));
            } catch (WithdrawalAmountTooLargeException e) {
                System.out.println(e.getMessage());
             }
        }

        BigDecimal remainingBalance = account.getBalance().subtract(amountToWithdraw);
        this.setBalance(remainingBalance);
    }
}
