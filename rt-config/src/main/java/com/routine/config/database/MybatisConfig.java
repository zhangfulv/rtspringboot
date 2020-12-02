package com.routine.config.database;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName MybatisConfig
 * @DESCRIPTION TODO
 * @Author zf
 * @Date 2019/1/15 14:16
 */
@Configuration
@MapperScan(basePackages = "com.routine.rtmapper*",sqlSessionTemplateRef = "dbSqlSessionTemplate")
public class MybatisConfig {
    /* @Description  设置连接池到数据会话工厂里面，设置xml文件资源信息路径
     * @param dataSource
     * @return org.apache.ibatis.session.SqlSessionFactory
     * @Author zf
     * @Date 2019-01-02 10:29
     * @Update
     **/
    @Bean("dbSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysql") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.routine.rtpojo*");
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("classpath*:**/rtmapper/xml/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);


        return sqlSessionFactoryBean.getObject();
    }

    /* @Description   连接池对应数据资源事物
     * @param dataSource
     * @return org.springframework.jdbc.datasource.DataSourceTransactionManager
     * @Author zf
     * @Date 2019-01-02 10:30
     * @Update
     **/
    @Bean("dbTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("mysql")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /* @Description  建立sql会话模板
     * @param sqlSessionFactory
     * @return org.mybatis.spring.SqlSessionTemplate
     * @Author zf
     * @Date 2019-01-02 10:30
     * @Update
     **/
    @Bean("dbSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("dbSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
