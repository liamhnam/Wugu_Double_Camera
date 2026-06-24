package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.para.C0683b;

public class PCReplyPara extends C0683b {
    public PCReplyPara(int i) {
        setFun((byte) 6);
        m260a((short) i);
        setReg((short) 4103);
    }
}
