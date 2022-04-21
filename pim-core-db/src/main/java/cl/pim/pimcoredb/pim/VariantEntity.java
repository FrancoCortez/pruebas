package cl.pim.pimcoredb.pim;

import cl.pim.pimcoredb.base.BaseIdEntity;
import cl.pim.pimcoredb.enumes.BasicStatusEnum;
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
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "variant")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(indexes = {
        @Index(columnList = "status")
})
public class VariantEntity extends BaseIdEntity {

    @Column(name = "status", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private BasicStatusEnum status;

    @Column(name = "sku_seller", length = 50, unique = true, nullable = false)
    private String skuSeller;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "price_id")
    private PriceEntity priceEntity;

    @OneToMany(mappedBy = "variantEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<EanVariantRelationEntity> eanVariantRelationEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "variantEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<MediaVariantRelationEntity> mediaVariantRelationEntities = new LinkedHashSet<>();


}
