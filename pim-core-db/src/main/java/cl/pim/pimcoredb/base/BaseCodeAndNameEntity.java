package cl.pim.pimcoredb.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class BaseCodeAndNameEntity extends BaseIdEntity {

    @Column(name = "name", unique = true, length = 50, nullable = false)
    private String name;

    @Column(name = "code", unique = true, length = 50, nullable = false)
    private String code;

}
