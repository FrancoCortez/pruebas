package cl.pim.auth.router;

import cl.pim.auth.dto.rolepermissionrelation.AddRolePermissionRelationResourceDto;
import cl.pim.auth.dto.rolepermissionrelation.NewRolePermissionRelationResourceDto;
import cl.pim.auth.dto.rolepermissionrelation.RolePermissionRelationResourceDto;
import cl.pim.auth.handler.RolePermissionRelationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController()
@RequestMapping("/role-permission-relation")
@RequiredArgsConstructor
public class RolePermissionRelationController {

    private final RolePermissionRelationHandler rolePermissionRelationHandler;

    @PostMapping
    public Mono<RolePermissionRelationResourceDto> create(@Valid @RequestBody NewRolePermissionRelationResourceDto item) {
        return this.rolePermissionRelationHandler.create(item);
    }

    @PostMapping("/add/permission-to-roles")
    public Flux<RolePermissionRelationResourceDto> addPermissionToRole(@Valid @RequestBody AddRolePermissionRelationResourceDto item) {
        return this.rolePermissionRelationHandler.addPermissionToRole(item);
    }

    @GetMapping()
    public Flux<RolePermissionRelationResourceDto> findAll() {
        return this.rolePermissionRelationHandler.findAll();
    }
}
