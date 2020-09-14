package com.example.rpc.commen;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankInterface extends Remote {

    public int getDatabaseTotalSize() throws RemoteException;

    public void saveCustomer(Customer customer) throws RemoteException;

}
