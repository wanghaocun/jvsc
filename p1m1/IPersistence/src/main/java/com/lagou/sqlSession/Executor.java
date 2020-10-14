package com.lagou.sqlSession;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public interface Executor {

    public <E> List<E> query(Configuration configuration,MappedStatement mappedStatement,Object... params) throws Exception;

    /**
     * 增删改操作
     * @param configuration Configuration
     * @param mappedStatement MappedStatement
     * @param params Object...
     * @return boolean
     * @throws Exception Exception
     */
    boolean operate(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

}
