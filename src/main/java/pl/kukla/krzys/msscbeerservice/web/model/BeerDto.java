package pl.kukla.krzys.msscbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
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
    @Null //it means you have to pass null value for that field
    private UUID id;

    @NotBlank
    private String beerName;

    @NotNull
    private BeerStyleEnum beerStyle;

    @NotNull
    @Positive
    private Long upc;

    private Integer quantityOnHand;

    @NotNull
    @Positive
    private BigDecimal price;

    @Null
    private Integer version;

    @Null
    @CreationTimestamp
    private OffsetDateTime createdDate; //OffsetDateTime includes UTC time

    @Null
    @UpdateTimestamp
    private OffsetDateTime lastModifiedDate;

}
