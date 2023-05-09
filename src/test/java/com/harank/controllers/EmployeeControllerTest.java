package com.harank.controllers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = EmployeeController.class)
@WithMockUser
@AutoConfigureMockMvc
class EmployeeControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private EmployeeService employeeService;
//
//    @Test
//    public void getEmployeeById() throws Exception {
//        Mockito.when().then();
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}").accept(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        System.out.println("result" + result);
//    }
}