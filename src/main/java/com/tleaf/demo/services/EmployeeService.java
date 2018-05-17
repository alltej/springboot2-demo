package com.tleaf.demo.services;

import com.tleaf.demo.models.EmployeeDto;
import com.tleaf.demo.models.EmployeeQuery;
import com.tleaf.demo.models.NewEmployee;
import com.tleaf.demo.services.exceptions.ResultNotFoundException;

import java.util.List;

/**
 * @author Allan Tejano
 * 4/20/2018
 */
public interface EmployeeService  {

    EmployeeDto addNewEmployee( NewEmployee newEmployee );

    List<EmployeeDto> getAllEmployees(int index);

    void updateEmployee( EmployeeDto employeeDto ) throws ResultNotFoundException;

    EmployeeDto getEmployee( Integer id ) throws ResultNotFoundException;

    List<EmployeeDto> findEmployees( EmployeeQuery query );

    void deleteEmployee( Integer id ) throws ResultNotFoundException;
}
