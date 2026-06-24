package com.hiti.usb.utility;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.hiti.usb.bitmapmanager.BitmapMonitor;
import com.hiti.usb.bitmapmanager.BitmapMonitorResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class PackBits {

    public class MyByteArrayOutputStream extends OutputStream {
        private final byte[] bytes;
        private int count = 0;

        public MyByteArrayOutputStream(int i) {
            this.bytes = new byte[i];
        }

        public int getBytesWritten() {
            return this.count;
        }

        public byte[] toByteArray() {
            int i = this.count;
            byte[] bArr = this.bytes;
            if (i >= bArr.length) {
                return bArr;
            }
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            return bArr2;
        }

        @Override
        public void write(int i) throws IOException {
            int i2 = this.count;
            byte[] bArr = this.bytes;
            if (i2 >= bArr.length) {
                throw new IOException("Write exceeded expected length (" + this.count + ", " + this.bytes.length + ")");
            }
            bArr[i2] = (byte) i;
            this.count = i2 + 1;
        }
    }

    private int findNextDuplicate(byte[] bArr, int i) {
        if (i >= bArr.length) {
            return -1;
        }
        byte b = bArr[i];
        int i2 = i + 1;
        while (i2 < bArr.length) {
            byte b2 = bArr[i2];
            if (b2 == b) {
                return i2 - 1;
            }
            i2++;
            b = b2;
        }
        return -1;
    }

    private int findRunLength(byte[] bArr, int i) {
        byte b = bArr[i];
        int i2 = i + 1;
        int i3 = i2;
        while (i3 < bArr.length && i3 < i2 + 128 && bArr[i3] == b) {
            i3++;
        }
        return i3 - i;
    }

    public byte[] GetMaskFromPackBitsMask(byte[] bArr, int i) {
        return decompress(bArr, i);
    }

    public byte[] GetPackBitsMask(Bitmap bitmap) {
        BitmapMonitorResult bitmapMonitorResultCreateBitmap = BitmapMonitor.CreateBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ALPHA_8);
        byte[] bArr = null;
        if (!bitmapMonitorResultCreateBitmap.IsSuccess()) {
            return null;
        }
        Bitmap bitmapGetBitmap = bitmapMonitorResultCreateBitmap.GetBitmap();
        new Canvas(bitmapGetBitmap).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        int width = bitmap.getWidth() * bitmap.getHeight();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(width);
        bitmapGetBitmap.copyPixelsToBuffer(byteBufferAllocate);
        bitmapGetBitmap.recycle();
        byte[] bArr2 = new byte[width];
        byteBufferAllocate.position(0);
        byteBufferAllocate.get(bArr2);
        try {
            byte[] bArrCompress = compress(bArr2);
            bArr = new byte[bArrCompress.length + 4];
            bArr[0] = (byte) (width >> 24);
            bArr[1] = (byte) (width >> 16);
            bArr[2] = (byte) (width >> 8);
            bArr[3] = (byte) (width & 255);
            System.arraycopy(bArrCompress, 0, bArr, 4, bArrCompress.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byteBufferAllocate.clear();
        return bArr;
    }

    public byte[] compress(byte[] bArr) throws IOException {
        int iFindRunLength;
        int i;
        int iFindNextDuplicate;
        MyByteArrayOutputStream myByteArrayOutputStream = new MyByteArrayOutputStream(bArr.length * 2);
        int i2 = 0;
        while (i2 < bArr.length) {
            int iFindNextDuplicate2 = findNextDuplicate(bArr, i2);
            if (iFindNextDuplicate2 == i2) {
                int iMin = Math.min(findRunLength(bArr, iFindNextDuplicate2), 128);
                myByteArrayOutputStream.write(-(iMin - 1));
                myByteArrayOutputStream.write(bArr[i2]);
                i2 += iMin;
            } else {
                int length = iFindNextDuplicate2 - i2;
                if (iFindNextDuplicate2 > 0 && (iFindRunLength = findRunLength(bArr, iFindNextDuplicate2)) < 3 && (iFindNextDuplicate = findNextDuplicate(bArr, (i = i2 + length + iFindRunLength))) != i) {
                    length = iFindNextDuplicate - i2;
                    iFindNextDuplicate2 = iFindNextDuplicate;
                }
                if (iFindNextDuplicate2 < 0) {
                    length = bArr.length - i2;
                }
                int iMin2 = Math.min(length, 128);
                myByteArrayOutputStream.write(iMin2 - 1);
                for (int i3 = 0; i3 < iMin2; i3++) {
                    myByteArrayOutputStream.write(bArr[i2]);
                    i2++;
                }
            }
        }
        byte[] byteArray = myByteArrayOutputStream.toByteArray();
        try {
            myByteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    public byte[] decompress(byte[] bArr, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = i3 + 1;
            int i5 = bArr[i3];
            if (i5 >= 0 && i5 <= 127) {
                int i6 = i5 + 1;
                i2 += i6;
                int i7 = 0;
                while (i7 < i6) {
                    byteArrayOutputStream.write(bArr[i4]);
                    i7++;
                    i4++;
                }
            } else if (i5 >= -127 && i5 <= -1) {
                int i8 = i4 + 1;
                byte b = bArr[i4];
                int i9 = (-i5) + 1;
                i2 += i9;
                for (int i10 = 0; i10 < i9; i10++) {
                    byteArrayOutputStream.write(b);
                }
                i3 = i8;
            }
            i3 = i4;
        }
        return byteArrayOutputStream.toByteArray();
    }
}
