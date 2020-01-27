package pl.kukla.krzys.msscbeerservice.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kukla.krzys.msscbeerservice.domain.Beer;
import pl.kukla.krzys.msscbeerservice.repository.BeerRepository;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author Krzysztof Kukla
 */
@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {
    private final BeerRepository beerRepository;

    //it will run when Spring Context starts
    @Override
    public void run(String... args) throws Exception {
        if (beerRepository.count() == 0) {
            loadBeers();
        }
    }

    private void loadBeers() {
        Beer beer1 = Beer.builder()
            .beerName("first").beerStyle("first style").minOnHand(10).quantityToBrew(20).price(new BigDecimal("19.25")).upc(122L).build();
        Beer beer2 = Beer.builder()
            .beerName("second").beerStyle("secondt style").minOnHand(20).quantityToBrew(30).price(new BigDecimal("29.25")).upc(722L).build();

        beerRepository.saveAll(Arrays.asList(beer1, beer2));

    }

}
