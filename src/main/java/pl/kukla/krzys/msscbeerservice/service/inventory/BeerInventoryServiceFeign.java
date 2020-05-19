package pl.kukla.krzys.msscbeerservice.service.inventory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kukla.krzys.msscbeerservice.service.inventory.model.BeerInventoryDto;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Profile("local-discovery")
@Service
@RequiredArgsConstructor
@Slf4j
public class BeerInventoryServiceFeign implements BeerInventoryService {

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.debug("Calling BeerInventory service from Feign Client with beerId {}", beerId);
        ResponseEntity<List<BeerInventoryDto>> onhandInventoryList = inventoryServiceFeignClient.getOnhandInventory(beerId);

        int onHand = Objects.requireNonNull(onhandInventoryList.getBody())
            .stream()
            .mapToInt(BeerInventoryDto::getQuantityOnHand)
            .sum();

        log.debug("BeerId->{} On hand is->{}", beerId, onHand);

        return onHand;
    }

}
