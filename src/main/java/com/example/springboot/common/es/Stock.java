package com.example.springboot.common.es;

import lombok.Data;

@Data
public class Stock {
    public String code;
    public String englishName;
    public String chineseName;
    public float codeLength;
}
