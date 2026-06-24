package cc.uling.usdk.board.mdb.para;

import android.util.Log;
import cc.uling.usdk.p015a.C0672a;
import cc.uling.usdk.para.C0683b;

public class IPReplyPara extends C0683b {

    private short f301a;

    private int f302b;

    public IPReplyPara(short s, int i) {
        m260a((short) 3);
        setFun((byte) 16);
        setReg((short) 8196);
        setNo(s);
        setMultiple(i);
        m252a();
    }

    private void m252a() {
        byte[] bArrM166a = C0672a.m166a(this.f301a, true);
        byte[] bArrM175d = C0672a.m175d(this.f302b);
        byte[] bArr = bArrM175d.length == 1 ? new byte[]{0, 0, 0, bArrM175d[0]} : bArrM175d.length == 2 ? new byte[]{0, 0, bArrM175d[0], bArrM175d[1]} : bArrM175d.length == 3 ? new byte[]{0, bArrM175d[0], bArrM175d[1], bArrM175d[2]} : new byte[]{bArrM175d[0], bArrM175d[1], bArrM175d[2], bArrM175d[3]};
        Log.i("USDK", C0672a.m162a(bArrM175d) + "|" + bArrM175d.length);
        setExtData(new byte[]{bArrM166a[0], bArrM166a[1], bArr[0], bArr[1], bArr[2], bArr[3]});
    }

    public int getMultiple() {
        return this.f302b;
    }

    public short getNo() {
        return this.f301a;
    }

    public void setMultiple(int i) {
        this.f302b = i;
    }

    public void setNo(short s) {
        this.f301a = s;
    }

    public String toString() {
        return "IPReplyPara{no=" + ((int) this.f301a) + ", multiple=" + this.f302b + ", resultCode=" + this.f455c + '}';
    }
}
