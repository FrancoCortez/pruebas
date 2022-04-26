package cl.pim.auth.service;

import cl.pim.auth.model.UserRoleRelation;
import reactor.core.publisher.Flux;

import java.util.Collection;

public interface UserRoleRelationService {

    Flux<UserRoleRelation> create(Collection<UserRoleRelation> item);
}
