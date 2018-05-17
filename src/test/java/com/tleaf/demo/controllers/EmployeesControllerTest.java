package com.tleaf.demo.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tleaf.demo.SampleData;
import com.tleaf.demo.models.EmployeeDto;
import com.tleaf.demo.models.NewEmployee;
import com.tleaf.demo.services.EmployeeService;
import com.tleaf.demo.services.exceptions.ResultNotFoundException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Allan Tejano
 * 4/23/2018
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeesController.class)
public class EmployeesControllerTest {

    @MockBean EmployeeService employeeService   ;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
       mapper.setSerializationInclusion( JsonInclude.Include.NON_NULL);
    }

    @Test public void getAllEmployees_with_page_index() throws Exception {
        List<EmployeeDto> dtos = SampleData.listOfEmployeeDtos();
        when( employeeService.getAllEmployees( 1 ) ).thenReturn( dtos );

        String contentAsString = mockMvc.perform( get( "/api/employees/all/1" ) ).andExpect( status().isOk() )
                .andReturn().getResponse().getContentAsString();
        //System.out.println(contentAsString);

        List<EmployeeDto> response = mapper.readValue(contentAsString, new TypeReference<List<EmployeeDto>>(){});
        assertEquals( 3,response.size() );

    }

    @Test public void addEmployee() throws Exception {
        NewEmployee newEmployee = SampleData.newEmployee();
        EmployeeDto dto = SampleData.employeeDto();
        when( employeeService.addNewEmployee( newEmployee ) ).thenReturn( dto );
        mockMvc.perform( post( "/api/employees/")
                .contentType( APPLICATION_JSON)
                .content( mapper.writeValueAsBytes(newEmployee) )).andDo(print())
                .andExpect(status().isCreated());
    }


    @Test public void get_employee_by_id() throws Exception {
        EmployeeDto dto = SampleData.employeeDto();
        when( employeeService.getEmployee( 1 ) ).thenReturn( dto );

        String contentAsString = mockMvc.perform( get( "/api/employees/1" ) ).andExpect( status().isOk() )
                .andReturn().getResponse().getContentAsString();
        //System.out.println(contentAsString);

        EmployeeDto response = mapper.readValue(contentAsString, new TypeReference<EmployeeDto>(){});
        assertEquals( "Aaa",response.getFirstName() );
        assertEquals( "Bbb",response.getLastName() );
        assertEquals( "Org AA",response.getOrganization() );

    }
}
