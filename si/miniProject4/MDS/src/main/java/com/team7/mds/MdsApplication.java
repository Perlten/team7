package com.team7.mds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MdsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdsApplication.class, args);
    }

}
