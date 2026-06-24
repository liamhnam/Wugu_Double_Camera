package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.para.C0683b;

public class PMReplyPara extends C0683b {

    private int f305a;

    private int f306b;

    public PMReplyPara() {
        m260a((short) 2);
        setReg((short) 3);
    }

    public int getMultiple() {
        return this.f306b;
    }

    public int getPayType() {
        return this.f305a;
    }

    public void setMultiple(int i) {
        this.f306b = i;
    }

    public void setPayType(int i) {
        this.f305a = i;
    }

    public String toString() {
        return "PMReplyPara{payType=" + this.f305a + ", multiple=" + this.f306b + ", resultCode=" + this.f455c + '}';
    }
}
