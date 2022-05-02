package cl.pim.auth.model;

import cl.pim.auth.shared.enumes.BasicStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Table(value = "role")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class Role implements Persistable<String> {
    @Id
    private String id;
    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdAt;

    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updatedAt;

    private String name;

    private String code;

    private BasicStatusEnum status;

    private String description;
    @Transient
    @JsonIgnore
    private Boolean isNew = false;

    @Override
    public boolean isNew() {
        return this.isNew || id == null;
    }
}
