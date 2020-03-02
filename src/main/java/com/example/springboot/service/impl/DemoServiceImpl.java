package com.example.springboot.service.impl;

import com.example.springboot.mappers.ExampleMapper;
import com.example.springboot.mappers.StudentMapper;
import com.example.springboot.pojo.Example;
import com.example.springboot.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ExampleMapper exampleMapper;
    @Transactional
    public void transactionalService(){
        Example example=new Example();
        example.setId(1L);
        example.setStatus((short)100);
        exampleMapper.updateByPrimaryKeySelective(example);
//        int a=10/0;
        Example example2=new Example();
        example2.setId(2L);
        example2.setStatus((short)200);
        exampleMapper.updateByPrimaryKeySelective(example2);


    }

    @Override
    public void print() {
        System.out.println("DemoServiceImpl print()");
    }
}
