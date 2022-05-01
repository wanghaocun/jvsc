package com.lianzhu.hanlphub.common;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 通用的执行结果
 *
 * @author wanghc
 * @since 2020-07-28
 */
@SuppressWarnings("unused")
public class BaseResult<T> implements Serializable {

    /**
     * 执行结果状态码
     */
    private Integer code;

    /**
     * 执行结果消息
     */
    private String message;

    /**
     * 执行结果数据
     */
    private T data;

    /**
     * 自定义返回信息 (without data)
     *
     * @param code    Integer
     * @param message String
     * @return BaseResult<T>
     */
    public static <T> BaseResult<T> of(Integer code, String message) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(code);
        baseResult.setMessage(message);

        return baseResult;
    }

    /**
     * 自定义返回信息 (with data)
     *
     * @param code    Integer
     * @param message String
     * @param data    T
     * @return BaseResult<T>
     */
    public static <T> BaseResult<T> of(Integer code, String message, T data) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(code);
        baseResult.setMessage(message);
        baseResult.setData(data);

        return baseResult;
    }

    /**
     * 成功返回信息 (without data)
     *
     * @return BaseResult<T>
     */
    public static <T> BaseResult<T> success() {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(HttpStatus.OK.value());
        baseResult.setMessage(HttpStatus.OK.getReasonPhrase());

        return baseResult;
    }

    /**
     * 成功返回信息 (with data)
     *
     * @param data T
     * @return BaseResult<T>
     */
    public static <T> BaseResult<T> success(T data) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(HttpStatus.OK.value());
        baseResult.setMessage(HttpStatus.OK.getReasonPhrase());
        baseResult.setData(data);

        return baseResult;
    }

    /**
     * 失败返回信息 (without data)
     *
     * @return BaseResult<T>
     */
    public static <T> BaseResult<T> error() {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        baseResult.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return baseResult;
    }

    /**
     * 失败返回信息 (with data)
     *
     * @param data T
     * @return BaseResult<T>
     */
    public static <T> BaseResult<T> error(T data) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        baseResult.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        baseResult.setData(data);

        return baseResult;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

