package com.wanghaocun.cache.service;

import com.wanghaocun.cache.domain.Comment;
import com.wanghaocun.cache.repository.CommentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

/**
 * @author wanghc
 * @since 2020-11-30
 **/
@Service
public class CommentApiService {

    @SuppressWarnings("rawtypes")
    @Qualifier("redisTemplate")
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    CommentRepository commentRepository;

    @SuppressWarnings("unchecked")
    public Comment findCommentById(Integer id) {
        Object o = redisTemplate.opsForValue().get("comment::" + id);
        if (o != null) {

            return (Comment) o;
        } else {
            Optional<Comment> commentOptional = commentRepository.findById(id);
            if (commentOptional.isPresent()) {
                Comment comment = commentOptional.get();
                redisTemplate.opsForValue().set("comment::" + id, comment, Duration.ofDays(1));

                return comment;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public Comment updateComment(@NotNull Comment comment) {
        boolean exists = commentRepository.existsById(comment.getId());
        if (!exists) {
            return null;
        }
        commentRepository.updateComment(comment.getAuthor(), comment.getId());
        Optional<Comment> commentOptional = commentRepository.findById(comment.getId());

        Comment commentResult = commentOptional.orElse(null);
        assert commentResult != null;
        redisTemplate.opsForValue().set("comment::" + comment.getId(), commentResult, Duration.ofDays(1));

        return commentResult;
    }

    @SuppressWarnings("unchecked")
    public void deleteComment(int commentId) {
        boolean exists = commentRepository.existsById(commentId);
        if (!exists) {
            return;
        }
        commentRepository.deleteById(commentId);
        redisTemplate.delete("comment::" + commentId);
    }

}
