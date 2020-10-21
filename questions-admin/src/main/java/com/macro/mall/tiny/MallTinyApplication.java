package com.macro.mall.tiny;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages ="com.macro.mall.tiny.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class MallTinyApplication {

    public static void main(String[] args) {
        ElasticApmAttacher.attach();
        SpringApplication.run(MallTinyApplication.class, args);
    }

}
