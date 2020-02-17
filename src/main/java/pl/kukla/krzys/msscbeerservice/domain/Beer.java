package pl.kukla.krzys.msscbeerservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer {

    @Id
    @GeneratedValue(generator = "UUID") //Hibernate automatically will generate UUID settings for us
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    //UUID is mapping to varchar and to easy read it from database we need to define type explicitly
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String beerName;
    private String beerStyle;

    @Column(unique = true)
    private String upc;

    private Integer minOnHand;
    private Integer quantityToBrew;
    private BigDecimal price;

    @Version //this gives us optimistic locking
    private Long version;

    //Hibernate is implementation of JPA
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

}
