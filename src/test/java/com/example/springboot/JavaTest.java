package com.example.springboot;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class JavaTest {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        List<Object> subList = list.subList(0, 1);
        System.out.println(JSON.toJSON(subList));
        testString();
    }

    public static void testString(){
        String open = "openjdk";
        String s = new StringBuilder("open").append("jdk").toString();
        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s.intern() == s);
        System.out.println(s2.intern() == s2);
    }

}
