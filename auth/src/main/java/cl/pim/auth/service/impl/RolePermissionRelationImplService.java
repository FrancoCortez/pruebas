package cl.pim.auth.service.impl;

import cl.pim.auth.model.RolePermissionRelation;
import cl.pim.auth.repository.PermissionRepository;
import cl.pim.auth.repository.RolePermissionRelationRepository;
import cl.pim.auth.repository.RoleRepository;
import cl.pim.auth.service.RolePermissionRelationService;
import cl.pim.auth.shared.enumes.BasicStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RolePermissionRelationImplService implements RolePermissionRelationService {

    private final RolePermissionRelationRepository rolePermissionRelationRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public Mono<RolePermissionRelation> create(RolePermissionRelation item) {
        item.setId(UUID.randomUUID().toString());
        item.setStatus(BasicStatusEnum.ENABLED);
        item.setIsNew(true);
        return this.rolePermissionRelationRepository.save(item)
                .flatMap(rolePermissionRelation -> Mono.just(rolePermissionRelation)
                        .zipWith(this.roleRepository.findById(rolePermissionRelation.getRoleId()))
                        .zipWith(this.permissionRepository.findById(rolePermissionRelation.getPermissionId()))
                        .map(result -> result.getT1().getT1()
                                .setRole(result.getT1().getT2())
                                .setPermission(result.getT2())
                        )

                );
    }

    @Override
    public Flux<RolePermissionRelation> addPermissionToRole(Collection<RolePermissionRelation> item) {
        return this.deleteByRoleId(item.stream().findAny().get().getRoleId())
                .thenMany(this.rolePermissionRelationRepository.saveAll(
                        item.stream()
                                .filter(f -> f.getPermissionId() != null && !f.getPermissionId().equals(""))
                                .peek(p -> {
                                    p.setId(UUID.randomUUID().toString());
                                    p.setStatus(BasicStatusEnum.ENABLED);
                                    p.setIsNew(true);
                                }).collect(Collectors.toSet())
                ));
    }

    public Mono<Void> deleteByRoleId(String id) {
        return this.rolePermissionRelationRepository.deleteByRoleId(id);
    }

    public Mono<Void> deleteMassiveRoleIds(List<String> idsRoles) {
        return this.rolePermissionRelationRepository.deleteAllByRoleId(idsRoles);
    }

    @Override
    public Flux<RolePermissionRelation> findAll() {
        return this.rolePermissionRelationRepository.findAll()
                .flatMap(rolePermissionRelation -> Mono.just(rolePermissionRelation)
                        .zipWith(this.roleRepository.findById(rolePermissionRelation.getRoleId()))
                        .zipWith(this.permissionRepository.findById(rolePermissionRelation.getPermissionId()))
                        .map(result -> result.getT1().getT1()
                                .setRole(result.getT1().getT2())
                                .setPermission(result.getT2())
                        )
                );
    }
}
