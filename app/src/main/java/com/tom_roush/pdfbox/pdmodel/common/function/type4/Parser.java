package com.tom_roush.pdfbox.pdmodel.common.function.type4;

public final class Parser {

    public static abstract class AbstractSyntaxHandler implements SyntaxHandler {
        @Override
        public void comment(CharSequence charSequence) {
        }

        @Override
        public void newLine(CharSequence charSequence) {
        }

        @Override
        public void whitespace(CharSequence charSequence) {
        }
    }

    private enum State {
        NEWLINE,
        WHITESPACE,
        COMMENT,
        TOKEN
    }

    public interface SyntaxHandler {
        void comment(CharSequence charSequence);

        void newLine(CharSequence charSequence);

        void token(CharSequence charSequence);

        void whitespace(CharSequence charSequence);
    }

    private Parser() {
    }

    public static void parse(CharSequence charSequence, SyntaxHandler syntaxHandler) {
        new Tokenizer(charSequence, syntaxHandler, null).tokenize();
    }

    private static final class Tokenizer {
        static final boolean $assertionsDisabled = false;

        private static final char f2364CR = '\r';
        private static final char EOT = 4;

        private static final char f2365FF = '\f';

        private static final char f2366LF = '\n';
        private static final char NUL = 0;
        private static final char SPACE = ' ';
        private static final char TAB = '\t';
        private final StringBuilder buffer;
        private final SyntaxHandler handler;
        private int index;
        private final CharSequence input;
        private State state;

        Tokenizer(CharSequence charSequence, SyntaxHandler syntaxHandler, C18741 c18741) {
            this(charSequence, syntaxHandler);
        }

        private Tokenizer(CharSequence charSequence, SyntaxHandler syntaxHandler) {
            this.state = State.WHITESPACE;
            this.buffer = new StringBuilder();
            this.input = charSequence;
            this.handler = syntaxHandler;
        }

        private boolean hasMore() {
            return this.index < this.input.length();
        }

        private char currentChar() {
            return this.input.charAt(this.index);
        }

        private char nextChar() {
            this.index++;
            return !hasMore() ? EOT : currentChar();
        }

        private char peek() {
            return this.index < this.input.length() + (-1) ? this.input.charAt(this.index + 1) : EOT;
        }

        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private com.tom_roush.pdfbox.pdmodel.common.function.type4.Parser.State nextState() {
            /*
                r2 = this;
                char r0 = r2.currentChar()
                if (r0 == 0) goto L2d
                r1 = 32
                if (r0 == r1) goto L2d
                r1 = 37
                if (r0 == r1) goto L28
                r1 = 9
                if (r0 == r1) goto L2d
                r1 = 10
                if (r0 == r1) goto L23
                r1 = 12
                if (r0 == r1) goto L23
                r1 = 13
                if (r0 == r1) goto L23
                com.tom_roush.pdfbox.pdmodel.common.function.type4.Parser$State r0 = com.tom_roush.pdfbox.pdmodel.common.function.type4.Parser.State.TOKEN
                r2.state = r0
                goto L31
            L23:
                com.tom_roush.pdfbox.pdmodel.common.function.type4.Parser$State r0 = com.tom_roush.pdfbox.pdmodel.common.function.type4.Parser.State.NEWLINE
                r2.state = r0
                goto L31
            L28:
                com.tom_roush.pdfbox.pdmodel.common.function.type4.Parser$State r0 = com.tom_roush.pdfbox.pdmodel.common.function.type4.Parser.State.COMMENT
                r2.state = r0
                goto L31
            L2d:
                com.tom_roush.pdfbox.pdmodel.common.function.type4.Parser$State r0 = com.tom_roush.pdfbox.pdmodel.common.function.type4.Parser.State.WHITESPACE
                r2.state = r0
            L31:
                com.tom_roush.pdfbox.pdmodel.common.function.type4.Parser$State r0 = r2.state
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.common.function.type4.Parser.Tokenizer.nextState():com.tom_roush.pdfbox.pdmodel.common.function.type4.Parser$State");
        }

        public void tokenize() {
            while (hasMore()) {
                this.buffer.setLength(0);
                nextState();
                int i = C18741.f2363xcf3e6a1c[this.state.ordinal()];
                if (i == 1) {
                    scanNewLine();
                } else if (i == 2) {
                    scanWhitespace();
                } else if (i == 3) {
                    scanComment();
                } else {
                    scanToken();
                }
            }
        }

        private void scanNewLine() {
            char cCurrentChar = currentChar();
            this.buffer.append(cCurrentChar);
            if (cCurrentChar == '\r' && peek() == '\n') {
                this.buffer.append(nextChar());
            }
            this.handler.newLine(this.buffer);
            nextChar();
        }

        private void scanWhitespace() {
            char cNextChar;
            this.buffer.append(currentChar());
            while (hasMore() && ((cNextChar = nextChar()) == 0 || cNextChar == '\t' || cNextChar == ' ')) {
                this.buffer.append(cNextChar);
            }
            this.handler.whitespace(this.buffer);
        }

        private void scanComment() {
            char cNextChar;
            this.buffer.append(currentChar());
            while (hasMore() && (cNextChar = nextChar()) != '\n' && cNextChar != '\f' && cNextChar != '\r') {
                this.buffer.append(cNextChar);
            }
            this.handler.comment(this.buffer);
        }

        private void scanToken() {
            char cNextChar;
            char cCurrentChar = currentChar();
            this.buffer.append(cCurrentChar);
            if (cCurrentChar == '{' || cCurrentChar == '}') {
                this.handler.token(this.buffer);
                nextChar();
                return;
            }
            while (hasMore() && (cNextChar = nextChar()) != 0 && cNextChar != 4 && cNextChar != ' ' && cNextChar != '{' && cNextChar != '}' && cNextChar != '\t' && cNextChar != '\n' && cNextChar != '\f' && cNextChar != '\r') {
                this.buffer.append(cNextChar);
            }
            this.handler.token(this.buffer);
        }
    }

    static class C18741 {

        static final int[] f2363xcf3e6a1c;

        static {
            int[] iArr = new int[State.values().length];
            f2363xcf3e6a1c = iArr;
            try {
                iArr[State.NEWLINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2363xcf3e6a1c[State.WHITESPACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2363xcf3e6a1c[State.COMMENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
