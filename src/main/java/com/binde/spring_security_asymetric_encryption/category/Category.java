package com.binde.spring_security_asymetric_encryption.category;

import com.binde.spring_security_asymetric_encryption.common.BaseEntity;
import com.binde.spring_security_asymetric_encryption.todo.Todo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "CATEGORIES")
public class Category extends BaseEntity {
    @Column(name = "NAME",nullable = false)
    private String name;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Todo>todos;
}
