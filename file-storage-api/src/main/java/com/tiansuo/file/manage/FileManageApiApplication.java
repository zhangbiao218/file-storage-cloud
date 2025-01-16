package com.tiansuo.file.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class FileManageApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileManageApiApplication.class, args);
        System.out.println("=================================服务启动成功!=================================");
    }

}
