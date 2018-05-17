package com.tleaf.demo.services;

import com.tleaf.demo.domain.Department;
import com.tleaf.demo.domain.Organization;

import java.util.List;

/**
 * @author Allan Tejano
 * 4/23/2018
 */
public interface OrganizationService {

    List<Department> getAllDepartments();
    List<Organization> getAllOrganizations();

    Department upsertDepartment( Department department );
    Organization upsertOrganization( Organization organization );

    Department findDepartmentById( Integer departmentId );
    Organization findOrganizationById( Integer organization );

}
