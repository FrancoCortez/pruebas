package clpim.pimcoreigdb.dto.family;


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
public class NewFamilyResourceDto {
    private String name;
    private Boolean visible;
    private String familyGroupId;
}
