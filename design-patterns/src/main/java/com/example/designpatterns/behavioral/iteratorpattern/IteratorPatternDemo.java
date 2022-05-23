package com.example.designpatterns.behavioral.iteratorpattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wanghaocun
 */
@Slf4j
public class IteratorPatternDemo {

    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        for (Iterator iter = namesRepository.getIterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            log.info("Name : {}", name);
        }
    }

}
