package pl.kukla.krzys.msscbeerservice.service;

import pl.kukla.krzys.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
public interface BeerService {

    BeerDto getById(UUID beerId);

    BeerDto saveBeer(BeerDto beerDto);

    void updateBeer(UUID beerId, BeerDto beerDto);

}
