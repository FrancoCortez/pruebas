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
import javax.persistence.Table;

@Entity(name = "category_family_relation")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(indexes = {
        @Index(columnList = "status")
})
public class CategoryFamilyRelationEntity extends BaseIdEntity {

    @Column(name = "status", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private BasicStatusEnum status;
    @ManyToOne
    @JoinColumn(name = "family_id", nullable = false)
    private FamilyEntity familyEntity;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity categoryEntity;

}
