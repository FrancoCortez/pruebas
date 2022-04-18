package clpim.pimcoreigdb.dto.family;


import clpim.pimcoreigdb.shared.enumes.BasicStatusEnum;
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
public class UpdateFamilyResourceDto {
    private String name;
    private Boolean visible;
    private BasicStatusEnum status;
    private String familyGroupId;
}
