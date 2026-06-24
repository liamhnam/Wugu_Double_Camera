package cc.uling.usdk.board.p016wz.para;

import cc.uling.usdk.p015a.C0672a;

public class SXPReplyPara extends WzClsPara {

    private int f385a;

    private int f386b;

    private int f387d;

    private int f388e;

    private int f389f;

    private int f390g;

    private int f391h;

    private int f392i;

    private int f393j;

    private int f394k;

    public SXPReplyPara(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        super(i);
        setFun((byte) 16);
        setReg((short) 8195);
        m260a((short) 10);
        byte[] bArrM164a = C0672a.m164a(i2);
        byte[] bArrM164a2 = C0672a.m164a(i3);
        byte[] bArrM164a3 = C0672a.m164a(i4);
        byte[] bArrM164a4 = C0672a.m164a(i5);
        byte[] bArrM164a5 = C0672a.m164a(i6);
        byte[] bArrM164a6 = C0672a.m164a(i7);
        byte[] bArrM164a7 = C0672a.m164a(i8);
        byte[] bArrM164a8 = C0672a.m164a(i9);
        byte[] bArrM164a9 = C0672a.m164a(i10);
        byte[] bArrM164a10 = C0672a.m164a(i11);
        setExtData(new byte[]{bArrM164a[0], bArrM164a[1], bArrM164a2[0], bArrM164a2[1], bArrM164a3[0], bArrM164a3[1], bArrM164a4[0], bArrM164a4[1], bArrM164a5[0], bArrM164a5[1], bArrM164a6[0], bArrM164a6[1], bArrM164a7[0], bArrM164a7[1], bArrM164a8[0], bArrM164a8[1], bArrM164a9[0], bArrM164a9[1], bArrM164a10[0], bArrM164a10[1]});
    }

    public int getY0() {
        return this.f385a;
    }

    public int getY1() {
        return this.f386b;
    }

    public int getY2() {
        return this.f387d;
    }

    public int getY3() {
        return this.f388e;
    }

    public int getY4() {
        return this.f389f;
    }

    public int getY5() {
        return this.f390g;
    }

    public int getY6() {
        return this.f391h;
    }

    public int getY7() {
        return this.f392i;
    }

    public int getY8() {
        return this.f393j;
    }

    public int getY9() {
        return this.f394k;
    }

    public void setY0(int i) {
        this.f385a = i;
    }

    public void setY1(int i) {
        this.f386b = i;
    }

    public void setY2(int i) {
        this.f387d = i;
    }

    public void setY3(int i) {
        this.f388e = i;
    }

    public void setY4(int i) {
        this.f389f = i;
    }

    public void setY5(int i) {
        this.f390g = i;
    }

    public void setY6(int i) {
        this.f391h = i;
    }

    public void setY7(int i) {
        this.f392i = i;
    }

    public void setY8(int i) {
        this.f393j = i;
    }

    public void setY9(int i) {
        this.f394k = i;
    }

    public String toString() {
        return "{y0=" + this.f385a + ", y1=" + this.f386b + ", y2=" + this.f387d + ", y3=" + this.f388e + ", y4=" + this.f389f + ", y5=" + this.f390g + ", y6=" + this.f391h + ", y7=" + this.f392i + ", y8=" + this.f393j + ", y9=" + this.f394k + '}';
    }
}
