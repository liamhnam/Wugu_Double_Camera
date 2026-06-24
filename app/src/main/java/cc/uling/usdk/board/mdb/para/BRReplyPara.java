package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.para.C0683b;
import java.util.HashMap;
import java.util.Map;

public class BRReplyPara extends C0683b {

    private Map<Integer, Integer> f290a = new HashMap();

    public BRReplyPara() {
        m260a((short) 8);
        setReg((short) 8);
    }

    public int getCount(int i) {
        if (this.f290a.containsKey(Integer.valueOf(i))) {
            return this.f290a.get(Integer.valueOf(i)).intValue();
        }
        return 0;
    }

    public void setChannel(int i, int i2) {
        this.f290a.put(Integer.valueOf(i), Integer.valueOf(i2));
    }

    public String toString() {
        return "BRReplyPara{mCounts=" + this.f290a + ", resultCode=" + this.f455c + '}';
    }
}
