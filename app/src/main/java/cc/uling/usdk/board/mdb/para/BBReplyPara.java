package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.para.C0683b;

public class BBReplyPara extends C0683b {

    private int f274a;

    private int f275b;

    private int f276d;

    private int f277e;

    private int f278f;

    private int f279g;

    private int f280h;

    private int f281i;

    private int f282j;

    private int f283k;

    private int f284l;

    private int f285m;

    private int f286n;

    private int f287o;

    private int f288p;

    private int f289q;

    public BBReplyPara(int i) {
        m260a((short) 8);
        setReg(i == 0 ? (short) 6 : (short) 10);
    }

    public int getValue(int i) {
        if (i == 1) {
            return this.f274a;
        }
        if (i == 2) {
            return this.f275b;
        }
        if (i == 3) {
            return this.f276d;
        }
        if (i == 4) {
            return this.f277e;
        }
        if (i == 5) {
            return this.f278f;
        }
        if (i == 6) {
            return this.f279g;
        }
        if (i == 7) {
            return this.f280h;
        }
        if (i == 8) {
            return this.f281i;
        }
        if (i == 9) {
            return this.f282j;
        }
        if (i == 10) {
            return this.f283k;
        }
        if (i == 11) {
            return this.f284l;
        }
        if (i == 12) {
            return this.f285m;
        }
        if (i == 13) {
            return this.f286n;
        }
        if (i == 14) {
            return this.f287o;
        }
        if (i == 15) {
            return this.f288p;
        }
        if (i == 16) {
            return this.f289q;
        }
        return 0;
    }

    public int getValue1() {
        return this.f274a;
    }

    public int getValue10() {
        return this.f283k;
    }

    public int getValue11() {
        return this.f284l;
    }

    public int getValue12() {
        return this.f285m;
    }

    public int getValue13() {
        return this.f286n;
    }

    public int getValue14() {
        return this.f287o;
    }

    public int getValue15() {
        return this.f288p;
    }

    public int getValue16() {
        return this.f289q;
    }

    public int getValue2() {
        return this.f275b;
    }

    public int getValue3() {
        return this.f276d;
    }

    public int getValue4() {
        return this.f277e;
    }

    public int getValue5() {
        return this.f278f;
    }

    public int getValue6() {
        return this.f279g;
    }

    public int getValue7() {
        return this.f280h;
    }

    public int getValue8() {
        return this.f281i;
    }

    public int getValue9() {
        return this.f282j;
    }

    public void setValue1(int i) {
        this.f274a = i;
    }

    public void setValue10(int i) {
        this.f283k = i;
    }

    public void setValue11(int i) {
        this.f284l = i;
    }

    public void setValue12(int i) {
        this.f285m = i;
    }

    public void setValue13(int i) {
        this.f286n = i;
    }

    public void setValue14(int i) {
        this.f287o = i;
    }

    public void setValue15(int i) {
        this.f288p = i;
    }

    public void setValue16(int i) {
        this.f289q = i;
    }

    public void setValue2(int i) {
        this.f275b = i;
    }

    public void setValue3(int i) {
        this.f276d = i;
    }

    public void setValue4(int i) {
        this.f277e = i;
    }

    public void setValue5(int i) {
        this.f278f = i;
    }

    public void setValue6(int i) {
        this.f279g = i;
    }

    public void setValue7(int i) {
        this.f280h = i;
    }

    public void setValue8(int i) {
        this.f281i = i;
    }

    public void setValue9(int i) {
        this.f282j = i;
    }

    public String toString() {
        return "BBReplyPara{value1=" + this.f274a + ", value2=" + this.f275b + ", value3=" + this.f276d + ", value4=" + this.f277e + ", value5=" + this.f278f + ", value6=" + this.f279g + ", value7=" + this.f280h + ", value8=" + this.f281i + ", value9=" + this.f282j + ", value10=" + this.f283k + ", value11=" + this.f284l + ", value12=" + this.f285m + ", value13=" + this.f286n + ", value14=" + this.f287o + ", value15=" + this.f288p + ", value16=" + this.f289q + '}';
    }
}
