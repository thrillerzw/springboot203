package com.example.springboot.common.test;

import lombok.ToString;

/**
 * callSuper -> toString方法体内会带上超类的toString方法
 * includeFieldNames -> 是否包含字段名，false不包含 true包含  默认是true
 *  @ToString.Exclude  字段标注，toString方法不包含此字段
 *  static field不会被生成到toString方法内
 */
@ToString
public class ToStringExample {

    //静态field不会被生成到toString方法内
    private static final int STATIC_VAR = 10;
    private String name="tom";
    @ToString.Exclude
    private int id;

    public static void main(String[] args) {
        System.out.println(new ToStringExample());
        System.out.println(new Child());
    }
}
@ToString(callSuper = true)
class Child extends ToStringExample {
    private  int age=20;
}

