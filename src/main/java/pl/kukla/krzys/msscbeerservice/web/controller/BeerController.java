package pl.kukla.krzys.msscbeerservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.kukla.krzys.msscbeerservice.service.BeerService;
import pl.kukla.krzys.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@RequestMapping("/api/v1/beer")
@RestController
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {
        BeerDto beerDto = beerService.getById(beerId);
        return new ResponseEntity<>(beerDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> createNewBeer(@RequestBody BeerDto beerDto) {
        BeerDto savedBeer = beerService.saveBeer(beerDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("location", "/api/v1/beer" + savedBeer.getId().toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBeer(@PathVariable UUID beerId, @RequestBody BeerDto beerDto) {
        beerService.updateBeer(beerId, beerDto);

    }

}
