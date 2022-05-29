package cl.pim.auth.mapper;

import cl.pim.auth.dto.userrolerelation.NewUserRoleRelationResourceDto;
import cl.pim.auth.dto.userrolerelation.UserRoleRelationResourceDto;
import cl.pim.auth.model.User;
import cl.pim.auth.model.UserRoleRelation;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserRoleRelationMapper {

    UserRoleRelationResourceDto toResource(UserRoleRelation item);

    default UserRoleRelation toModel(NewUserRoleRelationResourceDto item) {
        return UserRoleRelation.builder()
                .userId(item.getUserId())
                .roleId(item.getRoleId())
                .build();
    }

    default Collection<UserRoleRelation> toModelList(User item, Collection<String> roleId) {
        return roleId.stream()
                .map(role -> UserRoleRelation.builder()
                        .roleId(role)
                        .userId(item.getId())
                        .build())
                .collect(Collectors.toSet());
    }
}
