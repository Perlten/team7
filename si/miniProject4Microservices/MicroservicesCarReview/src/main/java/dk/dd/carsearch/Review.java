package dk.dd.carsearch;

import lombok.Data;
import lombok.NonNull;

@Data
public class Car
{
        //@Id
        //@GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @NonNull private long carId;
        @NonNull private long userId;
        @NonNull private int rating;
}
