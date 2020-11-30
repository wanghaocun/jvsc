package com.wanghaocun.cache.repository;

import com.wanghaocun.cache.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author wanghc
 * @since 2020-11-30
 **/
public interface PersonRepository extends CrudRepository<Person, String> {

    /**
     * 根据城市名获取人
     *
     * @param city string
     * @return {@link Person}
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    List<Person> findByAddress_City(String city);

}
