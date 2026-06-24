package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.para.C0683b;

public class WMReplyPara extends C0683b {
    public WMReplyPara(int i) {
        setFun((byte) 6);
        m260a((short) i);
        setReg((short) 4102);
    }
}
