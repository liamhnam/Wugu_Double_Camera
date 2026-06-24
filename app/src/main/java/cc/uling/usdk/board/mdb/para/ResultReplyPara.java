package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.para.C0683b;

public class ResultReplyPara extends C0683b {
    public ResultReplyPara(boolean z) {
        setFun((byte) 6);
        m260a(z ? (short) 1 : (short) 0);
        setReg((short) 4099);
    }

    public String toString() {
        return "ResultReplyPara{resultCode=" + this.f455c + '}';
    }
}
