package com.wanghaocun.cache.repository;

import com.wanghaocun.cache.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @author wanghc
 * @since 2020-11-30
 **/
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * 更新评论
     *
     * @param author String
     * @param id     Integer
     * @return int
     */
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query("update t_comment set author = ?1 where id = ?2")
    int updateComment(String author, Integer id);

}
