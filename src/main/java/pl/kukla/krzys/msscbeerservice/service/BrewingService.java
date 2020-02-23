package pl.kukla.krzys.msscbeerservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.kukla.krzys.msscbeerservice.config.JmsConfig;
import pl.kukla.krzys.msscbeerservice.domain.Beer;
import pl.kukla.krzys.msscbeerservice.event.BrewBeerEvent;
import pl.kukla.krzys.msscbeerservice.repository.BeerRepository;
import pl.kukla.krzys.msscbeerservice.service.inventory.BeerInventoryService;
import pl.kukla.krzys.msscbeerservice.web.mapper.BeerMapper;
import pl.kukla.krzys.msscbeerservice.web.model.BeerDto;

import java.util.List;

/**
 * @author Krzysztof Kukla
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 10000)
    public void checkForLowInventory() {
        List<Beer> beers = beerRepository.findAll();
        beers.forEach(beer -> {
            Integer inventoryQuantityOnHand = beerInventoryService.getOnHandInventory(beer.getId());

            log.debug("Min inventory on hand is-> " + beer.getMinOnHand());
            log.debug("Inventory is-> " + inventoryQuantityOnHand);

            if (beer.getMinOnHand() >= inventoryQuantityOnHand) {
                BeerDto beerDto = beerMapper.beerToBeerDto(beer);
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerDto));
            }
        });
    }

}
