package com.binde.spring_security_asymetric_encryption.category.request;

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
public class CategoryUpdateRequest {

    @NotBlank(message = "VALIDATION.CATEGORY.NAME.NOT_BLANK")
    private String name;
    @NotBlank(message = "VALIDATION.CATEGORY.DESCRIPTION.NOT_BLANK")
    private String description;

}
