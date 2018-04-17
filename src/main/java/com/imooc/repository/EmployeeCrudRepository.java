package com.imooc.repository;

import com.imooc.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeCrudRepository extends CrudRepository<Employee, Integer> {

}
