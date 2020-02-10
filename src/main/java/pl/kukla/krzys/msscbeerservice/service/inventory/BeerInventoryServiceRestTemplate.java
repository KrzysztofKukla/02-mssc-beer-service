package pl.kukla.krzys.msscbeerservice.service.inventory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kukla.krzys.msscbeerservice.service.inventory.model.BeerInventoryDto;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */

//This is RestTemplate Client for BeerInventoryService
@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class BeerInventoryServiceRestTemplate implements BeerInventoryService {

    private static final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
    private final RestTemplate restTemplate;

    //this binds with 'beerInventoryServiceHost' field defined in 'application.properties' with 'sfg.brewery prefix defined in @ConfigurationProperties
    private String beerInventoryServiceHost;

    public BeerInventoryServiceRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    //to bind 'beerInventoryServiceHost' field we need setter here
    public void setBeerInventoryServiceHost(String beerInventoryServiceHost) {
        this.beerInventoryServiceHost = beerInventoryServiceHost;
    }

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.debug("Calling inventory service");

        //it returns list of inventory records
//        ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate.getForObject(beerInventoryServiceHost + INVENTORY_PATH,
//            ResponseEntity.class, beerId);

        ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate.exchange(beerInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET, null,
            new ParameterizedTypeReference<List<BeerInventoryDto>>() {
            }, (Object) beerId); //here we bind 'beerId' with that one form url from "/api/v1/beer/{beerId}/inventory";

        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
            .stream().mapToInt(BeerInventoryDto::getQuantityOnHand)
            .sum();
        return onHand;
    }

}
