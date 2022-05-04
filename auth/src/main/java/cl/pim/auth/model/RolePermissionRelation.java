package cl.pim.auth.model;

import cl.pim.auth.shared.enumes.BasicStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(value = "role_permission_relation")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RolePermissionRelation implements Persistable<String> {

    @Id
    private String id;
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private BasicStatusEnum status;
    @Transient
    private Boolean isNew;
    private String roleId;
    private String permissionId;

    @Transient
    private Role role;
    @Transient
    private Permission permission;

    @Override
    public boolean isNew() {
        return this.isNew || id == null;
    }

}
