package com.lwrs.app.controller.conf;

import com.lwrs.app.domain.dto.BaseResp;
import com.lwrs.app.enums.RespCode;
import com.lwrs.app.exception.BurgeonException;
import com.lwrs.app.exception.InvalidParamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by xumingjia on 16/4/3.
 */
@Slf4j
@ControllerAdvice
//controller抛出异常时先于ResponseBodyAdvice执行
public class RestfulExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({Exception.class})
    public final ResponseEntity<Object> handleExceptions(Exception ex, ServletWebRequest request) throws URISyntaxException {
        HttpServletRequest servletRequest = request.getRequest();
        logger.warn("Got exception when visiting: " + request.getDescription(false), ex);

        String uri = servletRequest.getRequestURI();
        if(uri.contains("/ajax/")){
            BaseResp resp;
            if(ex instanceof InvalidParamException){
                resp = BaseResp.of(RespCode.INVALID_PARAM, ex.getMessage());
            }else {
                resp = BaseResp.of(RespCode.EXCEPTION);
            }
            return this.handleExceptionInternal(ex, resp, null, HttpStatus.OK, request);
        }
        //redirect home
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI("/"));
        return this.handleExceptionInternal(ex, ex.getMessage(), httpHeaders, HttpStatus.MOVED_PERMANENTLY, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

}
