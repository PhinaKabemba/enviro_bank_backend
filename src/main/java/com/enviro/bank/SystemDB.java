package com.enviro.bank;


import com.enviro.bank.domains.Account;

import java.util.HashMap;
import java.util.Map;

// Creating a singleton class "SystemDB"
public class SystemDB {
    //Creating a HashMap where we can prepopulate the dummy accounts
    public static Map<String, Account> ACCOUNTS = new HashMap<>();
}
