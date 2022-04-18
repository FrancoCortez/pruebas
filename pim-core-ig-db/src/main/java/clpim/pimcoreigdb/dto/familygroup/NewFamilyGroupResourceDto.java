package clpim.pimcoreigdb.dto.familygroup;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class NewFamilyGroupResourceDto {

    @NotBlank
    @NotNull
    @Size(max = 50)
    private String name;
    private Boolean visible;
}
