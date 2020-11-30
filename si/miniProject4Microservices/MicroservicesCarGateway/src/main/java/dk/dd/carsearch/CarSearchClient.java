package dk.dd.carsearch;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@FeignClient("car-catalog")
@RibbonClient(name="car-catalog", configuration = RibbonConfig.class)
public interface CarSearchClient
{
    @GetMapping("/cars")
    Resources<Car> readCars();
}
