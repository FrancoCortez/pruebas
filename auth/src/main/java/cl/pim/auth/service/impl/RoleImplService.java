package cl.pim.auth.service.impl;

import cl.pim.auth.model.Role;
import cl.pim.auth.repository.RoleRepository;
import cl.pim.auth.service.RoleService;
import cl.pim.auth.shared.enumes.BasicStatusEnum;
import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleImplService implements RoleService {

    private final RoleRepository roleRepository;

    public Mono<Role> create(Role item) {
        item.setId(UUID.randomUUID().toString());
        item.setStatus(BasicStatusEnum.ENABLED);
        item.setIsNew(true);
        item.setCode(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, item.getName().toUpperCase().replaceAll(" ", "_")));
        return this.roleRepository.save(item);
    }

    public Flux<Role> findAll() {
        return this.roleRepository.findAll();
    }
}
