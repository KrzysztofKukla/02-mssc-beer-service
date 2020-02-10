package pl.kukla.krzys.msscbeerservice;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kukla.krzys.msscbeerservice.bootstrap.BeerLoader;
import pl.kukla.krzys.msscbeerservice.service.inventory.BeerInventoryService;

@Disabled //only for manual tests
@SpringBootTest
class ApplicationTests {

	@Autowired
	private BeerInventoryService beerInventoryService;

	@Test
	void getOnHandInventory() throws Exception {
		Integer onHand = beerInventoryService.getOnHandInventory(BeerLoader.BEER_1_UUID);

		System.out.println(onHand);
	}

}
