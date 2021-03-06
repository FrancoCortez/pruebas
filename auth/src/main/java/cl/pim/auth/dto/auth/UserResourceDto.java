package cl.pim.auth.dto.auth;

import cl.pim.auth.dto.role.RoleResourceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResourceDto {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String username;
    private Boolean enabled;
    private LocalDateTime lastPasswordUpdate;
    private List<RoleResourceDto> roles;
}
