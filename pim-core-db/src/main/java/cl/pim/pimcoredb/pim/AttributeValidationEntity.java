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

@Entity(name = "attribute_validation")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(indexes = {
        @Index(columnList = "status")
})
public class AttributeValidationEntity extends BaseIdEntity {

    @Column(name = "status", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private BasicStatusEnum status;
    @ManyToOne
    @JoinColumn(name = "validation_definition_id", nullable = false)
    private ValidationDefinitionEntity validationDefinitionEntity;

    @ManyToOne
    @JoinColumn(name = "attribute_id", nullable = false)
    private AttributeEntity attributeEntity;

    @ManyToOne
    @JoinColumn(name = "family_id", nullable = false)
    private FamilyEntity familyEntity;

}
