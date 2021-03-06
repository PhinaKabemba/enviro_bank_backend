package com.enviro.bank.controllers;

import com.enviro.bank.SystemDB;
import com.enviro.bank.domains.Account;
import com.enviro.bank.domains.CurrentAccount;
import com.enviro.bank.domains.SavingsAccount;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "accounts")
@CrossOrigin("http://localhost:4200")
public class AccountController {

    public AccountController() {

        SavingsAccount savingsAccount1 = new SavingsAccount(101, "011334466", new BigDecimal(2000), "Customer1");
        SavingsAccount savingsAccount2 = new SavingsAccount(102, "011443339", new BigDecimal(5000), "Customer2");
        CurrentAccount currentAccount1 = new CurrentAccount(103, "011556688", new BigDecimal(1000), new BigDecimal(10000), "Customer3");
        CurrentAccount currentAccount2 = new CurrentAccount(104, "012546907", new BigDecimal(-5000), new BigDecimal(20000), "Customer4");
//        Prepopulate memory in database in a map
        SystemDB.ACCOUNTS.put(savingsAccount1.getAccountNum(), savingsAccount1);
        SystemDB.ACCOUNTS.put(savingsAccount2.getAccountNum(), savingsAccount2);
        SystemDB.ACCOUNTS.put(currentAccount1.getAccountNum(), currentAccount1);
        SystemDB.ACCOUNTS.put(currentAccount2.getAccountNum(), currentAccount2);
    }

    @GetMapping
    public List<Account> getAccounts() {

        Map<String, Account> accounts = SystemDB.ACCOUNTS;

        List<Account> allAccounts = new ArrayList<>();

        for (Account account : accounts.values()) {
            allAccounts.add(account);
        }

        return allAccounts;
    }

    @PostMapping(value = "/withdraw")
    public Account withdraw(String accountNumber, BigDecimal amount) {

        Account account = SystemDB.ACCOUNTS.get(accountNumber);

        if (account != null) {

            if (account instanceof CurrentAccount) {
                CurrentAccount currentAccount = (CurrentAccount) account;
                currentAccount.withdraw(accountNumber, amount);

                return currentAccount;
            } else {
                SavingsAccount savingsAccount = (SavingsAccount) account;
                savingsAccount.withdraw(accountNumber, amount);

                return savingsAccount;
            }
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Bank account not found");
        }
    }


}
