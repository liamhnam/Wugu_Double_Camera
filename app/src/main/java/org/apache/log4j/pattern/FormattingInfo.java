package org.apache.log4j.pattern;

public final class FormattingInfo {
    private final boolean leftAlign;
    private final int maxLength;
    private final int minLength;
    private static final char[] SPACES = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    private static final FormattingInfo DEFAULT = new FormattingInfo(false, 0, Integer.MAX_VALUE);

    public FormattingInfo(boolean z, int i, int i2) {
        this.leftAlign = z;
        this.minLength = i;
        this.maxLength = i2;
    }

    public static FormattingInfo getDefault() {
        return DEFAULT;
    }

    public boolean isLeftAligned() {
        return this.leftAlign;
    }

    public int getMinLength() {
        return this.minLength;
    }

    public int getMaxLength() {
        return this.maxLength;
    }

    public void format(int i, StringBuffer stringBuffer) {
        int length = stringBuffer.length() - i;
        if (length > this.maxLength) {
            stringBuffer.delete(i, stringBuffer.length() - this.maxLength);
            return;
        }
        int i2 = this.minLength;
        if (length < i2) {
            if (this.leftAlign) {
                stringBuffer.setLength(i + this.minLength);
                for (int length2 = stringBuffer.length(); length2 < stringBuffer.length(); length2++) {
                    stringBuffer.setCharAt(length2, ' ');
                }
                return;
            }
            int i3 = i2 - length;
            while (i3 > 8) {
                stringBuffer.insert(i, SPACES);
                i3 -= 8;
            }
            stringBuffer.insert(i, SPACES, 0, i3);
        }
    }
}
