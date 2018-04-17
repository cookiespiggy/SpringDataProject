package com.imooc.service;

import com.imooc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void update(Integer id, Integer age) {
        // employeeRepository是代理对象
        employeeRepository.update(id, age);
    }


}
