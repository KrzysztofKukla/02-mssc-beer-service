package pl.kukla.krzys.msscbeerservice.web.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import pl.kukla.krzys.brewery.model.BeerDto;
import pl.kukla.krzys.msscbeerservice.domain.Beer;

/**
 * @author Krzysztof Kukla
 */
@Mapper(uses = DateMapper.class)
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDto beerDto);

    BeerDto beerToBeerDto(Beer beer);

    BeerDto beerToBeerDtoWithInventory(Beer beer);

}
