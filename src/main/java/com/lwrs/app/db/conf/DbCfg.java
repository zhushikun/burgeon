package com.lwrs.app.db.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.lwrs.app.db.DBProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Slf4j
@Configuration
@MapperScan(basePackages = {"com.lwrs.app.db.mapper"})
public class DbCfg {
    public static final String DB_PROPERTY_CONFIG_NAME = "dBProperties";
    private static final String DB_PROPERTY_PREFIX = "lwrs.db.account";
    private static final String DATA_SOURCE_NAME = "dataSource";
    private static final String SESSION_FACTORY_NAME = "sessionFactory";

    @Bean(name = DB_PROPERTY_CONFIG_NAME)
    @ConfigurationProperties(DB_PROPERTY_PREFIX)
    public DBProperties untDBProperties() {
        return new DBProperties();
    }

    @Bean(name = DATA_SOURCE_NAME)
    public DataSource dataSource(@Qualifier(DB_PROPERTY_CONFIG_NAME) DBProperties dbProperties){
        DruidDataSource ds = new DruidDataSource();
        log.info("db connection url:{}",dbProperties.getUrl());
        ds.setUrl(dbProperties.getUrl());
        ds.setUsername(dbProperties.getUserName());
        ds.setPassword(dbProperties.getPassword());
        ds.setDriverClassName(dbProperties.getDriverClassName());
        return ds;
    }

    @Bean(name = SESSION_FACTORY_NAME)
    @Autowired
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        //factoryBean.setPlugins(new Interceptor[] {new SqlTraceInterceptor()});
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCacheEnabled(false);//全局禁用缓存
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);
        return factoryBean;
    }


}
