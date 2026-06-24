package cc.uling.usdk.board.p016wz.para;

import cc.uling.usdk.p015a.C0672a;

public class RMReplyPara extends WzClsPara {

    private int f376a;

    private int f377b;

    public RMReplyPara(int i, short s, short s2) {
        super(i);
        setFun((byte) 6);
        setReg((short) 4099);
        setMode(s);
        setStatus(s2);
        m260a((short) C0672a.m169b(new byte[]{C0672a.m171b(s)[0], C0672a.m171b(s2)[0]}));
    }

    public int getMode() {
        return this.f376a;
    }

    public int getStatus() {
        return this.f377b;
    }

    public void setMode(int i) {
        this.f376a = i;
    }

    public void setStatus(int i) {
        this.f377b = i;
    }
}
