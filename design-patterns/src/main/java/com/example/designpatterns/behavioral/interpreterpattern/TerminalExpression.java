package com.example.designpatterns.behavioral.interpreterpattern;

/**
 * @author wanghc
 */
public class TerminalExpression implements Expression {

    private final String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        return context.contains(data);
    }

}
