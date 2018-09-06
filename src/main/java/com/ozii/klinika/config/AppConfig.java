package com.ozii.klinika.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ozii.klinika")
// for db
@PropertySource("classpath:persistence-mysql.properties")
@EnableTransactionManagement

public class AppConfig {

	// variable to hold the properties
	@Autowired
	private Environment environment;

	// logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());

	// bean for ViewResolver
	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	// bean for security database
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "user")
	public DataSource securityDataSource() {

		// connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		// jdbc driver class
		try {
			securityDataSource.setDriverClass(environment.getProperty("user.jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}

		// connection confirmation
		logger.info(">>> jdbc.url=" + environment.getProperty("user.jdbc.url"));
		logger.info(">>> jdbc.user=" + environment.getProperty("user.jdbc.user"));

		// database connection props
		securityDataSource.setJdbcUrl(environment.getProperty("user.jdbc.url"));
		securityDataSource.setUser(environment.getProperty("user.jdbc.user"));
		securityDataSource.setPassword(environment.getProperty("user.jdbc.password"));

		// connection pool props
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return securityDataSource;
	}
//
//	// bean for patient database
//	@ConfigurationProperties(prefix = "patient")
//	@Bean
//	public DataSource patientDataSource() {
//
//		// connection pool
//		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
//
//		// jdbc driver class
//		try {
//			securityDataSource.setDriverClass(environment.getProperty("patient.jdbc.driver"));
//		} catch (PropertyVetoException exc) {
//			throw new RuntimeException(exc);
//		}
//
//		// connection confirmation
//		logger.info(">>> jdbc.url=" + environment.getProperty("patient.jdbc.url"));
//		logger.info(">>> jdbc.user=" + environment.getProperty("patient.jdbc.user"));
//
//		// database connection props
//		securityDataSource.setJdbcUrl(environment.getProperty("patient.jdbc.url"));
//		securityDataSource.setUser(environment.getProperty("patient.jdbc.user"));
//		securityDataSource.setPassword(environment.getProperty("patient.jdbc.password"));
//
//		// connection pool props
//		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
//		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
//		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
//		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
//
//		return securityDataSource;
//	}
//
//	@Bean
//	@Autowired
//	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(sessionFactory);
//		return txManager;
//	}
//
//	@Bean
//	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//		return new PersistenceExceptionTranslationPostProcessor();
//	}
//
//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(patientDataSource());
//		sessionFactory.setPackagesToScan(new String[] { "org.baeldung.spring.persistence.model" });
//		sessionFactory.setHibernateProperties(hibernateProperties());
//
//		return sessionFactory;
//	}
//
//	Properties hibernateProperties() {
//		return new Properties() {
//			{
//				setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
//				setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
//				setProperty("hibernate.globally_quoted_identifiers", "true");
//			}
//		};
//	}

	private int getIntProperty(String propName) {

		String propVal = environment.getProperty(propName);
		int intPropVal = Integer.parseInt(propVal);

		return intPropVal;
	}

}
