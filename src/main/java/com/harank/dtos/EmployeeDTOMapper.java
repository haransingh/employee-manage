package com.harank.dtos;

import com.harank.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EmployeeDTOMapper implements Function<Employee, EmployeeResponseDTO> {
    @Override
    public EmployeeResponseDTO apply(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getStatus()
        );
    }
}
