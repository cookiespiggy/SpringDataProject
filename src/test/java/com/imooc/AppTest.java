package com.imooc;

import com.imooc.config.JDBCConfig;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {
    protected ApplicationContext applicationContext;


    @Before
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(JDBCConfig.class);
    }

    @After
    public void tearDown() {
        applicationContext = null;
    }
}
