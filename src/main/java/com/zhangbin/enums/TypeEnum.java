package com.zhangbin.enums;

public enum TypeEnum implements BaseEnum {

    BIG(11, "大"),
    SMALL(12, "小");

    TypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    private Integer value;

    private String name;


    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }

}
