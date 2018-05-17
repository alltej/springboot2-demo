package com.tleaf.demo.services;

import com.tleaf.demo.domain.Department;
import com.tleaf.demo.domain.Employee;
import com.tleaf.demo.domain.Organization;
import com.tleaf.demo.mappers.EmployeeMapper;
import com.tleaf.demo.models.EmployeeDto;
import com.tleaf.demo.models.EmployeeQuery;
import com.tleaf.demo.models.NewEmployee;
import com.tleaf.demo.repositories.EmployeeRepository;
import com.tleaf.demo.services.exceptions.ResultNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

/**
 * @author Allan Tejano
 * 4/20/2018
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int PAGE_SIZE = 10;
    private final ExampleMatcher matcher;
    @Autowired EmployeeRepository employeeRepository;

    @Autowired OrganizationService organizationService;

    @Autowired EmployeeMapper employeeMapper;

    public EmployeeServiceImpl() {
        matcher = ExampleMatcher.matching()
                .withMatcher("employeeId", exact())
                .withMatcher("firstName", startsWith().ignoreCase())
                .withMatcher("lastName", startsWith().ignoreCase())
                .withMatcher("departmentId", exact())
                .withMatcher("organizationId", exact());
    }

    @Transactional
    @Override public EmployeeDto addNewEmployee( NewEmployee newEmployee ) {
        Employee employee = employeeMapper.convert( newEmployee );

        //if need to validate dept
        Department dept = organizationService.findDepartmentById( newEmployee.getDepartmentId() );
        employee.setDepartment( dept );

        //if need to validate org
        Organization org = organizationService.findOrganizationById( newEmployee.getOrganizationId() );
        employee.setOrganization( org );

        Employee savedEmployee = employeeRepository.save( employee );

        return  employeeMapper.convert( savedEmployee);
    }

    @Override public List<EmployeeDto> getAllEmployees(int index) {
        //TODO
        PageRequest pageRequest = new PageRequest(index, PAGE_SIZE, Sort.Direction.ASC, "lastName");

        List<Employee> employees = employeeRepository.findAllBy( pageRequest );

        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for ( Employee employee : employees ) {
            employeeDtos.add( employeeMapper.convert( employee ) );
        }
        return  employeeDtos;
    }

    @Transactional
    @Override public void updateEmployee( EmployeeDto employeeDto ) throws ResultNotFoundException {
        Employee employee = getOrElseThrowException( employeeDto.getEmployeeId() );

        employee.setFirstName( employeeDto.getFirstName() );
        employee.setLastName( employeeDto.getLastName() );
        employee.setDateOfBirth( employeeDto.getDateOfBirth() );
        employeeRepository.save( employee );
    }

    @Override public EmployeeDto getEmployee( Integer id ) throws ResultNotFoundException {
        Employee employee = getOrElseThrowException( id );
        return  employeeMapper.convert( employee);

    }

    @Override public List<EmployeeDto> findEmployees( EmployeeQuery query ) {

        Employee find = employeeMapper.convert( query );

        List<Employee> employees = employeeRepository.findAll( Example.of( find, matcher ) );
        List<EmployeeDto> dtos = employees.stream().map( e -> employeeMapper.convert( e ) ).collect( toList() );

        return dtos;
    }

    @Override public void deleteEmployee( Integer id ) throws ResultNotFoundException {
        Employee employee = getOrElseThrowException( id );
        employeeRepository.delete( employee);
    }

    private Employee getOrElseThrowException( Integer id ) {
        Employee employee = employeeRepository.findByEmployeeId( id );

        if (employee==null) throw new ResultNotFoundException( "Employee not found" );
        return employee;
    }
}
