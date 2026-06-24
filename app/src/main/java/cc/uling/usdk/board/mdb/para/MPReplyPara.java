package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.para.C0683b;

public class MPReplyPara extends C0683b {

    int f303a;

    int f304b;

    public MPReplyPara() {
        m260a((short) 2);
        setReg((short) 4);
    }

    public int getDecimal() {
        return this.f304b;
    }

    public int getValue() {
        return this.f303a;
    }

    public void setDecimal(int i) {
        this.f304b = i;
    }

    public void setValue(int i) {
        this.f303a = i;
    }

    public String toString() {
        return "MPReplyPara{value=" + this.f303a + ",decimal=" + this.f304b + ", resultCode=" + this.f455c + '}';
    }
}
