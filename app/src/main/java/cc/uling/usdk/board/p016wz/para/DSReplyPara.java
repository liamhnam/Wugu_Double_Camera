package cc.uling.usdk.board.p016wz.para;

public class DSReplyPara extends WzClsPara {

    private int f358a;

    public DSReplyPara(int i) {
        super(i);
        setReg((short) 8);
        m260a((short) 1);
    }

    public int getStatus() {
        return this.f358a;
    }

    public void setStatus(int i) {
        this.f358a = i;
    }
}
