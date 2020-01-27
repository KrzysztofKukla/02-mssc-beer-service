package pl.kukla.krzys.msscbeerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kukla.krzys.msscbeerservice.domain.Beer;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
//
public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
