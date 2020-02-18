package pl.kukla.krzys.msscbeerservice.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kukla.krzys.msscbeerservice.domain.Beer;
import pl.kukla.krzys.msscbeerservice.repository.BeerRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

    //is very common to treat UPC as a String
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

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
            .beerName("first").beerStyle("first style").minOnHand(10).quantityToBrew(20).price(new BigDecimal("19.25")).upc(BEER_1_UPC).build();
        Beer beer2 = Beer.builder()
            .beerName("second").beerStyle("secondt style").minOnHand(20).quantityToBrew(30).price(new BigDecimal("29.25")).upc(BEER_2_UPC).build();
        Beer beer3 = Beer.builder()
            .beerName("third").beerStyle("third style").minOnHand(30).quantityToBrew(40).price(new BigDecimal("45.25")).upc(BEER_3_UPC).build();

        beerRepository.saveAll(Arrays.asList(beer1, beer2, beer3));

    }

}
