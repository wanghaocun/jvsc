package com.example.designpatterns.behavioral.iteratorpattern;

import java.util.List;

/**
 * @author wanghaocun
 */
public class NameIterator implements Iterator {

    List<String> names = List.of("Robert", "John", "Julie", "Lora");

    int index;

    @Override
    public boolean hasNext() {
        return index < names.size();
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return names.get(index++);
        }

        return null;
    }

}
