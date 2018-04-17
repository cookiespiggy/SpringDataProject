package com.imooc.repository;

import com.imooc.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeePagingAndSortingRepository extends PagingAndSortingRepository<Employee, Integer> {
}
