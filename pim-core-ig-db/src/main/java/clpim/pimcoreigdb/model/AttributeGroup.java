package clpim.pimcoreigdb.model;

import clpim.pimcoreigdb.shared.entity.BaseCodeAndNameEntity;
import clpim.pimcoreigdb.shared.enumes.BasicStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "attribute_group")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class AttributeGroup extends BaseCodeAndNameEntity {
    private Boolean visible;
    private Integer position;
    private BasicStatusEnum status;
}
