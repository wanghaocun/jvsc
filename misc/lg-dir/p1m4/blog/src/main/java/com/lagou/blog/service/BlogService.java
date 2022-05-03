package com.lagou.blog.service;

import com.lagou.blog.pojo.Blog;

import java.util.List;

/**
 * @author wanghaocun
 * @since 2020-11-02
 */
public interface BlogService {

    /**
     * 获取文章列表
     *
     * @param page Integer
     * @return List
     */
    List<Blog> getList(Integer page);

}
