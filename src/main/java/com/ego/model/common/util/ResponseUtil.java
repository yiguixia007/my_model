package com.ego.model.common.util;


import com.ego.model.common.constant.CommonConstant;
import com.ego.model.common.mo.ResponseMO;

/**
 * 响应工具类
 * @author ego
 * @since 2018-10-27 16:07
 */
public class ResponseUtil {

    public static <T> ResponseMO<T> response(int code, String msg, T data, String debugInfo) {
        ResponseMO<T> responseMO = new ResponseMO<>();
        responseMO.setCode(code);
        responseMO.setMsg(msg);
        responseMO.setData(data);
        responseMO.setDebugInfo(debugInfo);
        return responseMO;
    }

    public static <T> ResponseMO<T> response(int code, String msg, T data) {
        return response(code, msg, data, null);
    }

    public static ResponseMO response(int code, String msg) {
        return response(code, msg, null, null);
    }

    public static ResponseMO success(String msg) {
        return response(CommonConstant.RESPONSE_CODE_SUCCESS, msg, null, null);
    }

    public static ResponseMO success() {
        return response(CommonConstant.RESPONSE_CODE_SUCCESS, null, null, null);
    }

    public static <T> ResponseMO<T> successWithData(String msg, T data) {
        return response(CommonConstant.RESPONSE_CODE_SUCCESS, msg, data, null);
    }

    public static <T> ResponseMO<T> successWithData(T data) {
        return response(CommonConstant.RESPONSE_CODE_SUCCESS, null, data, null);
    }
    public static ResponseMO error(int code, String message) {
        return response(code, message);
    }

    public static ResponseMO error(String message, String debugInfo) {
        return response(CommonConstant.RESPONSE_CODE_FAILURE, message, null, debugInfo);
    }

    public static ResponseMO error(String message) {
        return error(CommonConstant.RESPONSE_CODE_FAILURE, message);
    }


}
