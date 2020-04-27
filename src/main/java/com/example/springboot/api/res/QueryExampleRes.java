package com.example.springboot.api.res;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class QueryExampleRes implements Serializable {

    private static final long serialVersionUID = 447818632191199859L;
    /**
     * id
     */
    private Long id;

    /**
     * 状态，0：待发送，1：发送成功，2：发送失败
     * status
     */
    private Short status;

    /**
     * 发送到mq的消息体内容，json格式
     * message_content
     */
    private String messageContent;

    /**
     * 重试次数，最多重试5次
     * retry_times
     */
    private Short retryTimes;

    /**
     * amount
     */
    private BigDecimal amount;

    /**
     * create_time
     */
    private Date createTime;

    /**
     * update_time
     */
    private Date updateTime;
}
