package cc.uling.usdk.board.p016wz.para;

public class SVReplyPara extends WzClsPara {

    private String f384a;

    public SVReplyPara(int i) {
        super(i);
        setReg((short) 2);
        m260a((short) 2);
    }

    public String getVersion() {
        return this.f384a;
    }

    public void setVersion(String str) {
        this.f384a = str;
    }
}
