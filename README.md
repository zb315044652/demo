### 枚举对象序列化(Serializer)与反序列化(Deserializer)

> 在Java中对于类似类型、状态都采用枚举类型代表model，如果前端能自动完成枚举的转换例如Android就非常容易方便了，但与前端交互很多都是采用某个具体的值来标识类型、状态，因此需要考虑到序列化与 反序列化的操作。

枚举接口
```java
public interface BaseEnum {

    Integer getValue();

    String getName();

}
```

枚举对象
```java
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
```

封装请求对象
```java
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
```

封装返回对象
```java
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
```

Controller控制器
```java
import com.zhangbin.dto.CarDto;
import com.zhangbin.vo.CarVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    public CarVO test(@RequestBody CarDto carDto) {
        System.out.println(carDto);
        CarVO carVO = new CarVO();
        BeanUtils.copyProperties(carDto, carVO);
        carVO.setId(carVO.getId() + 1);
        carVO.setType(carDto.getPayType());
        return carVO;
    }

}
```

> 如果不使用自定义序列化类、反序列化类，请求与相应的参数如下

requestBody
```json
{
	"id":1234,
	"name":"myname",
	"payType":"BIG", //枚举标识
	"createdAt":"2018-12-12 12:12:12",
	"date":"2018-01-12 10:12:22"
}
```

responseBody
```json
{
    "id": 1235,
    "name": "myname",
    "type": "BIG",  //枚举标识
    "createdAt": "2018-12-12 12:12:12",
    "date": "2018-01-12 18:12:22"
}
```


> 使用自定序列化、反序列化类

序列化工具类，能够完成通用的value、name枚举类型
```java
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zhangbin.enums.BaseEnum;

import java.io.IOException;

public class BaseEnumSerializer extends JsonSerializer<BaseEnum> {

    @Override
    public void serialize(BaseEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.getValue());
        gen.writeStringField(gen.getOutputContext().getCurrentName() + "Remark", value.getName());
    }

}
```

反序列化类，```TODO``` 针对每一个枚举类都要写一个反序列化工具类，如何封装成达到可以通用
```java
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.zhangbin.enums.TypeEnum;

import java.io.IOException;

/**
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

```

requestBody
```json
{
	"id":1234,
	"name":"myname",
	"payType":12, //枚举类型具体值
	"createdAt":"2018-12-12 12:12:12",
	"date":"2018-01-12 10:12:22"
}
```

responseBody
```json
{
    "id": 1235,
    "name": "myname",
    "type": 12, //枚举类型具体值
    "typeRemark": "小", //枚举类型说明，供前端参考使用
    "createdAt": "2018-12-12 12:12:12",
    "date": "2018-01-12 18:12:22"
}
```


> 另外对于时间类型的序列化反序列化例子中也给出了参考
