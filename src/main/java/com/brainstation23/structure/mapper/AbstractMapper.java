package com.brainstation23.structure.mapper;

import com.brainstation23.structure.domain.response.BaseResponse;
import com.brainstation23.structure.repository.schema.AbstractEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
public abstract class AbstractMapper<ENTITY extends AbstractEntity, REQUEST, RESPONSE extends BaseResponse> {
    /**
     * Converts forwarded entity into RESPONSE.
     *
     * @param entity Entity to convert.
     * @return Converted entity as a RESPONSE.
     */
    public abstract RESPONSE convertToResponse(final ENTITY entity);

    /**
     * Convert forwarded entity to forwarded dto.
     *
     * @param entity   Entity to convert.
     * @param response Response in which we are converting.
     */
    public void convert(final ENTITY entity, final RESPONSE response) {
        response.setId(entity.getId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setLastModifiedAt(entity.getLastModifiedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setLastModifiedBy(entity.getLastModifiedBy());
    }


    /**
     * Convert forwarded entities to list of DTOs.
     *
     * @param entities Entities to convert.
     * @return List of converted Responses.
     */
    public List<RESPONSE> convertToResponseList(final List<ENTITY> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }

        return entities.stream()
                .sequential()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
}
