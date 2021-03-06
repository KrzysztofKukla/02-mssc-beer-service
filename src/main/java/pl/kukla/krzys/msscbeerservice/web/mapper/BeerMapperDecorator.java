package pl.kukla.krzys.msscbeerservice.web.mapper;

/**
 * @author Krzysztof Kukla
 */

import org.springframework.beans.factory.annotation.Autowired;
import pl.kukla.krzys.brewery.model.BeerDto;
import pl.kukla.krzys.msscbeerservice.domain.Beer;
import pl.kukla.krzys.msscbeerservice.service.inventory.BeerInventoryService;

/**
 * Created by jt on 2019-06-08.
 */
//this class allows us to decorate ( enhance ) our BeerMapper with additional functionality
public abstract class BeerMapperDecorator implements BeerMapper {
    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        return mapper.beerToBeerDto(beer);
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return mapper.beerDtoToBeer(beerDto);
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        BeerDto beerDto = mapper.beerToBeerDto(beer);

        //here we added specific functionality for mapper, others things remain the same
        beerDto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
        return beerDto;
    }

}
