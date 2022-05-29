package cl.pim.auth.dto.userrolerelation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewUserRoleRelationResourceDto {
    @NotNull
    @NotBlank
    @NotEmpty
    private String userId;

    @NotNull
    @NotBlank
    @NotEmpty
    private String roleId;
}
