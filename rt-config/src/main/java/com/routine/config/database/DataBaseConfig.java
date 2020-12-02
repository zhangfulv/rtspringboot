package com.routine.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName DataBaseConfig
 * @DESCRIPTION TODO 配置数据源
 * @Author zf
 * @Date 2019/1/14 17:18
 */
@Configuration
public class DataBaseConfig {
    @Autowired
    private PoolProperties poolProperties;
    @Autowired
    private MySQLProperties mySQLProperties;

    @Bean(value="mysql")
    public DataSource mysqlDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(mySQLProperties.getDriverClassName());
        druidDataSource.setUsername(mySQLProperties.getUserName());
        druidDataSource.setPassword(mySQLProperties.getPassword());
        druidDataSource.setUrl(mySQLProperties.getUrl());

        druidDataSource.setInitialSize(poolProperties.getInitialSize());
        druidDataSource.setMaxActive(poolProperties.getMaxActive());
        druidDataSource.setMinIdle(poolProperties.getMinIdle());
        druidDataSource.setMaxWait(poolProperties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(poolProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(poolProperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(poolProperties.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setFilters(poolProperties.getFilters());
        druidDataSource.setDefaultReadOnly(poolProperties.getDefaultReadOnly());
        druidDataSource.setTestWhileIdle(poolProperties.getTestWhileIdle());
        druidDataSource.setTestOnBorrow(poolProperties.getTestOnBorrow());
        druidDataSource.setTestOnReturn(poolProperties.getTestOnReturn());
        druidDataSource.setValidationQuery(poolProperties.getValidationQuery());
        druidDataSource.setTimeBetweenLogStatsMillis(poolProperties.getTimeBetweenLogStatsMillis());
        return druidDataSource;
    }
}
