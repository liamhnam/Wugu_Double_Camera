package cc.uling.usdk.board.p016wz.para;

import cc.uling.usdk.p015a.C0672a;
import cc.uling.usdk.para.C0683b;

public class SSReplyPara extends C0683b {

    private int f382a;

    private int f383b;

    public SSReplyPara(int i) {
        setFun((byte) 3);
        setReg((short) 3);
        m260a((short) 1);
        setAddr(C0672a.m171b(i)[0]);
    }

    public int getFaultCode() {
        return this.f383b;
    }

    public int getRunStatus() {
        return this.f382a;
    }

    public void setFaultCode(int i) {
        this.f383b = i;
    }

    public void setRunStatus(int i) {
        this.f382a = i;
    }
}
