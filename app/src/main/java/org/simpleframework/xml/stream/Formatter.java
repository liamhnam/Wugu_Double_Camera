package org.simpleframework.xml.stream;

import java.io.BufferedWriter;
import java.io.Writer;
import kotlin.text.Typography;

class Formatter {
    private OutputBuffer buffer = new OutputBuffer();
    private Indenter indenter;
    private Tag last;
    private String prolog;
    private Writer result;
    private static final char[] NAMESPACE = {'x', 'm', 'l', 'n', 's'};
    private static final char[] LESS = {Typography.amp, 'l', 't', ';'};
    private static final char[] GREATER = {Typography.amp, 'g', 't', ';'};
    private static final char[] DOUBLE = {Typography.amp, 'q', 'u', 'o', 't', ';'};
    private static final char[] SINGLE = {Typography.amp, 'a', 'p', 'o', 's', ';'};
    private static final char[] AND = {Typography.amp, 'a', 'm', 'p', ';'};
    private static final char[] OPEN = {Typography.less, '!', '-', '-', ' '};
    private static final char[] CLOSE = {' ', '-', '-', Typography.greater};

    private enum Tag {
        COMMENT,
        START,
        TEXT,
        END
    }

    private boolean isText(char c) {
        if (c == '\t' || c == '\n' || c == '\r' || c == ' ') {
            return true;
        }
        return c > ' ' && c <= '~' && c != 247;
    }

    public Formatter(Writer writer, Format format) {
        this.result = new BufferedWriter(writer, 1024);
        this.indenter = new Indenter(format);
        this.prolog = format.getProlog();
    }

    public void writeProlog() throws Exception {
        String str = this.prolog;
        if (str != null) {
            write(str);
            write("\n");
        }
    }

    public void writeComment(String str) throws Exception {
        String pVar = this.indenter.top();
        if (this.last == Tag.START) {
            append(Typography.greater);
        }
        if (pVar != null) {
            append(pVar);
            append(OPEN);
            append(str);
            append(CLOSE);
        }
        this.last = Tag.COMMENT;
    }

    public void writeStart(String str, String str2) throws Exception {
        String strPush = this.indenter.push();
        if (this.last == Tag.START) {
            append(Typography.greater);
        }
        flush();
        append(strPush);
        append(Typography.less);
        if (!isEmpty(str2)) {
            append(str2);
            append(':');
        }
        append(str);
        this.last = Tag.START;
    }

    public void writeAttribute(String str, String str2, String str3) throws Exception {
        if (this.last != Tag.START) {
            throw new NodeException("Start element required");
        }
        write(' ');
        write(str, str3);
        write('=');
        write(Typography.quote);
        escape(str2);
        write(Typography.quote);
    }

    public void writeNamespace(String str, String str2) throws Exception {
        if (this.last != Tag.START) {
            throw new NodeException("Start element required");
        }
        write(' ');
        write(NAMESPACE);
        if (!isEmpty(str2)) {
            write(':');
            write(str2);
        }
        write('=');
        write(Typography.quote);
        escape(str);
        write(Typography.quote);
    }

    public void writeText(String str) throws Exception {
        writeText(str, Mode.ESCAPE);
    }

    public void writeText(String str, Mode mode) throws Exception {
        if (this.last == Tag.START) {
            write(Typography.greater);
        }
        if (mode == Mode.DATA) {
            data(str);
        } else {
            escape(str);
        }
        this.last = Tag.TEXT;
    }

    public void writeEnd(String str, String str2) throws Exception {
        String strPop = this.indenter.pop();
        if (this.last == Tag.START) {
            write('/');
            write(Typography.greater);
        } else {
            if (this.last != Tag.TEXT) {
                write(strPop);
            }
            if (this.last != Tag.START) {
                write(Typography.less);
                write('/');
                write(str, str2);
                write(Typography.greater);
            }
        }
        this.last = Tag.END;
    }

    private void write(char c) throws Exception {
        this.buffer.write(this.result);
        this.buffer.clear();
        this.result.write(c);
    }

    private void write(char[] cArr) throws Exception {
        this.buffer.write(this.result);
        this.buffer.clear();
        this.result.write(cArr);
    }

    private void write(String str) throws Exception {
        this.buffer.write(this.result);
        this.buffer.clear();
        this.result.write(str);
    }

    private void write(String str, String str2) throws Exception {
        this.buffer.write(this.result);
        this.buffer.clear();
        if (!isEmpty(str2)) {
            this.result.write(str2);
            this.result.write(58);
        }
        this.result.write(str);
    }

    private void append(char c) throws Exception {
        this.buffer.append(c);
    }

    private void append(char[] cArr) throws Exception {
        this.buffer.append(cArr);
    }

    private void append(String str) throws Exception {
        this.buffer.append(str);
    }

    private void data(String str) throws Exception {
        write("<![CDATA[");
        write(str);
        write("]]>");
    }

    private void escape(String str) throws Exception {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            escape(str.charAt(i));
        }
    }

    private void escape(char c) throws Exception {
        char[] cArrSymbol = symbol(c);
        if (cArrSymbol != null) {
            write(cArrSymbol);
        } else {
            write(c);
        }
    }

    public void flush() throws Exception {
        this.buffer.write(this.result);
        this.buffer.clear();
        this.result.flush();
    }

    private String unicode(char c) {
        return Integer.toString(c);
    }

    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private char[] symbol(char c) {
        if (c == '\"') {
            return DOUBLE;
        }
        if (c == '<') {
            return LESS;
        }
        if (c == '>') {
            return GREATER;
        }
        if (c == '&') {
            return AND;
        }
        if (c != '\'') {
            return null;
        }
        return SINGLE;
    }
}
