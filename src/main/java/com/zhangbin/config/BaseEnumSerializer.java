package com.zhangbin.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zhangbin.enums.BaseEnum;

import java.io.IOException;

/**
 * @Auth HC
 * @Date 2018/11/03
 *  自定义枚举类型序列化
 */
public class BaseEnumSerializer extends JsonSerializer<BaseEnum> {

    @Override
    public void serialize(BaseEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.getValue());
        gen.writeStringField(gen.getOutputContext().getCurrentName() + "Remark", value.getName());
    }

}
