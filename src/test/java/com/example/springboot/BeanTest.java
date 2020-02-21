package com.example.springboot;

import com.example.springboot.service.DemoService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanTest {

    @Test
    public void getBean(){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ScanTest.class);
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println("bean name= "+name);
        }
        DemoService demoService = (DemoService)context.getBean("demoServiceImpl");
        demoService.print();
    }
}
