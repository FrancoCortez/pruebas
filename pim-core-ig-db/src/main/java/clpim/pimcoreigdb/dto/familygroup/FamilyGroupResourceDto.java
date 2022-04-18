package clpim.pimcoreigdb.dto.familygroup;


import clpim.pimcoreigdb.dto.family.FamilyResourceDto;
import clpim.pimcoreigdb.shared.enumes.BasicStatusEnum;
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
public class FamilyGroupResourceDto {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private String code;
    private BasicStatusEnum status;
    private Boolean visible;
    private List<FamilyResourceDto> families;
}
