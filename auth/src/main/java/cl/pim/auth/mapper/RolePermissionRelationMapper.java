package cl.pim.auth.mapper;

import cl.pim.auth.dto.rolepermissionrelation.AddRolePermissionRelationResourceDto;
import cl.pim.auth.dto.rolepermissionrelation.NewRolePermissionRelationResourceDto;
import cl.pim.auth.dto.rolepermissionrelation.RolePermissionRelationResourceDto;
import cl.pim.auth.model.RolePermissionRelation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, PermissionMapper.class})
public interface RolePermissionRelationMapper {
    RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);
    PermissionMapper permissionMapper = Mappers.getMapper(PermissionMapper.class);

    default RolePermissionRelationResourceDto toResource(RolePermissionRelation item) {
        return RolePermissionRelationResourceDto.builder()
                .id(item.getId())
                .permission(this.permissionMapper.toResource(item.getPermission()))
                .role(this.roleMapper.toResource(item.getRole()))
                .createdAt(item.getCreatedAt())
                .status(item.getStatus())
                .updatedAt(item.getUpdatedAt())
                .build();
    }

    default RolePermissionRelation toModel(NewRolePermissionRelationResourceDto item) {
        return RolePermissionRelation.builder()
                .roleId(item.getIdRole())
                .permissionId(item.getIdPermission())
                .build();
    }

    default Collection<RolePermissionRelation> addPermissionToRole(AddRolePermissionRelationResourceDto item) {
        if (item.getIdPermissions().size() > 0) {
            return item.getIdPermissions().stream()
                    .map(m -> RolePermissionRelation.builder()
                            .roleId(item.getIdRole())
                            .permissionId(m)
                            .build()
                    ).collect(Collectors.toList());
        } else {
            return List.of(RolePermissionRelation.builder().roleId(item.getIdRole()).build());
        }

    }
}
