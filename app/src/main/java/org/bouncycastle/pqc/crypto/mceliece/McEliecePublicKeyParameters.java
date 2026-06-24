package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;

public class McEliecePublicKeyParameters extends McElieceKeyParameters {

    private GF2Matrix f3723g;

    private int f3724n;

    private int f3725t;

    public McEliecePublicKeyParameters(int i, int i2, GF2Matrix gF2Matrix) {
        super(false, null);
        this.f3724n = i;
        this.f3725t = i2;
        this.f3723g = new GF2Matrix(gF2Matrix);
    }

    public GF2Matrix getG() {
        return this.f3723g;
    }

    public int getK() {
        return this.f3723g.getNumRows();
    }

    public int getN() {
        return this.f3724n;
    }

    public int getT() {
        return this.f3725t;
    }
}
