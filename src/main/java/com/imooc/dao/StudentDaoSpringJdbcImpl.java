package com.imooc.dao;

import com.imooc.domain.Student;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * StudentDao访问接口的实现类:通过最Spring Jdbc方式操作数据库
 */
public class StudentDaoSpringJdbcImpl implements StudentDao {
    private JdbcTemplate jdbcTemplate;

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String sql = "select id,name ,age from student";
        jdbcTemplate.query(sql, (resultSet) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setAge(age);
            students.add(student);
        });
        return students;
    }

    public void save(Student student) {
        String sql = "insert into student(name,age) values (?,?)";
        jdbcTemplate.update(sql, new Object[]{student.getName(), student.getAge()});
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
