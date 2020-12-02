package com.routine.config.database;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName DataSourceMySQL
 * @DESCRIPTION TODO
 * @Author zf
 * @Date 2020/12/2 14:46
 */
@Component
@Data
@ConfigurationProperties(prefix = "runtine.datasource.mysql")
public class MySQLProperties {
    private String driverClassName;
    private String userName;
    private String password;
    private String url;
}
