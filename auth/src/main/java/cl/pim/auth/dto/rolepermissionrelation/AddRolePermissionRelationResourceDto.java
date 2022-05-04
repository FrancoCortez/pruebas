package cl.pim.auth.dto.rolepermissionrelation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddRolePermissionRelationResourceDto {
    private String idRole;
    private List<String> idPermissions;
}
