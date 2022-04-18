package clpim.pimcoreigdb.service;

import clpim.pimcoreigdb.model.FamilyGroupEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface FamilyGroupService {
    Flux<FamilyGroupEntity> findAll();

    Mono<FamilyGroupEntity> create(FamilyGroupEntity familyGroupEntity);

    Mono<FamilyGroupEntity> findById(String id);

    Mono<FamilyGroupEntity> update(FamilyGroupEntity familyGroupEntity);
}
