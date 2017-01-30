package com.zeal.spending.configuration;

import com.zeal.spending.model.CategoryLookupEntity;
import com.zeal.spending.model.PaymethodLookupEntity;
import com.zeal.spending.model.ReceiptEntity;
import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangchun-imac on 1/27/17.
 */

@Configuration
@EnableJpaRepositories("com.zeal.spending.repository")
@EnableTransactionManagement
public class DatabaseConfiguration implements EnvironmentAware {
    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    private RelaxedPropertyResolver propertyResolver;

    @Inject
    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        propertyResolver = new RelaxedPropertyResolver(environment, "spending.datasource.");
    }

    @Bean
    public DataSource dataSource(){
        log.debug("configure datasource");

        String url = propertyResolver.getProperty("url");
        String username = propertyResolver.getProperty("username");
        String password = propertyResolver.getProperty("password");

        if(url == null) {
            log.error("database connection pool confugration is incorrect, current profiles are {}", Arrays.toString(env.getActiveProfiles()));
            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }

        BasicDataSource dataSource = new BasicDataSource();
        //TODO replace with environment variable
        dataSource.setDriverClassName("org.postgresql.driver");
        dataSource.setUrl("jdbc:postgresql://localhost:3306/leaz");
        dataSource.setUsername("dev");
        dataSource.setPassword("welcome");

        //set some properties
        dataSource.setValidationQuery("select 1");
        dataSource.setMaxActive(10);
        dataSource.setMaxWait(1);
        dataSource.setMinIdle(1);

        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

        sessionBuilder.setProperty("jadira.usertype.autoRegisterUserTypes", "true");
        sessionBuilder.setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        sessionBuilder.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        sessionBuilder.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        sessionBuilder.setProperty("hibernate.jadira.usertype.autoRegisterUserTypes", "true");
        sessionBuilder.setProperty("hibernate.jadira.usertype.javaZone", "UTC");
        sessionBuilder.setProperty("hibernate.jadira.usertype.databaseZone", "UTC");

        sessionBuilder.addAnnotatedClasses(CategoryLookupEntity.class);
        sessionBuilder.addAnnotatedClasses(PaymethodLookupEntity.class);
        sessionBuilder.addAnnotatedClasses(ReceiptEntity.class);


        return sessionBuilder.buildSessionFactory();
    }

    @Bean(name = {"org.springframework.boot.autoconfigure.AutoConfigurationUtils.basePackages"})
    public List<String> getBasePackages() {
        List<String> basePackages = new ArrayList<>();
        basePackages.add("com.zeal.spending.model");
        return basePackages;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(
            SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
                sessionFactory);

        return transactionManager;
    }

    @Bean
    public SpringLiquibase liquibase() {
        log.debug("Configuring Liquibase");
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("classpath:resources/changelog.xml");
        liquibase.setContexts("development, production");
        return liquibase;
    }

}
