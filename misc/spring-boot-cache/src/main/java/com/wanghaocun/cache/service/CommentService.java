package com.wanghaocun.cache.service;

import com.wanghaocun.cache.domain.Comment;
import com.wanghaocun.cache.repository.CommentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author wanghc
 * @since 2020-11-30
 **/
@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Cacheable(cacheNames = "comment", unless = "#result==null")
    public Comment findCommentById(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElse(null);
    }

    @CachePut(cacheNames = "comment", key = "#result.id")
    public Comment updateComment(@NotNull Comment comment) {
        boolean exists = commentRepository.existsById(comment.getId());
        if (!exists) {
            return null;
        }
        commentRepository.updateComment(comment.getAuthor(), comment.getId());
        Optional<Comment> commentOptional = commentRepository.findById(comment.getId());

        return commentOptional.orElse(null);
    }

    @CacheEvict(cacheNames = "comment")
    public void deleteComment(int commentId) {
        boolean exists = commentRepository.existsById(commentId);
        if (!exists) {
            return;
        }
        commentRepository.deleteById(commentId);
    }
}
