package com.brainstation23.structure.domain.response;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Data
public class BaseResponse {
    private Long id;
    protected String createdBy;
    protected LocalDateTime createdAt;
    protected String lastModifiedBy;
    protected LocalDateTime lastModifiedAt;
}
