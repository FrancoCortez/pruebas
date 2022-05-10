package clpim.pimcoreigdb.mapper;

import clpim.pimcoreigdb.dto.attributegroup.AttributeGroupResourceDto;
import clpim.pimcoreigdb.dto.attributegroup.NewAttributeGroupResourceDto;
import clpim.pimcoreigdb.model.AttributeGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttributeGroupMapper {

    AttributeGroupResourceDto toResource(AttributeGroup item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "status", ignore = true)
    AttributeGroup toModel(NewAttributeGroupResourceDto item);
}
