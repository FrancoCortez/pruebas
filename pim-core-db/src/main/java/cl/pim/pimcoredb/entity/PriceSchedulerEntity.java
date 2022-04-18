package cl.pim.pimcoredb.entity;

import cl.pim.pimcoredb.base.BaseIdEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity(name = "price_scheduler")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class PriceSchedulerEntity extends BaseIdEntity {

}
