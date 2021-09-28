package com.yan2b.web.other;

import com.yan2b.common.utils.MessageUtils;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author : Gangbb
 * @ClassName : TestI18n
 * @Description : 测试MessageUtils
 * @Date : 2021/3/9 16:00
 */
public class TestI18n {
    public static String getMessage(){
        return MessageUtils.getMessage("exception.default", null, LocaleContextHolder.getLocale());
    }
}
