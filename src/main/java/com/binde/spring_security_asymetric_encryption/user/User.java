package com.binde.spring_security_asymetric_encryption.user;

import com.binde.spring_security_asymetric_encryption.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "USERS")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = UUID)
        private String id;

        @Column(name = "FIRST_NAME", nullable = false)
        private String firstName;

        @Column(name = "LAST_NAME", nullable = false)
        private String lastName;

        @Column(name = "EMAIL", unique = true, nullable = false)
        private String email;

        @Column(name = "PHONE_NUMBER", unique = true, nullable = false)
        private String phoneNumber;

        @Column(name = "PASSWORD", nullable = false)
        private String password;

        @Column(name = "IS_ENABLED")
        private boolean enabled;

        @Column(name = "IS_ACCOUNT_LOCKED")
        private boolean locked;

        @Column(name = "IS_CREDENTIALS_EXPIRED")
        private boolean credentialsExpired;

        @Column(name = "IS_EMAIL_VERIFIED")
        private boolean emailVerified;

        @Column(name = "IS_PHONE_VERIFIED")
        private boolean phoneNumberVerified;

        @Column(name = "DATE_OF_BIRTH")
        private LocalDateTime dateOfBirth;

        @CreatedDate
        @Column(name = "CREATED_DATE", updatable = false, nullable = false)
        private LocalDateTime createdDate;

        @LastModifiedDate
        @Column(name = "LAST_MODIFIED_DATE", insertable = false)
        private LocalDateTime lastModifiedDate;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "USER_ROLES",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
        )
        private List<Role> roles = new ArrayList<>();

        public void addRole(final Role role) {
            this.roles.add(role);
            role.getUsers().add(this);
        }

        public void removeRole(final Role role) {
            this.roles.remove(role);
            role.getUsers().remove(this);
        }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(CollectionUtils.isEmpty(roles)){
            return List.of();
        }
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentialsExpired;
    }
    }
