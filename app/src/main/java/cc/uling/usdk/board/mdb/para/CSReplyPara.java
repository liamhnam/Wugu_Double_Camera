package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.para.C0683b;

public class CSReplyPara extends C0683b {

    private int f292a;

    private int f293b;

    public CSReplyPara() {
        m260a((short) 2);
        setReg((short) 5);
    }

    public int getMultiple() {
        return this.f293b;
    }

    public int getStatus() {
        return this.f292a;
    }

    public void setMultiple(int i) {
        this.f293b = i;
    }

    public void setStatus(int i) {
        this.f292a = i;
    }
}
