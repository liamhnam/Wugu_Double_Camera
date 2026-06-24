package cc.uling.usdk.board.p016wz.para;

public class XPReplyPara extends WzClsPara {

    private int f407a;

    private int f408b;

    private int f409d;

    private int f410e;

    private int f411f;

    private int f412g;

    private int f413h;

    private int f414i;

    private int f415j;

    private int f416k;

    public XPReplyPara(int i) {
        super(i);
        setReg((short) 16);
        m260a((short) 10);
    }

    public int getX0() {
        return this.f407a;
    }

    public int getX1() {
        return this.f408b;
    }

    public int getX2() {
        return this.f409d;
    }

    public int getX3() {
        return this.f410e;
    }

    public int getX4() {
        return this.f411f;
    }

    public int getX5() {
        return this.f412g;
    }

    public int getX6() {
        return this.f413h;
    }

    public int getX7() {
        return this.f414i;
    }

    public int getX8() {
        return this.f415j;
    }

    public int getX9() {
        return this.f416k;
    }

    public void setX0(int i) {
        this.f407a = i;
    }

    public void setX1(int i) {
        this.f408b = i;
    }

    public void setX2(int i) {
        this.f409d = i;
    }

    public void setX3(int i) {
        this.f410e = i;
    }

    public void setX4(int i) {
        this.f411f = i;
    }

    public void setX5(int i) {
        this.f412g = i;
    }

    public void setX6(int i) {
        this.f413h = i;
    }

    public void setX7(int i) {
        this.f414i = i;
    }

    public void setX8(int i) {
        this.f415j = i;
    }

    public void setX9(int i) {
        this.f416k = i;
    }

    public String toString() {
        return "{x0=" + this.f407a + ", x1=" + this.f408b + ", x2=" + this.f409d + ", x3=" + this.f410e + ", x4=" + this.f411f + ", x5=" + this.f412g + ", x6=" + this.f413h + ", x7=" + this.f414i + ", x8=" + this.f415j + ", x9=" + this.f416k + '}';
    }
}
