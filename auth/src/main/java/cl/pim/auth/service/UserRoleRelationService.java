package cl.pim.auth.service;

import cl.pim.auth.model.UserRoleRelation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

public interface UserRoleRelationService {

    Mono<UserRoleRelation> create(UserRoleRelation item);

    Flux<UserRoleRelation> createMassive(Collection<UserRoleRelation> item);

    Mono<Void> deleteByRoleId(String id);

    Mono<Void> deleteMassiveRoleIds(List<String> id);

    Flux<UserRoleRelation> findAll();
}
