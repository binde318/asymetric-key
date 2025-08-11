package com.binde.spring_security_asymetric_encryption.todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, String> {
    @Query("""
SELECT t FROM Todo t 
WHERE t.user.id = :userid
AND t.startDate = CURRENT_DATE 
""")
    List<Todo> findAllByUserId(@Param("userid") String userId);

//    @Query("""
//SELECT t FROM Todo t
//WHERE t.user.id = :userid
//AND t.startDate = CURRENT_DATE
//""")
//    List<Todo> findAllByUserId(String userId);

    List<Todo> findAllByUserIdAndCategoryId(String userId, String categoryId);
    @Query("""
SELECT t FROM Todo t
WHERE t.endDate >= CURRENT_DATE 
AND t.endTime >= CURRENT_TIME 
""")
    List<Todo> findAllDueTodos();

//@Query("""
//SELECT t FROM Todo t
//WHERE t.endDate >= CURRENT_DATE
//AND t.endTime >= CURRENT_TIME
//""")
//    List<Todo> findAllDueTodos(String userId);
}
