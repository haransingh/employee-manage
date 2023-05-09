package com.harank.services;

import com.harank.dtos.EmployeeDTOMapper;
import com.harank.dtos.EmployeeRequest;
import com.harank.dtos.EmployeeResponseDTO;
import com.harank.entities.Department;
import com.harank.entities.Employee;
import com.harank.exceptions.EmployeeNotFoundException;
import com.harank.repository.DepartmentRepository;
import com.harank.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private EmployeeDTOMapper employeeDTOMapper;

    @Captor
    private ArgumentCaptor<Employee> postArgumentCaptor;

    @MockBean
    private EmployeeService employeeService;

    private Department department;
    private Employee employee;

    @BeforeEach
    public void setData() {
        employeeService = new EmployeeService(employeeRepository, departmentRepository, employeeDTOMapper);
        department = new Department(1L, "Development");
        employee = new Employee(123L, "Haran", "Singh", "haran@gmail.com", department, true);

    }

    @Test
    @DisplayName("Test getEmployee")
    public void shouldReturnTrue() throws EmployeeNotFoundException {
        EmployeeResponseDTO employeeResponseExc = new EmployeeResponseDTO(123L, "Haran", "Singh", "haran@gmail.com", department, true);
        Mockito.when(employeeRepository.findById(123L)).thenReturn(Optional.of(employee));
        EmployeeResponseDTO employeeResponseActual = employeeService.getEmployee(123L);
        Assertions.assertThat(employeeResponseActual.id()).isEqualTo(employeeResponseExc.id());
    }

    @Test
    public void shouldSaveEmployee() {
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        EmployeeRequest employeeRequest = new EmployeeRequest("Haran", "Singh", "haran@gmail.com", 1L);
        employeeService.createEmployee(employeeRequest);
        Mockito.verify(employeeRepository, Mockito.times(1)).save(postArgumentCaptor.capture());
    }


}