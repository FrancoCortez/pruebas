package cl.pim.auth.dto.permission;

import cl.pim.auth.shared.enumes.BasicStatusEnum;
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
public class UpdatePermissionResourceDto {

    private String name;
    private String description;
    private BasicStatusEnum status;
}
