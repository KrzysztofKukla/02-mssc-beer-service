package pl.kukla.krzys.common.events;

import lombok.NoArgsConstructor;
import pl.kukla.krzys.msscbeerservice.web.model.BeerDto;

/**
 * @author Krzysztof Kukla
 */
//Jackson wants to have @NoArgsConstructor
@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }

}
