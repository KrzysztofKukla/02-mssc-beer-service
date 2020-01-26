package pl.kukla.krzys.msscbeerservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kukla.krzys.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getById(UUID beerId) {
        return BeerDto.builder().build();
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        beerDto.setId(UUID.randomUUID());
        return beerDto;
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //TODO
    }

}
