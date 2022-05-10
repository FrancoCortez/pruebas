package clpim.pimcoreigdb.service;

import clpim.pimcoreigdb.model.AttributeGroup;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AttributeGroupService {

    Mono<AttributeGroup> create(AttributeGroup item);

    Flux<AttributeGroup> findAll();
}
