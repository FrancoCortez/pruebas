package cl.pim.auth.mapper;

import cl.pim.auth.dto.auth.LoginResponseResourceDto;
import cl.pim.auth.dto.auth.NewUserResourceDto;
import cl.pim.auth.dto.auth.UserResourceDto;
import cl.pim.auth.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResourceDto toResource(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toModel(NewUserResourceDto item);

    LoginResponseResourceDto toLogin(User user);
}
