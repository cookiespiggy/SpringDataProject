package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * 优化老师的xml配置
 */
@Configuration
@ComponentScan(basePackages = "com.imooc") //ioc扫描
@EnableJpaRepositories(basePackages = "com.imooc.repository") //开启仓库扫描
@EnableTransactionManagement // 开启注解事务配置
public class JpaConfig {

    /**
     * 配置数据源
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_data");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    /**
     * 配置EntityManagerFactoryBean
     *
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter jpaVendor = new HibernateJpaVendorAdapter();
        jpaVendor.setGenerateDdl(false);//不让打印

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendor);
        entityManagerFactoryBean.setPackagesToScan("com.imooc.entity");
        entityManagerFactoryBean.setJpaProperties(properties());
        return entityManagerFactoryBean;
    }


    @Bean
    public Properties properties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    /**
     * 配置事务管理器
     * <p>
     * 注意上面@Bean声明了一个entityManagerFactorybean,这里直接可以参数注入实例了
     *
     * @return
     */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
