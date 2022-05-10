package clpim.pimcoreigdb.model;

import clpim.pimcoreigdb.shared.enumes.BasicStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table(value = "family_group")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class FamilyGroupEntity implements Persistable<String> {

    @Id
    private String id;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private String name;
    private String code;
    private BasicStatusEnum status;
    private Boolean visible;
    @Transient
    private List<FamilyEntity> familyEntities;
    @Transient
    private Boolean isNew;

    @Override
    public boolean isNew() {
        return this.isNew || id == null;
    }
}
