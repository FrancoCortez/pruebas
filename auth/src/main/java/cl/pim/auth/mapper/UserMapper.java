package cl.pim.auth.mapper;

import cl.pim.auth.dto.auth.LoginResponseResourceDto;
import cl.pim.auth.dto.auth.NewUserResourceDto;
import cl.pim.auth.dto.auth.UserResourceDto;
import cl.pim.auth.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

    default UserResourceDto toResource(User user) {
        return UserResourceDto.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .enabled(user.isEnabled())
                .lastPasswordUpdate(user.getLastPasswordUpdate())
                // .password(user.getPassword())
                .username(user.getUsername())
                .updatedAt(user.getUpdatedAt())
                .roles(user.getRoles().stream().map(this.roleMapper::toResource).collect(Collectors.toList()))
                .build();
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toModel(NewUserResourceDto item);

    LoginResponseResourceDto toLogin(User user);
}
