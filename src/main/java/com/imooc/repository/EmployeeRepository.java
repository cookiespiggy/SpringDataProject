package com.imooc.repository;

import com.imooc.entity.Employee;
import org.springframework.data.repository.Repository;

public interface EmployeeRepository extends Repository<Employee, Integer> {
    /**
     * 根据名字查找员工
     *
     * @param name
     * @return
     */
    Employee findByName(String name);
}
