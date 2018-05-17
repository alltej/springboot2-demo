package com.tleaf.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Allan Tejano
 * 4/23/2018
 */
@Entity
@Table(name = "Departments")
public class Department {

    public static Department of( Integer departmentId ) {
        Department department = new Department();
        department.setDepartmentId( departmentId );
        return department;
    }

    public Department() {
    }

    public Department( Integer departmentId, String name ) {
        this.departmentId = departmentId;
        this.name = name;
    }

    @Id
    @Column(name = "department_id")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer departmentId;

    @Column(name = "name")
    private String name;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId( Integer departmentId ) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
