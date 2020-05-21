package pl.kukla.krzys.msscbeerservice.service.inventory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kukla.krzys.msscbeerservice.service.inventory.failover.InventoryFailoverFeignClient;
import pl.kukla.krzys.msscbeerservice.service.inventory.model.BeerInventoryDto;

import java.util.List;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceFeignClientFailover implements InventoryServiceFeignClient {

    private final InventoryFailoverFeignClient inventoryFailoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(UUID beerId) {
        return inventoryFailoverFeignClient.getOnhandInventory();
    }

    @Override
    public ResponseEntity<List<BeerInventoryDto>> findAll() {
        //TODO
        throw new RuntimeException("NOT IMPLEMENTED");
    }

}
