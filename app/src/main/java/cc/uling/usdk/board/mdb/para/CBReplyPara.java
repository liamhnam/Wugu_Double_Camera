package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.p015a.C0672a;
import cc.uling.usdk.para.C0683b;

public class CBReplyPara extends C0683b {

    private int f291a;

    public CBReplyPara(int i) {
        m260a((short) 2);
        setFun((byte) 16);
        setReg((short) 8193);
        setMultiple(i);
        byte[] bArrM175d = C0672a.m175d(i);
        setExtData(bArrM175d.length == 1 ? new byte[]{0, 0, 0, bArrM175d[0]} : bArrM175d.length == 2 ? new byte[]{0, 0, bArrM175d[0], bArrM175d[1]} : bArrM175d.length == 3 ? new byte[]{0, bArrM175d[0], bArrM175d[1], bArrM175d[2]} : new byte[]{bArrM175d[0], bArrM175d[1], bArrM175d[2], bArrM175d[3]});
    }

    public int getMultiple() {
        return this.f291a;
    }

    public void setMultiple(int i) {
        this.f291a = i;
    }

    public String toString() {
        return "CBReplyPara{multiple=" + this.f291a + ", resultCode=" + this.f455c + '}';
    }
}
