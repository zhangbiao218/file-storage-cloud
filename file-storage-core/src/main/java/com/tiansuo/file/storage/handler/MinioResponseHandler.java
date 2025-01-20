package com.tiansuo.file.storage.handler;


import com.tiansuo.file.storage.api.response.ResultModel;
import com.tiansuo.file.storage.api.response.ReturnCodeEnum;
import com.tiansuo.file.storage.core.exception.MinioPlusException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@Slf4j
@ControllerAdvice
public class MinioResponseHandler implements ResponseBodyAdvice<Object> {

    @Override

    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        //为true则处理全部待返回结果,自动调用beforeBodyWrite方法

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //全部返回固定格式的,也可以根据类型自行处理
        if (body instanceof ResultModel){
            return body;
        }
        //
        // string类型的返回数据特殊处理,防止报错java.lang.ClassCastException: xxx cannot be cast to java.lang.String
/*        if (body instanceof String){
            return JSONUtil.toJsonStr(ResultModel.success(body));
        }*/
        return ResultModel.success(body);
    }

    //对异常进行全局处理
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultModel handleException(Exception e){
        log.error("出现异常:",e);
        if (e instanceof MinioPlusException){
            MinioPlusException exception = (MinioPlusException) e;
            return ResultModel.fail(exception.getCode(), exception.getMessage());
        }else {
            return ResultModel.fail(ReturnCodeEnum.RC500.getCode(),ReturnCodeEnum.RC500.getMessage());
        }
    }


    // string类型的返回数据特殊处理,防止报错java.lang.ClassCastException: xxx cannot be cast to java.lang.String
    @Configuration
    public class WebConfiguration implements WebMvcConfigurer {

        @Override
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            converters.add(0, new MappingJackson2HttpMessageConverter());
        }
    }
}
