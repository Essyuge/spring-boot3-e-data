package com.example.demo.service;


import com.example.demo.Entity.Employee;
//import com.example.demo.dao.EmployeeDao;

import com.example.demo.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImplementation implements EmployeeService {
  //

    private EmployeeRepository employeeRepository;
@Autowired
    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
    Employee employee = null;

    if (result.isPresent()) {
        employee = result.get();
    }else{
        throw new RuntimeException("Id Not Found for Employee Id " + id);
    }
    return employee;
}


    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    @Override
    public void deleteById(int id) {
         employeeRepository.deleteById(id);
    }
}
