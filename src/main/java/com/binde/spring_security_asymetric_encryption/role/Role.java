package com.binde.spring_security_asymetric_encryption.role;

import com.binde.spring_security_asymetric_encryption.common.BaseEntity;
import com.binde.spring_security_asymetric_encryption.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "ROLES")
@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseEntity {
    @Column(name = "NAME", nullable = false)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

}
