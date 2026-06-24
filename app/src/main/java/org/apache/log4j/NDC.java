package org.apache.log4j;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import org.apache.log4j.helpers.LogLog;

public class NDC {
    static final int REAP_THRESHOLD = 5;

    static Hashtable f2785ht = new Hashtable();
    static int pushCounter = 0;

    private NDC() {
    }

    private static Stack getCurrentStack() {
        Hashtable hashtable = f2785ht;
        if (hashtable != null) {
            return (Stack) hashtable.get(Thread.currentThread());
        }
        return null;
    }

    public static void clear() {
        Stack currentStack = getCurrentStack();
        if (currentStack != null) {
            currentStack.setSize(0);
        }
    }

    public static Stack cloneStack() {
        Stack currentStack = getCurrentStack();
        if (currentStack == null) {
            return null;
        }
        return (Stack) currentStack.clone();
    }

    public static void inherit(Stack stack) {
        if (stack != null) {
            f2785ht.put(Thread.currentThread(), stack);
        }
    }

    public static String get() {
        Stack currentStack = getCurrentStack();
        if (currentStack == null || currentStack.isEmpty()) {
            return null;
        }
        return ((DiagnosticContext) currentStack.peek()).fullMessage;
    }

    public static int getDepth() {
        Stack currentStack = getCurrentStack();
        if (currentStack == null) {
            return 0;
        }
        return currentStack.size();
    }

    private static void lazyRemove() {
        int i;
        Thread thread;
        Hashtable hashtable = f2785ht;
        if (hashtable == null) {
            return;
        }
        synchronized (hashtable) {
            int i2 = pushCounter + 1;
            pushCounter = i2;
            if (i2 <= 5) {
                return;
            }
            pushCounter = 0;
            Vector vector = new Vector();
            Enumeration enumerationKeys = f2785ht.keys();
            loop0: while (true) {
                for (0; enumerationKeys.hasMoreElements() && i <= 4; i + 1) {
                    thread = (Thread) enumerationKeys.nextElement();
                    i = thread.isAlive() ? i + 1 : 0;
                }
                vector.addElement(thread);
            }
            int size = vector.size();
            for (int i3 = 0; i3 < size; i3++) {
                Thread thread2 = (Thread) vector.elementAt(i3);
                LogLog.debug(new StringBuffer("Lazy NDC removal for thread [").append(thread2.getName()).append("] (").append(f2785ht.size()).append(").").toString());
                f2785ht.remove(thread2);
            }
        }
    }

    public static String pop() {
        Stack currentStack = getCurrentStack();
        return (currentStack == null || currentStack.isEmpty()) ? "" : ((DiagnosticContext) currentStack.pop()).message;
    }

    public static String peek() {
        Stack currentStack = getCurrentStack();
        return (currentStack == null || currentStack.isEmpty()) ? "" : ((DiagnosticContext) currentStack.peek()).message;
    }

    public static void push(String str) {
        Stack currentStack = getCurrentStack();
        if (currentStack == null) {
            DiagnosticContext diagnosticContext = new DiagnosticContext(str, null);
            Stack stack = new Stack();
            f2785ht.put(Thread.currentThread(), stack);
            stack.push(diagnosticContext);
            return;
        }
        if (currentStack.isEmpty()) {
            currentStack.push(new DiagnosticContext(str, null));
        } else {
            currentStack.push(new DiagnosticContext(str, (DiagnosticContext) currentStack.peek()));
        }
    }

    public static void remove() {
        Hashtable hashtable = f2785ht;
        if (hashtable != null) {
            hashtable.remove(Thread.currentThread());
            lazyRemove();
        }
    }

    public static void setMaxDepth(int i) {
        Stack currentStack = getCurrentStack();
        if (currentStack == null || i >= currentStack.size()) {
            return;
        }
        currentStack.setSize(i);
    }

    private static class DiagnosticContext {
        String fullMessage;
        String message;

        DiagnosticContext(String str, DiagnosticContext diagnosticContext) {
            this.message = str;
            if (diagnosticContext != null) {
                this.fullMessage = new StringBuffer().append(diagnosticContext.fullMessage).append(' ').append(str).toString();
            } else {
                this.fullMessage = str;
            }
        }
    }
}
