package ru.sbt.employees.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"/create-employees-before.sql", "/employees-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/employees-list-after.sql", "/create-employees-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void employeeControllerAutowiredTest() throws Exception {
        assertThat(employeeController).isNotNull();
    }

    @Test
    public void mockMvcAutowiredTest() throws Exception {
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/employee"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("Андрей"));
    }

    @Test
    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/employee/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string((containsString(""))));
    }

    @Test
    public void addTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/employee")
                .param("first_name", "Иван")
                .param("last_name", "Петров")
                .param("age", "32")
                .param("department_id", "1");

        mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string((containsString(""))));
    }
}
