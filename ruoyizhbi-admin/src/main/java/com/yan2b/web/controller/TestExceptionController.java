package com.yan2b.web.controller;

import com.yan2b.common.annotation.Log;
import com.yan2b.common.enums.BusinessType;
import com.yan2b.common.exception.ApiException;
import com.yan2b.common.model.ApiRestResponse;
import com.yan2b.web.model.entity.User;
import com.yan2b.web.model.properties.ErrorCodeMessage;
import com.yan2b.web.other.TestI18n;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Gangbb
 * @ClassName : TestExceptionController
 * @Description :
 * @Date : 2021/3/9 14:18
 */
@RestController
@RequestMapping("/test/exception")
public class TestExceptionController {
    @Autowired
    MessageSource messageSource;

    /**
     * 测试i8n国际化
     * 传入参数lang 决定中英文环境
     *
     * @return
     */
    @GetMapping("/i8n")
    public String testI18n() {
        //直接获取i18n数据
        //return messageSource.getMessage("exception.default", null, LocaleContextHolder.getLocale());
        //通过MessageUtils获取i18n数据
        return TestI18n.getMessage();
        // 控制为中文数据
//        Locale locale = new Locale("zh", "CN");
//        return messageSource.getMessage("exception.default", null, locale);
    }

    @Log(title = "测试系统自定义异常", businessType = BusinessType.OTHER)
    @GetMapping("/e")
    public String testApiException() {
        //throw new ApiException("01");
        // 用Properties配置类的方式，根据错误码获取对应错误信息
        System.out.println("B0001=" + ErrorCodeMessage.getMessage("B0001"));
        throw new ApiException("B0001");
    }

    /**
     * 对应的异常是: BindException
     * @param user
     * @return
     */
    @Log(title = "测试校验异常", businessType = BusinessType.OTHER)
    @PostMapping("/e2")
    public String testApiException2(@Validated User user) {
        //@Validated UserDto user
        return "测试参数校验异常";
    }

    @Log(title = "测试success", businessType = BusinessType.OTHER)
    @PostMapping("/success")
    public ApiRestResponse testSuccess() {
        User user = new User();
        user.setName("LLL");
        user.setInfo("Test");
        return ApiRestResponse.success(user);
    }
}
