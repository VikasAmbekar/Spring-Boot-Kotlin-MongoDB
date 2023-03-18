package com.kotlin.service

import com.kotlin.entity.Employee
import com.kotlin.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeService {

    @Autowired
    private lateinit var repository: EmployeeRepository;

    fun getAllEmployees(): List<Employee>{
        return repository.findAll();
    }

    fun getEmployeeById(id : String) : Employee {
        return repository.findById(id).get();
    }

    fun addEmployee(employee: Employee) : Employee {
        return repository.save(employee);
    }

    fun updateEmployee(id : String, employee: Employee): Employee? {
        if (employee.id == null) {
            return null
        }
        val existingEmployee = getEmployeeById(employee.id!!)
        if (existingEmployee == null) {
            return null
        }
        return repository.save(employee)
    }

    fun deleteEmployee(id: String): Boolean {
        if (getEmployeeById(id) == null) {
            return false
        }
        repository.deleteById(id)
        return true
    }
}
