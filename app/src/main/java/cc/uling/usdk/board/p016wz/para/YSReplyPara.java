package cc.uling.usdk.board.p016wz.para;

public class YSReplyPara extends WzClsPara {

    private int f437a;

    private int f438b;

    public YSReplyPara(int i) {
        super(i);
        setReg((short) 13);
        m260a((short) 1);
    }

    public int getFaultCode() {
        return this.f438b;
    }

    public int getRunStatus() {
        return this.f437a;
    }

    public void setFaultCode(int i) {
        this.f438b = i;
    }

    public void setRunStatus(int i) {
        this.f437a = i;
    }
}
