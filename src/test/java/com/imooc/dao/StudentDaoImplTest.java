package com.imooc.dao;

import com.imooc.domain.Student;
import org.junit.Test;

import java.util.List;

public class StudentDaoImplTest {
    @Test
    public void testFindAll() {
        StudentDao studentDao = new StudentDaoImpl();
        List<Student> students = studentDao.findAll();
        students.forEach(o -> {
            System.out.println(o.getId() + "====" + o.getName() + "====" + o.getAge());
        });
    }

    @Test
    public void testSave() {
        StudentDao studentDao = new StudentDaoImpl();
        Student student = new Student();
        student.setName("小王");//乱码了..
        student.setAge(18);
        studentDao.save(student);
    }
}
