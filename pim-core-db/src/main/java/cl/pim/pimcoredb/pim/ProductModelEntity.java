package cl.pim.pimcoredb.pim;

import cl.pim.pimcoredb.base.BaseIdEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity(name = "product_model")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class ProductModelEntity extends BaseIdEntity {

}
