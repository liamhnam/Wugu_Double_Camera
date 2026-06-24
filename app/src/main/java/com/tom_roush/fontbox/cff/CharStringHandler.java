package com.tom_roush.fontbox.cff;

import java.util.List;
import java.util.Stack;

public abstract class CharStringHandler {
    public abstract List<Integer> handleCommand(List<Integer> list, CharStringCommand charStringCommand);

    public List<Integer> handleSequence(List<Object> list) {
        Stack stack = new Stack();
        for (Object obj : list) {
            if (obj instanceof CharStringCommand) {
                List<Integer> listHandleCommand = handleCommand(stack, (CharStringCommand) obj);
                stack.clear();
                if (listHandleCommand != null) {
                    stack.addAll(listHandleCommand);
                }
            } else {
                stack.push((Integer) obj);
            }
        }
        return stack;
    }
}
