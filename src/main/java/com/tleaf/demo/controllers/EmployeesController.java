package com.tleaf.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tleaf.demo.models.EmployeeDto;
import com.tleaf.demo.models.EmployeeQuery;
import com.tleaf.demo.models.NewEmployee;
import com.tleaf.demo.services.EmployeeService;
import com.tleaf.demo.services.exceptions.ResultNotFoundException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Allan Tejano
 * 4/20/2018
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
//    Write simple REST service(s) to do CRUD, Search, List actions on Employee Data and package it in a
//    Docker container.
//    Use the simplest data model (employee, department, Organization) with minimum number of fields.
//    Use any DB (MongoDB or MySQL) or plain JSON file. Feel free to use any Java framework for services.
//    Optionally write a web page to test the services, but it is not required.
//            Looking for high performing quality code (unit tests).
//    Zip and Send the Docker Image with this file.

    @Autowired EmployeeService employeeService;
    //@Autowired EmployeeMapper employeeMapper;

    @GetMapping("/all/{page}")
    public ResponseEntity<?> getEmplooyees(@PathVariable("page")Integer pageIndex) {
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees(pageIndex);

        return new ResponseEntity<>( employeeDtos, HttpStatus.OK );
    }

    @PostMapping("/")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody NewEmployee employee) {
        EmployeeDto newEmployee = employeeService.addNewEmployee( employee);
            return new ResponseEntity<>( newEmployee, HttpStatus.CREATED );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee( @PathVariable("id") Integer id) throws ResultNotFoundException {
        EmployeeDto existingEmployee = employeeService.getEmployee(id);
        //if (existingEmployee == null) return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        return new ResponseEntity<>( existingEmployee, HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee( @PathVariable("id") Integer id) throws ResultNotFoundException {
        employeeService.deleteEmployee(id);
        //if (existingEmployee == null) return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee( @PathVariable("id") Integer id,
            @RequestBody final EmployeeDto employeeDto ) throws ResultNotFoundException {
        employeeService.updateEmployee( employeeDto );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "employeeQuery",
                    value = "Example: { \"organizationId\" : 1, \"departmentId\": 21, \"lastName\": \"Smi\"}",
                    required = true,
                    dataType = "string",
                    paramType = "query"
            )
    })
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "employeeQuery") String employeeQuery)
            throws IOException {

        final EmployeeQuery query =
                new ObjectMapper().setDateFormat(simpleDateFormat).readValue(employeeQuery, EmployeeQuery.class);

        List<EmployeeDto> employeeDtos = employeeService.findEmployees(query);
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK );
    }

}
