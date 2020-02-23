package pl.kukla.krzys.msscbeerservice.event;

import pl.kukla.krzys.msscbeerservice.web.model.BeerDto;

/**
 * @author Krzysztof Kukla
 */
public class BrewBeerEvent extends BeerEvent {
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }

}
