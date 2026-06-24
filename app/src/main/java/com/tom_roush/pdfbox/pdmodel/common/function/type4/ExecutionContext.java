package com.tom_roush.pdfbox.pdmodel.common.function.type4;

import java.util.Stack;

public class ExecutionContext {
    private final Operators operators;
    private final Stack<Object> stack = new Stack<>();

    public ExecutionContext(Operators operators) {
        this.operators = operators;
    }

    public Stack<Object> getStack() {
        return this.stack;
    }

    public Operators getOperators() {
        return this.operators;
    }

    public Number popNumber() {
        return (Number) this.stack.pop();
    }

    public int popInt() {
        return ((Integer) this.stack.pop()).intValue();
    }

    public float popReal() {
        return ((Number) this.stack.pop()).floatValue();
    }
}
