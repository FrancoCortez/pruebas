package clpim.pimcoreigdb.service.impl;

import clpim.pimcoreigdb.model.AttributeGroup;
import clpim.pimcoreigdb.repository.AttributeGroupRepository;
import clpim.pimcoreigdb.service.AttributeGroupService;
import clpim.pimcoreigdb.shared.enumes.BasicStatusEnum;
import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class AttributeGroupImplService implements AttributeGroupService {
    private final AttributeGroupRepository attributeGroupRepository;

    public Mono<AttributeGroup> create(AttributeGroup item) {
        item.setCode(CaseFormat
                .UPPER_UNDERSCORE
                .to(CaseFormat.LOWER_CAMEL, item.getName()
                        .toUpperCase()
                        .replaceAll(" ", "_")));
        item.setStatus(BasicStatusEnum.ENABLED);
        item.setId(UUID.randomUUID().toString());
        item.setIsNew(true);
        return this.attributeGroupRepository.save(item);
    }

    public Flux<AttributeGroup> findAll() {
        return this.attributeGroupRepository.findAll();
    }
}
