package com.brainstation23.structure.service;

import com.brainstation23.structure.domain.request.BaseRequest;
import com.brainstation23.structure.domain.response.BaseResponse;
import com.brainstation23.structure.repository.schema.AbstractEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
public interface AbstractCRUDApi<ENTITY extends AbstractEntity, REQUEST extends BaseRequest, RESPONSE extends BaseResponse> {
    /**
     * Saves forwarded REQUEST.
     *
     * @param request REQUEST to save.
     * @return Returns saved entity as a RESPONSE.
     */
    RESPONSE save(REQUEST request);

    /**
     * Update REQUEST.
     *
     * @param request REQUEST to update.
     * @return Returns update entity as a RESPONSE.
     */
    RESPONSE update(Long id,REQUEST request);

    /**
     * Finds RESPONSE by forwarded ID.
     *
     * @param id ID used for searching.
     * @return Returns found RESPONSE otherwise null.
     */
    RESPONSE getById(Long id);

    /**
     * Lists all found RESPONSES.
     *
     * @return Returns a list of RESPONSES.
     */
    List<RESPONSE> list();

    /**
     * Finds all entities modified since forwarded timestamp.
     *
     * @param time Timestamp.
     * @return Returns a list of RESPONSES.
     */
    List<RESPONSE> modifiedSince(LocalDateTime time);

    /**
     * Deletes the entity by forwarded ID.
     *
     * @param id ID of the entity to delete.
     * @return Returns true if entity is deleted.
     */
    Boolean delete(Long id);
}
