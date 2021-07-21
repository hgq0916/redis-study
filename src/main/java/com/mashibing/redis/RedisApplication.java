package com.mashibing.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author hugangquan
 * @date 2021/07/21 08:14
 */
@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RedisApplication.class, args);
        RedisTest redisTest = applicationContext.getBean(RedisTest.class);
        redisTest.testRedis();
    }


}
