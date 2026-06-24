package com.tom_roush.pdfbox.pdmodel.common.function.type4;

import java.util.Stack;

class RelationalOperators {
    RelationalOperators() {
    }

    static class C1876Eq implements Operator {
        C1876Eq() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            stack.push(Boolean.valueOf(isEqual(stack.pop(), stack.pop())));
        }

        protected boolean isEqual(Object obj, Object obj2) {
            if ((obj instanceof Number) && (obj2 instanceof Number)) {
                return ((Number) obj).floatValue() == ((Number) obj2).floatValue();
            }
            return obj.equals(obj2);
        }
    }

    private static abstract class AbstractNumberComparisonOperator implements Operator {
        protected abstract boolean compare(Number number, Number number2);

        private AbstractNumberComparisonOperator() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            stack.push(Boolean.valueOf(compare((Number) stack.pop(), (Number) stack.pop())));
        }
    }

    static class C1877Ge extends AbstractNumberComparisonOperator {
        C1877Ge() {
            super();
        }

        @Override
        protected boolean compare(Number number, Number number2) {
            return number.floatValue() >= number2.floatValue();
        }
    }

    static class C1878Gt extends AbstractNumberComparisonOperator {
        C1878Gt() {
            super();
        }

        @Override
        protected boolean compare(Number number, Number number2) {
            return number.floatValue() > number2.floatValue();
        }
    }

    static class C1879Le extends AbstractNumberComparisonOperator {
        C1879Le() {
            super();
        }

        @Override
        protected boolean compare(Number number, Number number2) {
            return number.floatValue() <= number2.floatValue();
        }
    }

    static class C1880Lt extends AbstractNumberComparisonOperator {
        C1880Lt() {
            super();
        }

        @Override
        protected boolean compare(Number number, Number number2) {
            return number.floatValue() < number2.floatValue();
        }
    }

    static class C1881Ne extends C1876Eq {
        C1881Ne() {
        }

        @Override
        protected boolean isEqual(Object obj, Object obj2) {
            return !super.isEqual(obj, obj2);
        }
    }
}
