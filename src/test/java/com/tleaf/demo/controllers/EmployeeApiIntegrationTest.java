package com.tleaf.demo.controllers;

import com.tleaf.demo.SampleData;
import com.tleaf.demo.models.EmployeeDto;
import com.tleaf.demo.models.NewEmployee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author Allan Tejano
 * 4/24/2018
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeApiIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void create_get_employee() {
        NewEmployee newE = SampleData.newEmployee();
        newE.setFirstName( "Hello" );
        newE.setLastName( "World" );
        ResponseEntity<EmployeeDto> responseEntity =
                restTemplate.postForEntity("/api/employees/", newE, EmployeeDto.class);
        EmployeeDto dto = responseEntity.getBody();
        assertEquals( HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(newE.getFirstName(), dto.getFirstName());

        /* get */
        Map<String, Object> params = new HashMap<>();
        params.put("id", dto.getEmployeeId());

        ResponseEntity<EmployeeDto> getEntity =
                restTemplate.getForEntity("/api/employees/{id}", EmployeeDto.class, params);
        EmployeeDto getDto = getEntity.getBody();
        assertEquals( HttpStatus.OK, getEntity.getStatusCode());
        assertEquals(newE.getFirstName(), getDto.getFirstName());
        assertEquals(newE.getLastName(), getDto.getLastName());
    }

}
