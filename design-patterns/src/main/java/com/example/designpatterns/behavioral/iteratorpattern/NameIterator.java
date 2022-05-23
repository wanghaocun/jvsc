package com.example.designpatterns.behavioral.iteratorpattern;

/**
 * @author wanghaocun
 */
public class NameIterator implements Iterator {

    String[] names = {"Robert", "John", "Julie", "Lora"};

    int index;

    @Override
    public boolean hasNext() {
        return index < names.length;
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return names[index++];
        }

        return null;
    }

}
