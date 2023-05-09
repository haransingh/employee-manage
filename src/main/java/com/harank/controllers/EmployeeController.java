package com.harank.controllers;

import com.harank.dtos.EmployeeRequest;
import com.harank.dtos.EmployeeResponseDTO;
import com.harank.exceptions.EmployeeNotFoundException;
import com.harank.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        employeeService.createEmployee(employeeRequest);
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @PutMapping("/employees/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public EmployeeResponseDTO updateEmployee(@RequestBody EmployeeRequest employeeRequest, @PathVariable Long id) {
       return employeeService.updateEmployee(employeeRequest, id);
    }

    @DeleteMapping("/employees/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

}
