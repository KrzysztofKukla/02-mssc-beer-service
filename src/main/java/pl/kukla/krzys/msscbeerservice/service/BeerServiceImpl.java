package pl.kukla.krzys.msscbeerservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kukla.krzys.msscbeerservice.domain.Beer;
import pl.kukla.krzys.msscbeerservice.repository.BeerRepository;
import pl.kukla.krzys.msscbeerservice.web.controller.NotFoundException;
import pl.kukla.krzys.msscbeerservice.web.mapper.BeerMapper;
import pl.kukla.krzys.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        return beerMapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        Beer beer = beerMapper.beerDtoToBeer(beerDto);
        Beer savedBeer = beerRepository.save(beer);
        return beerMapper.beerToBeerDto(savedBeer);
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        if (beerRepository.existsById(beerId)) {
            Beer beer = beerMapper.beerDtoToBeer(beerDto);
            beer.setId(beerId);
            Beer savedBeer = beerRepository.save(beer);
            return beerMapper.beerToBeerDto(savedBeer);
        }
        throw new NotFoundException();
    }

}
