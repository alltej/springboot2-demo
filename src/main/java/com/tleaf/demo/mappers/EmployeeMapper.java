package com.tleaf.demo.mappers;

import com.tleaf.demo.domain.Department;
import com.tleaf.demo.domain.Employee;
import com.tleaf.demo.domain.Organization;
import com.tleaf.demo.models.EmployeeDto;
import com.tleaf.demo.models.EmployeeQuery;
import com.tleaf.demo.models.NewEmployee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Allan Tejano
 * 4/20/2018
 */
@Component
public class EmployeeMapper implements Converter<EmployeeDto, Employee> {
    @Override public Employee convert( EmployeeDto dto ) {
        Employee employee = new Employee();
        employee.setEmployeeId( dto.getEmployeeId() );
        employee.setLastName( dto.getLastName() );
        employee.setFirstName( dto.getFirstName() );
        employee.setDateOfBirth( dto.getDateOfBirth());
        return employee;
    }

    public Employee convert( NewEmployee dto ) {
        Employee employee = new Employee();
        employee.setSsn( dto.getSsn() );
        employee.setDateOfBirth( dto.getDateOfBirth() );
        employee.setFirstName( dto.getFirstName() );
        employee.setLastName( dto.getLastName() );

        if (dto.getDepartmentId() != null) {
            employee.setDepartment( Department.of( dto.getDepartmentId() ) );
        }
        if (dto.getOrganizationId() != null) {
            employee.setOrganization( Organization.of( dto.getOrganizationId() ) );
        }
        //employee.setDepartment( dto.getDepartment() );
        //employee.setOrganization( dto.getOrganization() );
        return employee;
    }

    public EmployeeDto convert( Employee entity ) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId( entity.getEmployeeId() );
        employeeDto.setFirstName( entity.getFirstName() );
        employeeDto.setLastName( entity.getLastName() );
        employeeDto.setDateOfBirth( entity.getDateOfBirth() );
        employeeDto.setDepartment( entity.getDepartment()!=null?entity.getDepartment().getName():"" );
        employeeDto.setOrganization( entity.getOrganization()!=null?entity.getOrganization().getName():"" );
        return employeeDto;
    }

    public Employee convert( EmployeeQuery query ) {
        Employee find = new Employee();
        find.setEmployeeId( query.getEmployeeId() );
        find.setFirstName( query.getFirstName() );
        find.setLastName( query.getLastName() );
        find.setDepartment( Department.of( query.getDepartmentId()) );
        find.setOrganization( Organization.of( query.getOrganizationId()) );
        return find;
    }
}
