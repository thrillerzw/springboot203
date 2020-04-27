package com.example.springboot.api.req;

import lombok.Data;

import java.io.Serializable;
@Data
public class QueryExampleReq implements Serializable {
    private static final long serialVersionUID = 7436646388149114460L;

    private Long id;

    /**
     * 状态，0：待发送，1：发送成功，2：发送失败
     * status
     */
    private Short status;
}
