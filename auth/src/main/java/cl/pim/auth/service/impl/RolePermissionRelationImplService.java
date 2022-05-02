package cl.pim.auth.service.impl;

import cl.pim.auth.repository.RolePermissionRelationRepository;
import cl.pim.auth.service.RolePermissionRelationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RolePermissionRelationImplService implements RolePermissionRelationService {

    private final RolePermissionRelationRepository rolePermissionRelationRepository;

//    public Flux<UserRoleRelation> create(Collection<UserRoleRelation> item) {
//        return this.userRoleRelationRepository.saveAll(item.stream().peek(m -> {
//            m.setId(UUID.randomUUID().toString());
//            m.setStatus(BasicStatusEnum.ENABLED);
//            m.setIsNew(true);
//        }).collect(Collectors.toSet()));
//    }

    public Mono<Void> deleteByRoleId(String id) {
        return this.rolePermissionRelationRepository.deleteByRoleId(id);
    }

    public Mono<Void> deleteMassiveRoleIds(List<String> idsRoles) {
        return this.rolePermissionRelationRepository.deleteAllByRoleId(idsRoles);
    }
}
