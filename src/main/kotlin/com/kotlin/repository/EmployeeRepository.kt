package com.kotlin.repository

import com.kotlin.entity.Employee
import org.springframework.data.mongodb.repository.MongoRepository

interface EmployeeRepository : MongoRepository<Employee, String> {
}
