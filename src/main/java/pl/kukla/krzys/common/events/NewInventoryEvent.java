package pl.kukla.krzys.common.events;

import lombok.NoArgsConstructor;
import pl.kukla.krzys.msscbeerservice.web.model.BeerDto;

/**
 * @author Krzysztof Kukla
 */

//the same package 'pl.kukla.krzys.common.events' for many microservices is recommended by Spring framework to improve performance

//Jackson wants to have @NoArgsConstructor
@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }

}
