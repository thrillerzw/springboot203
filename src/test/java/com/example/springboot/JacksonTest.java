package com.example.springboot;

import com.example.springboot.pojo.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @JsonProperty("Name")
 * private String name;
 *序列化{"id":1,"Name":"tom"}
 * 反序列化可以把上面大写的N序列化为小写的set到对象里
 *
 */
public class JacksonTest {

    public static void main(String[] args) throws JsonProcessingException {
        Student student=new Student();
        student.setId(1);
        student.setName("tom");
        String str = new ObjectMapper().writeValueAsString(student);
        System.out.println(str);
        Student student2= new ObjectMapper().readValue(str, Student.class);
        System.out.println(student2);
    }
}
