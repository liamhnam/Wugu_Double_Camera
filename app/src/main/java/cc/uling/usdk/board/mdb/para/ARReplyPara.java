package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.para.C0683b;

public class ARReplyPara extends C0683b {

    private int f256a;

    public ARReplyPara() {
        m260a((short) 1);
        setReg((short) 11);
    }

    public int getStatus() {
        return this.f256a;
    }

    public String getStatusMsg() {
        StringBuilder sbAppend;
        String str;
        String str2 = this.f256a + "-";
        int i = this.f256a;
        if (i == 0) {
            sbAppend = new StringBuilder().append(str2);
            str = "未刷卡";
        } else if (i == 1) {
            sbAppend = new StringBuilder().append(str2);
            str = "身份认证成功";
        } else if (i == 2) {
            sbAppend = new StringBuilder().append(str2);
            str = "身份认证失败(未达到设定年 龄)";
        } else if (i == 3) {
            sbAppend = new StringBuilder().append(str2);
            str = "错误(如设备未连接)";
        } else {
            sbAppend = new StringBuilder().append(str2);
            str = "其它错误";
        }
        return sbAppend.append(str).toString();
    }

    public void setStatus(int i) {
        this.f256a = i;
    }
}
