package com.example.designpatterns.structural.filterpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghaocun
 * @since 2022-05-08
 */
public class CriteriaSingle implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> singlePersons = new ArrayList<>();
        for (Person person : persons) {
            if (person.maritalStatus().equalsIgnoreCase("SINGLE")) {
                singlePersons.add(person);
            }
        }
        return singlePersons;
    }

}
