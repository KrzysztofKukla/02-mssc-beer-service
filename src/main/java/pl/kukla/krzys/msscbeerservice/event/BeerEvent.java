package pl.kukla.krzys.msscbeerservice.event;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.kukla.krzys.msscbeerservice.web.model.BeerDto;

import java.io.Serializable;

/**
 * @author Krzysztof Kukla
 */
@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -8898740913569738304L;

    private final BeerDto beerDto;
}
