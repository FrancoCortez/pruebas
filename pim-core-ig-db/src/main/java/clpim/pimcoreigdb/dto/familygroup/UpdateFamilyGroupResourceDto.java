package clpim.pimcoreigdb.dto.familygroup;

import clpim.pimcoreigdb.shared.enumes.BasicStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class UpdateFamilyGroupResourceDto {
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String name;
    private Boolean visible;
    private BasicStatusEnum status;
}
