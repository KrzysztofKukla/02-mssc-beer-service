package pl.kukla.krzys.msscbeerservice.event;

import pl.kukla.krzys.msscbeerservice.web.model.BeerDto;

/**
 * @author Krzysztof Kukla
 */
public class NewInventoryEvent extends BeerEvent {
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }

}
