package clpim.pimcoreigdb.mapper;

import clpim.pimcoreigdb.dto.family.FamilyResourceDto;
import clpim.pimcoreigdb.dto.family.NewFamilyResourceDto;
import clpim.pimcoreigdb.dto.family.PatchFamilyResourceDto;
import clpim.pimcoreigdb.dto.family.UpdateFamilyResourceDto;
import clpim.pimcoreigdb.model.FamilyEntity;
import clpim.pimcoreigdb.model.FamilyGroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface FamilyMapper {

    /**
     * @param familyEntity
     * @return
     */
    FamilyResourceDto toResource(FamilyEntity familyEntity);

    /**
     * @param item
     * @return
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "status", ignore = true)
    FamilyEntity toModel(NewFamilyResourceDto item);

    /**
     * @param item
     * @param target
     * @return
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "code", ignore = true)
    FamilyEntity toUpdate(UpdateFamilyResourceDto item, @MappingTarget FamilyEntity target);

    /**
     * @param patch
     * @param item
     * @return
     */
    default FamilyEntity patch(PatchFamilyResourceDto patch, FamilyEntity item) {
        if (patch.getName().isPresent()) {
            item.setName(patch.getName().orElse(null));
        }
        if (patch.getVisible().isPresent()) {
            item.setVisible(patch.getVisible().orElse(null));
        }
        if (patch.getStatus().isPresent()) {
            item.setStatus(patch.getStatus().orElse(null));
        }
        if (patch.getFamilyGroupId().isPresent()) {
            item.setFamilyGroupEntity(new FamilyGroupEntity().setId(patch.getFamilyGroupId().orElse(null)));
        }
        return item;
    }
}
