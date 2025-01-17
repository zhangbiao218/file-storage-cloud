package com.tiansuo.file.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.tiansuo.file.storage.core.mapper")
@SpringBootApplication
public class FileStorageCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileStorageCoreApplication.class, args);
        System.out.println("=================================服务启动成功!=================================");
    }

}
