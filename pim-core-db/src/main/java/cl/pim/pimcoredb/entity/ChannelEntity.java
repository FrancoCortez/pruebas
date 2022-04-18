package cl.pim.pimcoredb.entity;

import cl.pim.pimcoredb.base.BaseCodeAndNameEntity;
import cl.pim.pimcoredb.enumes.BasicStatusEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "channel")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(indexes = {
        @Index(columnList = "status")
})
public class ChannelEntity extends BaseCodeAndNameEntity {

    @Column(name = "status", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private BasicStatusEnum status;

    @OneToMany(mappedBy = "channelEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<StoreEntity> storeEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "channelEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<ChannelProductRelationEntity> channelProductRelationEntities = new LinkedHashSet<>();

}
