package com.tleaf.demo.repositories;

import com.tleaf.demo.domain.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * @author Allan Tejano
 * 4/20/2018
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, QueryByExampleExecutor<Employee> {

    List<Employee> findAllBy(Pageable pageable);
    Employee findByEmployeeId(Integer employeeId);
}
