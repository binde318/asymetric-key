package com.binde.spring_security_asymetric_encryption.category;

import com.binde.spring_security_asymetric_encryption.category.request.CategoryRequest;
import com.binde.spring_security_asymetric_encryption.category.request.CategoryUpdateRequest;
import com.binde.spring_security_asymetric_encryption.category.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    String createCategory(CategoryRequest request, String userId);
    void updateCategory(CategoryUpdateRequest request,String catId, String userId);
    List<CategoryResponse> findAllByOwner(String userId);
    CategoryResponse findCategoryById(String catId);
    void deleteCategoryById(String catId);
}
