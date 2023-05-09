package com.harank.repository;

import com.harank.entities.Department;
import com.harank.entities.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    private static EmployeeRepository employeeRepository;

    @BeforeAll
    public static void setUp() {
        Employee employee = new Employee(null, "Haran", "Singh", "haran.kharte@gmail.com", new Department(1l, "Develpment"), true);
        employeeRepository.save(employee);
    }

    @Test
    public void shouldSaveEmployee() {
        Employee employee = new Employee(null, "Haran", "Singh", "haran.kharte@gmail.com", new Department(1l, "Develpment"), true);
        Employee saveEmployee =  employeeRepository.save(employee);
        assertThat(saveEmployee).usingRecursiveComparison().ignoringFields("id").isEqualTo(employee);
    }

    @Test
    public void shouldReturnSingle() {
        Optional<Employee> employee = employeeRepository.findByEmail("haran.kharte@gmail.com");
        assertThat(employee).isNotEmpty();

    }


}