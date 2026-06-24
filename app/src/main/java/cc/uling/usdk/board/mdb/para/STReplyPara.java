package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.p015a.C0672a;
import cc.uling.usdk.para.C0683b;
import java.util.Calendar;

public class STReplyPara extends C0683b {
    public STReplyPara() {
        setFun((byte) 16);
        setReg((short) 8197);
        m260a((short) 4);
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        int i6 = calendar.get(13);
        byte[] bArrM177f = C0672a.m177f(i);
        setExtData(new byte[]{bArrM177f[0], bArrM177f[1], (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, 0});
    }
}
