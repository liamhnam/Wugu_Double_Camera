package androidx.constraintlayout.core.widgets;

public class Rectangle {
    public int height;
    public int width;

    public int f157x;

    public int f158y;

    public void setBounds(int i, int i2, int i3, int i4) {
        this.f157x = i;
        this.f158y = i2;
        this.width = i3;
        this.height = i4;
    }

    void grow(int i, int i2) {
        this.f157x -= i;
        this.f158y -= i2;
        this.width += i * 2;
        this.height += i2 * 2;
    }

    boolean intersects(Rectangle rectangle) {
        int i;
        int i2;
        int i3 = this.f157x;
        int i4 = rectangle.f157x;
        return i3 >= i4 && i3 < i4 + rectangle.width && (i = this.f158y) >= (i2 = rectangle.f158y) && i < i2 + rectangle.height;
    }

    public boolean contains(int i, int i2) {
        int i3;
        int i4 = this.f157x;
        return i >= i4 && i < i4 + this.width && i2 >= (i3 = this.f158y) && i2 < i3 + this.height;
    }

    public int getCenterX() {
        return (this.f157x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f158y + this.height) / 2;
    }
}
