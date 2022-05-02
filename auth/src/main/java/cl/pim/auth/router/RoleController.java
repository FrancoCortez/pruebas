package cl.pim.auth.router;

import cl.pim.auth.dto.role.NewRoleResourceDto;
import cl.pim.auth.dto.role.RoleResourceDto;
import cl.pim.auth.dto.role.UpdateRoleResourceDto;
import cl.pim.auth.handler.RoleHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleHandler roleHandler;

    @PostMapping()
    public Mono<RoleResourceDto> create(@Valid @RequestBody NewRoleResourceDto item) {
        return this.roleHandler.create(item);
    }

    @PutMapping("/{id}")
    public Mono<RoleResourceDto> update(@Valid @RequestBody UpdateRoleResourceDto item,
                                        @PathVariable String id) {
        return this.roleHandler.update(id, item);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable final String id) {
        return this.roleHandler.deleteById(id);
    }

    @PostMapping("massive/deleted")
    public Mono<ResponseEntity<Void>> deleteMassiveByIds(@Valid @RequestBody List<String> ids) {
        return this.roleHandler.deleteMassiveByIds(ids);
    }

    @GetMapping()
    public Flux<RoleResourceDto> findAll() {
        return this.roleHandler.findAll();
    }

}
