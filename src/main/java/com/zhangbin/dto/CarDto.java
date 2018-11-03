package com.zhangbin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zhangbin.config.TypeEnumDeserializer;
import com.zhangbin.enums.TypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
public class CarDto {

    private Long id;

    private String name;

    @JsonDeserialize(using = TypeEnumDeserializer.class)
    private TypeEnum payType;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createdAt;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date date;

}
