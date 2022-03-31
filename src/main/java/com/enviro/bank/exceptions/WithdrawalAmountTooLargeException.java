package com.enviro.bank.exceptions;

public class WithdrawalAmountTooLargeException extends Exception {
    public WithdrawalAmountTooLargeException(String message){
        super(message);
    }
}
