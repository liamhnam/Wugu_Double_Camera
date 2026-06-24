package org.bouncycastle.crypto.util;

import androidx.core.view.MotionEventCompat;
import java.util.HashMap;
import java.util.Map;
import kotlin.UByte;
import org.bouncycastle.crypto.AlphabetMapper;

public class BasicAlphabetMapper implements AlphabetMapper {
    private Map<Integer, Character> charMap;
    private Map<Character, Integer> indexMap;

    public BasicAlphabetMapper(String str) {
        this(str.toCharArray());
    }

    public BasicAlphabetMapper(char[] cArr) {
        this.indexMap = new HashMap();
        this.charMap = new HashMap();
        for (int i = 0; i != cArr.length; i++) {
            if (this.indexMap.containsKey(Character.valueOf(cArr[i]))) {
                throw new IllegalArgumentException("duplicate key detected in alphabet: " + cArr[i]);
            }
            this.indexMap.put(Character.valueOf(cArr[i]), Integer.valueOf(i));
            this.charMap.put(Integer.valueOf(i), Character.valueOf(cArr[i]));
        }
    }

    @Override
    public char[] convertToChars(byte[] bArr) {
        char[] cArr;
        int i = 0;
        if (this.charMap.size() <= 256) {
            cArr = new char[bArr.length];
            while (i != bArr.length) {
                cArr[i] = this.charMap.get(Integer.valueOf(bArr[i] & UByte.MAX_VALUE)).charValue();
                i++;
            }
        } else {
            if ((bArr.length & 1) != 0) {
                throw new IllegalArgumentException("two byte radix and input string odd length");
            }
            cArr = new char[bArr.length / 2];
            while (i != bArr.length) {
                cArr[i / 2] = this.charMap.get(Integer.valueOf(((bArr[i] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArr[i + 1] & UByte.MAX_VALUE))).charValue();
                i += 2;
            }
        }
        return cArr;
    }

    @Override
    public byte[] convertToIndexes(char[] cArr) {
        byte[] bArr;
        int i = 0;
        if (this.indexMap.size() <= 256) {
            bArr = new byte[cArr.length];
            while (i != cArr.length) {
                bArr[i] = this.indexMap.get(Character.valueOf(cArr[i])).byteValue();
                i++;
            }
        } else {
            bArr = new byte[cArr.length * 2];
            while (i != cArr.length) {
                int iIntValue = this.indexMap.get(Character.valueOf(cArr[i])).intValue();
                int i2 = i * 2;
                bArr[i2] = (byte) ((iIntValue >> 8) & 255);
                bArr[i2 + 1] = (byte) (iIntValue & 255);
                i++;
            }
        }
        return bArr;
    }

    @Override
    public int getRadix() {
        return this.indexMap.size();
    }
}
