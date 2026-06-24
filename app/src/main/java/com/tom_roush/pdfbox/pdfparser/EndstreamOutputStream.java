package com.tom_roush.pdfbox.pdfparser;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class EndstreamOutputStream extends BufferedOutputStream {
    private boolean hasCR;
    private boolean hasLF;
    private boolean mustFilter;
    private int pos;

    EndstreamOutputStream(OutputStream outputStream) {
        super(outputStream);
        this.hasCR = false;
        this.hasLF = false;
        this.pos = 0;
        this.mustFilter = true;
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.pos == 0 && i2 > 10) {
            this.mustFilter = false;
            for (int i3 = 0; i3 < 10; i3++) {
                byte b = bArr[i3];
                if (b < 9 || (b > 10 && b < 32 && b != 13)) {
                    this.mustFilter = true;
                    break;
                }
            }
        }
        if (this.mustFilter) {
            if (this.hasCR) {
                this.hasCR = false;
                if (!this.hasLF && i2 == 1 && bArr[i] == 10) {
                    return;
                } else {
                    super.write(13);
                }
            }
            if (this.hasLF) {
                super.write(10);
                this.hasLF = false;
            }
            if (i2 > 0) {
                byte b2 = bArr[(i + i2) - 1];
                if (b2 == 13) {
                    this.hasCR = true;
                } else if (b2 == 10) {
                    this.hasLF = true;
                    i2--;
                    if (i2 > 0 && bArr[(i + i2) - 1] == 13) {
                        this.hasCR = true;
                    }
                }
                i2--;
            }
        }
        super.write(bArr, i, i2);
        this.pos += i2;
    }

    @Override
    public void flush() throws IOException {
        if (this.hasCR && !this.hasLF) {
            super.write(13);
            this.pos++;
        }
        this.hasCR = false;
        this.hasLF = false;
        super.flush();
    }
}
