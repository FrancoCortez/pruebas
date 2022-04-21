package cl.pim.pimcoredb.pim;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "media")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(indexes = {
        @Index(columnList = "status")
})
public class MediaEntity extends BaseIdEntity {

    @Column(name = "status", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private MediaStatusEnum status;

    @Column(name = "code_color_relation", length = 30)
    private String codeColorRelation;

    @Column(name = "type", length = 20)
    private String type;


    @Column(name = "original_url", length = 200, nullable = false)
    private String originalUrl;

    @Column(name = "ecommerce_url", length = 200)
    private String ecommerceUrl;

    @Column(name = "visible_url", length = 200)
    private String visibleUrl;

    @Column(name = "original_name", length = 50, nullable = false)
    private String originalName;

    @Column(name = "ecommerce_name", length = 50)
    private String ecommerceName;

    @Column(name = "visible_name", length = 50)
    private String visibleName;

    @OneToMany(mappedBy = "mediaEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<MediaVariantRelationEntity> mediaVariantRelationEntities = new LinkedHashSet<>();

}
