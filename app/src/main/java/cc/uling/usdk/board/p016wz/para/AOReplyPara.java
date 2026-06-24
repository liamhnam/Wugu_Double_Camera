package cc.uling.usdk.board.p016wz.para;

import cc.uling.usdk.p015a.C0672a;

public class AOReplyPara extends WzClsPara {

    private int f308a;

    private int f309b;

    private int f310d;

    private int f311e;

    private int f312f;

    private int f313g;

    private int f314h;

    private int f315i;

    private int f316j;

    private int f317k;

    private int f318l;

    private int f319m;

    private int f320n;

    private int f321o;

    private int f322p;

    private int f323q;

    private int f324r;

    private int f325s;

    private int f326t;

    private int f327u;

    private int f328v;

    public AOReplyPara(int i, int i2, int[] iArr) {
        super(i);
        setFun((byte) 16);
        setReg((short) 8196);
        m260a((short) 3);
        setTime(i2);
        setN0(m253a(iArr, 0));
        setN1(m253a(iArr, 1));
        setN2(m253a(iArr, 2));
        setN3(m253a(iArr, 3));
        setN4(m253a(iArr, 4));
        setN5(m253a(iArr, 5));
        setN6(m253a(iArr, 6));
        setN7(m253a(iArr, 7));
        setN8(m253a(iArr, 8));
        setN9(m253a(iArr, 9));
        setY0(m253a(iArr, 10));
        setY1(m253a(iArr, 11));
        setY2(m253a(iArr, 12));
        setY3(m253a(iArr, 13));
        setY4(m253a(iArr, 14));
        setY5(m253a(iArr, 15));
        setY6(m253a(iArr, 16));
        setY7(m253a(iArr, 17));
        setY8(m253a(iArr, 18));
        setY9(m253a(iArr, 19));
        byte[] bArrM171b = C0672a.m171b(i2);
        byte bM160a = C0672a.m160a(new boolean[]{false, false, false, false, false, false, false, false});
        boolean[] zArr = new boolean[8];
        zArr[0] = this.f325s == 1;
        zArr[1] = this.f326t == 1;
        zArr[2] = this.f327u == 1;
        zArr[3] = this.f328v == 1;
        zArr[4] = false;
        zArr[5] = false;
        zArr[6] = false;
        zArr[7] = false;
        byte bM160a2 = C0672a.m160a(zArr);
        boolean[] zArr2 = new boolean[8];
        zArr2[0] = this.f317k == 1;
        zArr2[1] = this.f318l == 1;
        zArr2[2] = this.f319m == 1;
        zArr2[3] = this.f320n == 1;
        zArr2[4] = this.f321o == 1;
        zArr2[5] = this.f322p == 1;
        zArr2[6] = this.f323q == 1;
        zArr2[7] = this.f324r == 1;
        byte bM160a3 = C0672a.m160a(zArr2);
        boolean[] zArr3 = new boolean[8];
        zArr3[0] = this.f309b == 1;
        zArr3[1] = this.f310d == 1;
        zArr3[2] = this.f311e == 1;
        zArr3[3] = this.f312f == 1;
        zArr3[4] = this.f313g == 1;
        zArr3[5] = this.f314h == 1;
        zArr3[6] = this.f315i == 1;
        zArr3[7] = this.f316j == 1;
        setExtData(new byte[]{bArrM171b[1], bArrM171b[0], bM160a, bM160a2, bM160a3, C0672a.m160a(zArr3)});
    }

    private int m253a(int[] iArr, int i) {
        if (iArr == null || iArr.length <= i) {
            return 0;
        }
        return iArr[i];
    }

    public int getN0() {
        return this.f309b;
    }

    public int getN1() {
        return this.f310d;
    }

    public int getN2() {
        return this.f311e;
    }

    public int getN3() {
        return this.f312f;
    }

    public int getN4() {
        return this.f313g;
    }

    public int getN5() {
        return this.f314h;
    }

    public int getN6() {
        return this.f315i;
    }

    public int getN7() {
        return this.f316j;
    }

    public int getN8() {
        return this.f317k;
    }

    public int getN9() {
        return this.f318l;
    }

    public int getTime() {
        return this.f308a;
    }

    public int getY0() {
        return this.f319m;
    }

    public int getY1() {
        return this.f320n;
    }

    public int getY2() {
        return this.f321o;
    }

    public int getY3() {
        return this.f322p;
    }

    public int getY4() {
        return this.f323q;
    }

    public int getY5() {
        return this.f324r;
    }

    public int getY6() {
        return this.f325s;
    }

    public int getY7() {
        return this.f326t;
    }

    public int getY8() {
        return this.f327u;
    }

    public int getY9() {
        return this.f328v;
    }

    public void setN0(int i) {
        this.f309b = i;
    }

    public void setN1(int i) {
        this.f310d = i;
    }

    public void setN2(int i) {
        this.f311e = i;
    }

    public void setN3(int i) {
        this.f312f = i;
    }

    public void setN4(int i) {
        this.f313g = i;
    }

    public void setN5(int i) {
        this.f314h = i;
    }

    public void setN6(int i) {
        this.f315i = i;
    }

    public void setN7(int i) {
        this.f316j = i;
    }

    public void setN8(int i) {
        this.f317k = i;
    }

    public void setN9(int i) {
        this.f318l = i;
    }

    public void setTime(int i) {
        this.f308a = i;
    }

    public void setY0(int i) {
        this.f319m = i;
    }

    public void setY1(int i) {
        this.f320n = i;
    }

    public void setY2(int i) {
        this.f321o = i;
    }

    public void setY3(int i) {
        this.f322p = i;
    }

    public void setY4(int i) {
        this.f323q = i;
    }

    public void setY5(int i) {
        this.f324r = i;
    }

    public void setY6(int i) {
        this.f325s = i;
    }

    public void setY7(int i) {
        this.f326t = i;
    }

    public void setY8(int i) {
        this.f327u = i;
    }

    public void setY9(int i) {
        this.f328v = i;
    }

    public String toString() {
        return "AOReplyPara{time=" + this.f308a + ", n0=" + this.f309b + ", n1=" + this.f310d + ", n2=" + this.f311e + ", n3=" + this.f312f + ", n4=" + this.f313g + ", n5=" + this.f314h + ", n6=" + this.f315i + ", n7=" + this.f316j + ", n8=" + this.f317k + ", n9=" + this.f318l + ", y0=" + this.f319m + ", y1=" + this.f320n + ", y2=" + this.f321o + ", y3=" + this.f322p + ", y4=" + this.f323q + ", y5=" + this.f324r + ", y6=" + this.f325s + ", y7=" + this.f326t + ", y8=" + this.f327u + ", y9=" + this.f328v + '}';
    }
}
