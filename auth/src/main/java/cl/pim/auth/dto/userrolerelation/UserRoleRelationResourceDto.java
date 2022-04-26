package cl.pim.auth.dto.userrolerelation;


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
public class UserRoleRelationResourceDto {

    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BasicStatusEnum status;
    private String roleId;
    private String userId;

}
