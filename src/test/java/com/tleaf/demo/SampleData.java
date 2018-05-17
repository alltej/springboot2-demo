package com.tleaf.demo;

import com.tleaf.demo.domain.Department;
import com.tleaf.demo.domain.Employee;
import com.tleaf.demo.domain.Organization;
import com.tleaf.demo.models.EmployeeDto;
import com.tleaf.demo.models.NewEmployee;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Allan Tejano
 * 4/23/2018
 */
public final class SampleData {

    private SampleData() {

    }

    public static NewEmployee newEmployee() {
        return NewEmployee.builder().firstName( "Aaa" ).lastName( "Bbb" ).departmentId( 21 )
                .ssn( "123456789" )
                //.dateOfBirth( LocalDate.of( 1990, 12,31 ) )
                .organizationId( 1 ).build();
    }

    public static List<Employee> listOfEmployees() {
        Employee ee1 = createEmployee(1,1,21,"JOhn","Smith");
        Employee ee2 = createEmployee(2,2,22,"Mary","Poppins");
        Employee ee3= createEmployee(3,3,23,"Mike","Henz");

        return asList( ee1, ee2, ee3 );
    }

    public static List<EmployeeDto> listOfEmployeeDtos() {
        EmployeeDto ee1 = createEmployeeDto(1,"Org1","DeptA","JOhn","Smith");
        EmployeeDto ee2 = createEmployeeDto(2,"Org2","DeptB","Mary","Poppins");
        EmployeeDto ee3= createEmployeeDto(3,"Org3","DeptC","Mike","Henz");

        return asList( ee1, ee2, ee3 );
    }

    private static Employee createEmployee(Integer id, Integer orgId, Integer deptId, String firstName, String lastName) {
        Employee ee = new Employee();
        ee.setEmployeeId( id );
        ee.setOrganization( Organization.of( orgId ) );
        ee.setDepartment( Department.of( deptId ) );
        ee.setFirstName( firstName );
        ee.setLastName( lastName );
        return ee;
    }
    private static EmployeeDto createEmployeeDto(Integer id, String org, String dept, String firstName, String lastName) {
        return EmployeeDto.builder().employeeId( id ).organization( dept )
                .department( org )
                .firstName( firstName )
                .lastName( lastName ).build();
    }

    public static EmployeeDto employeeDto() {
        return EmployeeDto.builder().employeeId( 99 ).firstName( "Aaa" ).lastName( "Bbb" ).department( "Finance" )
                //.dateOfBirth( LocalDate.of( 1990, 12,31 ) )
                .organization( "Org AA").build();
    }

    public static Employee existingEmployee() {
        return createEmployee(999,1,21,"Existing","Entity");
    }
}
