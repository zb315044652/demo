package com.zhangbin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zhangbin.config.TypeEnumDeserializer;
import com.zhangbin.enums.TypeEnum;

import java.time.LocalDateTime;
import java.util.Date;

public class CarDto {

    private Long id;

    private String name;

    @JsonDeserialize(using = TypeEnumDeserializer.class)
    private TypeEnum payType;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createdAt;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeEnum getPayType() {
        return payType;
    }

    public void setPayType(TypeEnum payType) {
        this.payType = payType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
