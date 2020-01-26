package pl.kukla.krzys.msscbeerservice.service;

import pl.kukla.krzys.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getById(UUID beerId) {
        return BeerDto.builder().build();
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        return beerDto;
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //TODO
    }

}
