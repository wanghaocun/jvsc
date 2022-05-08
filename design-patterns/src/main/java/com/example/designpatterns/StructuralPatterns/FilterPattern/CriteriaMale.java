package com.example.designpatterns.StructuralPatterns.FilterPattern;

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
            if (person.getGender().equalsIgnoreCase("MALE")) {
                malePersons.add(person);
            }
        }
        return malePersons;
    }

}
