package cc.uling.usdk.board.p016wz.para;

public class XSReplyPara extends WzClsPara {

    private int f417a;

    private int f418b;

    public XSReplyPara(int i) {
        super(i);
        setReg((short) 14);
        m260a((short) 1);
    }

    public int getFaultCode() {
        return this.f418b;
    }

    public int getRunStatus() {
        return this.f417a;
    }

    public void setFaultCode(int i) {
        this.f418b = i;
    }

    public void setRunStatus(int i) {
        this.f417a = i;
    }
}
