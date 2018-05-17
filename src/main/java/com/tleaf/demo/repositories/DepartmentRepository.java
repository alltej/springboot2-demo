package com.tleaf.demo.repositories;

import com.tleaf.demo.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Allan Tejano
 * 4/23/2018
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
