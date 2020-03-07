package pl.kukla.krzys.msscbeerservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * @author Krzysztof Kukla
 */
@Configuration
public class JmsConfig {

    public static final String BREWING_REQUEST_QUEUE = "brewing-request-queue";
    public static final String NEW_INVENTORY_QUEUE = "new-inventory-queue";
    public static final String VALIDATE_ORDER_QUEUE = "validate-order-queue";
    public static final String VALIDATE_ORDER_RESPONSE_QUEUE = "valid-order-response-queue";

    //when we send the message to JMS, Spring converts that message to JMS text message
    //and the payload takes Java object and converts to JSON payload
    //conversion between JMS message and from Java Object into serialized JSON into JMS message
    //here we have others objectMappers, but have to use the same passing passing that like below
    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) { //so here we are telling Spring to inject the managed instance of objectMapper
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper); // so now converter is using Spring Boot managed object mapper

        return converter;
    }
}
