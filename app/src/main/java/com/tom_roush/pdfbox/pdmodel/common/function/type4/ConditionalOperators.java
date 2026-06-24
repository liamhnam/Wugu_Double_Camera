package com.tom_roush.pdfbox.pdmodel.common.function.type4;

import java.util.Stack;

class ConditionalOperators {
    ConditionalOperators() {
    }

    static class C1873If implements Operator {
        C1873If() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            InstructionSequence instructionSequence = (InstructionSequence) stack.pop();
            if (((Boolean) stack.pop()).booleanValue()) {
                instructionSequence.execute(executionContext);
            }
        }
    }

    static class IfElse implements Operator {
        IfElse() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            InstructionSequence instructionSequence = (InstructionSequence) stack.pop();
            InstructionSequence instructionSequence2 = (InstructionSequence) stack.pop();
            if (((Boolean) stack.pop()).booleanValue()) {
                instructionSequence2.execute(executionContext);
            } else {
                instructionSequence.execute(executionContext);
            }
        }
    }
}
