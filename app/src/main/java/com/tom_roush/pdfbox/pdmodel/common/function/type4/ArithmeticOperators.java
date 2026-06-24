package com.tom_roush.pdfbox.pdmodel.common.function.type4;

import java.util.Stack;

class ArithmeticOperators {
    ArithmeticOperators() {
    }

    static class Abs implements Operator {
        Abs() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Number numberPopNumber = executionContext.popNumber();
            if (numberPopNumber instanceof Integer) {
                executionContext.getStack().push(Integer.valueOf(Math.abs(numberPopNumber.intValue())));
            } else {
                executionContext.getStack().push(Float.valueOf(Math.abs(numberPopNumber.floatValue())));
            }
        }
    }

    static class Add implements Operator {
        Add() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Number numberPopNumber = executionContext.popNumber();
            Number numberPopNumber2 = executionContext.popNumber();
            if ((numberPopNumber2 instanceof Integer) && (numberPopNumber instanceof Integer)) {
                long jLongValue = numberPopNumber2.longValue() + numberPopNumber.longValue();
                if (jLongValue < -2147483648L || jLongValue > 2147483647L) {
                    executionContext.getStack().push(Float.valueOf(jLongValue));
                    return;
                } else {
                    executionContext.getStack().push(Integer.valueOf((int) jLongValue));
                    return;
                }
            }
            executionContext.getStack().push(Float.valueOf(numberPopNumber2.floatValue() + numberPopNumber.floatValue()));
        }
    }

    static class Atan implements Operator {
        Atan() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            float degrees = ((float) Math.toDegrees((float) Math.atan2(executionContext.popReal(), executionContext.popReal()))) % 360.0f;
            if (degrees < 0.0f) {
                degrees += 360.0f;
            }
            executionContext.getStack().push(Float.valueOf(degrees));
        }
    }

    static class Ceiling implements Operator {
        Ceiling() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Number numberPopNumber = executionContext.popNumber();
            if (numberPopNumber instanceof Integer) {
                executionContext.getStack().push(numberPopNumber);
            } else {
                executionContext.getStack().push(Float.valueOf((float) Math.ceil(numberPopNumber.doubleValue())));
            }
        }
    }

    static class Cos implements Operator {
        Cos() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Float.valueOf((float) Math.cos(Math.toRadians(executionContext.popReal()))));
        }
    }

    static class Cvi implements Operator {
        Cvi() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Integer.valueOf(executionContext.popNumber().intValue()));
        }
    }

    static class Cvr implements Operator {
        Cvr() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Float.valueOf(executionContext.popNumber().floatValue()));
        }
    }

    static class Div implements Operator {
        Div() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Number numberPopNumber = executionContext.popNumber();
            executionContext.getStack().push(Float.valueOf(executionContext.popNumber().floatValue() / numberPopNumber.floatValue()));
        }
    }

    static class Exp implements Operator {
        Exp() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Float.valueOf((float) Math.pow(executionContext.popNumber().doubleValue(), executionContext.popNumber().doubleValue())));
        }
    }

    static class Floor implements Operator {
        Floor() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Number numberPopNumber = executionContext.popNumber();
            if (numberPopNumber instanceof Integer) {
                executionContext.getStack().push(numberPopNumber);
            } else {
                executionContext.getStack().push(Float.valueOf((float) Math.floor(numberPopNumber.doubleValue())));
            }
        }
    }

    static class IDiv implements Operator {
        IDiv() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            int iPopInt = executionContext.popInt();
            executionContext.getStack().push(Integer.valueOf(executionContext.popInt() / iPopInt));
        }
    }

    static class C1870Ln implements Operator {
        C1870Ln() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Float.valueOf((float) Math.log(executionContext.popNumber().doubleValue())));
        }
    }

    static class Log implements Operator {
        Log() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Float.valueOf((float) Math.log10(executionContext.popNumber().doubleValue())));
        }
    }

    static class Mod implements Operator {
        Mod() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            int iPopInt = executionContext.popInt();
            executionContext.getStack().push(Integer.valueOf(executionContext.popInt() % iPopInt));
        }
    }

    static class Mul implements Operator {
        Mul() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Number numberPopNumber = executionContext.popNumber();
            Number numberPopNumber2 = executionContext.popNumber();
            if ((numberPopNumber2 instanceof Integer) && (numberPopNumber instanceof Integer)) {
                long jLongValue = numberPopNumber2.longValue() * numberPopNumber.longValue();
                if (jLongValue >= -2147483648L && jLongValue <= 2147483647L) {
                    executionContext.getStack().push(Integer.valueOf((int) jLongValue));
                    return;
                } else {
                    executionContext.getStack().push(Float.valueOf(jLongValue));
                    return;
                }
            }
            executionContext.getStack().push(Float.valueOf((float) (numberPopNumber2.doubleValue() * numberPopNumber.doubleValue())));
        }
    }

    static class Neg implements Operator {
        Neg() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Number numberPopNumber = executionContext.popNumber();
            if (numberPopNumber instanceof Integer) {
                if (numberPopNumber.intValue() == Integer.MIN_VALUE) {
                    executionContext.getStack().push(Float.valueOf(-numberPopNumber.floatValue()));
                    return;
                } else {
                    executionContext.getStack().push(Integer.valueOf(-numberPopNumber.intValue()));
                    return;
                }
            }
            executionContext.getStack().push(Float.valueOf(-numberPopNumber.floatValue()));
        }
    }

    static class Round implements Operator {
        Round() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Number numberPopNumber = executionContext.popNumber();
            if (numberPopNumber instanceof Integer) {
                executionContext.getStack().push(Integer.valueOf(numberPopNumber.intValue()));
            } else {
                executionContext.getStack().push(Float.valueOf(Math.round(numberPopNumber.doubleValue())));
            }
        }
    }

    static class Sin implements Operator {
        Sin() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Float.valueOf((float) Math.sin(Math.toRadians(executionContext.popReal()))));
        }
    }

    static class Sqrt implements Operator {
        Sqrt() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            float fPopReal = executionContext.popReal();
            if (fPopReal < 0.0f) {
                throw new IllegalArgumentException("argument must be nonnegative");
            }
            executionContext.getStack().push(Float.valueOf((float) Math.sqrt(fPopReal)));
        }
    }

    static class Sub implements Operator {
        Sub() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            Number numberPopNumber = executionContext.popNumber();
            Number numberPopNumber2 = executionContext.popNumber();
            if ((numberPopNumber2 instanceof Integer) && (numberPopNumber instanceof Integer)) {
                long jLongValue = numberPopNumber2.longValue() - numberPopNumber.longValue();
                if (jLongValue < -2147483648L || jLongValue > 2147483647L) {
                    stack.push(Float.valueOf(jLongValue));
                    return;
                } else {
                    stack.push(Integer.valueOf((int) jLongValue));
                    return;
                }
            }
            stack.push(Float.valueOf(numberPopNumber2.floatValue() - numberPopNumber.floatValue()));
        }
    }

    static class Truncate implements Operator {
        Truncate() {
        }

        @Override
        public void execute(ExecutionContext executionContext) {
            Number numberPopNumber = executionContext.popNumber();
            if (numberPopNumber instanceof Integer) {
                executionContext.getStack().push(Integer.valueOf(numberPopNumber.intValue()));
            } else {
                executionContext.getStack().push(Float.valueOf((int) numberPopNumber.floatValue()));
            }
        }
    }
}
