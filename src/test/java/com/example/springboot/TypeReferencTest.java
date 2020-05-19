package com.example.springboot;
 
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
 
public class TypeReferencTest {
    public static class TestMap extends HashMap<String, Integer> {}
 
    void test1() {
        TestMap testMap = new TestMap();
        System.out.println("getSuperclass:" + testMap.getClass().getSuperclass());
        System.out.println("getGenericSuperclass:" + testMap.getClass().getGenericSuperclass());
        Type type = testMap.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType p = (ParameterizedType)type;
            for (Type t : p.getActualTypeArguments()) {
                System.out.println(t);
            }
        }
    }
 
    void test2() {
        Map<String, Integer> testMap = new HashMap<>();
        System.out.println("\ngetSuperclass:" + testMap.getClass().getSuperclass());
        System.out.println("getGenericSuperclass:" + testMap.getClass().getGenericSuperclass());
        Type type = testMap.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType p = (ParameterizedType)type;
            for (Type t : p.getActualTypeArguments()) {
                System.out.println(t);
            }
        }
    }
 
    void test3() {
        Map<String, Integer> testMap = new HashMap<String, Integer>(){};
        System.out.println("\ngetSuperclass:" + testMap.getClass().getSuperclass());
        System.out.println("getGenericSuperclass:" + testMap.getClass().getGenericSuperclass());
        Type type = testMap.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType p = (ParameterizedType)type;
            for (Type t : p.getActualTypeArguments()) {
                System.out.println(t);
            }
        }
    }
 
    public static void main(String[] args) {
        TypeReferencTest obj = new TypeReferencTest();
        obj.test1();
        obj.test2();
        obj.test3();
    }
}