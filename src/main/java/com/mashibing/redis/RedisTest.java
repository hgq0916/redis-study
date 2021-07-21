package com.mashibing.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author hugangquan
 * @date 2021/07/21 08:18
 */
@Component
public class RedisTest {

    @Autowired
    @Qualifier("myRedisTemplate")
    private RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void testRedis(){
        //使用低阶api
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        RedisConnection connection = connectionFactory.getConnection();
        connection.stringCommands().set("hello".getBytes(),"haha".getBytes());
        String str = new String(connection.stringCommands().get("hello".getBytes()));
        System.out.println(str);

        //使用高阶api

        redisTemplate.opsForValue().set("k1","beaut");
        String k1 = (String) redisTemplate.opsForValue().get("k1");
        System.out.println(k1);

        Person person = new Person();
        person.setAge(11);
        person.setName("sean green");

        Map map = objectMapper.convertValue(person, Map.class);

        redisTemplate.opsForHash().putAll("sean",map);

        Map<Object, Object> sean = redisTemplate.opsForHash().entries("sean");

        Person person1 = objectMapper.convertValue(sean, Person.class);

        System.out.println(person1.getName()+","+person1.getAge());

        //自定义序列化方式
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));

        redisTemplate.opsForHash().putAll("sean1",map);

        Map<Object, Object> sean1 = redisTemplate.opsForHash().entries("sean1");

        Person person2 = objectMapper.convertValue(sean1, Person.class);

        System.out.println(person2.getName()+","+person2.getAge());

        //redis发布订阅
        redisTemplate.convertAndSend("mychannel","heihei");

        redisTemplate.getConnectionFactory().getConnection().subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] bytes) {
                byte[] body = message.getBody();
                System.out.println(new String(body));
            }
        },"mychannel".getBytes());

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
