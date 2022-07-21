package com.cxp.openadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.cxp.openadmin.dao")
@SpringBootApplication
public class OpenAdminApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(OpenAdminApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(OpenAdminApplication.class, args);
    }

}
