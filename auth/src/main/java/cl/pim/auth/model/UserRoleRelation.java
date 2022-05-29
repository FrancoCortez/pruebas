package cl.pim.auth.model;


import cl.pim.auth.shared.entity.BaseIdEntity;
import cl.pim.auth.shared.enumes.BasicStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;


@Table(value = "user_role_relation")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserRoleRelation extends BaseIdEntity {
    private BasicStatusEnum status;
    private String roleId;
    private String userId;
}
