package com.example.designpatterns.structural.filterpattern;

import java.util.List;
/**
 * @author wanghaocun
 * @since 2022-05-08
 */
public interface Criteria {

   List<Person> meetCriteria(List<Person> persons);

}
