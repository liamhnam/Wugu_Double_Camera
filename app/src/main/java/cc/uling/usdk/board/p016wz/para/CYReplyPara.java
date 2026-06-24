package cc.uling.usdk.board.p016wz.para;

public class CYReplyPara extends WzClsPara {

    private int f352a;

    public CYReplyPara(int i) {
        super(i);
        setReg((short) 6);
        m260a((short) 1);
    }

    public int getValue() {
        return this.f352a;
    }

    public void setValue(int i) {
        this.f352a = i;
    }
}
