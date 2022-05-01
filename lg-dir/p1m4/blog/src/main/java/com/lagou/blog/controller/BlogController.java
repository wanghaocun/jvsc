package com.lagou.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.blog.pojo.Blog;
import com.lagou.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wanghaocun
 * @since 2020-11-02
 */
@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @RequestMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "0") Integer page) {
        page = page <= 0 ? 1 : page;
        PageHelper.startPage(page, 2);
        List<Blog> list = blogService.getList(page);
        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("page", page);

        return "client/index";
    }

}
