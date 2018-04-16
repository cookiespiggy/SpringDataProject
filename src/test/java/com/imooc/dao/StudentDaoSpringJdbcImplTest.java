package com.imooc.dao;

import com.imooc.AppTest;
import com.imooc.domain.Student;
import org.junit.Test;

import java.util.List;

public class StudentDaoSpringJdbcImplTest extends AppTest {

    @Test
    public void testFindAll() {
        StudentDaoSpringJdbcImpl springJdbc = applicationContext.getBean(StudentDaoSpringJdbcImpl.class);
        List<Student> students = springJdbc.findAll();
        students.forEach(o -> {
            System.out.println(o.getId() + "====" + o.getName() + "====" + o.getAge());
        });
    }

    @Test
    public void testSave() {
        StudentDaoSpringJdbcImpl springJdbc = applicationContext.getBean(StudentDaoSpringJdbcImpl.class);
        Student student = new Student();
        student.setName("小王2");//乱码了..
        student.setAge(18);
        springJdbc.save(student);
    }
}
