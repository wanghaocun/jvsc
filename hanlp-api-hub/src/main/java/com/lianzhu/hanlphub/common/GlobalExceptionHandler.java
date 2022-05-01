package com.lianzhu.hanlphub.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * 通用异常处理
 *
 * @author wanghc
 * @since 2020-07-28
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 参数验证异常
     *
     * @param e MethodArgumentNotValidException
     * @return BaseResult
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResult<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        HashMap<String, String> errorMessage = new HashMap<>(16);
        e.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errorMessage.put(fieldError.getField(), fieldError.getDefaultMessage()));
        logger.error("method argument not valid exception", e);

        return BaseResult.of(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage);
    }

    /**
     * 全局异常
     *
     * @param e Exception
     * @return BaseResult
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResult<Object> handleGlobalException(Exception e) {
        logger.error("handle global exception", e);

        return BaseResult.error(e.getMessage());
    }

}
