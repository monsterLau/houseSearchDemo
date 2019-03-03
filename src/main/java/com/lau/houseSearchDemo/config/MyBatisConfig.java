package com.lau.houseSearchDemo.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class MyBatisConfig {
    @Resource
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean(ApplicationContext applicationContext) throws IOException {

        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();

        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath*:mapper/*.xml"));

        //别名注册器
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
        typeAliasRegistry.registerAlias("STDOUT_LOGGING", MyBatisConfig.class);

        return sqlSessionFactory;

    }

    //    pageHelper分页插件
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }


}
