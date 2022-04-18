package clpim.pimcoreigdb.service;


import clpim.pimcoreigdb.model.FamilyEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FamilyService {

    Mono<FamilyEntity> create(FamilyEntity item);

    Mono<FamilyEntity> update(FamilyEntity item);

    Flux<FamilyEntity> findAll();

    Mono<FamilyEntity> findById(String id);
}
