package cc.uling.usdk.board.p016wz.para;

public class ResetReplyPara extends WzClsPara {
    public ResetReplyPara(int i) {
        super(i);
        setFun((byte) 6);
        setReg((short) 4098);
        m260a((short) 1);
    }
}
