package com.lagou.blog.service;

import com.lagou.blog.dao.BlogDao;
import com.lagou.blog.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaocun
 * @since 2020-11-02
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    public BlogDao blogDao;

    @Override
    public List<Blog> getList(Integer page) {
        return blogDao.getList();
    }

}
