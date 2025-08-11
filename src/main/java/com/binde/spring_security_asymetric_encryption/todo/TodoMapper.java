package com.binde.spring_security_asymetric_encryption.todo;

import com.binde.spring_security_asymetric_encryption.category.response.CategoryResponse;
import com.binde.spring_security_asymetric_encryption.todo.request.TodoRequest;
import com.binde.spring_security_asymetric_encryption.todo.request.TodoUpdateRequest;
import com.binde.spring_security_asymetric_encryption.todo.response.TodoResponse;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class TodoMapper {
    public Todo toTodo(final TodoRequest request) {
        return Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .done(false)
                .build();
    }

    public void mergerTodo(final Todo todoUpdate, final TodoUpdateRequest request) {
        if (StringUtils.isNotBlank(request.getTitle())
        && !todoUpdate.getTitle().equals(request.getTitle())){
            todoUpdate.setTitle(request.getTitle());
        }
        if (StringUtils.isNotBlank(request.getDescription())
        && !todoUpdate.getDescription().equals(request.getDescription())){
            todoUpdate.setDescription(request.getDescription());
        }
    }

    public TodoResponse TodoResponse(final Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .startDate(todo.getStartDate())
                .endDate(todo.getEndDate())
                .startTime(todo.getStartTime())
                .endTime(todo.getEndTime())
                .done(todo.isDone())
                .response(
                        CategoryResponse.builder()
                                .name(todo.getCategory().getName())
                                .description(todo.getCategory().getDescription())
                                .build())
                .build();
    }
}
