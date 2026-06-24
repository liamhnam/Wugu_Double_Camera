package cc.uling.usdk.board.p016wz.para;

public class HCReplyPara extends WzClsPara {

    private String f359a;

    private int f360b;

    private int f361d;

    public HCReplyPara(int i) {
        super(i);
        setReg((short) 1);
        m260a((short) 2);
    }

    public int getColumn() {
        return this.f361d;
    }

    public int getRow() {
        return this.f360b;
    }

    public String getVersion() {
        return this.f359a;
    }

    public void setColumn(int i) {
        this.f361d = i;
    }

    public void setRow(int i) {
        this.f360b = i;
    }

    public void setVersion(String str) {
        this.f359a = str;
    }

    public String toString() {
        return "HCReplyPara{version='" + this.f359a + "', row=" + this.f360b + ", column=" + this.f361d + ", resultCode=" + this.f455c + '}';
    }
}
