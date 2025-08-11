package com.binde.spring_security_asymetric_encryption.todo;

import com.binde.spring_security_asymetric_encryption.common.RestResponse;
import com.binde.spring_security_asymetric_encryption.todo.request.TodoRequest;
import com.binde.spring_security_asymetric_encryption.todo.request.TodoUpdateRequest;
import com.binde.spring_security_asymetric_encryption.todo.response.TodoResponse;
import com.binde.spring_security_asymetric_encryption.user.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
@Tag(name = "Todos", description = "Todo API")
public class TodoController {
    private final TodoService todoService;
    @PostMapping
    public ResponseEntity<RestResponse> createTodo(
            @RequestBody
            @Valid
            TodoRequest request,
            Authentication authentication
    ){
      final String userId =((User) authentication.getPrincipal()).getId();
      final String todoId = this.todoService.createTodo(request,userId);
      return ResponseEntity.status(CREATED)
              .body(new RestResponse(todoId));
    }
    @PutMapping("/{todo-id}")
    @PreAuthorize("@todoSecurityService.isTodoOwner(#todoId)")
    public ResponseEntity<Void> updateTodo(
            @RequestBody
            @Valid
            final TodoUpdateRequest request,
            @PathVariable("todo-id")
            final String todoId,
            final Authentication authentication
    ){
        final String userId = ((User) authentication.getPrincipal()).getId();
        this.todoService.updateTodo(request,todoId,userId);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/{todo-id}")
    @PreAuthorize("@todoSecurityService.isTodoOwner(#todoId)")
    public ResponseEntity<TodoResponse> findTodoById(
            @PathVariable("todo-id")
            final  String todoId
    ){
        return ResponseEntity.ok(this.todoService.findTodoById(todoId));
    }
    @GetMapping("/today")
    public ResponseEntity<List<TodoResponse>> findAllTodosForTodayByUserId(
            @RequestBody
            @Valid
            final Authentication authentication
    ){
        final String userId = ((User) authentication.getPrincipal()).getId();
        return ResponseEntity.ok(this.todoService.findAllTodosForToday(userId));
    }
    @GetMapping("/category/{category-id}")
    @PreAuthorize("@categorySecurityService.isCategoryOwner(#categoryId)")
    public ResponseEntity<List<TodoResponse>> findAllTodosByCategory(
            @PathVariable("category-id")
            final String categoryId,
            final Authentication authentication
    ){
        final String userId = ((User) authentication.getPrincipal()).getId();
        return ResponseEntity.ok(this.todoService.findAllTodosByCategory(categoryId,userId));
    }
    @GetMapping("/due")
    public ResponseEntity<List<TodoResponse>> findAllDueTodos(
            @RequestBody
            @Valid
            final Authentication authentication
    ){
        final  String userId = ((User) authentication.getPrincipal()).getId();
        return ResponseEntity.ok(this.todoService.findAllDueTodos(userId));
    }
    @DeleteMapping("/{todo-id}")
    @PreAuthorize("@todoSecurityService.isTodoOwner(#todoId)")
    public ResponseEntity<Void> deleteTodoById(
            @PathVariable("todo-id")
            final String todoId
    ){
        this.todoService.deleteTodoById(todoId);
        return ResponseEntity.ok().build();
    }
}
