package cc.uling.usdk.board.mdb.para;

import cc.uling.usdk.para.C0683b;

public class AVReplyPara extends C0683b {

    private Double f257a;

    private Double f258b;

    private Double f259d;

    private Double f260e;

    private Double f261f;

    private Double f262g;

    private Double f263h;

    private Double f264i;

    private Double f265j;

    private Double f266k;

    private Double f267l;

    private Double f268m;

    private Double f269n;

    private Double f270o;

    private Double f271p;

    private Double f272q;

    private Double f273r;

    public AVReplyPara(int i) {
        m260a((short) 9);
        if (i == 0) {
            setReg((short) 7);
        } else {
            setReg((short) 9);
        }
    }

    public Double getMinValue() {
        return this.f257a;
    }

    public Double getValue(int i) {
        return i == 1 ? this.f258b : i == 2 ? this.f259d : i == 3 ? this.f260e : i == 4 ? this.f261f : i == 5 ? this.f262g : i == 6 ? this.f263h : i == 7 ? this.f264i : i == 8 ? this.f265j : i == 9 ? this.f266k : i == 10 ? this.f267l : i == 11 ? this.f268m : i == 12 ? this.f269n : i == 13 ? this.f270o : i == 14 ? this.f271p : i == 15 ? this.f272q : i == 16 ? this.f273r : Double.valueOf(0.0d);
    }

    public Double getValue1() {
        return this.f258b;
    }

    public Double getValue10() {
        return this.f267l;
    }

    public Double getValue11() {
        return this.f268m;
    }

    public Double getValue12() {
        return this.f269n;
    }

    public Double getValue13() {
        return this.f270o;
    }

    public Double getValue14() {
        return this.f271p;
    }

    public Double getValue15() {
        return this.f272q;
    }

    public Double getValue16() {
        return this.f273r;
    }

    public Double getValue2() {
        return this.f259d;
    }

    public Double getValue3() {
        return this.f260e;
    }

    public Double getValue4() {
        return this.f261f;
    }

    public Double getValue5() {
        return this.f262g;
    }

    public Double getValue6() {
        return this.f263h;
    }

    public Double getValue7() {
        return this.f264i;
    }

    public Double getValue8() {
        return this.f265j;
    }

    public Double getValue9() {
        return this.f266k;
    }

    public void setMinValue(Double d) {
        this.f257a = d;
    }

    public void setValue1(Double d) {
        this.f258b = d;
    }

    public void setValue10(Double d) {
        this.f267l = d;
    }

    public void setValue11(Double d) {
        this.f268m = d;
    }

    public void setValue12(Double d) {
        this.f269n = d;
    }

    public void setValue13(Double d) {
        this.f270o = d;
    }

    public void setValue14(Double d) {
        this.f271p = d;
    }

    public void setValue15(Double d) {
        this.f272q = d;
    }

    public void setValue16(Double d) {
        this.f273r = d;
    }

    public void setValue2(Double d) {
        this.f259d = d;
    }

    public void setValue3(Double d) {
        this.f260e = d;
    }

    public void setValue4(Double d) {
        this.f261f = d;
    }

    public void setValue5(Double d) {
        this.f262g = d;
    }

    public void setValue6(Double d) {
        this.f263h = d;
    }

    public void setValue7(Double d) {
        this.f264i = d;
    }

    public void setValue8(Double d) {
        this.f265j = d;
    }

    public void setValue9(Double d) {
        this.f266k = d;
    }

    public String toString() {
        return "AVReplyPara{minValue=" + this.f257a + ", value1=" + this.f258b + ", value2=" + this.f259d + ", value3=" + this.f260e + ", value4=" + this.f261f + ", value5=" + this.f262g + ", value6=" + this.f263h + ", value7=" + this.f264i + ", value8=" + this.f265j + ", value9=" + this.f266k + ", value10=" + this.f267l + ", value11=" + this.f268m + ", value12=" + this.f269n + ", value13=" + this.f270o + ", value14=" + this.f271p + ", value15=" + this.f272q + ", value16=" + this.f273r + '}';
    }
}
