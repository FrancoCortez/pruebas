package clpim.pimcoreigdb.mapper;

import clpim.pimcoreigdb.dto.family.FamilyResourceDto;
import clpim.pimcoreigdb.dto.familygroup.FamilyGroupResourceDto;
import clpim.pimcoreigdb.dto.familygroup.NewFamilyGroupResourceDto;
import clpim.pimcoreigdb.dto.familygroup.PatchFamilyGroupResourceDto;
import clpim.pimcoreigdb.dto.familygroup.UpdateFamilyGroupResourceDto;
import clpim.pimcoreigdb.model.FamilyGroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FamilyMapper.class})
public interface FamilyGroupMapper {
    FamilyMapper familyMapper = Mappers.getMapper(FamilyMapper.class);

    /**
     * @param f
     * @return
     */
    default FamilyGroupResourceDto toResource(FamilyGroupEntity f) {
        if (f == null) {
            return null;
        }
        List<FamilyResourceDto> familyResourceDtos = new java.util.ArrayList<>(List.of());
        if (f.getFamilyEntities().size() > 0) {
            f.getFamilyEntities().forEach(ff -> familyResourceDtos.add(familyMapper.toResource(ff)));
        }
        return FamilyGroupResourceDto.builder()
                .id(f.getId())
                .code(f.getCode())
                .name(f.getName())
                .status(f.getStatus())
                .createdAt(f.getCreatedAt())
                .updatedAt(f.getUpdatedAt())
                .visible(f.getVisible())
                .families(familyResourceDtos)
                .build();
    }

    /**
     * @param newFamilyGroupResourceDto
     * @return
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "status", ignore = true)
    FamilyGroupEntity toModel(NewFamilyGroupResourceDto newFamilyGroupResourceDto);

    /**
     * @param updateFamilyGroupResourceDto
     * @param familyGroupEntity
     * @return
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "code", ignore = true)
    FamilyGroupEntity toUpdate(UpdateFamilyGroupResourceDto updateFamilyGroupResourceDto, @MappingTarget FamilyGroupEntity familyGroupEntity);

    /**
     * @param patchResource
     * @param item
     * @return
     */
    default FamilyGroupEntity patch(PatchFamilyGroupResourceDto patchResource, FamilyGroupEntity item) {
        if (patchResource.getName().isPresent()) {
            item.setName(patchResource.getName().orElse(null));
        }
        if (patchResource.getVisible().isPresent()) {
            item.setVisible(patchResource.getVisible().orElse(null));
        }
        if (patchResource.getStatus().isPresent()) {
            item.setStatus(patchResource.getStatus().orElse(null));
        }
        return item;
    }

    /**
     * @param item
     * @return
     */
    default String extractGroupId(FamilyGroupEntity item) {
        if (item == null) {
            return null;
        }
        return item.getId();
    }
}
