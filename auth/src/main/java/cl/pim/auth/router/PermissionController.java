package cl.pim.auth.router;

import cl.pim.auth.dto.permission.NewPermissionResourceDto;
import cl.pim.auth.dto.permission.PermissionResourceDto;
import cl.pim.auth.dto.permission.UpdatePermissionResourceDto;
import cl.pim.auth.handler.PermissionHandler;
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
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionHandler permissionHandler;

    @PostMapping()
    public Mono<PermissionResourceDto> create(@Valid @RequestBody NewPermissionResourceDto item) {
        return this.permissionHandler.create(item);
    }

    @PutMapping("/{id}")
    public Mono<PermissionResourceDto> update(@Valid @RequestBody UpdatePermissionResourceDto item,
                                              @PathVariable String id) {
        return this.permissionHandler.update(id, item);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id) {
        return this.permissionHandler.deleteById(id);
    }

    @PostMapping("massive/deleted")
    public Mono<ResponseEntity<Void>> deleteMassiveByIds(@Valid @RequestBody List<String> ids) {
        return this.permissionHandler.deleteMassiveByIds(ids);
    }

    @GetMapping()
    public Flux<PermissionResourceDto> findAll() {
        return this.permissionHandler.findAll();
    }

    @GetMapping("/{id}")
    public Mono<PermissionResourceDto> findById(@PathVariable String id) {
        return this.permissionHandler.findById(id);
    }

    @GetMapping("/find-by-role/{id}")
    public Flux<PermissionResourceDto> findByRoleId(@PathVariable String id) {
        return this.permissionHandler.findByRoleId(id);
    }

    @GetMapping("/find-by-not-role/{id}")
    public Flux<PermissionResourceDto> findPermissionNotByRoleId(@PathVariable String id) {
        return this.permissionHandler.findPermissionNotByRoleId(id);
    }
}
