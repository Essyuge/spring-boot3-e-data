package com.example.demo.dao;

import com.example.demo.Entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDaoJpaImplementation implements EmployeeDao{

    //define field for entity manager
    private EntityManager entityManager;
    @Autowired
    //set up constructor injection
    public EmployeeDaoJpaImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        //execute query and get result
      List<Employee> employees = theQuery.getResultList();
        //return the result
        return employees;
    }

    @Override
    public Employee findById(int id) {
        //get an employee

        Employee employee = entityManager.find(Employee.class, id);

        //return the employee
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        //save employee
        //if id == 0 then save else update
        Employee dbemployee = entityManager.merge(employee);

        //return employee
        return dbemployee;
    }

    @Override
    public void deleteById(int id) {
        //find employee
        Employee employee = entityManager.find(Employee.class, id);

        //remove employee
        entityManager.remove(employee);
    }


}
