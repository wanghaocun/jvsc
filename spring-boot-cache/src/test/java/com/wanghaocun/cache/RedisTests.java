package com.wanghaocun.cache;

import com.wanghaocun.cache.domain.Address;
import com.wanghaocun.cache.domain.Person;
import com.wanghaocun.cache.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wanghc
 * @since 2020-11-30
 **/
public class RedisTests extends BaseTest {

    @Autowired
    private PersonRepository repository;

    @Test
    public void savePerson() {
        Person person = Person.builder().firstname("李").lastname("四").address(Address.builder().country("中国").city(
                "南京").build()).build();
        Person save = repository.save(person);
        System.out.println(save);
    }

    @Test
    public void findAllPerson() {
        Iterable<Person> all = repository.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void findOnePerson() {
        List<Person> list = repository.findByAddress_City("南京");
        list.forEach(System.out::println);
    }
}
