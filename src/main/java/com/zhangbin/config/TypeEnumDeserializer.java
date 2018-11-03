package com.zhangbin.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.zhangbin.enums.TypeEnum;

import java.io.IOException;

/**
 * @Auth ZB
 * @Date 2018/11/03
 * 枚举反序列化工类 对于这种类型的枚举值，使用11、12值作为反序列化,
 *  TODO 对每个类需要写一个反序列化类，如何做到统一
 *  enum TypeEnum
 *      BIG(11, "大"),
 *      SMALL(12, "小");
 */
public class TypeEnumDeserializer extends JsonDeserializer<TypeEnum> {

    @Override
    public TypeEnum deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        int typeValue = p.getIntValue();
        for (TypeEnum typeEnum : TypeEnum.values()) {
            if (typeValue == typeEnum.getValue()) {
                return typeEnum;
            }
        }
        return null;
    }

}
