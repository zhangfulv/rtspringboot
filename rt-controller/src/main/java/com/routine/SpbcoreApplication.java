package com.routine;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "com.routine")
public class SpbcoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpbcoreApplication.class, args);
	}

}
