package com.wanghaocun.cache.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wanghc
 * @since 2020-11-30
 **/
@Data
@Entity(name = "t_comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private String author;
    @Column(name = "a_id")
    private Integer aId;

}
