package com.example.springboot.common.design.proxy;

import com.example.springboot.service.DemoService;
import com.example.springboot.service.impl.DemoServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    //动态代理
    public static Object getDynamicProxyInstance(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("动态代理执行前执行相关增强");
                        Object invoke = method.invoke(target, args);
                        System.out.println("动态代理执行后执行相关增强");
                        return invoke;
                    }
                });
    }
     /*<dependency>
            <groupId>cglib</groupId>
            <artifactId>cglib</artifactId>
            <version>3.3.0</version>
        </dependency>*/
    //cglib proxy    spring-core也有自己的cglib实现，没测试
    public static Object getCglibProxyInstance(Object target){
        Enhancer enhancer = new Enhancer();
        //指定目标类，生成的类将会继承此类
        enhancer.setSuperclass(target.getClass());
        //指定拦截器
        enhancer.setCallback(new MethodInterceptor(){
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("cglib代理执行前执行相关增强");
                //Object result = methodProxy.invokeSuper(obj, args);
                Object result = method.invoke(target, args);
                System.out.println("cglib代理执行后执行相关增强");
                return result;
            }
        });
        //创建子类对象，代理类
        Object obj = enhancer.create();
        return obj;
    }

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
