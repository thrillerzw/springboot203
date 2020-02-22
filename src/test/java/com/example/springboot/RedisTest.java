package com.example.springboot;

import com.example.springboot.pojo.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test(){
        stringRedisTemplate.opsForValue().set("string-key","hi");
        String strValue = stringRedisTemplate.opsForValue().get("string-key");
        stringRedisTemplate.opsForList().leftPush("list-key","tom");
        String listPopValue = stringRedisTemplate.opsForList().rightPop("list-key");
        stringRedisTemplate.opsForSet().add("set-key","a");
        String setValue = stringRedisTemplate.opsForSet().pop("set-key");
        stringRedisTemplate.opsForHash().put("hash-key","id","20");
        Object hashValue = stringRedisTemplate.opsForHash().get("hash-key", "id");
        stringRedisTemplate.opsForZSet().add("zset-key","apple",10.00);
        Set<String> zsetAllData = stringRedisTemplate.opsForZSet().range("zset-key", 0, -1);
    }
    @Test
    public void testRedisTemplate(){
        Example example=new Example();
        example.setId(1L);
        example.setAmount(new BigDecimal("20.05"));
        example.setMessageContent("内容");
        redisTemplate.opsForValue().set("example1",example);
        Example cacheExample = (Example)redisTemplate.opsForValue().get("example1");
    }

}
