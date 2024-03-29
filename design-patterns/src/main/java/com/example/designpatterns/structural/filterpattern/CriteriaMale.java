package com.example.designpatterns.structural.filterpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghaocun
 * @since 2022-05-08
 */
public class CriteriaMale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<>();
        for (Person person : persons) {
            if (person.gender().equalsIgnoreCase("MALE")) {
                malePersons.add(person);
            }
        }
        return malePersons;
    }

}
