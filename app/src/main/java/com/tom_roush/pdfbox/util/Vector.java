package com.tom_roush.pdfbox.util;

public final class Vector {

    private final float f2397x;

    private final float f2398y;

    public Vector(float f, float f2) {
        this.f2397x = f;
        this.f2398y = f2;
    }

    public float getX() {
        return this.f2397x;
    }

    public float getY() {
        return this.f2398y;
    }

    public Vector scale(float f) {
        return new Vector(this.f2397x * f, this.f2398y * f);
    }

    public String toString() {
        return "(" + this.f2397x + ", " + this.f2398y + ")";
    }
}
