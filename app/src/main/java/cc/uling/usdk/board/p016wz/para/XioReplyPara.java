package cc.uling.usdk.board.p016wz.para;

public class XioReplyPara extends WzClsPara {

    private int f419a;

    private int f420b;

    private int f421d;

    private int f422e;

    private int f423f;

    private int f424g;

    private int f425h;

    private int f426i;

    public XioReplyPara(int i) {
        super(i);
        setReg((short) 12);
        m260a((short) 1);
    }

    public int getIo0() {
        return this.f419a;
    }

    public int getIo1() {
        return this.f420b;
    }

    public int getIo2() {
        return this.f421d;
    }

    public int getIo3() {
        return this.f422e;
    }

    public int getIo4() {
        return this.f423f;
    }

    public int getIo5() {
        return this.f424g;
    }

    public int getIo6() {
        return this.f425h;
    }

    public int getIo7() {
        return this.f426i;
    }

    public void setIo0(int i) {
        this.f419a = i;
    }

    public void setIo1(int i) {
        this.f420b = i;
    }

    public void setIo2(int i) {
        this.f421d = i;
    }

    public void setIo3(int i) {
        this.f422e = i;
    }

    public void setIo4(int i) {
        this.f423f = i;
    }

    public void setIo5(int i) {
        this.f424g = i;
    }

    public void setIo6(int i) {
        this.f425h = i;
    }

    public void setIo7(int i) {
        this.f426i = i;
    }

    public String toString() {
        return "{io0=" + this.f419a + ", io1=" + this.f420b + ", io2=" + this.f421d + ", io3=" + this.f422e + ", io4=" + this.f423f + ", io5=" + this.f424g + ", io6=" + this.f425h + ", io7=" + this.f426i + '}';
    }
}
