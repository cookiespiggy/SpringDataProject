package com.imooc.repository;

import com.imooc.config.JpaConfig;
import com.imooc.entity.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EmployeeRepositoryTest {
    private ApplicationContext ctx;
    private EmployeeRepository employeeRepository;
    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        employeeRepository = ctx.getBean(EmployeeRepository.class);
    }
    @After
    public void tearDown() {
        ctx = null;
    }
    @Test
    public void testFindByName() {
        Employee employee = employeeRepository.findByName("xiaozhang");
        System.out.println(employee);
    }
}
