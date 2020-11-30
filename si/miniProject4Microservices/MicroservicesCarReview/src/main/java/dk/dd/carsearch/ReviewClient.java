package dk.dd.carsearch;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@FeignClient("review-catalog")
@RibbonClient(name="review-catalog", configuration = RibbonConfig.class)
public interface ReviewClient
{
    @GetMapping("/reviews")
    Resources<Car> readReviews();
}
