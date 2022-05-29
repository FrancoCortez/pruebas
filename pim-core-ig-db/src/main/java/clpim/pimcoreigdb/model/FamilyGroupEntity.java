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

import java.util.List;

@Table(value = "family_group")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class FamilyGroupEntity extends BaseCodeAndNameEntity {
    private BasicStatusEnum status;
    private Boolean visible;
    @Transient
    private List<FamilyEntity> familyEntities;
}
