package pl.kukla.krzys.msscbeerservice.service.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.kukla.krzys.brewery.model.BeerOrderDto;
import pl.kukla.krzys.msscbeerservice.domain.Beer;
import pl.kukla.krzys.msscbeerservice.exception.NotFoundException;
import pl.kukla.krzys.msscbeerservice.repository.BeerRepository;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Krzysztof Kukla
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrderDto) {

        AtomicInteger beersNotFound = new AtomicInteger();

        beerOrderDto.getBeerOrderLines().forEach(orderLine -> {
            Beer beer = beerRepository.findByUpc(orderLine.getUpc())
                .orElseThrow(() -> new NotFoundException("Beer does not exist for given upc"));
            if (beer == null) {
                beersNotFound.incrementAndGet();
            }
        });

        return beersNotFound.get() == 0;
    }

}
