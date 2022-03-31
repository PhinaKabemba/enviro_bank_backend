package com.enviro.bank.controllers;

import com.enviro.bank.SystemDB;
import com.enviro.bank.domains.Account;
import com.enviro.bank.domains.CurrentAccount;
import com.enviro.bank.domains.SavingsAccount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "accounts")
public class AccountController {

    public AccountController() {

        SavingsAccount savingsAccount1 = new SavingsAccount(101, "1", new BigDecimal(2000), "Customer1");
        SavingsAccount savingsAccount2 = new SavingsAccount(102, "2", new BigDecimal(5000), "Customer2");
        CurrentAccount currentAccount1 = new CurrentAccount(103, "3", new BigDecimal(1000), new BigDecimal(10000), "Customer3");
        CurrentAccount currentAccount2 = new CurrentAccount(104, "4", new BigDecimal(-5000), new BigDecimal(20000), "Customer4");
//
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

}
