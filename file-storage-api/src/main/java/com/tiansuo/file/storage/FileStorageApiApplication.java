package com.tiansuo.file.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileStorageApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileStorageApiApplication.class, args);
        System.out.println("=================================服务启动成功!=================================");
    }

}
