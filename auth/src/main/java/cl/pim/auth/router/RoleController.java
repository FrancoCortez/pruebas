package cl.pim.auth.router;

import cl.pim.auth.dto.role.NewRoleResourceDto;
import cl.pim.auth.dto.role.RoleResourceDto;
import cl.pim.auth.handler.RoleHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleHandler roleHandler;

    @PostMapping()
    public Mono<RoleResourceDto> create(@Valid @RequestBody NewRoleResourceDto item) {
        return this.roleHandler.create(item);
    }

    @GetMapping()
    public Flux<RoleResourceDto> findAll() {
        return this.roleHandler.findAll();
    }

}
