package cl.pim.auth.shared.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class BaseCodeAndNameEntity extends BaseIdEntity implements Serializable {
    private String name;
    private String code;
}
