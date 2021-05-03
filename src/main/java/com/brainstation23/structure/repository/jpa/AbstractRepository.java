package com.brainstation23.structure.repository.jpa;

import com.brainstation23.structure.repository.schema.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@NoRepositoryBean
public interface AbstractRepository <ENTITY extends AbstractEntity> extends JpaRepository<ENTITY, Long> {
//    List<ENTITY> findAllLastModifiedAt(LocalDateTime time);
}
