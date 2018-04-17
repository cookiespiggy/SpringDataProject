package com.imooc.repository;

import com.imooc.entity.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends Repository<Employee, Integer> {
    /**
     * 根据名字查找员工
     *
     * @param name
     * @return
     */
    Employee findByName(String name);

    /**
     * 复杂查询方式编写示例 where name like ?% and age <?
     */
    List<Employee> findByNameStartingWithAndAgeLessThan(String name, Integer age);

    /**
     * 复杂查询方式编写示例 where name in(?,?,...) or age <?
     */
    List<Employee> findByNameInOrAgeLessThan(List<String> names, Integer age);

    /**
     * 获取id最大的员工
     * <p>
     * Employee为类名
     *
     * @return
     */
    @Query("select o from Employee o where id=(select max(id) from Employee t1)")
    Employee getEmployeeByMaxId();

    /**
     * 根据索引参数进行查询
     */
    @Query("select o from Employee o where o.name =?1 and o.age=?2")
    List<Employee> queryParams1(String name, Integer age);

    /**
     * 根据命名参数进行查询
     */
    @Query("select o from Employee o where o.name =:name and o.age=:age")
    List<Employee> queryParams2(@Param("name") String name, @Param("age") Integer age);

    @Query("select o from Employee o where o.name like %?1% ")
    List<Employee> queryLike1(String name);

    @Query("select o from Employee o where o.name like %:name% ")
    List<Employee> queryLike2(@Param("name") String name);

    /**
     * 原生SQL查询
     *
     * @return
     */
    @Query(nativeQuery = true, value = "select count(1) from employee")
    long getCount();

    /**
     * 根据ID更新年龄
     */
    @Modifying
    @Query("update Employee o set o.age= :age where o.id = :id")
    void update(@Param("id") Integer id, @Param("age") Integer age);
}
