package com.example.rpc.commen;

import java.io.Serializable;

public class Customer implements Serializable {

    public String firstName, lastName;
    public int accountNumber;
    public int balance;

    public Customer(String firstName, String lastName, int accountNumber, int balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
