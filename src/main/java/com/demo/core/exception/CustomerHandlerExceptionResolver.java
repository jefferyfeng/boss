package com.demo.core.exception;

import com.demo.core.constants.ResultConstants;
import com.demo.core.util.ResultUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 全局异常处理
 */
public class CustomerHandlerExceptionResolver implements HandlerExceptionResolver{
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerHandlerExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        //如果是ajax请求响应头会有x-requested-with
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
            response.setContentType("application/json ; charset=UTF-8");
            try {
            PrintWriter writer = response.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            String exceptionJson = objectMapper.writeValueAsString(ResultUtil.result(ResultConstants.EXCEPTION));
                writer.write(exceptionJson);
            } catch (Exception e1) {
                LOGGER.error("CustomerHandlerExceptionResolver异常",e1);
            }
            return null;
        }else {//非ajax请求
            return new ModelAndView("redirect:xxxx");
        }
    }
}
