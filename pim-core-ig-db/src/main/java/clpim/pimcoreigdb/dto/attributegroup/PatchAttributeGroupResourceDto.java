package clpim.pimcoreigdb.dto.attributegroup;

import clpim.pimcoreigdb.shared.enumes.BasicStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatchAttributeGroupResourceDto {

    private Optional<@NotBlank @Size(max = 100) String> name;
    private Optional<Boolean> visible;
    private Optional<BasicStatusEnum> status;
    private Optional<Integer> position;
}
