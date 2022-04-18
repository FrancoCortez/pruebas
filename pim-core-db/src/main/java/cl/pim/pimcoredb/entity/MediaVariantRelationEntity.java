package cl.pim.pimcoredb.entity;

import cl.pim.pimcoredb.base.BaseIdEntity;
import cl.pim.pimcoredb.enumes.MediaStatusEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "media_variant_relation")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(indexes = {
        @Index(columnList = "status")
})
public class MediaVariantRelationEntity extends BaseIdEntity {

    @Column(name = "visible", columnDefinition = "boolean default true")
    private Boolean visible;

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "status", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private MediaStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    private MediaEntity mediaEntity;

    @ManyToOne
    @JoinColumn(name = "variant_id", nullable = false)
    private VariantEntity variantEntity;

}
