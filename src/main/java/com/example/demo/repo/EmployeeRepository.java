package com.example.demo.repo;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "employ")
public interface EmployeeRepository extends JpaRepository <Employee, Integer>{

}
