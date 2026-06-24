package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.p015a.C0672a;
import cc.uling.usdk.para.C0683b;

public class PBReplyPara extends C0683b {
    public PBReplyPara(int i, int i2) {
        setFun((byte) 16);
        m260a((short) 2);
        setReg(i == 0 ? (short) 8194 : (short) 8195);
        setExtData(C0672a.m173c(i2));
    }
}
