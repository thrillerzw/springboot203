package com.example.springboot.service;

import com.example.springboot.api.ApiResult;
import com.example.springboot.api.req.QueryExampleReq;
import com.example.springboot.api.res.QueryExampleRes;

public interface DemoService {
    void transactionalService();

    void print();

    ApiResult<QueryExampleRes> queryExample(QueryExampleReq queryExampleReq);
}
