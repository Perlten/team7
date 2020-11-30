package dk.dd.carcatalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarRepo extends JpaRepository<Car, Long>
{
    // Here are additional customer defined search methods
    Car findByBrand(String name);
}
