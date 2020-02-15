package com.example.springboot;

import com.alibaba.fastjson.JSON;
import com.example.springboot.common.es.EsHelper;
import com.example.springboot.common.es.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {

    @Test
    public void testAdd(){
        Stock stock=new Stock();
        stock.code="abc";
        stock.englishName="abc";
        stock.chineseName="abc股票";
        stock.codeLength=1.0f/stock.code.length();
        boolean b = EsHelper.addDocument("stock", "tiger", "abc", JSON.toJSONString(stock));
        System.out.println(b);

    }

    @Test
    public void testQuery(){
        List<Stock> stocks = EsHelper.searchStockMatch("stock", "tiger", 0, 10, "abc");
        System.out.println(JSON.toJSONString(stocks));
    }
}
