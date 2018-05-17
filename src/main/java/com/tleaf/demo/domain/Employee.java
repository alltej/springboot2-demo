package com.tleaf.demo.domain;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Allan Tejano
 * 4/20/2018
 */
@Entity
@Table(name = "Employees")
public class Employee {

    @Id
    @Column(name="employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employeeId;

    @Column(name = "ssn")
    private String ssn;

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column( name= "dateOfBirth" )
    private LocalDate dateOfBirth;

    //@Column(name = "department")
    @OneToOne
    private Department department;

    @OneToOne
    private Organization organization;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId( Integer employeeId ) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth( LocalDate dateOfBirth ) {
        this.dateOfBirth = dateOfBirth;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment( Department department ) {
        this.department = department;
    }
//    public void setDepartmentId( Integer departmentId ) {
//        if (this.department != null) {
//            this.department.setDepartmentId( departmentId );
//        }else{
//            this.department = Department.of( departmentId );
//        }
//        //this.department.setDepartmentId( departmentId );
//    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization( Organization organization ) {
        this.organization = organization;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn( String ssn ) {
        this.ssn = ssn;
    }

}
