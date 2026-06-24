package p066do.p026do.p028if.p029do.p068case;

import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
import p066do.p026do.p028if.p029do.p068case.Celse;

public final class C2063do extends InputStream {

    public boolean f3898for;

    public final Celse.C2064do f2486if;

    public C2063do(Celse.C2064do usbConnection) {
        Intrinsics.checkNotNullParameter(usbConnection, "usbConnection");
        this.f2486if = usbConnection;
    }

    @Override
    public void close() {
        this.f3898for = true;
    }

    @Override
    public int read() {
        return read(new byte[8192], 0, 1);
    }

    @Override
    public int read(byte[] bytes, int i, int i2) throws InterruptedException, IOException {
        int iBulkTransfer;
        Intrinsics.checkNotNullParameter(bytes, "b");
        if (this.f3898for) {
            throw new IOException("Input stream closed");
        }
        Celse.C2064do c2064do = this.f2486if;
        c2064do.getClass();
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        c2064do.m1202do();
        do {
            iBulkTransfer = c2064do.f3904for.bulkTransfer(c2064do.f3907try, bytes, i, i2, 0);
            if (iBulkTransfer != 0) {
                break;
            }
            Thread.sleep(20L);
        } while (!c2064do.f3905goto);
        c2064do.f3903else.m446d(c2064do + " read bytes(" + bytes.length + "), offset(" + i + "), len(" + i2 + "), result(" + iBulkTransfer + ')');
        return iBulkTransfer;
    }
}
