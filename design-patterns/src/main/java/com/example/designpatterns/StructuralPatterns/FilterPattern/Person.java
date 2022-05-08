package com.example.designpatterns.StructuralPatterns.FilterPattern;

/**
 * @author wanghaocun
 * @since 2022-05-08
 */
public class Person {

    private final String name;
    private final String gender;
    private final String maritalStatus;

    public Person(String name, String gender, String maritalStatus) {
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

}
