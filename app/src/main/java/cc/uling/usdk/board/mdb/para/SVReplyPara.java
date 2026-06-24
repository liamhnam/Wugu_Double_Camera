package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.para.C0683b;

public class SVReplyPara extends C0683b {

    private String f307a;

    public SVReplyPara() {
        m260a((short) 2);
        setReg((short) 2);
    }

    public String getVersion() {
        return this.f307a;
    }

    public void setVersion(String str) {
        this.f307a = str;
    }

    public String toString() {
        return "SVReplyPara{version='" + this.f307a + "', resultCode=" + this.f455c + '}';
    }
}
