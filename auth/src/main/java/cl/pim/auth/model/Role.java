package cl.pim.auth.model;

import cl.pim.auth.shared.entity.BaseCodeAndNameEntity;
import cl.pim.auth.shared.enumes.BasicStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table(value = "role")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class Role extends BaseCodeAndNameEntity implements Serializable {
    private BasicStatusEnum status;
    private String description;
}
