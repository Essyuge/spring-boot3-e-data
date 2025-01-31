package com.example.demo.rest;


import com.example.demo.Entity.Employee;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;
  //inject employee dao(constructor injection)

@Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
//get a single employee
    @GetMapping("/employee/{id}")

    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new RuntimeException("Employee ID Not Found: " + id);
        }
        return employee;

    }



//post an employee
  @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee){
    //just in case they pass an id in Json set id to 0
    employee.setId(0);
      Employee dbEmployee = employeeService.save(employee);
      return dbEmployee;
  }

  //update an employee by finding an id

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) {
        // Find existing employee by ID
        Employee existingEmployee = employeeService.findById(id);

        // Update fields of the existing employee
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setEmail(updatedEmployee.getEmail());

        // Save and return the updated employee
        return employeeService.save(existingEmployee);
    }


    //PUT2
    @PutMapping("/employee")
    public Employee updateEmployee( @RequestBody Employee updatedEmployee) {
        // Save and return the updated employee
        Employee dbEmployee = employeeService.save(updatedEmployee);
        return dbEmployee;
    }



    //delete an employee
@DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable int id){
    Employee employee = employeeService.findById(id);
    if (employee == null) {
        throw new RuntimeException("Employee ID Not Found: " + id);
    }
     employeeService.deleteById(id);
    return ( "Deleted Employee id " + id + " Successfully");

}



}
