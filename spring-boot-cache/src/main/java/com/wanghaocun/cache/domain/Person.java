package com.wanghaocun.cache.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

/**
 * @author wanghc
 * @since 2020-11-30
 **/
@Data
@Builder
@RedisHash("persons")
public class Person {

    @Id
    private String id;

    private String firstname;
    private String lastname;
    private Address address;

}
