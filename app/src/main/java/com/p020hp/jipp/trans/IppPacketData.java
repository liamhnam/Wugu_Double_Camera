package com.p020hp.jipp.trans;

import com.p020hp.jipp.encoding.IppPacket;
import com.p020hp.jipp.model.PrinterServiceType;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u001f\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, m1293d2 = {"Lcom/hp/jipp/trans/IppPacketData;", "Ljava/io/Closeable;", "ippPacket", "Lcom/hp/jipp/encoding/IppPacket;", "(Lcom/hp/jipp/encoding/IppPacket;)V", "packet", "data", "Ljava/io/InputStream;", "(Lcom/hp/jipp/encoding/IppPacket;Ljava/io/InputStream;)V", "getData", "()Ljava/io/InputStream;", "getPacket", "()Lcom/hp/jipp/encoding/IppPacket;", "close", "", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class IppPacketData implements Closeable {
    private final InputStream data;
    private final IppPacket packet;

    public static IppPacketData copy$default(IppPacketData ippPacketData, IppPacket ippPacket, InputStream inputStream, int i, Object obj) {
        if ((i & 1) != 0) {
            ippPacket = ippPacketData.packet;
        }
        if ((i & 2) != 0) {
            inputStream = ippPacketData.data;
        }
        return ippPacketData.copy(ippPacket, inputStream);
    }

    public final IppPacket getPacket() {
        return this.packet;
    }

    public final InputStream getData() {
        return this.data;
    }

    public final IppPacketData copy(IppPacket packet, InputStream data) {
        Intrinsics.checkNotNullParameter(packet, "packet");
        return new IppPacketData(packet, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IppPacketData)) {
            return false;
        }
        IppPacketData ippPacketData = (IppPacketData) other;
        return Intrinsics.areEqual(this.packet, ippPacketData.packet) && Intrinsics.areEqual(this.data, ippPacketData.data);
    }

    public int hashCode() {
        IppPacket ippPacket = this.packet;
        int iHashCode = (ippPacket != null ? ippPacket.hashCode() : 0) * 31;
        InputStream inputStream = this.data;
        return iHashCode + (inputStream != null ? inputStream.hashCode() : 0);
    }

    public String toString() {
        return "IppPacketData(packet=" + this.packet + ", data=" + this.data + ")";
    }

    public IppPacketData(IppPacket packet, InputStream inputStream) {
        Intrinsics.checkNotNullParameter(packet, "packet");
        this.packet = packet;
        this.data = inputStream;
    }

    public final InputStream getData() {
        return this.data;
    }

    public final IppPacket getPacket() {
        return this.packet;
    }

    public IppPacketData(IppPacket ippPacket) {
        this(ippPacket, null);
        Intrinsics.checkNotNullParameter(ippPacket, "ippPacket");
    }

    @Override
    public void close() throws IOException {
        InputStream inputStream = this.data;
        if (inputStream != null) {
            inputStream.close();
        }
    }
}
