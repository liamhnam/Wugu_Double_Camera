package cc.uling.usdk.board.p016wz.para;

public class CXReplyPara extends WzClsPara {

    private int f351a;

    public CXReplyPara(int i) {
        super(i);
        setReg((short) 7);
        m260a((short) 1);
    }

    public int getValue() {
        return this.f351a;
    }

    public void setValue(int i) {
        this.f351a = i;
    }
}
