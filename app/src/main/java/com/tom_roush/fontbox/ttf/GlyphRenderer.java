package com.tom_roush.fontbox.ttf;

import android.graphics.Path;
import java.util.ArrayList;

class GlyphRenderer {
    private GlyphDescription glyphDescription;

    GlyphRenderer(GlyphDescription glyphDescription) {
        this.glyphDescription = glyphDescription;
    }

    public Path getPath() {
        return calculatePath(describe(this.glyphDescription));
    }

    private Point[] describe(GlyphDescription glyphDescription) {
        Point[] pointArr = new Point[glyphDescription.getPointCount()];
        int i = 0;
        int i2 = 0;
        while (i < glyphDescription.getPointCount()) {
            boolean z = true;
            boolean z2 = glyphDescription.getEndPtOfContours(i2) == i;
            if (z2) {
                i2++;
            }
            short xCoordinate = glyphDescription.getXCoordinate(i);
            short yCoordinate = glyphDescription.getYCoordinate(i);
            if ((glyphDescription.getFlags(i) & 1) == 0) {
                z = false;
            }
            pointArr[i] = new Point(xCoordinate, yCoordinate, z, z2);
            i++;
        }
        return pointArr;
    }

    private Path calculatePath(Point[] pointArr) {
        Path path = new Path();
        int length = pointArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (pointArr[i2].endOfContour) {
                Point point = pointArr[i];
                Point point2 = pointArr[i2];
                ArrayList arrayList = new ArrayList();
                for (int i3 = i; i3 <= i2; i3++) {
                    arrayList.add(pointArr[i3]);
                }
                if (pointArr[i].onCurve) {
                    arrayList.add(point);
                } else if (pointArr[i2].onCurve) {
                    arrayList.add(0, point2);
                } else {
                    Point pointMidValue = midValue(point, point2);
                    arrayList.add(0, pointMidValue);
                    arrayList.add(pointMidValue);
                }
                moveTo(path, (Point) arrayList.get(0));
                int size = arrayList.size();
                int i4 = 1;
                while (i4 < size) {
                    Point point3 = (Point) arrayList.get(i4);
                    if (point3.onCurve) {
                        lineTo(path, point3);
                    } else {
                        int i5 = i4 + 1;
                        if (((Point) arrayList.get(i5)).onCurve) {
                            quadTo(path, point3, (Point) arrayList.get(i5));
                            i4 = i5;
                        } else {
                            quadTo(path, point3, midValue(point3, (Point) arrayList.get(i5)));
                        }
                    }
                    i4++;
                }
                path.close();
                i = i2 + 1;
            }
        }
        return path;
    }

    private void moveTo(Path path, Point point) {
        path.moveTo(point.f2224x, point.f2225y);
    }

    private void lineTo(Path path, Point point) {
        path.lineTo(point.f2224x, point.f2225y);
    }

    private void quadTo(Path path, Point point, Point point2) {
        path.quadTo(point.f2224x, point.f2225y, point2.f2224x, point2.f2225y);
    }

    private int midValue(int i, int i2) {
        return i + ((i2 - i) / 2);
    }

    private Point midValue(Point point, Point point2) {
        return new Point(midValue(point.f2224x, point2.f2224x), midValue(point.f2225y, point2.f2225y));
    }

    private static class Point {
        private boolean endOfContour;
        private boolean onCurve;

        private int f2224x;

        private int f2225y;

        Point(int i, int i2, boolean z, boolean z2) {
            this.f2224x = i;
            this.f2225y = i2;
            this.onCurve = z;
            this.endOfContour = z2;
        }

        Point(int i, int i2) {
            this(i, i2, true, false);
        }

        public String toString() {
            Object[] objArr = new Object[4];
            objArr[0] = Integer.valueOf(this.f2224x);
            objArr[1] = Integer.valueOf(this.f2225y);
            objArr[2] = this.onCurve ? "onCurve" : "";
            objArr[3] = this.endOfContour ? "endOfContour" : "";
            return String.format("Point(%d,%d,%s,%s)", objArr);
        }
    }
}
