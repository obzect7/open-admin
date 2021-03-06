package com.cxp.openadmin.configuration;

import com.cxp.openadmin.exception.ExceptionResponse;
import com.cxp.openadmin.util.JsonUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(JsonUtils.objectToJson(new ExceptionResponse(1000, "죄송합니다. 작동 권한이 없습니다.！",httpServletRequest.getMethod()+':'+httpServletRequest.getRequestURI())));
    }
}
