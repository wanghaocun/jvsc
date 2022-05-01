package com.example.streamtest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 苹果实体
 *
 * @author wanghc
 * @version 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
public class Apple {

    /**
     * 编号
     */
    private long id;

    /**
     * 颜色
     */
    private Color color;

    /**
     * 重量
     */
    private float weight;

    /**
     * 产地
     */
    private String origin;

    public enum Color {
        RED, GREEN, YELLOW,
    }

}
