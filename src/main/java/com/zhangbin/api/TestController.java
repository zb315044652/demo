package com.zhangbin.api;

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
