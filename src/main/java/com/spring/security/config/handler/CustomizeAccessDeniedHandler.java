package com.spring.security.config.handler;

import com.spring.security.common.entity.JsonResult;
import com.spring.security.common.enums.ResultCode;
import com.spring.security.common.utils.ResponseUtil;
import com.spring.security.common.utils.ResultTool;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 权限拒绝处理逻辑
 */
@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        JsonResult<Object> result = ResultTool.fail(ResultCode.NO_PERMISSION);
        ResponseUtil.out(httpServletResponse, result);
    }
}
