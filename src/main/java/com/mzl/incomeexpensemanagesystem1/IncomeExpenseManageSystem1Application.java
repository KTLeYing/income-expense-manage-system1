package com.mzl.incomeexpensemanagesystem1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.mzl.incomeexpensemanagesystem1.mapper"})
//@EnableAdminServer  //开启监控可视化注解
public class IncomeExpenseManageSystem1Application {

    public static void main(String[] args) {
        SpringApplication.run(IncomeExpenseManageSystem1Application.class, args);
    }

}
