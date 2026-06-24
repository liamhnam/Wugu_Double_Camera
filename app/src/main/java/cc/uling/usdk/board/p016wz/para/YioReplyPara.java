package cc.uling.usdk.board.p016wz.para;

public class YioReplyPara extends WzClsPara {

    private int f439a;

    private int f440b;

    private int f441d;

    private int f442e;

    private int f443f;

    private int f444g;

    private int f445h;

    private int f446i;

    public YioReplyPara(int i) {
        super(i);
        setReg((short) 11);
        m260a((short) 1);
    }

    public int getIo0() {
        return this.f439a;
    }

    public int getIo1() {
        return this.f440b;
    }

    public int getIo2() {
        return this.f441d;
    }

    public int getIo3() {
        return this.f442e;
    }

    public int getIo4() {
        return this.f443f;
    }

    public int getIo5() {
        return this.f444g;
    }

    public int getIo6() {
        return this.f445h;
    }

    public int getIo7() {
        return this.f446i;
    }

    public void setIo0(int i) {
        this.f439a = i;
    }

    public void setIo1(int i) {
        this.f440b = i;
    }

    public void setIo2(int i) {
        this.f441d = i;
    }

    public void setIo3(int i) {
        this.f442e = i;
    }

    public void setIo4(int i) {
        this.f443f = i;
    }

    public void setIo5(int i) {
        this.f444g = i;
    }

    public void setIo6(int i) {
        this.f445h = i;
    }

    public void setIo7(int i) {
        this.f446i = i;
    }

    public String toString() {
        return "{io0=" + this.f439a + ", io1=" + this.f440b + ", io2=" + this.f441d + ", io3=" + this.f442e + ", io4=" + this.f443f + ", io5=" + this.f444g + ", io6=" + this.f445h + ", io7=" + this.f446i + '}';
    }
}
