package clpim.pimcoreigdb.model;

import clpim.pimcoreigdb.shared.entity.BaseCodeAndNameEntity;
import clpim.pimcoreigdb.shared.enumes.BasicStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "family")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class FamilyEntity extends BaseCodeAndNameEntity {
    private BasicStatusEnum status;
    private Boolean visible;
    private String familyGroupEntityId;
    @Transient
    private FamilyGroupEntity familyGroupEntity;
}
