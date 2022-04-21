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

@Entity(name = "attribute")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(indexes = {
        @Index(columnList = "status")
})
public class AttributeEntity extends BaseCodeAndNameEntity {

    @Column(name = "status", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private BasicStatusEnum status;

    @Column(name = "position", nullable = false, columnDefinition = "integer default 1")
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "attribute_group_id", nullable = false)
    private AttributeGroupEntity attributeGroupEntity;

    @OneToMany(mappedBy = "attributeEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<AttributeFamilyRelationEntity> attributeFamilyEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "attributeEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<AttributeOptionEntity> attributeOptionEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "attributeEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<AttributeValidationEntity> attributeValidationEntities = new LinkedHashSet<>();


}
