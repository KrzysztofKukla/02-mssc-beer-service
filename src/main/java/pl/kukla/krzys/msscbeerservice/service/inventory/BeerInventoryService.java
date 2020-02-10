package pl.kukla.krzys.msscbeerservice.service.inventory;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
public interface BeerInventoryService {
    Integer getOnHandInventory(UUID beerId);

}
