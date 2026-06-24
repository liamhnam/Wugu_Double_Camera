package cc.uling.usdk.board.p016wz.para;

public class PXReplyPara extends WzClsPara {
    public PXReplyPara(int i, short s) {
        super(i);
        setFun((byte) 6);
        setReg((short) 4102);
        m260a(s);
    }
}
