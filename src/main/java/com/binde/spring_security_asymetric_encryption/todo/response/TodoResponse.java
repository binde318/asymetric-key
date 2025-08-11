package com.binde.spring_security_asymetric_encryption.todo.response;

import com.binde.spring_security_asymetric_encryption.category.response.CategoryResponse;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoResponse {
    private String id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean done;
    private CategoryResponse response;
}
