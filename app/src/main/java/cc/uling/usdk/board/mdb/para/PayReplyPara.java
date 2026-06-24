package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.constants.MdbAddr;
import cc.uling.usdk.para.C0683b;

public class PayReplyPara extends C0683b {
    public PayReplyPara(boolean z) {
        setFun((byte) 6);
        m260a((short) 1);
        setReg(z ? MdbAddr.PAY_COMPLETED : (short) 4098);
    }

    public String toString() {
        return "PayReplyPara{resultCode=" + this.f455c + '}';
    }
}
