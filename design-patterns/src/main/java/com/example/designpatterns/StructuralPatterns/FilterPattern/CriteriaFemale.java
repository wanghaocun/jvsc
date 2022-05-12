package com.example.designpatterns.StructuralPatterns.FilterPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghaocun
 * @since 2022-05-08
 */
public class CriteriaFemale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<>();
        for (Person person : persons) {
            if (person.gender().equalsIgnoreCase("FEMALE")) {
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }

}
