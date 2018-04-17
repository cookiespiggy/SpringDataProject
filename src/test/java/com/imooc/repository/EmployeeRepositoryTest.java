package com.imooc.repository;

import com.imooc.config.JpaConfig;
import com.imooc.entity.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class EmployeeRepositoryTest {
    private ApplicationContext ctx;
    private EmployeeRepository employeeRepository;
    private EmployeePagingAndSortingRepository employeePagingAndSortingRepository;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        employeeRepository = ctx.getBean(EmployeeRepository.class);
        employeePagingAndSortingRepository = ctx.getBean(EmployeePagingAndSortingRepository.class);
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

    @Test
    public void testPage() {
        // page:你要查询第几页,从0开始    size:每页查询多少条
        Pageable pageable = new PageRequest(0, 5);
        Page<Employee> page = employeePagingAndSortingRepository.findAll(pageable);
        System.out.println("查询的总页数" + page.getTotalPages());
        System.out.println("查询的总记录数" + page.getTotalElements());
        System.out.println("查询的当前第几页" + page.getNumber() + 1);
        System.out.println("查询的当前页面的集合" + page.getContent());
        System.out.println("查询的当前页面的记录数" + page.getNumberOfElements());
    }

    @Test
    public void testPageAndSort() {
        // 构建Sort
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"id");
        Sort sort = new Sort(order);
        // page:你要查询第几页,从0开始    size:每页查询多少条
        Pageable pageable = new PageRequest(0, 5,sort);
        Page<Employee> page = employeePagingAndSortingRepository.findAll(pageable);
        System.out.println("查询的总页数" + page.getTotalPages());
        System.out.println("查询的总记录数" + page.getTotalElements());
        System.out.println("查询的当前第几页" + page.getNumber() + 1);
        System.out.println("查询的当前页面的集合" + page.getContent());
        System.out.println("查询的当前页面的记录数" + page.getNumberOfElements());
    }
}
