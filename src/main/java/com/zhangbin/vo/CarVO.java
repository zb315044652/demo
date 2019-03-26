package com.zhangbin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhangbin.config.BaseEnumSerializer;
import com.zhangbin.enums.TypeEnum;

import java.time.LocalDateTime;
import java.util.Date;

public class CarVO {

    private Long id;

    private String name;

    @JsonSerialize(using = BaseEnumSerializer.class)
    private TypeEnum type;

//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 配置不起作用
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  配置不起作用
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )  配置不起作用
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
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

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
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
