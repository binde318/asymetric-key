package com.binde.spring_security_asymetric_encryption.category.security;

import com.binde.spring_security_asymetric_encryption.category.Category;
import com.binde.spring_security_asymetric_encryption.category.CategoryRepository;
import com.binde.spring_security_asymetric_encryption.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategorySecurityService {
    private final CategoryRepository categoryRepository;
     @Transactional(readOnly = true)
    public boolean isCategoryOwner(final String categoryId){
         final Authentication authentication = SecurityContextHolder
                 .getContext()
                 .getAuthentication();
         final String userId = ((User) authentication.getPrincipal()).getId();
         final Category category = this.categoryRepository.findById(categoryId)
                 .orElseThrow(()-> new RuntimeException(""));
         return category.getCreatedBy().equals(userId);
    }
}
