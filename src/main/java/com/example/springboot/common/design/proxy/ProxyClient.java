package com.example.springboot.common.design.proxy;

import com.example.springboot.service.DemoService;
import com.example.springboot.service.impl.DemoServiceImpl;

public class ProxyClient {
    public static void main(String[] args) {
        DemoService demoService = new DemoServiceImpl();
        //动态代理
        DemoService proxyInstance = (DemoService) ProxyFactory.getDynamicProxyInstance(demoService);
        //com.example.springboot.service.impl.DemoServiceImpl@7a4f0f29   object.toString() 也会被InvocationHandler拦截
        //System.out.println(proxyInstance);
        //class com.sun.proxy.$Proxy0
        System.out.println(proxyInstance.getClass());
        //被InvocationHandler拦截
        proxyInstance.print();

        //cglib
        DemoService cglibProxyInstance = (DemoService) ProxyFactory.getCglibProxyInstance(demoService);
        cglibProxyInstance.print();
    }
}
