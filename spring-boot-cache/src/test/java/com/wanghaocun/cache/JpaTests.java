package com.wanghaocun.cache;

import com.wanghaocun.cache.domain.Comment;
import com.wanghaocun.cache.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wanghc
 * @since 2020-11-30
 **/
public class JpaTests extends BaseTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void jpaTest() {
        List<Comment> all = commentRepository.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void jpaUpdateTest() {
        int i = commentRepository.updateComment("lucy", 1);
        System.out.println(i);
    }

}
