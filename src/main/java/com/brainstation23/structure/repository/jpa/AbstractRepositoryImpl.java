package com.brainstation23.structure.repository.jpa;

import com.brainstation23.structure.repository.schema.AbstractEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
public class AbstractRepositoryImpl<ENTITY extends AbstractEntity> extends SimpleJpaRepository<ENTITY, Long> implements AbstractRepository<ENTITY>, JpaSpecificationExecutor<ENTITY> {

    public AbstractRepositoryImpl(final JpaEntityInformation<ENTITY, Integer> entityInformation,
                                     final EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
    @Override
    public List<ENTITY> findAllModifiedSince(final LocalDateTime time) {
        return super.findAll(modifiedSince(time));
    }

    private Specification<ENTITY> modifiedSince(final LocalDateTime time) {
        return ((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("modified"), time));
    }
}
