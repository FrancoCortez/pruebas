package clpim.pimcoreigdb.dto.familygroup;

import clpim.pimcoreigdb.shared.enumes.BasicStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

@Data
@Accessors(chain = true)
public class PatchFamilyGroupResourceDto {

    private Optional<@NotBlank @Size(max = 100) String> name;
    private Optional<Boolean> visible;
    private Optional<BasicStatusEnum> status;
}
