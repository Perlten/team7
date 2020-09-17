package com.example.rpc.server;

import com.example.rpc.commen.BankInterface;
import com.example.rpc.commen.Customer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class BankEngine extends UnicastRemoteObject implements BankInterface {
    public static final String URL = "jdbc:h2:./database";
    public static final String DRIVER = "org.h2.Driver";
    public static final String USER = "team7";
    public static final String PASSWORD = "team7";


    protected BankEngine() throws RemoteException {
    }

    @Override
    public int getDatabaseTotalSize() {
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = con.createStatement();

            String sql = "select count(*) as total from Customers;";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int res = resultSet.getInt("total");
            return res;
        } catch (Exception e) {
            System.out.println("Something went wrong");
            return -1;
        }
    }

    @Override
    public void saveCustomer(Customer customer) {
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = con.createStatement();
            String sql = "INSERT INTO Customers(firstName, lastName, account, balance) " + "VALUES ('" + customer.firstName + "', '" + customer.lastName + "', " + customer.accountNumber + ", " + customer.balance + ");";
            statement.execute(sql);
        } catch (Exception e) {
            System.out.println("Stuff went wrong");
        }
    }
}
