package p066do.p026do.p028if.p029do.p068case;

import java.io.IOException;
import java.io.OutputStream;
import kotlin.jvm.internal.Intrinsics;
import p066do.p026do.p028if.p029do.p068case.Celse;

public final class C2067if extends OutputStream {

    public boolean f3911for;

    public final Celse.C2064do f2494if;

    public C2067if(Celse.C2064do usbConnection) {
        Intrinsics.checkNotNullParameter(usbConnection, "usbConnection");
        this.f2494if = usbConnection;
    }

    @Override
    public void close() {
        this.f3911for = true;
    }

    @Override
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    @Override
    public void write(byte[] bytes, int i, int i2) throws IOException {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (this.f3911for) {
            throw new IOException("Output stream closed");
        }
        Celse.C2064do c2064do = this.f2494if;
        c2064do.getClass();
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        c2064do.m1202do();
        c2064do.f3903else.m446d(c2064do + " write bytes(" + bytes.length + "), offset(" + i + "), len(" + i2 + "), result(" + c2064do.f3904for.bulkTransfer(c2064do.f3902case, bytes, i, i2, 0) + ')');
    }
}
