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
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "role_permission_relation")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RolePermissionRelation extends BaseIdEntity {
    private BasicStatusEnum status;
    private String roleId;
    private String permissionId;

    @Transient
    private Role role;

    @Transient
    private Permission permission;

}
