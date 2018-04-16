package com.imooc.config;

import com.imooc.dao.StudentDaoSpringJdbcImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * 优化老师的代码,老师用的是xml的方式
 */
@Configuration
public class JDBCConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_data");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public StudentDaoSpringJdbcImpl studentDaoSpringJdbcImpl() {
        StudentDaoSpringJdbcImpl studentDaoSpringJdbcImpl = new StudentDaoSpringJdbcImpl();
        // 注意此处!!!需要把jdbcTemplate设置进去,也称作是注入吧,很形象
        studentDaoSpringJdbcImpl.setJdbcTemplate(jdbcTemplate());
        return studentDaoSpringJdbcImpl;
    }
}
