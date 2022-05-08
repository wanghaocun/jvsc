package com.example.designpatterns.StructuralPatterns.FilterPattern;

import java.util.List;

/**
 * @author wanghaocun
 * @since 2022-05-08
 */
public class AndCriteria implements Criteria {

    private final Criteria criteria;

    private final Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);
        return otherCriteria.meetCriteria(firstCriteriaPersons);
    }

}
