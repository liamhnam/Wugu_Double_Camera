package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.para.C0683b;

public class ASReplyPara extends C0683b {
    public ASReplyPara(int i) {
        setFun((byte) 6);
        setReg((short) 4104);
        m260a((short) i);
    }
}
