package com.example.springboot.api;

import com.example.springboot.api.req.QueryExampleReq;
import com.example.springboot.api.res.QueryExampleRes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/demo")
public interface DemoFacade {
    @PostMapping("/queryExample")
    ApiResult<QueryExampleRes> queryExample(@RequestBody QueryExampleReq queryExampleReq);
}
