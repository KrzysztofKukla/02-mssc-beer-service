package pl.kukla.krzys.msscbeerservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.kukla.krzys.brewery.model.BeerDto;
import pl.kukla.krzys.brewery.model.BeerPagedList;
import pl.kukla.krzys.msscbeerservice.service.BeerService;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    //each instance has own local cache, but we can configure ehcache to have only single cache for multiple instances of service
    //@GET method like this one - listBeers will NOT change to much, so it is good idea to add caching here to avoid call to database
    //only use cache when we are not getting inventory, because inventory can change often and quickly
    @GetMapping(value = "/beer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) String beerStyle,
                                                   @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        BeerPagedList beerPagedList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return ResponseEntity.ok(beerPagedList);
    }

    //@GET by beerId will NOT change to much, so it is good idea to add caching here to avoid call to database
    //only use cache when we are not getting inventory, because inventory can change often and quickly
    @GetMapping("/beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId,
                                               @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }
        BeerDto beerDto = beerService.getById(beerId, showInventoryOnHand);
        return ResponseEntity.ok(beerDto);
    }

    @GetMapping(value = "/beerUpc/{upc}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BeerDto> getByUpc(@PathVariable String upc) {
        BeerDto beerDto = beerService.getByUpc(upc);
        return ResponseEntity.ok(beerDto);
    }

    @PostMapping(value = "/beer")
    public ResponseEntity<BeerDto> createNewBeer(@RequestBody @Valid BeerDto beerDto) {
        BeerDto savedBeer = beerService.saveBeer(beerDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("location", "/api/v1/beer" + savedBeer.getId().toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/beer/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBeer(@PathVariable UUID beerId, @RequestBody @Valid BeerDto beerDto) {
        beerService.updateBeer(beerId, beerDto);

    }

}
