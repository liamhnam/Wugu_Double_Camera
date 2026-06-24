package cc.uling.usdk.board.p016wz.para;

public class PYReplyPara extends WzClsPara {
    public PYReplyPara(int i, short s) {
        super(i);
        setFun((byte) 6);
        setReg((short) 4101);
        m260a(s);
    }
}
