package com.yan2b.common.model;



import com.yan2b.common.exception.ApiException;
import com.yan2b.common.utils.MessageUtils;
import com.yan2b.common.utils.ServletUtils;
import com.yan2b.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;


/**
 * @author : Gangbb
 * @ClassName : ApiRestResponse
 * @Description : 统一结果返回
 * @Date : 2021/3/9 19:35
 */
public class ApiRestResponse extends LinkedHashMap<String, Object>
{
    /** 日志记录 */
    private static final Logger log = LoggerFactory.getLogger(ApiException.class);

    private static final long serialVersionUID = 1L;


    /** 系统自定义状态码 */
    public static final String CODE = "code";

    /** 返回信息 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /** 请求url */
    public static final String REQUEST_TAG = "request";

    /** 系统成功 默认code 20000 */
    public static final String SYSTEM_SUCCESS = "20000";

    /** 系统默认异常 默认code 50000 */
    public static final String SYSTEM_FAIL = "B0001";


    /**
     * 初始化一个新创建的 ApiRestResponse 对象(成功时使用)
     *
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public ApiRestResponse(String code, String msg, Object data)
    {
        super.put(CODE, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * exception专用返回(错误时使用)
     * @param code 系统自定义状态码
     * @param msg 错误信息
     */
    public ApiRestResponse(String code, String msg)
    {

        super.put(CODE, code);
        super.put(MSG_TAG, msg);
        super.put(REQUEST_TAG, ServletUtils.getRequest().getRequestURI());
    }


    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ApiRestResponse success()
    {
        return ApiRestResponse.success(null);
    }

    /**
     * 返回失败数据
     *
     * @return 失败消息
     */
    public static ApiRestResponse error()
    {
        String message = "操作失败(error)";
        return ApiRestResponse.error(message);
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ApiRestResponse success(Object data)
    {
        String message = "操作成功(success)";
        return new ApiRestResponse(SYSTEM_SUCCESS, message, data);
    }



    /**
     *  返回错误消息
     * @param code 错误信息
     * @return
     */
    public static ApiRestResponse error(String code)
    {
        return new ApiRestResponse(code, MessageUtils.getMessageByCode(code));
    }

    /**
     * 返回错误消息
     * @param code 系统自定义状态码
     * @param msg 错误信息
     * @return
     */
    public static ApiRestResponse error(String code, String msg)
    {
        // 如果填了错误信息 错误信息 = 状态码对应的信息 + 输入的信息
        if(StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(msg)){
            String codeDefaultMsg = MessageUtils.getMessageByCode(code);
            if(!msg.equals(codeDefaultMsg)){
                msg = codeDefaultMsg + "。[具体信息] :" + msg;
            }
        }else {
            return ApiRestResponse.error();
        }
        log.error(msg);
        return new ApiRestResponse(code, msg);
    }


}

