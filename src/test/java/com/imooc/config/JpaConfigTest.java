package com.imooc.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JpaConfigTest {
    private ApplicationContext ctx;
    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
    }
    @After
    public void tearDown() {
        ctx = null;
    }
    // 如果可以根据一个类创建一张表,就说明配置没有问题
    @Test
    public void testEntityManagerFactoryBean() {

    }
}
