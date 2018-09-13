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
import org.springframework.context.annotation.Primary;
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
// for db
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

	// bean for security database
	@Bean
	@Primary
	// @ConfigurationProperties(prefix = "user")
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

	@Bean
	public DataSource patientDataSource() {

		// connection pool
		ComboPooledDataSource patientDataSource = new ComboPooledDataSource();

		// jdbc driver class
		try {
			patientDataSource.setDriverClass(environment.getProperty("patient.jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}

		// connection confirmation
		logger.info(">>> jdbc.url=" + environment.getProperty("patient.jdbc.url"));
		logger.info(">>> jdbc.user=" + environment.getProperty("patient.jdbc.user"));

		// database connection props
		patientDataSource.setJdbcUrl(environment.getProperty("patient.jdbc.url"));
		patientDataSource.setUser(environment.getProperty("patient.jdbc.user"));
		patientDataSource.setPassword(environment.getProperty("patient.jdbc.password"));

		// connection pool props
		patientDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		patientDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		patientDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		patientDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return patientDataSource;
	}

	// Step 2: Setup Hibernate session factory

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	//
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(patientDataSource());
		sessionFactory.setPackagesToScan("com.ozii.klinika.entity");
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

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

	// Step 3: Setup Hibernate transaction manager

	@Bean
	@Autowired
	public HibernateTransactionManager myTransactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager myTransactionManager = new HibernateTransactionManager();
		myTransactionManager.setSessionFactory(sessionFactory);
		return myTransactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	private int getIntProperty(String propName) {

		String propVal = environment.getProperty(propName);
		int intPropVal = Integer.parseInt(propVal);

		return intPropVal;
	}

	/*
	 * To read resources: css...
	 * 
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#
	 * addResourceHandlers(org.springframework.web.servlet.config.annotation.
	 * ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}
