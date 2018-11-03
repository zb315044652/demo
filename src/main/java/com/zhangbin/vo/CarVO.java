package com.zhangbin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhangbin.config.BaseEnumSerializer;
import com.zhangbin.config.TypeEnumDeserializer;
import com.zhangbin.enums.TypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
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

}
