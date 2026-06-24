package cc.uling.usdk.board.p016wz.para;

public class MTReplyPara extends WzClsPara {

    private int f375a;

    public MTReplyPara(int i, short s) {
        super(i);
        setFun((byte) 6);
        setReg((short) 4100);
        m260a(s);
        setTime(s);
    }

    public int getTime() {
        return this.f375a;
    }

    public void setTime(int i) {
        this.f375a = i;
    }
}
