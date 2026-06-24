package cc.uling.usdk.board.p016wz.para;

import cc.uling.usdk.p015a.C0672a;

public class SYPReplyPara extends WzClsPara {

    private int f395a;

    private int f396b;

    private int f397d;

    private int f398e;

    private int f399f;

    private int f400g;

    private int f401h;

    private int f402i;

    private int f403j;

    private int f404k;

    public SYPReplyPara(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        super(i);
        setFun((byte) 16);
        setReg((short) 8194);
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
        return this.f395a;
    }

    public int getY1() {
        return this.f396b;
    }

    public int getY2() {
        return this.f397d;
    }

    public int getY3() {
        return this.f398e;
    }

    public int getY4() {
        return this.f399f;
    }

    public int getY5() {
        return this.f400g;
    }

    public int getY6() {
        return this.f401h;
    }

    public int getY7() {
        return this.f402i;
    }

    public int getY8() {
        return this.f403j;
    }

    public int getY9() {
        return this.f404k;
    }

    public void setY0(int i) {
        this.f395a = i;
    }

    public void setY1(int i) {
        this.f396b = i;
    }

    public void setY2(int i) {
        this.f397d = i;
    }

    public void setY3(int i) {
        this.f398e = i;
    }

    public void setY4(int i) {
        this.f399f = i;
    }

    public void setY5(int i) {
        this.f400g = i;
    }

    public void setY6(int i) {
        this.f401h = i;
    }

    public void setY7(int i) {
        this.f402i = i;
    }

    public void setY8(int i) {
        this.f403j = i;
    }

    public void setY9(int i) {
        this.f404k = i;
    }

    public String toString() {
        return "{y0=" + this.f395a + ", y1=" + this.f396b + ", y2=" + this.f397d + ", y3=" + this.f398e + ", y4=" + this.f399f + ", y5=" + this.f400g + ", y6=" + this.f401h + ", y7=" + this.f402i + ", y8=" + this.f403j + ", y9=" + this.f404k + '}';
    }
}
