package com.spring.security.common.utils;

import com.spring.security.common.entity.JsonResult;
import com.spring.security.common.enums.ResultCode;

public class ResultTool {
	
    public static JsonResult<Object> success() {
        return new JsonResult<Object>(true);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult<T>(true, data);
    }

    public static JsonResult<Object> fail() {
        return new JsonResult<Object>(false);
    }

    public static JsonResult<Object> fail(ResultCode resultEnum) {
        return new JsonResult<Object>(false, resultEnum);
    }
}
