package com.example.springboot;

import com.example.springboot.mappers.ExampleMapper;
import com.example.springboot.pojo.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {

    @Autowired
    ExampleMapper exampleMapper;
    @Test
    public void testSelect(){
        Example example = exampleMapper.selectByPrimaryKey(1L);
        System.out.println(example.getAmount());
    }
}
