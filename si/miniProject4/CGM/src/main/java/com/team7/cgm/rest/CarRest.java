package com.team7.cgm.rest;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.team7.cgm.DTO.CarDTO;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarRest {

    @GetMapping("/mycars/{year}")
    public List<CarDTO> getCarsWithFilter(@PathVariable("year") int year) {
        var factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(3000);
        var restTemplate = new RestTemplate(factory);

        var res = restTemplate.getForObject("http://ccm:8090/getCars", String.class);
        List<CarDTO> tempList = new Gson().fromJson(res, new TypeToken<ArrayList<CarDTO>>() {
        }.getType());
        List<CarDTO> resList = new ArrayList<>();
        for (CarDTO car : tempList) {
            if (car.year >= year) {
                resList.add(car);
            }
        }
        return resList;
    }
}
