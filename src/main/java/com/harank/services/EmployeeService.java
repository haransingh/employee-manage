package com.harank.services;

import com.harank.dtos.EmployeeDTOMapper;
import com.harank.dtos.EmployeeRequest;
import com.harank.dtos.EmployeeResponseDTO;
import com.harank.entities.Department;
import com.harank.entities.Employee;
import com.harank.exceptions.EmployeeNotFoundException;
import com.harank.repository.DepartmentRepository;
import com.harank.repository.EmployeeRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger log =  LogManager.getLogger(EmployeeService.class);


    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeDTOMapper employeeDTOMapper;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, EmployeeDTOMapper employeeDTOMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.employeeDTOMapper = employeeDTOMapper;
    }


    public void createEmployee(EmployeeRequest employeeRequest) {
        log.info("createEmployee employee");
        Department department = departmentRepository.findById(employeeRequest.getDepartmentId()).orElse(null);
        Employee employee = Employee.builder()
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .email(employeeRequest.getEmail())
                .department(department)
                .build();
        employeeRepository.save(employee);
    }

    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findByStatusIsFalse();
        return employees.stream().map(employeeDTOMapper).toList();
    }


    public EmployeeResponseDTO getEmployee(Long id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id).map(employeeDTOMapper).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));
    }

    public EmployeeResponseDTO updateEmployee(EmployeeRequest employeeRequest, Long id) {
        Department department = departmentRepository.findById(employeeRequest.getDepartmentId()).orElse(null);
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee not found with id " + id));
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName((employeeRequest.getLastName()));
        employee.setEmail(employeeRequest.getEmail());
        employee.setDepartment(department);
        employeeRepository.save(employee);
       return employeeDTOMapper.apply(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
