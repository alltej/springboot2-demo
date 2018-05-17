package com.tleaf.demo.services;

import com.tleaf.demo.SampleData;
import com.tleaf.demo.domain.Department;
import com.tleaf.demo.domain.Employee;
import com.tleaf.demo.domain.Organization;
import com.tleaf.demo.mappers.EmployeeMapper;
import com.tleaf.demo.models.EmployeeDto;
import com.tleaf.demo.models.EmployeeQuery;
import com.tleaf.demo.models.NewEmployee;
import com.tleaf.demo.repositories.EmployeeRepository;
import com.tleaf.demo.services.exceptions.ResultNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Allan Tejano
 * 4/23/2018
 */
@RunWith( MockitoJUnitRunner.class )
public class EmployeeServiceTest {

    @Mock EmployeeRepository employeeRepository;

    @Mock OrganizationService organizationService;

    @Spy EmployeeMapper employeeMapper;
    @InjectMocks EmployeeServiceImpl employeeService;

    @Test public void addNewEmployee() {
        NewEmployee newEmp = SampleData.newEmployee();
        Employee entity = new Employee();
        when(employeeMapper.convert( newEmp )).thenReturn( entity );
        when(organizationService.findDepartmentById( newEmp.getDepartmentId() )).thenReturn( Department.of( newEmp.getDepartmentId() ) );
        when(organizationService.findOrganizationById( newEmp.getOrganizationId() )).thenReturn( Organization.of( newEmp.getOrganizationId() ) );

        Employee saved = new Employee();
        saved.setEmployeeId( 1 );
        when(employeeRepository.save( entity )).thenReturn(saved  );
        EmployeeDto dto = employeeService.addNewEmployee( newEmp );
        assertEquals( Integer.valueOf( 1 ), dto.getEmployeeId() );
        verify( employeeRepository, Mockito.atLeastOnce()).save(entity);
    }

    @Test public void getAllEmployees() {
        when( employeeRepository.findAllBy( any( PageRequest.class ) ) ).thenReturn( SampleData.listOfEmployees() );
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees( 1 );
        assertEquals( 3, allEmployees.size() );
    }

    @Test(expected = ResultNotFoundException.class)
    public void updateEmployee_w_non_existingId_throw_exception() throws ResultNotFoundException {
        EmployeeDto existingEmployee = SampleData.employeeDto();
        when( employeeRepository.findByEmployeeId( existingEmployee.getEmployeeId() ) ).thenReturn( null );
        employeeService.updateEmployee( existingEmployee );
    }

    @Test
    public void updateEmployee_save_success() throws ResultNotFoundException {
        EmployeeDto existingEmployee = SampleData.employeeDto();
        Employee entity = SampleData.existingEmployee();
        when( employeeRepository.findByEmployeeId( existingEmployee.getEmployeeId() ) ).thenReturn(entity );
        when(employeeRepository.save( entity )).thenReturn( any( Employee.class ) );
        employeeService.updateEmployee( existingEmployee );
        verify( employeeRepository, Mockito.atLeastOnce()).save(entity);
    }

    @Test public void getEmployee() {
        Employee entity = SampleData.existingEmployee();
        when( employeeRepository.findByEmployeeId( anyInt() ) ).thenReturn(entity );
        EmployeeDto employee = employeeService.getEmployee( 1 );
        assertEquals( Integer.valueOf( 999 ), employee.getEmployeeId() );
        assertEquals( "Existing", employee.getFirstName() );
    }

    @Test public void findEmployees() {
        when( employeeRepository.findAll( any( Example.class ) ) ).thenReturn( SampleData.listOfEmployees() );
        List<EmployeeDto> employees = employeeService.findEmployees( EmployeeQuery.builder().employeeId( 1 ).build() );
        assertEquals( 3, employees.size() );
    }

    @Test(expected = ResultNotFoundException.class)
    public void deleteEmployee_w_non_existingId_throw_exception() throws ResultNotFoundException {
        EmployeeDto existingEmployee = SampleData.employeeDto();
        when( employeeRepository.findByEmployeeId( existingEmployee.getEmployeeId() ) ).thenReturn( null );
        employeeService.deleteEmployee( existingEmployee.getEmployeeId() );
    }

    @Test
    public void deleteEmployee_success() throws ResultNotFoundException {
        EmployeeDto employeeDto = SampleData.employeeDto();
        Employee employee = SampleData.existingEmployee();
        when( employeeRepository.findByEmployeeId( employeeDto.getEmployeeId() ) ).thenReturn( employee );
        employeeService.deleteEmployee( employeeDto.getEmployeeId() );
        verify( employeeRepository, Mockito.atLeastOnce()).delete(employee);
    }
}
