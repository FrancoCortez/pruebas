package clpim.pimcoreigdb.dto.family;


import clpim.pimcoreigdb.shared.enumes.BasicStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Optional;


@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatchFamilyResourceDto {
    private Optional<String> name;
    private Optional<Boolean> visible;
    private Optional<BasicStatusEnum> status;
    private Optional<String> familyGroupId;
}
