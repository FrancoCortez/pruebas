package cl.pim.auth.dto.role;

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
public class RoleResourceDto {

    private String id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String name;

    private String code;

    private BasicStatusEnum status;

    private String description;
}
