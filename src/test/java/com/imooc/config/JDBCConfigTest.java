package com.imooc.config;

import com.imooc.AppTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JDBCConfigTest extends AppTest {


    @Test
    public void testDataSource() {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        Assert.assertNotNull(dataSource);
    }

    @Test
    public void testJdbcTemplate() {
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        Assert.assertNotNull(jdbcTemplate);
    }
}
