package cl.pim.pimcoredb.pim;

import cl.pim.pimcoredb.base.BaseIdEntity;
import cl.pim.pimcoredb.enumes.PriceStatusEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "price")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(indexes = {
        @Index(columnList = "status")
})
public class PriceEntity extends BaseIdEntity {

    @Column(name = "show_to")
    private LocalDate showTo;

    @Column(name = "show_from")
    private LocalDate showFrom;

    @Column(name = "status", length = 10)
    @Enumerated(EnumType.STRING)
    private PriceStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "price_type_id", nullable = false)
    private PriceTypeEntity priceTypeEntity;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity storeEntity;

    @OneToMany(mappedBy = "priceEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<VariantEntity> variantEntities = new LinkedHashSet<>();


    @OneToMany(mappedBy = "priceEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<PriceAmountEntity> priceAmountEntities = new LinkedHashSet<>();

}
