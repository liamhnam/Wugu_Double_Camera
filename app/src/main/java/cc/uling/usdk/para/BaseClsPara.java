package cc.uling.usdk.para;

public class BaseClsPara {

    private String f453a;

    private byte f454b;

    protected int f455c;

    private byte f456d;

    private short f457e;

    private short f458f;

    private byte[] f459g;

    private int f460h;

    protected void m260a(short s) {
        this.f458f = s;
    }

    public byte getAddr() {
        return this.f454b;
    }

    public String getErrorMsg() {
        return this.f453a;
    }

    public byte[] getExtData() {
        return this.f459g;
    }

    public byte getFun() {
        return this.f456d;
    }

    public int getLen() {
        return this.f460h;
    }

    public short getReadLen() {
        return this.f458f;
    }

    public short getReg() {
        return this.f457e;
    }

    public int getResultCode() {
        return this.f455c;
    }

    public boolean isOK() {
        return this.f455c == 0;
    }

    public void setAddr(byte b) {
        this.f454b = b;
    }

    public void setErrorMsg(String str) {
        this.f453a = str;
    }

    public void setExtData(byte[] bArr) {
        this.f459g = bArr;
    }

    public void setFun(byte b) {
        this.f456d = b;
    }

    public void setLen(int i) {
        this.f460h = i;
    }

    public void setReg(short s) {
        this.f457e = s;
    }

    public void setResultCode(int i) {
        this.f455c = i;
    }
}
