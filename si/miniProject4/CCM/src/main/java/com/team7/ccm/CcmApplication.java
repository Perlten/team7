package com.team7.ccm;

import com.team7.ccm.controllers.CarController;
import com.team7.ccm.models.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class CcmApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CcmApplication.class, args);
//        CarController cc = new CarController();
//        List<Car> carList = cc.getAllCars();
//        System.out.println(carList);
    }

}
