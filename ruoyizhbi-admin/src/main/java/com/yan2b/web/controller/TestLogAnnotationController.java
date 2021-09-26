package com.yan2b.web.controller;

import com.yan2b.common.annotation.Log;
import com.yan2b.common.enums.BusinessType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Gangbb
 * @ClassName : TestLogAnnotationController
 * @Description :
 * @Date : 2021/3/8 8:56
 */
@RestController
@RequestMapping("log")
public class TestLogAnnotationController {

    @Log(title = "测试@Log注解", businessType = BusinessType.OTHER)
    @GetMapping("/hello")
    public String HelloLog(){
        return "测试@Log注解，记录操作日志";
    }
}
