package pl.kukla.krzys.msscbeerservice.service.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import pl.kukla.krzys.brewery.model.BeerOrderDto;
import pl.kukla.krzys.brewery.model.event.ValidateOrderRequestEvent;
import pl.kukla.krzys.brewery.model.event.ValidateOrderResultEvent;
import pl.kukla.krzys.msscbeerservice.config.JmsConfig;

/**
 * @author Krzysztof Kukla
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BeerOrderValidationListener {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderValidator beerOrderValidator;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listenBeerOrder(ValidateOrderRequestEvent validateOrderRequestEvent) {
        log.debug("Received message from Beer Order Service");

        BeerOrderDto beerOrderDto = validateOrderRequestEvent.getBeerOrderDto();
        ValidateOrderResultEvent validateOrderResultEvent = ValidateOrderResultEvent.builder()
            .isValid(beerOrderValidator.validateOrder(beerOrderDto))
            .id(beerOrderDto.getId())
            .build();
        log.debug("Sending validated object to result queue");
        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE, validateOrderResultEvent);
    }

}
