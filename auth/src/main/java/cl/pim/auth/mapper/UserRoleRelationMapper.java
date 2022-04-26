package cl.pim.auth.mapper;

import cl.pim.auth.dto.userrolerelation.UserRoleRelationResourceDto;
import cl.pim.auth.model.User;
import cl.pim.auth.model.UserRoleRelation;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserRoleRelationMapper {

    UserRoleRelationResourceDto toResource(UserRoleRelation item);

    default Collection<UserRoleRelation> toModel(User item, Collection<String> roleId) {
        return roleId.stream()
                .map(role -> UserRoleRelation.builder()
                        .roleId(role)
                        .userId(item.getId())
                        .build())
                .collect(Collectors.toSet());
    }
}
