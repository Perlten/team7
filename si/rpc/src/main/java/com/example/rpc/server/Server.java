package com.example.rpc.server;

import com.example.rpc.commen.BankInterface;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static Registry registry;


    public Server() {
    }

    public static void main(String[] args) {
        try{
            System.out.println("Server has started");
            registry = LocateRegistry.createRegistry(1099);

            String bankRMIPoint = "BankServices";
            BankInterface remoteBankEngine = new BankEngine();
            System.out.println("RMI registry created ");

            Naming.rebind("//localhost/" + bankRMIPoint, remoteBankEngine);
            System.out.println("Engine " + bankRMIPoint + " bound in registry");
        }catch(Exception e){
            System.out.println("Something went wrong");
        }
    }
}
