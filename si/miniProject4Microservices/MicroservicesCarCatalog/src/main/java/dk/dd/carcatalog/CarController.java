package dk.dd.carcatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
//@RepositoryRestController
@RepositoryRestResource
@ResponseBody
@RequestMapping("/cars")
public class CarController
{
        @Autowired
        private CarRepo repo;
        private Object Car;

        @GetMapping("/")
        public List<dk.dd.carcatalog.Car> retrieveAllCars()
        {
            return (List<dk.dd.carcatalog.Car>) repo.findAll();
        }

        @GetMapping("/{brand}")
        public dk.dd.carcatalog.Car findByBrand(@PathVariable String brand)
        {
            return repo.findByBrand(brand);
        }

        @PostMapping("/")
        public String saveCar(@RequestBody dk.dd.carcatalog.Car car)
        {
            repo.save(car);
            return " record saved..";
        }

        @DeleteMapping("/{id}")
        public String deleteCar(@PathVariable int id)
        {
            repo.deleteById((long) id);
            return " record deleted...";
        }

        @PutMapping("/{id}")
        public ResponseEntity<Object> updateCar(@RequestBody dk.dd.carcatalog.Car car, @PathVariable int id)
        {
            Optional<dk.dd.carcatalog.Car> found = repo.findById((long) id);

            if (!found.isPresent())
                return ResponseEntity.badRequest()
                        .header("Custom-Header", "foo")
                        .body("Try again")
                        .notFound().build();
            car.setId((long) id);
            repo.save(car);
            return   ResponseEntity.ok()
                    .header("Custom-Header", "foo")
                    .body("Notice the custom header")
                    .noContent().build();
        }
    }

