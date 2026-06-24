package androidx.constraintlayout.motion.widget;

public interface CustomFloatAttributes {
    float get(String attribute);

    String[] getListOfAttributes();

    void set(String attribute, float value);
}
