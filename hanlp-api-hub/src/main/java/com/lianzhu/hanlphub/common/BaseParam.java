package com.lianzhu.hanlphub.common;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 通用接口请求参数
 *
 * @author wanghc
 * @since 2020-07-28
 **/
@SuppressWarnings("unused")
public class BaseParam {

    /**
     * Hanlp 处理方法
     */
    @NotBlank(message = "Hanlp 处理方法不能为空")
    private String method;

    /**
     * Hanlp 处理内容
     */
    @NotBlank(message = "Hanlp 处理内容不能为空")
    private String content;

    /**
     * Hanlp 处理类型
     */
    @Min(value = 0, message = "最小值为 0")
    private Integer type;

    /**
     * Hanlp 处理数量
     */
    @Min(value = 0, message = "最小值为 0")
    private Integer size;

    /**
     * Hanlp 文本推荐关键字
     */
    private String keyword;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "BaseParam{" +
                "method='" + method + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", size=" + size +
                ", keyword='" + keyword + '\'' +
                '}';
    }

}
