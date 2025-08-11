package com.binde.spring_security_asymetric_encryption.category;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query("""
        SELECT COUNT (c)>0
                FROM Category c 
                WHERE lower(c.name) = lower(:name)
                AND c.createdBy = :userId OR c.createdBy = 'APP'
                """)
    boolean findByNameAndUser(String name, String userId);
@Query("""
SELECT c FROM Category c
WHERE c.createdBy = :userId OR c.createdBy = 'APP'
""")
    List<Category> findAllByUserId(String userId);
    @Query("""
SELECT c FROM Category c 
WHERE c.id = :categoryId
AND (c.createdBy = :userId OR c.createdBy = 'APP')
""")
    Optional<Category> findByIdAndUserId(String categoryId, String userId);
}
