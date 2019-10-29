package com.example.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * http://127.0.0.1:8082/getjsonp?callback=mycall
 * mycal为前端定义的js函数
 */
@Slf4j
@Controller
public class JsonpCrontroller {

    @RequestMapping(value = "/getjsonp" ,produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public Object getJsonp(String callback){
        //需要返回的数据
        String data="{'id':1}";
        //callback js调用时传过来的参数，回调的方法名
        //第一种方式 ，把我们返回的数据转JSON后，然后拼接我们在js中定义的方法名，把json数据作为参数传递进去
        System.out.println(callback + "("+data+");");
//        return callback + "("+data+");";

        //第二种方式,Will be removed as of Spring Framework 5.1,让使用CORS
       MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(data);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}