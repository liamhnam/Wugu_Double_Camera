package cc.uling.usdk.board.p016wz.para;

import cc.uling.usdk.p015a.C0672a;

public class DCReplyPara extends WzClsPara {

    private int f353a;

    private int f354b;

    private int f355d;

    private int f356e;

    private int f357f;

    public DCReplyPara(int i, int i2, int i3, int i4, int i5, int i6) {
        super(i);
        setFun((byte) 16);
        setReg((short) 8197);
        m260a((short) 3);
        setNo(i2);
        setMode(i3);
        setChannel(i4);
        setStatus(i5);
        setTime(i6);
        byte[] bArrM171b = C0672a.m171b(i2);
        byte[] bArrM171b2 = C0672a.m171b(i3);
        byte[] bArrM171b3 = C0672a.m171b(i4);
        byte[] bArrM171b4 = C0672a.m171b(i5);
        byte[] bArrM171b5 = C0672a.m171b(i6);
        setExtData(new byte[]{bArrM171b[0], bArrM171b2[0], bArrM171b3[0], bArrM171b4[0], bArrM171b5[1], bArrM171b5[0]});
    }

    public int getChannel() {
        return this.f355d;
    }

    public int getMode() {
        return this.f354b;
    }

    public int getNo() {
        return this.f353a;
    }

    public int getStatus() {
        return this.f356e;
    }

    public int getTime() {
        return this.f357f;
    }

    public void setChannel(int i) {
        this.f355d = i;
    }

    public void setMode(int i) {
        this.f354b = i;
    }

    public void setNo(int i) {
        this.f353a = i;
    }

    public void setStatus(int i) {
        this.f356e = i;
    }

    public void setTime(int i) {
        this.f357f = i;
    }
}
