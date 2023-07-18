package com.szcgc.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @Title: WebJpaConfig.java
 * @Description: TODO
 * @author liaohong
 * @date May 15, 2019 2:30:36 PM
 * @version V1.0
 */
@Configuration
@EnableTransactionManagement
@EnableScheduling
@EnableAspectJAutoProxy
@EnableAsync
@EnableJpaRepositories(basePackages = { WebJpaConfig.PACKAGE_REPOSITORY_NAME })
@PropertySource("classpath:db.properties")
public class WebJpaConfig {

    public static final String PERSISTENCE_NAME = "com.szcgc.persistence";
    public static final String PACKAGE_REPOSITORY_NAME = "com.szcgc.*";
    public static final String PACKAGE_MODEL_NAME = "com.szcgc.*";

    private static final String PROPERTY_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_DATABASE_URL = "db.url";
    private static final String PROPERTY_DATABASE_USERNAME = "db.username";
    private static final String PROPERTY_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_DATABASE_CACHEPREPSTMTS = "cachePrepStmts";
    private static final String PROPERTY_DATABASE_PREPSTMTCACHESIZE = "prepStmtCacheSize";
    private static final String PROPERTY_DATABASE_PREPSTMTCACHESQLLIMIT = "prepStmtCacheSqlLimit";
    private static final String PROPERTY_DATABASE_PRE = "db.";

    private static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";

    @Resource
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPersistenceUnitName(PERSISTENCE_NAME);
        factory.setJpaVendorAdapter(vendorAdapter());
        factory.setJpaProperties(jpaProperties());
        factory.setPackagesToScan(PACKAGE_MODEL_NAME);
        factory.afterPropertiesSet();
        factory.setLoadTimeWeaver(loadTimeWeaver());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean
    public DataSource dataSource() {
        System.out.println("!!!!!!!!!!!!!!!" + env.getRequiredProperty(PROPERTY_DATABASE_URL));
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getRequiredProperty(PROPERTY_DATABASE_DRIVER));
        config.setJdbcUrl(env.getRequiredProperty(PROPERTY_DATABASE_URL));
        config.setUsername(env.getRequiredProperty(PROPERTY_DATABASE_USERNAME));
        config.setPassword(env.getRequiredProperty(PROPERTY_DATABASE_PASSWORD));
        config.addDataSourceProperty(PROPERTY_DATABASE_CACHEPREPSTMTS,
                env.getRequiredProperty(PROPERTY_DATABASE_PRE + PROPERTY_DATABASE_CACHEPREPSTMTS));
        config.addDataSourceProperty(PROPERTY_DATABASE_PREPSTMTCACHESIZE,
                env.getRequiredProperty(PROPERTY_DATABASE_PRE + PROPERTY_DATABASE_PREPSTMTCACHESIZE));
        config.addDataSourceProperty(PROPERTY_DATABASE_PREPSTMTCACHESQLLIMIT,
                env.getRequiredProperty(PROPERTY_DATABASE_PRE + PROPERTY_DATABASE_PREPSTMTCACHESQLLIMIT));
        return new HikariDataSource(config);
    }

//	@Bean
//	public DataSource dataSource() {
//		System.out.println("!!!!!!!!!!!!!!!"+env.getRequiredProperty(PROPERTY_DATABASE_URL));
//		 PoolProperties p = new PoolProperties();
//         p.setUrl(env.getRequiredProperty(PROPERTY_DATABASE_URL));
//         p.setDriverClassName("com.mysql.jdbc.Driver");
//         p.setUsername(env.getRequiredProperty(PROPERTY_DATABASE_USERNAME));
//         p.setPassword(env.getRequiredProperty(PROPERTY_DATABASE_PASSWORD));
//         p.setJmxEnabled(true);
//         p.setTestWhileIdle(false);
//         p.setTestOnBorrow(true);
//         p.setValidationQuery("SELECT 1");
//         p.setTestOnReturn(false);
//         p.setValidationInterval(30000);
//         p.setTimeBetweenEvictionRunsMillis(30000);
//         p.setMaxActive(100);
//         p.setInitialSize(10);
//         p.setMaxWait(10000);
//         p.setRemoveAbandonedTimeout(60);
//         p.setMinEvictableIdleTimeMillis(30000);
//         p.setMinIdle(10);
//         p.setLogAbandoned(true);
//         p.setRemoveAbandoned(true);
//         p.setJdbcInterceptors(
//           "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
//           "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
//         org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
//         ds.setPoolProperties(p);
//         return ds;
//	}

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    private HibernateJpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(Boolean.TRUE);
        vendorAdapter.setShowSql(Boolean.parseBoolean(env.getRequiredProperty(PROPERTY_HIBERNATE_SHOW_SQL)));
        return vendorAdapter;
    }

    private Properties jpaProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.put(PROPERTY_HIBERNATE_HBM2DDL_AUTO, env.getProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO));
        jpaProperties.put(PROPERTY_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_HIBERNATE_DIALECT));
        jpaProperties.put(PROPERTY_HIBERNATE_FORMAT_SQL, env.getRequiredProperty(PROPERTY_HIBERNATE_FORMAT_SQL));
        jpaProperties.put(PROPERTY_HIBERNATE_NAMING_STRATEGY,
                env.getRequiredProperty(PROPERTY_HIBERNATE_NAMING_STRATEGY));
        jpaProperties.put(PROPERTY_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_HIBERNATE_SHOW_SQL));
        return jpaProperties;
    }

    private LoadTimeWeaver loadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }

}
