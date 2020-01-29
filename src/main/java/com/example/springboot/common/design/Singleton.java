package com.example.springboot.common.design;

//双重检查，第一个if(instance == null)可能会有多个线程同时进入，第二不会。
public class Singleton {
    private Singleton(){}
    private static volatile Singleton instance;
    public static Singleton getInstance(){
        if(instance == null){
            synchronized(Singleton.class){
                if(instance==null){
                    instance=new Singleton();
                }
            }
        }
        return instance;
    }
}
