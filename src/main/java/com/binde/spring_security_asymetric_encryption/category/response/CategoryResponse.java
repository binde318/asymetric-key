package com.binde.spring_security_asymetric_encryption.category.response;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private String id;
    private String name;
    private String description;
    private int todoCounts;

}
