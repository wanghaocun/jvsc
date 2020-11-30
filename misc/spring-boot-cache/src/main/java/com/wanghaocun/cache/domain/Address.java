package com.wanghaocun.cache.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @author wanghc
 * @since 2020-11-30
 **/
@Data
@Builder
public class Address {

    private String country;
    @Indexed
    private String city;

}
