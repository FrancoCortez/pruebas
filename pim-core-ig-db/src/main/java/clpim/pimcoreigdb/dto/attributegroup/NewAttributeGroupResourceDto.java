package clpim.pimcoreigdb.dto.attributegroup;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewAttributeGroupResourceDto {

    @NotBlank
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    private Boolean visible;
    private Integer position;
}
