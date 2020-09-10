package com.example.rpc.client;

import com.example.rpc.commen.BankInterface;
import com.example.rpc.commen.Customer;

import java.rmi.Naming;

public class Client {
    public static void main(String[] args) throws Exception {
        String remoteEngine = "rmi://localhost/BankServices";

        BankInterface bankEngine = (BankInterface) Naming.lookup(remoteEngine);
        bankEngine.saveCustomer(new Customer("Nikolai", "Perlt", 1, 1000000));
        bankEngine.saveCustomer(new Customer("Jesper", "Rusbjerg", 2, -1));
        bankEngine.saveCustomer(new Customer("Hr", "Due", 3, 1));

        System.out.println("Total database size " + bankEngine.getDatabaseTotalSize());
    }
}
