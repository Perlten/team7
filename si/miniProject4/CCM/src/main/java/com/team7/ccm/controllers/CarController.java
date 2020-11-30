package com.team7.ccm.controllers;

import com.team7.ccm.models.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {

    static final String JDBC_DRIVER = "org.h2.Driver";

    private Connection connection;


    public CarController() throws Exception {
        Class.forName(JDBC_DRIVER);
        this.connection = DriverManager.getConnection("jdbc:h2:mem:car", "sa", "sa");

    }

    @GetMapping("/getCars")
    public List<Car> getAllCars() throws SQLException {
        List<Car> resList = new ArrayList<>();

        Statement st = this.connection.createStatement();
        ResultSet res = st.executeQuery("SELECT * FROM Car;");
        while (res.next()) {
            String brand = res.getString("brand");
            int year = res.getInt("year");
            int km = res.getInt("km");

            resList.add(new Car(brand, year, km));
        }

        return resList;
    }
}
