package cl.pim.pimcoredb.pim;

import cl.pim.pimcoredb.base.BaseCodeAndNameEntity;
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

@Entity(name = "family")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(indexes = {
        @Index(columnList = "status")
})
public class FamilyEntity extends BaseCodeAndNameEntity {

    @Column(name = "status", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private BasicStatusEnum status;

    @Column(name = "visible", columnDefinition = "boolean default true")
    private Boolean visible;
    @ManyToOne
    @JoinColumn(name = "family_group_id", nullable = false)
    private FamilyGroupEntity familyGroupEntity;

    @OneToMany(mappedBy = "familyEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<CategoryFamilyRelationEntity> categoryFamilyEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "familyEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<AttributeFamilyRelationEntity> attributeFamilyEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "familyEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<ProductEntity> productEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "familyEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<AttributeOptionFamilyRelationEntity> attributeOptionFamilyEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "familyEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<AttributeValidationEntity> attributeValidationEntities = new LinkedHashSet<>();

}
