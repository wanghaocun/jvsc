package com.example.designpatterns.behavioral.iteratorpattern;

/**
 * @author wanghaocun
 */
public class NameRepository implements Container {

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }

}
