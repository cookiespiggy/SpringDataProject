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
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class EmployeeRepositoryTest {
    private ApplicationContext ctx;
    private EmployeeRepository employeeRepository;
    private EmployeePagingAndSortingRepository employeePagingAndSortingRepository;
    private EmployeeJpaSpecificationExecutorRepository employeeJpaSpecificationExecutorRepository;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        employeeRepository = ctx.getBean(EmployeeRepository.class);
        employeePagingAndSortingRepository = ctx.getBean(EmployeePagingAndSortingRepository.class);
        employeeJpaSpecificationExecutorRepository = ctx.getBean(EmployeeJpaSpecificationExecutorRepository.class);
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

    /**
     * 分页
     */
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

    /**
     * 分页 排序
     */
    @Test
    public void testPageAndSort() {
        // 构建Sort
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(order);
        // page:你要查询第几页,从0开始    size:每页查询多少条
        Pageable pageable = new PageRequest(0, 5, sort);
        Page<Employee> page = employeePagingAndSortingRepository.findAll(pageable);
        System.out.println("查询的总页数" + page.getTotalPages());
        System.out.println("查询的总记录数" + page.getTotalElements());
        System.out.println("查询的当前第几页" + page.getNumber() + 1);
        System.out.println("查询的当前页面的集合" + page.getContent());
        System.out.println("查询的当前页面的记录数" + page.getNumberOfElements());
    }

    /**
     * 能够排序,还能分页,还能够添加一些查询条件(age>50)
     */
    @Test
    public void testQuery() {
        // 构建Sort
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(order);
        // 构建Pageable
        // page:你要查询第几页,从0开始    size:每页查询多少条
        Pageable pageable = new PageRequest(0, 5, sort);
        // 构建Specification
        Specification<Employee> specification = new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, // 你要查询的实体类
                                         CriteriaQuery<?> criteriaQuery, // 我们所需要添加的条件
                                         CriteriaBuilder criteriaBuilder) { // 构建Predicate
                // root (employee (age))
                Path path = root.get("age");
                return criteriaBuilder.gt(path, 50);
            }
        };
        Page<Employee> page = employeeJpaSpecificationExecutorRepository.findAll(specification, pageable);
        System.out.println("查询的总页数" + page.getTotalPages());
        System.out.println("查询的总记录数" + page.getTotalElements());
        System.out.println("查询的当前第几页" + page.getNumber() + 1);
        System.out.println("查询的当前页面的集合" + page.getContent());
        System.out.println("查询的当前页面的记录数" + page.getNumberOfElements());
    }
}
