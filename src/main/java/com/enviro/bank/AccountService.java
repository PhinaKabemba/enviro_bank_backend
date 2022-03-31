package com.enviro.bank;

import java.math.BigDecimal;

public interface AccountService {
    void withdraw(String accountNum, BigDecimal
            amountToWithdraw);


}
