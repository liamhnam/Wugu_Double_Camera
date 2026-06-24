package com.epson.isv.eprinterdriver.Ctrl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

class PrintCache implements IRenderRequest {
    Bitmap bitmap;
    ByteBuffer buffer;
    FileChannel channelIn;
    FileChannel channelOut;
    boolean enabledCache;
    String path;

    public PrintCache(String str) {
        if (str == null) {
            this.path = Environment.getExternalStorageDirectory() + "/band.cache";
        } else {
            this.path = str;
        }
    }

    public void enableCache(boolean z) {
        this.enabledCache = z;
    }

    public boolean isCacheEnabled() {
        return this.enabledCache;
    }

    public void cacheBand(EpsBandData epsBandData) {
        if (this.enabledCache) {
            if (this.channelOut == null) {
                try {
                    this.channelOut = new FileOutputStream(new File(this.path)).getChannel();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (this.buffer == null) {
                this.buffer = ByteBuffer.allocateDirect(epsBandData.getBandPixels().length * 4);
            }
            try {
                this.buffer.rewind();
                epsBandData.copyPixelsToBuffer(this.buffer);
                this.buffer.position(0);
                this.channelOut.write(this.buffer);
                this.channelOut.force(true);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void rewind() {
        close(false);
        File file = new File(this.path);
        if (file.exists()) {
            if (this.channelIn == null) {
                try {
                    this.channelIn = new FileInputStream(file).getChannel();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            try {
                this.channelIn.position(0L);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void close(boolean z) {
        FileChannel fileChannel = this.channelIn;
        if (fileChannel != null && fileChannel.isOpen()) {
            try {
                this.channelIn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.channelIn = null;
        }
        if (z) {
            FileChannel fileChannel2 = this.channelOut;
            if (fileChannel2 != null && fileChannel2.isOpen()) {
                try {
                    this.channelOut.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                this.channelOut = null;
            }
            new File(this.path).delete();
        }
    }

    @Override
    public void OnDraw(Canvas canvas, DrawInfo drawInfo) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int i = width * height * 4;
        try {
            if (this.buffer.capacity() != i) {
                this.buffer = ByteBuffer.allocateDirect(i);
            }
            this.buffer.rewind();
            this.channelIn.read(this.buffer);
            this.buffer.position(0);
            if (this.bitmap == null) {
                this.bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            }
            this.bitmap.copyPixelsFromBuffer(this.buffer);
            canvas.drawBitmap(this.bitmap, 0.0f, 0.0f, (Paint) null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
