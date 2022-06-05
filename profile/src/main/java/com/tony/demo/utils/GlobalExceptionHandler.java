package com.tony.demo.utils;

import com.tony.demo.exception.UserProfileException;
import com.tony.demo.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)

    public ResultVo myExceptionHandler(Exception e, HandlerMethod handlerMethod ){
        log.info("application exception: "+e.getMessage().toString());
        return ResultVoUtil.exception("application busy, please try again later");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResultVo dealNullPointerException(NullPointerException e) {
        log.info("application exception: "+e.getMessage().toString());
        return ResultVoUtil.nullPointer(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultVo runtimeException(RuntimeException e) {
        log.info("Runtime exception: "+e.getMessage().toString());
        return ResultVoUtil.exception(e.getMessage());
    }

    @ExceptionHandler(UserProfileException.class)
    public ResultVo userProfileException(UserProfileException e) {
        log.info("userProfile exception: "+e.getMessage().toString());
        return ResultVoUtil.userProfileException(e.getMessage());
    }

}

