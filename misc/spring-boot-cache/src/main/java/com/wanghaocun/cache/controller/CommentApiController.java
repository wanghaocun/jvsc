package com.wanghaocun.cache.controller;

import com.wanghaocun.cache.domain.Comment;
import com.wanghaocun.cache.service.CommentApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghc
 * @since 2020-11-30
 **/
@RestController
@RequestMapping("/api")
public class CommentApiController {

    @Autowired
    CommentApiService commentService;

    @GetMapping("/find")
    public Comment find(Integer id) {
        return commentService.findCommentById(id);
    }

    @GetMapping("/update")
    public Comment update(Comment comment) {
        return commentService.updateComment(comment);
    }

    @GetMapping("/delete")
    public void delete(Integer id) {
        commentService.deleteComment(id);
    }

}
