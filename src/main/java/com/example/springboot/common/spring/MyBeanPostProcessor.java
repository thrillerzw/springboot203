package com.example.springboot.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * BeanPostProcessor接口作用：所有bean都会执行
 * 在bean实例化、配置以及其他初始化方法前后添加一些我们自己的逻辑
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 实例化、依赖注入完毕，在调用显示的初始化之前完成一些定制的初始化任务
     * 注意：方法返回值不能为null
     * 如果返回null那么在后续初始化方法将报空指针异常或者通过getBean()方法获取不到bena实例对象
     * 因为后置处理器从Spring IoC容器中取出bean实例对象没有再次放回IoC容器中
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization" + bean + "--" + beanName);
        return bean;
    }

    /**
     * 实例化、依赖注入、初始化完毕时执行
     * 注意：方法返回值不能为null
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization" + bean + "--" + beanName);
        return bean;
    }

}
