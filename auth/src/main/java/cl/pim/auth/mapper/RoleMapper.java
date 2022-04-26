package cl.pim.auth.mapper;

import cl.pim.auth.dto.role.NewRoleResourceDto;
import cl.pim.auth.dto.role.RoleResourceDto;
import cl.pim.auth.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResourceDto toResource(Role role);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "status", ignore = true)
    Role toModel(NewRoleResourceDto item);
}
