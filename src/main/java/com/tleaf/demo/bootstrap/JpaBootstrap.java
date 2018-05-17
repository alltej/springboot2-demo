package com.tleaf.demo.bootstrap;

import com.tleaf.demo.domain.Department;
import com.tleaf.demo.domain.Organization;
import com.tleaf.demo.models.NewEmployee;
import com.tleaf.demo.services.EmployeeService;
import com.tleaf.demo.services.OrganizationService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Allan Tejano
 * 4/23/2018
 */
@Component
public class JpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired EmployeeService employeeService;
    @Autowired OrganizationService organizationService;

    @Override public void onApplicationEvent( ContextRefreshedEvent contextRefreshedEvent ) {
        insertOrganizations();

        insertDepartments();

        NewEmployee ee1 = NewEmployee.builder().ssn( "123456789" )
                .firstName( "John")
                .lastName( "Smith")
                .dateOfBirth( LocalDate.of(1961, 1, 1 ) )
                .departmentId( 21 ).organizationId( 1 )
                .build();

        employeeService.addNewEmployee( ee1 );

        insertEmployees(1, 10);
        insertEmployees(2, 15);
        insertEmployees(3, 20);

   }

    private void insertEmployees(Integer organizationId, Integer count) {
        IntStream.rangeClosed( 1, count ).forEach( c ->
                IntStream.rangeClosed( 21, 24).forEach( n ->{

                final String ssn = String.format( "%s%s", organizationId, n, RandomStringUtils.randomNumeric( 9 )  );
                NewEmployee ee1 = NewEmployee.builder().ssn( ssn )
                        .firstName( RandomStringUtils.randomAlphabetic(6))
                        .lastName( RandomStringUtils.randomAlphabetic(8))
                        .dateOfBirth( LocalDate.of( RandomUtils.nextInt( 1950, 2000 ), RandomUtils.nextInt(1,12), RandomUtils.nextInt(1,28) ) )
                        .departmentId( n ).organizationId( organizationId )
                        .build();

            employeeService.addNewEmployee( ee1 );
        } ) );

    }

    private void insertDepartments() {
        Department accounting = new Department(21, "Finance");
        organizationService.upsertDepartment( accounting );

        Department hr = new Department(22, "HR");
        organizationService.upsertDepartment( hr );

        Department operations = new Department(23, "Operations");
        organizationService.upsertDepartment( operations );

        Department prodDev = new Department(24, "Product Development");
        organizationService.upsertDepartment( prodDev );
    }

    private void insertOrganizations() {
        Organization auto = new Organization(1,"ABC Auto");
        organizationService.upsertOrganization( auto );
        auto.setDescription( "test update" );
        organizationService.upsertOrganization( auto );

        Organization house = new Organization(2,"ABC Digital");
        organizationService.upsertOrganization( house );

        Organization property = new Organization(3,"ABC Media");
        organizationService.upsertOrganization( property );

    }
}
