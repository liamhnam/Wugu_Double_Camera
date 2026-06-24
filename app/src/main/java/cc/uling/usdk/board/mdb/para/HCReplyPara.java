package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.para.C0683b;

public class HCReplyPara extends C0683b {

    private int f294a;

    private boolean f295b;

    private boolean f296d;

    private boolean f297e;

    private boolean f298f;

    private boolean f299g;

    private String f300h;

    public HCReplyPara() {
        m260a((short) 2);
        setReg((short) 1);
    }

    public String getCode() {
        return this.f300h;
    }

    public int getVersion() {
        return this.f294a;
    }

    public boolean isWithCash() {
        return this.f296d;
    }

    public boolean isWithCoin() {
        return this.f295b;
    }

    public boolean isWithIdentify() {
        return this.f299g;
    }

    public boolean isWithPOS() {
        return this.f297e;
    }

    public boolean isWithPulse() {
        return this.f298f;
    }

    public void setCode(String str) {
        this.f300h = str;
    }

    public void setVersion(int i) {
        this.f294a = i;
    }

    public void setWithCash(boolean z) {
        this.f296d = z;
    }

    public void setWithCoin(boolean z) {
        this.f295b = z;
    }

    public void setWithIdentify(boolean z) {
        this.f299g = z;
    }

    public void setWithPOS(boolean z) {
        this.f297e = z;
    }

    public void setWithPulse(boolean z) {
        this.f298f = z;
    }

    public String toString() {
        return "HCReplyPara{version=" + this.f294a + ", withCoin=" + this.f295b + ", withCash=" + this.f296d + ", withPOS=" + this.f297e + ", withPulse=" + this.f298f + ", withIdentify=" + this.f299g + ", code='" + this.f300h + "', resultCode='" + getResultCode() + "'}";
    }
}
