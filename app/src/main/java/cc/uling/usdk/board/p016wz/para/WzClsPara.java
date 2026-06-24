package cc.uling.usdk.board.p016wz.para;

import cc.uling.usdk.p015a.C0672a;
import cc.uling.usdk.para.BaseClsPara;

public class WzClsPara extends BaseClsPara {
    public WzClsPara(int i) {
        setFun((byte) 3);
        setAddr(C0672a.m171b(i)[0]);
    }
}
