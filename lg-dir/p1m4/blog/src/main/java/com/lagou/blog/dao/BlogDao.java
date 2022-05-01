package com.lagou.blog.dao;

import com.lagou.blog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wanghaocun
 * @since 2020-11-02
 */
@Mapper
public interface BlogDao {

    /**
     * 获取文章列表
     *
     * @return List
     */
    @Select("SELECT * FROM t_article")
    List<Blog> getList();

}
