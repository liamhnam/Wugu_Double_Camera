package cc.uling.usdk.board.p016wz.para;

import cc.uling.usdk.p015a.C0672a;
import cc.uling.usdk.para.C0683b;

public class SReplyPara extends C0683b {

    private byte f378a;

    private byte f379b;

    private byte f380d;

    private byte f381e;

    public SReplyPara(int i, int i2, int i3, boolean z, boolean z2) {
        setFun((byte) 16);
        setReg((short) 8193);
        m260a((short) 2);
        setAddr(C0672a.m171b(i)[0]);
        setNo(C0672a.m171b(i2)[0]);
        setType(C0672a.m171b(i3)[0]);
        setCheck(z ? (byte) 1 : (byte) 0);
        setLift(z2 ? (byte) 1 : (byte) 0);
        setExtData(new byte[]{this.f378a, this.f379b, this.f380d, this.f381e});
    }

    public byte getCheck() {
        return this.f380d;
    }

    public byte getLift() {
        return this.f381e;
    }

    public byte getNo() {
        return this.f378a;
    }

    public byte getType() {
        return this.f379b;
    }

    public void setCheck(byte b) {
        this.f380d = b;
    }

    public void setLift(byte b) {
        this.f381e = b;
    }

    public void setNo(byte b) {
        this.f378a = b;
    }

    public void setType(byte b) {
        this.f379b = b;
    }
}
