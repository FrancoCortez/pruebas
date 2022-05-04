package cl.pim.auth.dto.rolepermissionrelation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewRolePermissionRelationResourceDto {
    private String idRole;
    private String idPermission;
}
