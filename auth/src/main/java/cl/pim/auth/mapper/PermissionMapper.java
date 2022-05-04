package cl.pim.auth.mapper;

import cl.pim.auth.dto.permission.NewPermissionResourceDto;
import cl.pim.auth.dto.permission.PermissionResourceDto;
import cl.pim.auth.dto.permission.UpdatePermissionResourceDto;
import cl.pim.auth.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionResourceDto toResource(Permission item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "status", ignore = true)
    Permission toModel(NewPermissionResourceDto item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "code", ignore = true)
    Permission toUpdate(UpdatePermissionResourceDto dto, @MappingTarget Permission entity);
}
