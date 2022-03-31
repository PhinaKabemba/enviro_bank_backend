package com.enviro.bank.domains;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private int id;
    private String accountNum;
    private BigDecimal balance;
    private String customerNum;

    public Account(int id, String accountNum, BigDecimal balance, String customerNum) {
        this.id = id;
        this.accountNum = accountNum;
        this.balance = balance;
        this.customerNum = customerNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && accountNum.equals(account.accountNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNum);
    }
}
