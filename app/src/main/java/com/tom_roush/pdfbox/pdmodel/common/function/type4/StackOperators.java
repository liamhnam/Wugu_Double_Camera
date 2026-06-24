package com.tom_roush.pdfbox.pdmodel.common.function.type4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

class StackOperators {
    StackOperators() {
    }

    static class Copy implements Operator {
        Copy() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            int iIntValue = ((Number) stack.pop()).intValue();
            if (iIntValue > 0) {
                int size = stack.size();
                stack.addAll(new ArrayList(stack.subList(size - iIntValue, size)));
            }
        }
    }

    static class Dup implements Operator {
        Dup() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            stack.push(stack.peek());
        }
    }

    static class Exch implements Operator {
        Exch() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            Object objPop = stack.pop();
            Object objPop2 = stack.pop();
            stack.push(objPop);
            stack.push(objPop2);
        }
    }

    static class Index implements Operator {
        Index() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            int iIntValue = ((Number) stack.pop()).intValue();
            if (iIntValue < 0) {
                throw new IllegalArgumentException("rangecheck: " + iIntValue);
            }
            stack.push(stack.get((stack.size() - iIntValue) - 1));
        }
    }

    static class Pop implements Operator {
        Pop() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().pop();
        }
    }

    static class Roll implements Operator {
        Roll() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            int iIntValue = ((Number) stack.pop()).intValue();
            int iIntValue2 = ((Number) stack.pop()).intValue();
            if (iIntValue == 0) {
                return;
            }
            if (iIntValue2 < 0) {
                throw new IllegalArgumentException("rangecheck: " + iIntValue2);
            }
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            int i = 0;
            if (iIntValue < 0) {
                int i2 = iIntValue2 + iIntValue;
                while (i < i2) {
                    linkedList2.addFirst(stack.pop());
                    i++;
                }
                while (iIntValue < 0) {
                    linkedList.addFirst(stack.pop());
                    iIntValue++;
                }
                stack.addAll(linkedList2);
                stack.addAll(linkedList);
                return;
            }
            int i3 = iIntValue2 - iIntValue;
            while (iIntValue > 0) {
                linkedList.addFirst(stack.pop());
                iIntValue--;
            }
            while (i < i3) {
                linkedList2.addFirst(stack.pop());
                i++;
            }
            stack.addAll(linkedList);
            stack.addAll(linkedList2);
        }
    }
}
