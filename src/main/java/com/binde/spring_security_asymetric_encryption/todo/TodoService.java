package com.binde.spring_security_asymetric_encryption.todo;

import com.binde.spring_security_asymetric_encryption.todo.request.TodoRequest;
import com.binde.spring_security_asymetric_encryption.todo.request.TodoUpdateRequest;
import com.binde.spring_security_asymetric_encryption.todo.response.TodoResponse;

import java.util.List;

public interface TodoService {
    String createTodo(TodoRequest request, String userId);
    void updateTodo(TodoUpdateRequest request, String todoId, String userId);
    TodoResponse findTodoById(String userId);
    List<TodoResponse> findAllTodosForToday(String userId);
    List<TodoResponse> findAllTodosByCategory(String catId,String userId);
    List<TodoResponse> findAllDueTodos(String userId);
    void deleteTodoById(String userId);

}
