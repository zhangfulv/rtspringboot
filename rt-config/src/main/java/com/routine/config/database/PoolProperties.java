package com.routine.config.database;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName PoolProperties
 * @DESCRIPTION TODO 连接池基本属性信息
 * @Author zf
 * @Date 2019/1/15 9:59
 */
@Component
@ConfigurationProperties(value = "runtine.datasource.pool")
@Data
public class PoolProperties {
    private int initialSize;
    private int maxActive;
    private int maxIdle;
    private int minIdle;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private int maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private Boolean defaultReadOnly;
    private Boolean testWhileIdle;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;
    private String validationQuery;
    private int timeBetweenLogStatsMillis;

}
