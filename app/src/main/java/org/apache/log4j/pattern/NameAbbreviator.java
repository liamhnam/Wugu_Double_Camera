package org.apache.log4j.pattern;

import java.util.ArrayList;
import java.util.List;

public abstract class NameAbbreviator {
    private static final NameAbbreviator DEFAULT = new NOPAbbreviator();

    private static class NOPAbbreviator extends NameAbbreviator {
        @Override
        public void abbreviate(int i, StringBuffer stringBuffer) {
        }
    }

    public abstract void abbreviate(int i, StringBuffer stringBuffer);

    public static NameAbbreviator getAbbreviator(String str) {
        int i;
        int iCharAt;
        int i2;
        char cCharAt;
        if (str.length() > 0) {
            String strTrim = str.trim();
            if (strTrim.length() == 0) {
                return DEFAULT;
            }
            if (strTrim.length() > 0) {
                i = strTrim.charAt(0) == '-' ? 1 : 0;
                while (i < strTrim.length() && strTrim.charAt(i) >= '0' && strTrim.charAt(i) <= '9') {
                    i++;
                }
            } else {
                i = 0;
            }
            if (i == strTrim.length()) {
                int i3 = Integer.parseInt(strTrim);
                if (i3 >= 0) {
                    return new MaxElementAbbreviator(i3);
                }
                return new DropElementAbbreviator(-i3);
            }
            ArrayList arrayList = new ArrayList(5);
            int i4 = 0;
            while (i4 < strTrim.length() && i4 >= 0) {
                if (strTrim.charAt(i4) == '*') {
                    i2 = i4 + 1;
                    iCharAt = Integer.MAX_VALUE;
                } else if (strTrim.charAt(i4) < '0' || strTrim.charAt(i4) > '9') {
                    iCharAt = 0;
                    i2 = i4;
                } else {
                    iCharAt = strTrim.charAt(i4) - '0';
                    i2 = i4 + 1;
                }
                if (i2 >= strTrim.length() || (cCharAt = strTrim.charAt(i2)) == '.') {
                    cCharAt = 0;
                }
                arrayList.add(new PatternAbbreviatorFragment(iCharAt, cCharAt));
                int iIndexOf = strTrim.indexOf(".", i4);
                if (iIndexOf == -1) {
                    break;
                }
                i4 = iIndexOf + 1;
            }
            return new PatternAbbreviator(arrayList);
        }
        return DEFAULT;
    }

    public static NameAbbreviator getDefaultAbbreviator() {
        return DEFAULT;
    }

    private static class MaxElementAbbreviator extends NameAbbreviator {
        private final int count;

        public MaxElementAbbreviator(int i) {
            this.count = i;
        }

        @Override
        public void abbreviate(int i, StringBuffer stringBuffer) {
            int length = stringBuffer.length() - 1;
            String string = stringBuffer.toString();
            for (int i2 = this.count; i2 > 0; i2--) {
                length = string.lastIndexOf(".", length - 1);
                if (length == -1 || length < i) {
                    return;
                }
            }
            stringBuffer.delete(i, length + 1);
        }
    }

    private static class DropElementAbbreviator extends NameAbbreviator {
        private final int count;

        public DropElementAbbreviator(int i) {
            this.count = i;
        }

        @Override
        public void abbreviate(int i, StringBuffer stringBuffer) {
            int i2 = this.count;
            int iIndexOf = stringBuffer.indexOf(".", i);
            while (iIndexOf != -1) {
                i2--;
                if (i2 != 0) {
                    iIndexOf = stringBuffer.indexOf(".", iIndexOf + 1);
                } else {
                    stringBuffer.delete(i, iIndexOf + 1);
                    return;
                }
            }
        }
    }

    private static class PatternAbbreviatorFragment {
        private final int charCount;
        private final char ellipsis;

        public PatternAbbreviatorFragment(int i, char c) {
            this.charCount = i;
            this.ellipsis = c;
        }

        public int abbreviate(StringBuffer stringBuffer, int i) {
            int iIndexOf = stringBuffer.toString().indexOf(".", i);
            if (iIndexOf == -1) {
                return iIndexOf;
            }
            int i2 = iIndexOf - i;
            int i3 = this.charCount;
            if (i2 > i3) {
                stringBuffer.delete(i3 + i, iIndexOf);
                iIndexOf = this.charCount + i;
                char c = this.ellipsis;
                if (c != 0) {
                    stringBuffer.insert(iIndexOf, c);
                    iIndexOf++;
                }
            }
            return iIndexOf + 1;
        }
    }

    private static class PatternAbbreviator extends NameAbbreviator {
        private final PatternAbbreviatorFragment[] fragments;

        public PatternAbbreviator(List list) {
            if (list.size() == 0) {
                throw new IllegalArgumentException("fragments must have at least one element");
            }
            PatternAbbreviatorFragment[] patternAbbreviatorFragmentArr = new PatternAbbreviatorFragment[list.size()];
            this.fragments = patternAbbreviatorFragmentArr;
            list.toArray(patternAbbreviatorFragmentArr);
        }

        @Override
        public void abbreviate(int i, StringBuffer stringBuffer) {
            for (int i2 = 0; i2 < this.fragments.length - 1 && i < stringBuffer.length(); i2++) {
                i = this.fragments[i2].abbreviate(stringBuffer, i);
            }
            PatternAbbreviatorFragment patternAbbreviatorFragment = this.fragments[r0.length - 1];
            while (i < stringBuffer.length() && i >= 0) {
                i = patternAbbreviatorFragment.abbreviate(stringBuffer, i);
            }
        }
    }
}
