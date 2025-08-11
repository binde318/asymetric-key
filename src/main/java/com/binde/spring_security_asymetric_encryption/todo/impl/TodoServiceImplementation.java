package com.binde.spring_security_asymetric_encryption.todo.impl;

import com.binde.spring_security_asymetric_encryption.category.Category;
import com.binde.spring_security_asymetric_encryption.category.CategoryRepository;
import com.binde.spring_security_asymetric_encryption.todo.Todo;
import com.binde.spring_security_asymetric_encryption.todo.TodoMapper;
import com.binde.spring_security_asymetric_encryption.todo.TodoRepository;
import com.binde.spring_security_asymetric_encryption.todo.TodoService;
import com.binde.spring_security_asymetric_encryption.todo.request.TodoRequest;
import com.binde.spring_security_asymetric_encryption.todo.request.TodoUpdateRequest;
import com.binde.spring_security_asymetric_encryption.todo.response.TodoResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class TodoServiceImplementation implements TodoService {
    private final TodoRepository repository;
    private final CategoryRepository categoryRepository;
    private final TodoMapper todoMapper;
    @Override
    public String createTodo(final TodoRequest request, final String userId) {
        final Category category = checkCategory(request.getCategoryId(), userId);
        final Todo todo = this.todoMapper.toTodo(request);
        todo.setCategory(category);
        return this.repository.save(todo).getId();
    }

    @Override
    public void updateTodo(final TodoUpdateRequest request, final String todoId, final String userId) {
        final Todo todoUpdate = this.repository.findById(todoId)
                .orElseThrow(()-> new EntityNotFoundException("Todo not found with the id"));
        final Category category = checkCategory(request.getCategoryId(), userId);

        this.todoMapper.mergerTodo(todoUpdate,request);
        todoUpdate.setCategory(category);
        this.repository.save(todoUpdate);


    }

    @Override
    public TodoResponse findTodoById(final String userId) {
        return this.repository.findById(userId)
                .map(this.todoMapper::TodoResponse)
                .orElseThrow(()-> new EntityNotFoundException("No todo found with id"));
    }

    @Override
    public List<TodoResponse> findAllTodosForToday(final String userId) {
        return this.repository.findAllByUserId(userId)
                .stream()
                .map(this.todoMapper::TodoResponse)
                .toList();
    }

    @Override
    public List<TodoResponse> findAllTodosByCategory(final String catId, final String userId) {
        return this.repository.findAllByUserIdAndCategoryId(userId, catId)
                .stream()
                .map(this.todoMapper::TodoResponse)
                .toList();
    }

    @Override
    public List<TodoResponse> findAllDueTodos(final String userId) {
        return this.repository.findAllDueTodos()
                .stream()
                .map(this.todoMapper::TodoResponse)
                .toList();
    }

    @Override
    public void deleteTodoById(final String todoId) {
        this.repository.deleteById(todoId);

    }

    private Category checkCategory(final String categoryId, final String userId){
        return this.categoryRepository.findByIdAndUserId(categoryId,userId)
                .orElseThrow(()-> new EntityNotFoundException("No category with id " + categoryId));

    }
}
