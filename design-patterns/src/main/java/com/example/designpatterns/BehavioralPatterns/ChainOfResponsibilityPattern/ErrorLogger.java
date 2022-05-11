package com.example.designpatterns.BehavioralPatterns.ChainOfResponsibilityPattern;

/**
 * @author wanghc
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }

}
