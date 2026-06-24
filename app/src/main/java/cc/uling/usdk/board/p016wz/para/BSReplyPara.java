package cc.uling.usdk.board.p016wz.para;

public class BSReplyPara extends WzClsPara {

    private int f349a;

    private int f350b;

    public BSReplyPara(int i, int i2) {
        super(i);
        setReg((short) 5);
        m260a((short) i2);
    }

    public int getNo() {
        return this.f349a;
    }

    public int getStatus() {
        return this.f350b;
    }

    public void setNo(int i) {
        this.f349a = i;
    }

    public void setStatus(int i) {
        this.f350b = i;
    }
}
