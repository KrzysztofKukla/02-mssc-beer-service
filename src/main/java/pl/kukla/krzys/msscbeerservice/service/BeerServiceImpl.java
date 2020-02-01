package pl.kukla.krzys.msscbeerservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kukla.krzys.msscbeerservice.domain.Beer;
import pl.kukla.krzys.msscbeerservice.repository.BeerRepository;
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

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @Override

    public BeerDto getById(UUID beerId) {
        return BeerDto.builder().build();
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        beerDto.setId(UUID.randomUUID());
        Beer beer = beerMapper.beerDtoToBeer(beerDto);
        beerRepository.save(beer);
        return beerDto;
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerMapper.beerDtoToBeer(beerDto);
        beerRepository.save(beer);
    }
}
