package cl.pim.auth.dto.userrolerelation;


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
public class NewUserRoleRelationResourceDto {
    private String userId;
    private String roleId;
}
