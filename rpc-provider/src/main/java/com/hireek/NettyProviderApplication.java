package com.hireek;

import com.hireek.server.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述
 *
 * @Author Hireek
 * @Date: 2021/11/16 22:27
 */
@ComponentScan(basePackages = {"com.hireek.service.impl", "com.hireek.spring"})
@SpringBootApplication
public class NettyProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(NettyProviderApplication.class, args);
        new NettyServer("127.0.0.1", 8080).startNettyServer();
    }
}
