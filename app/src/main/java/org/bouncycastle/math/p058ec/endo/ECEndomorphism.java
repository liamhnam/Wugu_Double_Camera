package org.bouncycastle.math.p058ec.endo;

import org.bouncycastle.math.p058ec.ECPointMap;

public interface ECEndomorphism {
    ECPointMap getPointMap();

    boolean hasEfficientPointMap();
}
