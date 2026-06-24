package com.tom_roush.pdfbox.pdmodel.common.function.type4;

import java.util.Stack;

class BitwiseOperators {
    BitwiseOperators() {
    }

    private static abstract class AbstractLogicalOperator implements Operator {
        protected abstract boolean applyForBoolean(boolean z, boolean z2);

        protected abstract int applyforInteger(int i, int i2);

        private AbstractLogicalOperator() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            Object objPop = stack.pop();
            Object objPop2 = stack.pop();
            if ((objPop2 instanceof Boolean) && (objPop instanceof Boolean)) {
                stack.push(Boolean.valueOf(applyForBoolean(((Boolean) objPop2).booleanValue(), ((Boolean) objPop).booleanValue())));
            } else {
                if ((objPop2 instanceof Integer) && (objPop instanceof Integer)) {
                    stack.push(Integer.valueOf(applyforInteger(((Integer) objPop2).intValue(), ((Integer) objPop).intValue())));
                    return;
                }
                throw new ClassCastException("Operands must be bool/bool or int/int");
            }
        }
    }

    static class And extends AbstractLogicalOperator {
        @Override
        protected boolean applyForBoolean(boolean z, boolean z2) {
            return z & z2;
        }

        @Override
        protected int applyforInteger(int i, int i2) {
            return i & i2;
        }

        And() {
            super();
        }
    }

    static class Bitshift implements Operator {
        Bitshift() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            int iIntValue = ((Integer) stack.pop()).intValue();
            int iIntValue2 = ((Integer) stack.pop()).intValue();
            if (iIntValue < 0) {
                stack.push(Integer.valueOf(iIntValue2 >> Math.abs(iIntValue)));
            } else {
                stack.push(Integer.valueOf(iIntValue2 << iIntValue));
            }
        }
    }

    static class False implements Operator {
        False() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Boolean.FALSE);
        }
    }

    static class Not implements Operator {
        Not() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            Object objPop = stack.pop();
            if (objPop instanceof Boolean) {
                stack.push(Boolean.valueOf(!((Boolean) objPop).booleanValue()));
            } else {
                if (objPop instanceof Integer) {
                    stack.push(Integer.valueOf(-((Integer) objPop).intValue()));
                    return;
                }
                throw new ClassCastException("Operand must be bool or int");
            }
        }
    }

    static class C1872Or extends AbstractLogicalOperator {
        @Override
        protected boolean applyForBoolean(boolean z, boolean z2) {
            return z | z2;
        }

        @Override
        protected int applyforInteger(int i, int i2) {
            return i | i2;
        }

        C1872Or() {
            super();
        }
    }

    static class True implements Operator {
        True() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Boolean.TRUE);
        }
    }

    static class Xor extends AbstractLogicalOperator {
        @Override
        protected boolean applyForBoolean(boolean z, boolean z2) {
            return z ^ z2;
        }

        @Override
        protected int applyforInteger(int i, int i2) {
            return i ^ i2;
        }

        Xor() {
            super();
        }
    }
}
