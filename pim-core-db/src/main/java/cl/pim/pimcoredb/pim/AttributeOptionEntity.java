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

@Entity(name = "attribute_option")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(indexes = {
        @Index(columnList = "status")
})
public class AttributeOptionEntity extends BaseCodeAndNameEntity {

    @Column(name = "position", columnDefinition = "integer default 1")
    private Integer position;

    @Column(name = "status", length = 10)
    @Enumerated(EnumType.STRING)
    private BasicStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "attribute_id", nullable = false)
    private AttributeEntity attributeEntity;

    @OneToMany(mappedBy = "attributeOptionEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<AttributeOptionFamilyRelationEntity> attributeOptionFamilyEntities = new LinkedHashSet<>();

}
