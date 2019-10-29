package com.example.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@Controller
public class SpringSessionCrontroller {

    @RequestMapping("/setsession")
    @ResponseBody
    public String setSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("session.getClass().toString()="+session.getClass().toString());
        log.info("session.getId()=" +session.getId());
        String id  = "123";
        session.setAttribute("id", id);
        Object createtime=session.getAttribute("createtime");
        if(null == createtime){
            session.setAttribute("createtime",new Date().toString());
        }
        return "sessionid=" + session.getId()+" createtime="+createtime;
    }
}