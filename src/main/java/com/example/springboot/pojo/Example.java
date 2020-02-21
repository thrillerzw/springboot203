package com.example.springboot.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Example implements Serializable {
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

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public Short getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Short retryTimes) {
        this.retryTimes = retryTimes;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}