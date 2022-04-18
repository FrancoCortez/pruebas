package cl.pim.pimcoredb.entity;

import cl.pim.pimcoredb.base.BaseIdEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "product")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(indexes = {
        @Index(columnList = "name")
})
public class ProductEntity extends BaseIdEntity {

    @Column(name = "sku_seller", length = 50, unique = true, nullable = false)
    private String skuSeller;
    @Column(name = "ref_id", length = 50, unique = true)
    private String refId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "family_id", nullable = false)
    private FamilyEntity familyEntity;

    @OneToMany(mappedBy = "productEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<VariantEntity> variantEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<SellerProductRelationEntity> sellerProductRelationEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<ChannelProductRelationEntity> channelProductRelationEntities = new LinkedHashSet<>();

}
