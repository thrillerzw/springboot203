package com.example.springboot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.springboot.pojo.Student;
import org.springframework.util.CollectionUtils;

public class LambdaTest {


    public static void main(String[] args) {
//        list2map();
        listSort();

    }

    private static void listSort() {
        List<Student> list=new ArrayList<>();
        Student student=new Student();
        student.setId(1);
        student.setName("tom");
        Student student2=new Student();
        student2.setId(2);
        student2.setName("tom2");
        list.add(student);
        list.add(student2);

        Collections.sort(list,(o1,o2)->{
            //>返回1为正序，返回-1为降序
            if(o1.getId()>o2.getId()){
                return -1;
            }else if(o1.getId()<o2.getId()){
                return 1;
            }else{
                return 0;
            }
        });
        System.out.println(list);
    }


    private static void list2map() {
        List<Student> list=new ArrayList<>();
        /*Student student=new Student();
        student.setId(1);
        student.setName("tom");
        list.add(student);*/
//        list=null;
        //转为map,需要list判null
        if(!CollectionUtils.isEmpty(list)){
            Map<Integer, Student> map = list.stream()
                    .collect(Collectors.toMap(Student::getId, Student -> Student));
            System.out.println(map);
        }
    }


}
