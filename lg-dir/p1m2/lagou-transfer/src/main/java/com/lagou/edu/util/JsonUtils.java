package com.lagou.edu.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON工具类（使用的是jackson实现的）
 * @author 应癫
 */
@SuppressWarnings("unused")
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     */
    public static String object2Json(Object data) {
    	try {
            return MAPPER.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

    	return null;
    }
    
    /**
     * 将json结果集转化为对象
     */
    public static <T> T json2Pojo(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     */
    public static <T>List<T> json2List(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
            return MAPPER.readValue(jsonData, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    
}
