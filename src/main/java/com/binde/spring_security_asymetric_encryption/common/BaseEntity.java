package com.binde.spring_security_asymetric_encryption.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.UUID;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = UUID)
    private String id;
    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false,nullable = false)
    private LocalDateTime createdDated;
    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE",insertable = false)
    private LocalDateTime lastModifiedDate;
    @CreatedBy
    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;
    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY", insertable = false)
    private String lastModifiedBy;
}
