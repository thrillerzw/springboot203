package com.example.springboot.controller;

import com.example.springboot.mappers.StudentMapper;
import com.example.springboot.pojo.Student;
import com.example.springboot.util.IpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
public class DemoController {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    //,condition = "#mobileNo.length()==11",unless = "#result == null"
    @RequestMapping("/getdemo")
    //@Cacheable(value="owm_uc",key="#mobileNo",unless = "#result == null")
    public UserResp getDemo(@RequestParam String mobileNo, HttpServletRequest httpServletRequest){
        System.out.println("start getdemo()===================");
        redisTemplate.opsForValue().set("demo","getdemo");
        String str=(String)redisTemplate.opsForValue().get("demo");
        stringRedisTemplate.opsForValue().set("str","str");
        String str2=stringRedisTemplate.opsForValue().get("str");
        String owm_uc=stringRedisTemplate.opsForValue().get("owm_uc::13266660000");
        System.out.println(owm_uc);
        UserResp userResp= new UserResp();
        userResp.setId(1L);
        userResp.setName("张三san");
        userResp.setNow(new Date());
        String remoteClientIp = IpUtil.getRemoteClientIp(httpServletRequest);
        System.out.println(remoteClientIp);
        return userResp;
    }
    @GetMapping("/getpage")
    public List<Student> getpage(){
        PageHelper.startPage(1,2);
        List<Student> students = studentMapper.selectAll();
        PageInfo pageInfo = new PageInfo(students);
        PageHelper.clearPage();
        PageHelper.startPage(2,2);
        List<Student> students2 = studentMapper.selectAll();
        PageInfo pageInfo2 = new PageInfo(students2);
        PageHelper.clearPage();
        return students2;
    }



}
