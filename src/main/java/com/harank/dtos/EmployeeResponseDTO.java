package com.harank.dtos;

import com.harank.entities.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public record EmployeeResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        Department department,
        Boolean status
        ) {
}
