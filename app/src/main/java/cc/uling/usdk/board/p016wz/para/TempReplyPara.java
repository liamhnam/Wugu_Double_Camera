package cc.uling.usdk.board.p016wz.para;

import cc.uling.usdk.p015a.C0672a;
import cc.uling.usdk.para.C0683b;

public class TempReplyPara extends C0683b {

    private int f405a;

    private int f406b;

    public TempReplyPara(int i) {
        setReg((short) 4);
        m260a((short) 2);
        setAddr(C0672a.m171b(i)[0]);
    }

    public int getHumi() {
        return this.f406b;
    }

    public int getTemp() {
        return this.f405a;
    }

    public void setHumi(int i) {
        this.f406b = i;
    }

    public void setTemp(int i) {
        this.f405a = i;
    }
}
