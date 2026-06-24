package com.tom_roush.fontbox.afm;

import com.tom_roush.fontbox.util.BoundingBox;
import java.util.ArrayList;
import java.util.List;

public class CharMetric {
    private BoundingBox boundingBox;
    private int characterCode;
    private List<Ligature> ligatures = new ArrayList();
    private String name;

    private float[] f2213vv;

    private float[] f2214w;

    private float[] f2215w0;
    private float w0x;
    private float w0y;

    private float[] f2216w1;
    private float w1x;
    private float w1y;

    private float f2217wx;

    private float f2218wy;

    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public int getCharacterCode() {
        return this.characterCode;
    }

    public void setCharacterCode(int i) {
        this.characterCode = i;
    }

    public void addLigature(Ligature ligature) {
        this.ligatures.add(ligature);
    }

    public List<Ligature> getLigatures() {
        return this.ligatures;
    }

    public void setLigatures(List<Ligature> list) {
        this.ligatures = list;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public float[] getVv() {
        return this.f2213vv;
    }

    public void setVv(float[] fArr) {
        this.f2213vv = fArr;
    }

    public float[] getW() {
        return this.f2214w;
    }

    public void setW(float[] fArr) {
        this.f2214w = fArr;
    }

    public float[] getW0() {
        return this.f2215w0;
    }

    public void setW0(float[] fArr) {
        this.f2215w0 = fArr;
    }

    public float getW0x() {
        return this.w0x;
    }

    public void setW0x(float f) {
        this.w0x = f;
    }

    public float getW0y() {
        return this.w0y;
    }

    public void setW0y(float f) {
        this.w0y = f;
    }

    public float[] getW1() {
        return this.f2216w1;
    }

    public void setW1(float[] fArr) {
        this.f2216w1 = fArr;
    }

    public float getW1x() {
        return this.w1x;
    }

    public void setW1x(float f) {
        this.w1x = f;
    }

    public float getW1y() {
        return this.w1y;
    }

    public void setW1y(float f) {
        this.w1y = f;
    }

    public float getWx() {
        return this.f2217wx;
    }

    public void setWx(float f) {
        this.f2217wx = f;
    }

    public float getWy() {
        return this.f2218wy;
    }

    public void setWy(float f) {
        this.f2218wy = f;
    }
}
