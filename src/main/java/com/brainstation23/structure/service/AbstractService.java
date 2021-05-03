package com.brainstation23.structure.service;

import com.brainstation23.structure.domain.request.BaseRequest;
import com.brainstation23.structure.domain.response.BaseResponse;
import com.brainstation23.structure.mapper.AbstractMapper;
import com.brainstation23.structure.repository.jpa.AbstractRepository;
import com.brainstation23.structure.repository.schema.AbstractEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.GenericTypeResolver;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractService<ENTITY extends AbstractEntity,REQUEST extends BaseRequest, RESPONSE extends BaseResponse> implements AbstractCRUDApi<ENTITY,REQUEST,RESPONSE> {
    private AbstractRepository<ENTITY> repository;
    private AbstractMapper<ENTITY, REQUEST,RESPONSE> mapper;
    private Class<ENTITY> entityClass;

    public AbstractService(final AbstractRepository<ENTITY> repository,
                                final AbstractMapper<ENTITY, REQUEST,RESPONSE> mapper) {
        this.repository = repository;
        this.mapper = mapper;

        final Class<?>[] params = GenericTypeResolver.resolveTypeArguments(getClass(), AbstractService.class);
        entityClass = (Class<ENTITY>) params[0];
    }

    @Override
    public RESPONSE save(REQUEST request) {
        final ENTITY entity = initEntity();
        if (entity == null) {
            log.error("Failed to save the entity of class '{}'", entityClass.getSimpleName());
            return null;
        }
        final ENTITY mappedEntity =  mapToEntity(entity,request);
        final ENTITY savedEntity = repository.save(mappedEntity);
        return mapper.convertToResponse(savedEntity);
    }

    @Override
    public RESPONSE update(Long id, REQUEST request) {
        ENTITY entity = findEntityById(id);
        if (entity == null) {
            log.error("Failed to update the entity of class '{}'", entityClass.getSimpleName());
            return null;
        }else {
            final ENTITY mappedEntity = mapToEntity(entity,request);
            entity = repository.save(mappedEntity);
        }
        return mapper.convertToResponse(entity);
    }

    @Override
    public RESPONSE getById(Long id) {
        final ENTITY entity = findEntityById(id);
        if (entity == null) {
            log.error("Failed to find entity with ID '{}'", id);
            return null;
        }
        return mapper.convertToResponse(entity);
    }

    @Override
    public List<RESPONSE> list() {
        final List<ENTITY> entities = repository.findAll();
        return getResponses(entities);
    }

    @Override
    public List<RESPONSE> modifiedSince(LocalDateTime time) {
//        final List<ENTITY> entities = repository.findAllLastModifiedAt(time);
//        return getResponses(entities);
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        final ENTITY entity = findEntityById(id);
        if (entity == null) {
            log.error("Failed to delete entity with ID '{}' as it does not exist", id);
            return false;
        }

        try {
            repository.delete(entity);
            return true;
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    protected abstract ENTITY mapToEntity(final ENTITY entity,final REQUEST request);

    // region Helper
    private ENTITY initEntity() {
        try {
            final ENTITY entity = entityClass.newInstance();
            return entity;
        } catch (final InstantiationException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private ENTITY findEntityById(Long id) {
        return repository.findById(id).orElse(null);
    }

    private List<RESPONSE> getResponses(List<ENTITY> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }
        return mapper.convertToResponseList(entities);
    }

}
