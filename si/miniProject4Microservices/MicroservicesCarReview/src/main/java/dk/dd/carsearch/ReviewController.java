package dk.dd.carsearch;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class ReviewController
{
    private ReviewClient reviewClient = null;

    public ReviewController(ReviewClient reviewClient)
    {
        this.reviewClient = reviewClient;
    }

    @GetMapping("/myreviews")
    @ResponseBody
    @CrossOrigin(origins = "*") // allow request from any client
    @HystrixCommand(fallbackMethod = "fallback") // in case of failure
    public Collection<Car> myReviews()
    {
        return reviewClient.readCars()
                .getContent()
                .stream()
                .filter(this :: isMine)
                .collect(Collectors.toList());
    }

    private boolean isMine(Review review)
    {
        return  review.getUserId() != 0;
    }

    private Collection<Review> fallback()
    {
        return new ArrayList<>();
    }
}