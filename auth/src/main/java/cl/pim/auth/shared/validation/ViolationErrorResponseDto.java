package cl.pim.auth.shared.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViolationErrorResponseDto {
    private String field;
    private String violation;

    @Override
    public String toString() {
        return "{" +
                "field='" + field + '\'' +
                ", violation='" + violation + '\'' +
                '}';
    }
}
