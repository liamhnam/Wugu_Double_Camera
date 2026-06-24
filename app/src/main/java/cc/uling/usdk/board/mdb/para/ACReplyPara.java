package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.p015a.C0672a;
import cc.uling.usdk.para.C0683b;
import java.util.Arrays;

public class ACReplyPara extends C0683b {

    private int[] f255a = new int[16];

    public ACReplyPara(int i, int[] iArr) {
        setFun((byte) 6);
        setReg(i == 0 ? (short) 4100 : (short) 4101);
        if (iArr != null) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                int[] iArr2 = this.f255a;
                if (i2 < iArr2.length) {
                    iArr2[i2] = iArr[i2];
                }
            }
        }
        String str = new String();
        for (int i3 = 0; i3 < this.f255a.length; i3++) {
            str = this.f255a[i3] + str;
        }
        m260a((short) C0672a.m169b(C0672a.m165a(C0672a.m172c(str))));
    }

    public void setChannel(int i, int i2) {
        if (i > 0) {
            int[] iArr = this.f255a;
            if (i <= iArr.length) {
                iArr[i - 1] = i2;
            }
        }
    }

    public void setChannels(String str) {
        String string = new StringBuffer(str).reverse().toString();
        for (int i = 0; i < string.length(); i++) {
            if (i < this.f255a.length) {
                if (string.charAt(i) == '1') {
                    this.f255a[i] = 1;
                } else {
                    this.f255a[i] = 0;
                }
            }
        }
    }

    public String toString() {
        return "channels=" + Arrays.toString(this.f255a);
    }
}
