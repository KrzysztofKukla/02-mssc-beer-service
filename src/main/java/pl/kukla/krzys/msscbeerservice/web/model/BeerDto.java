package pl.kukla.krzys.msscbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    private UUID id;
    private String beerName;
    private BeerStyleEnum beerStyle;
    private Long upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private Integer version;
    private OffsetDateTime createdDate; //it includes UTC time
    private OffsetDateTime lastModifiedDate;

}
