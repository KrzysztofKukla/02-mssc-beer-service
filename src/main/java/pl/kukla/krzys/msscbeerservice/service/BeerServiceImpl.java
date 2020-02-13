package pl.kukla.krzys.msscbeerservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.kukla.krzys.msscbeerservice.domain.Beer;
import pl.kukla.krzys.msscbeerservice.repository.BeerRepository;
import pl.kukla.krzys.msscbeerservice.web.controller.NotFoundException;
import pl.kukla.krzys.msscbeerservice.web.mapper.BeerMapper;
import pl.kukla.krzys.msscbeerservice.web.model.BeerDto;
import pl.kukla.krzys.msscbeerservice.web.model.BeerPagedList;

import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Krzysztof Kukla
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    public static final String CANNOT_FIND_BEER = "Cannot find Beer with id-> ";
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    //'beerListCache' defines to use 'beerListCache' specif cache defined in 'ehcache.xml' file
    //condition means this cache will be used only when 'showInventoryOnHand' is equals to 'false'
    //here we did not specify the 'key' so Spring will generate key based on all those parameters
    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
    @Override
    public BeerPagedList listBeers(String beerName, String beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {

        System.out.println("I was called");

        BeerPagedList beerPagedList;
        Page<Beer> beerPage;
        if (StringUtils.isNotEmpty(beerName) && StringUtils.isNotEmpty(beerStyle)) {
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (StringUtils.isNotEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search by beerName
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && StringUtils.isNotEmpty(beerStyle)) {
            //search by beerStyle
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        beerPagedList = new BeerPagedList(
            beerPage
                .getContent()
                .stream()
                .map(enhanceInventory(showInventoryOnHand))
                .collect(Collectors.toList()),
            PageRequest.of(
                beerPage.getPageable().getPageNumber(),
                beerPage.getPageable().getPageSize()),
            beerPage.getTotalElements()
        );
        return beerPagedList;
    }

    //@Cacheable means the method will be call only once, but next time method will be read from cache
    //we generate cache base on 'key'
    //if the key is not specified like above, Spring will generate key based on all parameters
    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false")
    @Override
    public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {

        System.out.println("I was called");

        Beer beer = beerRepository.findById(beerId)
            .orElseThrow(() -> new NotFoundException(CANNOT_FIND_BEER + beerId.toString()));
        return showInventoryOnHand ?
            beerMapper.beerToBeerDtoWithInventory(beer) : beerMapper.beerToBeerDto(beer);
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
        throw new NotFoundException(CANNOT_FIND_BEER + beerId.toString());
    }

    private Function<Beer, BeerDto> enhanceInventory(boolean showInventoryOnHand) {
        return showInventoryOnHand ?
            beerMapper::beerToBeerDtoWithInventory : beerMapper::beerToBeerDto;
    }

}
