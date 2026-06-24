package okio;

import com.p020hp.jipp.model.Compression;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\b¨\u0006\u0003"}, m1293d2 = {Compression.gzip, "Lokio/GzipSink;", "Lokio/Sink;", "okio"}, m1294k = 2, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class GzipSinkExtensions {
    public static final GzipSink gzip(Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        return new GzipSink(sink);
    }
}
