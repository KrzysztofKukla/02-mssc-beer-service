package pl.kukla.krzys.msscbeerservice.service.inventory.failover;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kukla.krzys.msscbeerservice.service.inventory.model.BeerInventoryDto;

import java.util.List;

/**
 * @author Krzysztof Kukla
 */
@FeignClient("micro-12-mssc-inventory-failover-reactive")
public interface InventoryFailoverFeignClient {

    String INVENTORY_FAILOVER_PATH = "/inventory-failover";

    @GetMapping(value = INVENTORY_FAILOVER_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnhandInventory();

}
