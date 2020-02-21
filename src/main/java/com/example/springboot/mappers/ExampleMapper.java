package com.example.springboot.mappers;

import com.example.springboot.pojo.Example;

public interface ExampleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Example record);

    int insertSelective(Example record);

    Example selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Example record);

    int updateByPrimaryKey(Example record);
}