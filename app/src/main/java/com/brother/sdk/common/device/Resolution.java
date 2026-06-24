package com.brother.sdk.common.device;

import java.io.Serializable;

public class Resolution implements Serializable {
    private static final long serialVersionUID = -389945147026061218L;
    public final int height;
    public final int width;

    public Resolution(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Resolution resolution = (Resolution) obj;
        return resolution.width == this.width && resolution.height == this.height;
    }

    public int hashCode() {
        return this.width ^ this.height;
    }
}
