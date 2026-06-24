package com.tom_roush.fontbox.afm;

public class KernPair {
    private String firstKernCharacter;
    private String secondKernCharacter;

    private float f2219x;

    private float f2220y;

    public String getFirstKernCharacter() {
        return this.firstKernCharacter;
    }

    public void setFirstKernCharacter(String str) {
        this.firstKernCharacter = str;
    }

    public String getSecondKernCharacter() {
        return this.secondKernCharacter;
    }

    public void setSecondKernCharacter(String str) {
        this.secondKernCharacter = str;
    }

    public float getX() {
        return this.f2219x;
    }

    public void setX(float f) {
        this.f2219x = f;
    }

    public float getY() {
        return this.f2220y;
    }

    public void setY(float f) {
        this.f2220y = f;
    }
}
