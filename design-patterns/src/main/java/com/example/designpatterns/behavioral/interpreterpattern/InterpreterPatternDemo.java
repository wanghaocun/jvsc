package com.example.designpatterns.behavioral.interpreterpattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wanghc
 */
@Slf4j
public class InterpreterPatternDemo {

    //规则：Robert 和 John 是男性
    public static Expression getMaleExpression() {
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    //规则：Julie 是一个已婚的女性
    public static Expression getMarriedWomanExpression() {
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        log.info("John is male? " + isMale.interpret("John"));
        log.info("Julie is a married women? " + isMarriedWoman.interpret("Married Julie"));
    }

}
