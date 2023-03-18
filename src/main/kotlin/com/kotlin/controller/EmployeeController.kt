package com.kotlin.controller

import com.kotlin.entity.Employee
import com.kotlin.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
class EmployeeController {

    @Autowired
    private lateinit var service: EmployeeService

    @PostMapping("/")
    fun addEmployee(@RequestBody employee: Employee): ResponseEntity<Employee> {
        val newEmployee = service.addEmployee(employee)
        return ResponseEntity(newEmployee, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable id: String): ResponseEntity<Employee> {
        val employee = service.getEmployeeById(id)
        if (employee == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(employee, HttpStatus.OK)
    }

    @GetMapping("/")
    fun getAllEmployees(): ResponseEntity<List<Employee>> {
        val employees = service.getAllEmployees()
        return ResponseEntity(employees, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateEmployee(@PathVariable id : String, @RequestBody employee: Employee): ResponseEntity<Employee> {
        val updatedEmployee = service.updateEmployee(id, employee)
        if (updatedEmployee == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(updatedEmployee, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: String): ResponseEntity<Unit> {
        val isDeleted = service.deleteEmployee(id);
        if (isDeleted) {
            return ResponseEntity(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

}

