package com.binde.spring_security_asymetric_encryption.category.impl;

import com.binde.spring_security_asymetric_encryption.category.Category;
import com.binde.spring_security_asymetric_encryption.category.request.CategoryRequest;
import com.binde.spring_security_asymetric_encryption.category.request.CategoryUpdateRequest;
import com.binde.spring_security_asymetric_encryption.category.response.CategoryResponse;
import io.micrometer.common.util.StringUtils;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryMapper {

    public Category toCategoryEntity(final CategoryRequest request) {

       return Category.builder()
               .name(request.getName())
               .description(request.getDescription())
               .build();

    }

    public void mergeCategory(final Category categoryToUpdate, final CategoryUpdateRequest request) {
        if(StringUtils.isNotBlank(request.getName()) && !categoryToUpdate.getName().equals(request.getName())){
            categoryToUpdate.setName(request.getName());
        }
        if (StringUtils.isNotBlank(request.getDescription()) && !categoryToUpdate.getDescription().equals(request.getDescription())){
            categoryToUpdate.setDescription(request.getDescription());
        }
    }

    public CategoryResponse toCategoryResponse( final Category category) {
        return CategoryResponse.builder()
                .name(category.getName())
                .description(category.getDescription())
                .todoCounts(category.getTodos().size())
                .build();
    }
}


