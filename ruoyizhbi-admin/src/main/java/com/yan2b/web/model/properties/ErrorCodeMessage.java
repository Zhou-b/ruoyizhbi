package com.yan2b.web.model.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Gangbb
 * @ClassName : errorCodeMessage
 * @Description :
 * @Date : 2021/3/10 9:47
 */
@Component
@ConfigurationProperties(prefix = "error")
@PropertySource(value = "classpath:i18n/messages.properties", encoding = "UTF-8")
public class ErrorCodeMessage {
    private static Map<String, String> codeMessage = new HashMap<>();

    /**
     * 获取map中的value
     *
     * @param code
     * @return
     */
    public static String getMessage(String code) {
        return codeMessage.get(code);
    }

    public Map<String, String> getCodeMessage() {
        return codeMessage;
    }

    /**
     * 配置文件的初始化赋值是在次方法进行的,因此属性名的统一必须在此处
     *
     * @param codeMessage
     */
    public void setCodeMessage(Map<String, String> codeMessage) {
        ErrorCodeMessage.codeMessage = codeMessage;
    }
}
