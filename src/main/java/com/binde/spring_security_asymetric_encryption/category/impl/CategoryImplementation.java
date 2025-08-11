package com.binde.spring_security_asymetric_encryption.category.impl;

import com.binde.spring_security_asymetric_encryption.category.Category;
import com.binde.spring_security_asymetric_encryption.category.CategoryRepository;
import com.binde.spring_security_asymetric_encryption.category.CategoryService;
import com.binde.spring_security_asymetric_encryption.category.request.CategoryRequest;
import com.binde.spring_security_asymetric_encryption.category.request.CategoryUpdateRequest;
import com.binde.spring_security_asymetric_encryption.category.response.CategoryResponse;
import com.binde.spring_security_asymetric_encryption.exception.BusinessException;
import com.binde.spring_security_asymetric_encryption.exception.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class CategoryImplementation implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;
    @Override
    public String createCategory(final CategoryRequest request, String userId) {
        checkCategoryUnicityForUser(request.getName(),userId);
        final Category category = this.categoryMapper.toCategoryEntity(request);
        return this.repository.save(category).getId();
    }


    @Override
    public void updateCategory(final CategoryUpdateRequest request,final String catId, final String userId) {
        final Category categoryToUpdate = this.repository.findById(catId)
                        .orElseThrow(()-> new EntityNotFoundException("No Categoery found with id " + catId));
        checkCategoryUnicityForUser(request.getName(),userId);
        this.categoryMapper.mergeCategory(categoryToUpdate, request);
        this.repository.save(categoryToUpdate);

    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAllByOwner(final String userId) {
        return this.repository.findAllByUserId(userId)
                .stream()
                .map(this.categoryMapper::toCategoryResponse)
                .toList();
    }

    @Override
    public CategoryResponse findCategoryById(String catId) {
        return this.repository.findById(catId)
                .map(this.categoryMapper::toCategoryResponse)
                .orElseThrow(()-> new EntityNotFoundException("No category found with id " + catId));
    }

    @Override
    public void deleteCategoryById(String catId) {

    }

    public void checkCategoryUnicityForUser(String name, String userId) {
        final boolean alreadyExistsForUser = this.repository.findByNameAndUser(name, userId);
        if (alreadyExistsForUser) {
            throw new BusinessException(ErrorCode.CATEGORY_ALREADY_EXIST_FOR_THIS_USER);
        }
    }
}
