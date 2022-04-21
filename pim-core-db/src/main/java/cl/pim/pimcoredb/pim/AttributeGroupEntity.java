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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "attribute_group")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(indexes = {
        @Index(columnList = "status")
})
public class AttributeGroupEntity extends BaseCodeAndNameEntity {

    @Column(name = "visible", columnDefinition = "boolean default true")
    private Boolean visible;

    @Column(name = "position", columnDefinition = "integer default 1")
    private Integer position;

    @Column(name = "status", length = 10)
    @Enumerated(EnumType.STRING)
    private BasicStatusEnum status;

    @OneToMany(mappedBy = "attributeGroupEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<AttributeEntity> attributeEntities = new LinkedHashSet<>();

}
