package org.bouncycastle.crypto.params;

public class GOST3410ValidationParameters {

    private int f3373c;

    private long f3374cL;

    private int f3375x0;
    private long x0L;

    public GOST3410ValidationParameters(int i, int i2) {
        this.f3375x0 = i;
        this.f3373c = i2;
    }

    public GOST3410ValidationParameters(long j, long j2) {
        this.x0L = j;
        this.f3374cL = j2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410ValidationParameters)) {
            return false;
        }
        GOST3410ValidationParameters gOST3410ValidationParameters = (GOST3410ValidationParameters) obj;
        return gOST3410ValidationParameters.f3373c == this.f3373c && gOST3410ValidationParameters.f3375x0 == this.f3375x0 && gOST3410ValidationParameters.f3374cL == this.f3374cL && gOST3410ValidationParameters.x0L == this.x0L;
    }

    public int getC() {
        return this.f3373c;
    }

    public long getCL() {
        return this.f3374cL;
    }

    public int getX0() {
        return this.f3375x0;
    }

    public long getX0L() {
        return this.x0L;
    }

    public int hashCode() {
        int i = this.f3375x0 ^ this.f3373c;
        long j = this.x0L;
        int i2 = (i ^ ((int) j)) ^ ((int) (j >> 32));
        long j2 = this.f3374cL;
        return (i2 ^ ((int) j2)) ^ ((int) (j2 >> 32));
    }
}
