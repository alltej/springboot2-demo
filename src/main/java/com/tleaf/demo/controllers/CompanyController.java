package com.tleaf.demo.controllers;

import com.tleaf.demo.domain.Department;
import com.tleaf.demo.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Allan Tejano
 * 4/23/2018
 */
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired OrganizationService organizationService;

    @GetMapping("/organizations")
    public ResponseEntity<?> getOrganizations() {
        return new ResponseEntity<>( organizationService.getAllOrganizations(), HttpStatus.OK );
    }

    @GetMapping("/departments")
    public ResponseEntity<?> getDepartments() {
        return new ResponseEntity<>( organizationService.getAllDepartments(), HttpStatus.OK );
    }

    @PutMapping("/departments")
    public ResponseEntity<?> addDepartment(@RequestBody Department dept) {
        return new ResponseEntity<>( organizationService.upsertDepartment(dept), HttpStatus.OK );
    }
}
