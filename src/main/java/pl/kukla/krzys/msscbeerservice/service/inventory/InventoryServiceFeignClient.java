package pl.kukla.krzys.msscbeerservice.service.inventory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.kukla.krzys.msscbeerservice.service.inventory.model.BeerInventoryDto;

import java.util.List;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
//in runtime Spring provides implementation for that
// 'micro-06-mssc-beer-inventory-service' has to be registered in Eureka
//when inventoryService cannot be reached then it is going to fallback-> 'InventoryServiceFeignClientFailover'
@FeignClient(name = "micro-06-mssc-beer-inventory-service", fallback = InventoryServiceFeignClientFailover.class)
public interface InventoryServiceFeignClient {

    String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";

    @GetMapping(value = INVENTORY_PATH)
        //'beerId' will bind to 'beerId' from '/api/v1/beer/{beerId}/inventory'
    ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(@PathVariable UUID beerId);

    @GetMapping(value = "/inventory")
    ResponseEntity<List<BeerInventoryDto>> findAll();

}
