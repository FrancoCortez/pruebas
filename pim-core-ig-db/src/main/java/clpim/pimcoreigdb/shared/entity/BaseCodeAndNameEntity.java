package clpim.pimcoreigdb.shared.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class BaseCodeAndNameEntity extends BaseIdEntity {
    private String name;
    private String code;
}
