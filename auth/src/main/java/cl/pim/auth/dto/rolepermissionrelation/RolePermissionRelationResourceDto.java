package cl.pim.auth.dto.rolepermissionrelation;

import cl.pim.auth.dto.permission.PermissionResourceDto;
import cl.pim.auth.dto.role.RoleResourceDto;
import cl.pim.auth.shared.enumes.BasicStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionRelationResourceDto {

    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private RoleResourceDto role;
    private PermissionResourceDto permission;
    private BasicStatusEnum status;
}
