package com.ozii.klinika.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ozii.klinika")
// databases properties
@PropertySource("classpath:persistence-mysql.properties")
@EnableTransactionManagement
public class AppConfig implements WebMvcConfigurer {

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

	// Bean for security database
	@Bean

	public DataSource securityDataSource() {

		// Connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		// JDBC driver class
		try {
			securityDataSource.setDriverClass(environment.getProperty("user.jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}

		// Connection confirmation
		logger.info(">>> jdbc.url=" + environment.getProperty("user.jdbc.url"));
		logger.info(">>> jdbc.user=" + environment.getProperty("user.jdbc.user"));

		// Database connection props
		securityDataSource.setJdbcUrl(environment.getProperty("user.jdbc.url"));
		securityDataSource.setUser(environment.getProperty("user.jdbc.user"));
		securityDataSource.setPassword(environment.getProperty("user.jdbc.password"));

		// Connection pool props
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return securityDataSource;
	}

	// Bean for crm database
	@Bean
	public DataSource patientDataSource() {

		// Connection pool
		ComboPooledDataSource patientDataSource = new ComboPooledDataSource();

		// JDBC driver class
		try {
			patientDataSource.setDriverClass(environment.getProperty("patient.jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}

		// Connection confirmation
		logger.info(">>> jdbc.url=" + environment.getProperty("patient.jdbc.url"));
		logger.info(">>> jdbc.user=" + environment.getProperty("patient.jdbc.user"));

		// Database connection props
		patientDataSource.setJdbcUrl(environment.getProperty("patient.jdbc.url"));
		patientDataSource.setUser(environment.getProperty("patient.jdbc.user"));
		patientDataSource.setPassword(environment.getProperty("patient.jdbc.password"));

		// Connection pool props
		patientDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		patientDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		patientDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		patientDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return patientDataSource;
	}
	
	// Pool props getter
	private int getIntProperty(String propName) {

		String propVal = environment.getProperty(propName);
		int intPropVal = Integer.parseInt(propVal);

		return intPropVal;
	}

	// Setup Hibernate session factory
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	// Hibernate Session Factory
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(patientDataSource());
		sessionFactory.setPackagesToScan("com.ozii.klinika.entity");
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}
	
	// Hibernate properties
	private final Properties hibernateProperties() {
		return new Properties() {
			{
				// setProperty("hibernate.hbm2ddl.auto",
				// environment.getProperty("hibernate.hbm2ddl.auto"));
				setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
				setProperty("hibernate.show_sql", "true");
			}
		};
	}

	// Hibernate transaction manager
	@Bean
	@Autowired
	public HibernateTransactionManager myTransactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager myTransactionManager = new HibernateTransactionManager();
		myTransactionManager.setSessionFactory(sessionFactory);
		return myTransactionManager;
	}

	// Enable exception translation
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}


	// Read resources: css...
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}
