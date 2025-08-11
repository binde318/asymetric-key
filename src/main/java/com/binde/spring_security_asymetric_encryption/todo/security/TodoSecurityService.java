package com.binde.spring_security_asymetric_encryption.todo.security;

import com.binde.spring_security_asymetric_encryption.category.Category;
import com.binde.spring_security_asymetric_encryption.todo.Todo;
import com.binde.spring_security_asymetric_encryption.todo.TodoRepository;
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
public class TodoSecurityService {
    private final TodoRepository todoRepository;
    @Transactional(readOnly = true)
    public boolean isTodoOwner(final String todoId){
        final Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        final String userId = ((User) authentication.getPrincipal()).getId();
        final Todo todo = this.todoRepository.findById(todoId)
                .orElseThrow(()-> new RuntimeException("Todo is not found with id "));
        return todo.getUser().equals(userId);
    }
}
