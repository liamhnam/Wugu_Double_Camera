package com.p020hp.printsdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.jvm.internal.Intrinsics;

public final class C1671b2 {

    public final int f910a;

    public final int f911b;

    public static final class a {

        public final InputStream f912a;

        public final OutputStream f913b;

        public final int f914c;

        public final int f915d;

        public boolean f916e;

        public byte[] f917f;

        public boolean f918g;

        public byte[] f919h;

        public int f920i;

        public int f921j;

        public int f922k;

        public a(InputStream pixelsIn, OutputStream bytesOut, int i, int i2) {
            Intrinsics.checkNotNullParameter(pixelsIn, "pixelsIn");
            Intrinsics.checkNotNullParameter(bytesOut, "bytesOut");
            this.f912a = pixelsIn;
            this.f913b = bytesOut;
            this.f914c = i;
            int i3 = i * i2;
            this.f915d = i3;
            this.f917f = new byte[i3];
            this.f919h = new byte[i3];
        }

        public final void m472a() throws IOException {
            this.f913b.write(this.f922k - 1);
            this.f913b.write(this.f917f, this.f921j, this.f914c);
            this.f921j += this.f922k * this.f914c;
        }

        public final boolean m473a(byte[] bArr) throws IOException {
            int i = this.f912a.read(bArr);
            if (i == -1) {
                return false;
            }
            if (i == bArr.length) {
                return true;
            }
            throw new IOException("Could not read whole line (" + i + " bytes instead of " + this.f917f.length);
        }

        public final boolean m474a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
            for (int i4 = 0; i4 < i2; i4++) {
                if (bArr[i + i4] != bArr2[i3 + i4]) {
                    return false;
                }
            }
            return true;
        }
    }

    public C1671b2(int i, int i2) {
        this.f910a = i;
        this.f911b = i2;
    }
}
