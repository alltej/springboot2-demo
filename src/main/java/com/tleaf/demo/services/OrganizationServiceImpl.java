package com.tleaf.demo.services;

import com.tleaf.demo.domain.Department;
import com.tleaf.demo.domain.Organization;
import com.tleaf.demo.repositories.DepartmentRepository;
import com.tleaf.demo.repositories.OrganizationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Allan Tejano
 * 4/23/2018
 */
@Log4j2
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired DepartmentRepository departmentRepository;
    @Autowired OrganizationRepository organizationRepository;

    @Cacheable(value="departments")
    @Transactional(readOnly = true)
    @Override public List<Department> getAllDepartments() {
        log.info("fetch departments from db"  );
        return departmentRepository.findAll();
    }

    @Cacheable(value="organizations")
    @Transactional(readOnly = true)
    @Override public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    @CacheEvict(value = "departments", allEntries = true)
    @Transactional
    @Override public Department upsertDepartment( Department department ) {
        return  departmentRepository.save( department );
    }

    @CacheEvict(value = "organizations", allEntries = true)
    @Transactional
    @Override public Organization upsertOrganization( Organization organization ) {
        return organizationRepository.save( organization );
    }


    @Cacheable(value="departments", key="#departmentId")
    @Transactional(readOnly = true)
    @Override public Department findDepartmentById( Integer departmentId ) {
        return departmentRepository.findById(departmentId).orElse( null );
    }

    @Cacheable(value="organizations", key="#organizationId")
    @Transactional(readOnly = true)
    @Override public Organization findOrganizationById( Integer organizationId ) {
        return organizationRepository.findById(organizationId).orElse( null );
    }
}
