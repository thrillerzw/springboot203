package com.example.springboot.service.impl;

import com.example.springboot.service.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public void print() {
        System.out.println("DemoServiceImpl print()");
    }
}
