package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.KeywordOrName;
import com.p020hp.jipp.encoding.KeywordOrNameType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.encoding.Text;
import com.p020hp.jipp.encoding.TextType;
import com.p020hp.jipp.model.Orientation;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\bp\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0086\b\u0018\u0000 \u0099\u00012\u00020\u0001:\u0006\u0099\u0001\u009a\u0001\u009b\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B¥\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010 J\u000b\u0010y\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010z\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010,J\u000b\u0010{\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010|\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010}\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010,J\u000b\u0010~\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\u000b\u0010\u007f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0018HÆ\u0003J\u0011\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010,J\f\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010,J\u0011\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010,J\u0011\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010,J\f\u0010\u0087\u0001\u001a\u0004\u0018\u00010\fHÆ\u0003J\f\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010,J\f\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010,J\f\u0010\u008e\u0001\u001a\u0004\u0018\u00010\fHÆ\u0003J\f\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010,J°\u0002\u0010\u0091\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0003\u0010\u0092\u0001J\u0017\u0010\u0093\u0001\u001a\u00030\u0094\u00012\n\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0096\u0001HÖ\u0003J\n\u0010\u0097\u0001\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0098\u0001\u001a\u00020\fH\u0016R\u001e\u0010!\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030#0\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b*\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R \u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b3\u001a\u0004\b1\u0010'\"\u0004\b2\u0010)R\u001e\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b6\u001a\u0004\b4\u0010'\"\u0004\b5\u0010)R\u001e\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b9\u001a\u0004\b7\u0010'\"\u0004\b8\u0010)R \u0010\n\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b<\u00100\u001a\u0004\b:\u0010,\"\u0004\b;\u0010.R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\bA\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001e\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\bD\u001a\u0004\bB\u0010'\"\u0004\bC\u0010)R \u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bG\u00100\u001a\u0004\bE\u0010,\"\u0004\bF\u0010.R \u0010\u000f\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bJ\u00100\u001a\u0004\bH\u0010,\"\u0004\bI\u0010.R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\bM\u001a\u0004\bK\u0010'\"\u0004\bL\u0010)R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\bP\u001a\u0004\bN\u0010'\"\u0004\bO\u0010)R \u0010\u0012\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bS\u00100\u001a\u0004\bQ\u0010,\"\u0004\bR\u0010.R\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u0010\n\u0002\bX\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b[\u001a\u0004\bY\u0010'\"\u0004\bZ\u0010)R\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b^\u001a\u0004\b\\\u0010'\"\u0004\b]\u0010)R\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u0010\n\u0002\bc\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR \u0010\u0019\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bf\u00100\u001a\u0004\bd\u0010,\"\u0004\be\u0010.R\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\bi\u001a\u0004\bg\u0010'\"\u0004\bh\u0010)R \u0010\u001b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bl\u00100\u001a\u0004\bj\u0010,\"\u0004\bk\u0010.R \u0010\u001c\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bo\u00100\u001a\u0004\bm\u0010,\"\u0004\bn\u0010.R\u001e\u0010\u001d\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\br\u001a\u0004\bp\u0010>\"\u0004\bq\u0010@R\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\bu\u001a\u0004\bs\u0010'\"\u0004\bt\u0010)R \u0010\u001f\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bx\u00100\u001a\u0004\bv\u0010,\"\u0004\bw\u0010.¨\u0006\u009c\u0001"}, m1293d2 = {"Lcom/hp/jipp/model/MediaCol;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "mediaBackCoating", "Lcom/hp/jipp/encoding/KeywordOrName;", "mediaBottomMargin", "", "mediaColor", "mediaFrontCoating", "mediaGrain", "mediaHoleCount", "mediaInfo", "", "mediaKey", "mediaLeftMargin", "mediaOrderCount", "mediaPrePrinted", "mediaRecycled", "mediaRightMargin", "mediaSize", "Lcom/hp/jipp/model/MediaCol$MediaSize;", "mediaSizeName", "mediaSource", "mediaSourceProperties", "Lcom/hp/jipp/model/MediaCol$MediaSourceProperties;", "mediaThickness", "mediaTooth", "mediaTopMargin", "mediaTopOffset", "mediaTracking", "mediaType", "mediaWeightMetric", "(Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/Integer;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/Integer;Ljava/lang/String;Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/Integer;Lcom/hp/jipp/model/MediaCol$MediaSize;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/model/MediaCol$MediaSourceProperties;Ljava/lang/Integer;Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/Integer;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getMediaBackCoating", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setMediaBackCoating", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "mediaBackCoating$1", "getMediaBottomMargin", "()Ljava/lang/Integer;", "setMediaBottomMargin", "(Ljava/lang/Integer;)V", "mediaBottomMargin$1", "Ljava/lang/Integer;", "getMediaColor", "setMediaColor", "mediaColor$1", "getMediaFrontCoating", "setMediaFrontCoating", "mediaFrontCoating$1", "getMediaGrain", "setMediaGrain", "mediaGrain$1", "getMediaHoleCount", "setMediaHoleCount", "mediaHoleCount$1", "getMediaInfo", "()Ljava/lang/String;", "setMediaInfo", "(Ljava/lang/String;)V", "mediaInfo$1", "getMediaKey", "setMediaKey", "mediaKey$1", "getMediaLeftMargin", "setMediaLeftMargin", "mediaLeftMargin$1", "getMediaOrderCount", "setMediaOrderCount", "mediaOrderCount$1", "getMediaPrePrinted", "setMediaPrePrinted", "mediaPrePrinted$1", "getMediaRecycled", "setMediaRecycled", "mediaRecycled$1", "getMediaRightMargin", "setMediaRightMargin", "mediaRightMargin$1", "getMediaSize", "()Lcom/hp/jipp/model/MediaCol$MediaSize;", "setMediaSize", "(Lcom/hp/jipp/model/MediaCol$MediaSize;)V", "mediaSize$1", "getMediaSizeName", "setMediaSizeName", "mediaSizeName$1", "getMediaSource", "setMediaSource", "mediaSource$1", "getMediaSourceProperties", "()Lcom/hp/jipp/model/MediaCol$MediaSourceProperties;", "setMediaSourceProperties", "(Lcom/hp/jipp/model/MediaCol$MediaSourceProperties;)V", "mediaSourceProperties$1", "getMediaThickness", "setMediaThickness", "mediaThickness$1", "getMediaTooth", "setMediaTooth", "mediaTooth$1", "getMediaTopMargin", "setMediaTopMargin", "mediaTopMargin$1", "getMediaTopOffset", "setMediaTopOffset", "mediaTopOffset$1", "getMediaTracking", "setMediaTracking", "mediaTracking$1", "getMediaType", "setMediaType", "mediaType$1", "getMediaWeightMetric", "setMediaWeightMetric", "mediaWeightMetric$1", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component3", "component4", "component5", "component6", "component7", "component8", "component9", PrinterServiceType.copy, "(Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/Integer;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/Integer;Ljava/lang/String;Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/Integer;Lcom/hp/jipp/model/MediaCol$MediaSize;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/model/MediaCol$MediaSourceProperties;Ljava/lang/Integer;Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/Integer;)Lcom/hp/jipp/model/MediaCol;", "equals", "", "other", "", "hashCode", "toString", "Companion", "MediaSize", "MediaSourceProperties", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class MediaCol implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<MediaCol> cls;
    public static final KeywordOrNameType mediaBackCoating;
    public static final IntType mediaBottomMargin;
    public static final KeywordOrNameType mediaColor;
    public static final KeywordOrNameType mediaFrontCoating;
    public static final KeywordOrNameType mediaGrain;
    public static final IntType mediaHoleCount;
    public static final TextType mediaInfo;
    public static final KeywordOrNameType mediaKey;
    public static final IntType mediaLeftMargin;
    public static final IntType mediaOrderCount;
    public static final KeywordOrNameType mediaPrePrinted;
    public static final KeywordOrNameType mediaRecycled;
    public static final IntType mediaRightMargin;
    public static final AttributeCollection.Type<MediaSize> mediaSize;
    public static final KeywordOrNameType mediaSizeName;
    public static final KeywordOrNameType mediaSource;
    public static final AttributeCollection.Type<MediaSourceProperties> mediaSourceProperties;
    public static final IntType mediaThickness;
    public static final KeywordOrNameType mediaTooth;
    public static final IntType mediaTopMargin;
    public static final IntType mediaTopOffset;
    public static final KeywordType mediaTracking;
    public static final KeywordOrNameType mediaType;
    public static final IntType mediaWeightMetric;

    private KeywordOrName mediaBackCoating;

    private Integer mediaBottomMargin;

    private KeywordOrName mediaColor;

    private KeywordOrName mediaFrontCoating;

    private KeywordOrName mediaGrain;

    private Integer mediaHoleCount;

    private String mediaInfo;

    private KeywordOrName mediaKey;

    private Integer mediaLeftMargin;

    private Integer mediaOrderCount;

    private KeywordOrName mediaPrePrinted;

    private KeywordOrName mediaRecycled;

    private Integer mediaRightMargin;

    private MediaSize mediaSize;

    private KeywordOrName mediaSizeName;

    private KeywordOrName mediaSource;

    private MediaSourceProperties mediaSourceProperties;

    private Integer mediaThickness;

    private KeywordOrName mediaTooth;

    private Integer mediaTopMargin;

    private Integer mediaTopOffset;

    private String mediaTracking;

    private KeywordOrName mediaType;

    private Integer mediaWeightMetric;

    public final KeywordOrName getMediaBackCoating() {
        return this.mediaBackCoating;
    }

    public final Integer getMediaOrderCount() {
        return this.mediaOrderCount;
    }

    public final KeywordOrName getMediaPrePrinted() {
        return this.mediaPrePrinted;
    }

    public final KeywordOrName getMediaRecycled() {
        return this.mediaRecycled;
    }

    public final Integer getMediaRightMargin() {
        return this.mediaRightMargin;
    }

    public final MediaSize getMediaSize() {
        return this.mediaSize;
    }

    public final KeywordOrName getMediaSizeName() {
        return this.mediaSizeName;
    }

    public final KeywordOrName getMediaSource() {
        return this.mediaSource;
    }

    public final MediaSourceProperties getMediaSourceProperties() {
        return this.mediaSourceProperties;
    }

    public final Integer getMediaThickness() {
        return this.mediaThickness;
    }

    public final KeywordOrName getMediaTooth() {
        return this.mediaTooth;
    }

    public final Integer getMediaBottomMargin() {
        return this.mediaBottomMargin;
    }

    public final Integer getMediaTopMargin() {
        return this.mediaTopMargin;
    }

    public final Integer getMediaTopOffset() {
        return this.mediaTopOffset;
    }

    public final String getMediaTracking() {
        return this.mediaTracking;
    }

    public final KeywordOrName getMediaType() {
        return this.mediaType;
    }

    public final Integer getMediaWeightMetric() {
        return this.mediaWeightMetric;
    }

    public final KeywordOrName getMediaColor() {
        return this.mediaColor;
    }

    public final KeywordOrName getMediaFrontCoating() {
        return this.mediaFrontCoating;
    }

    public final KeywordOrName getMediaGrain() {
        return this.mediaGrain;
    }

    public final Integer getMediaHoleCount() {
        return this.mediaHoleCount;
    }

    public final String getMediaInfo() {
        return this.mediaInfo;
    }

    public final KeywordOrName getMediaKey() {
        return this.mediaKey;
    }

    public final Integer getMediaLeftMargin() {
        return this.mediaLeftMargin;
    }

    public final MediaCol copy(KeywordOrName mediaBackCoating2, Integer mediaBottomMargin2, KeywordOrName mediaColor2, KeywordOrName mediaFrontCoating2, KeywordOrName mediaGrain2, Integer mediaHoleCount2, String mediaInfo2, KeywordOrName mediaKey2, Integer mediaLeftMargin2, Integer mediaOrderCount2, KeywordOrName mediaPrePrinted2, KeywordOrName mediaRecycled2, Integer mediaRightMargin2, MediaSize mediaSize2, KeywordOrName mediaSizeName2, KeywordOrName mediaSource2, MediaSourceProperties mediaSourceProperties2, Integer mediaThickness2, KeywordOrName mediaTooth2, Integer mediaTopMargin2, Integer mediaTopOffset2, String mediaTracking2, KeywordOrName mediaType2, Integer mediaWeightMetric2) {
        return new MediaCol(mediaBackCoating2, mediaBottomMargin2, mediaColor2, mediaFrontCoating2, mediaGrain2, mediaHoleCount2, mediaInfo2, mediaKey2, mediaLeftMargin2, mediaOrderCount2, mediaPrePrinted2, mediaRecycled2, mediaRightMargin2, mediaSize2, mediaSizeName2, mediaSource2, mediaSourceProperties2, mediaThickness2, mediaTooth2, mediaTopMargin2, mediaTopOffset2, mediaTracking2, mediaType2, mediaWeightMetric2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MediaCol)) {
            return false;
        }
        MediaCol mediaCol = (MediaCol) other;
        return Intrinsics.areEqual(this.mediaBackCoating, mediaCol.mediaBackCoating) && Intrinsics.areEqual(this.mediaBottomMargin, mediaCol.mediaBottomMargin) && Intrinsics.areEqual(this.mediaColor, mediaCol.mediaColor) && Intrinsics.areEqual(this.mediaFrontCoating, mediaCol.mediaFrontCoating) && Intrinsics.areEqual(this.mediaGrain, mediaCol.mediaGrain) && Intrinsics.areEqual(this.mediaHoleCount, mediaCol.mediaHoleCount) && Intrinsics.areEqual(this.mediaInfo, mediaCol.mediaInfo) && Intrinsics.areEqual(this.mediaKey, mediaCol.mediaKey) && Intrinsics.areEqual(this.mediaLeftMargin, mediaCol.mediaLeftMargin) && Intrinsics.areEqual(this.mediaOrderCount, mediaCol.mediaOrderCount) && Intrinsics.areEqual(this.mediaPrePrinted, mediaCol.mediaPrePrinted) && Intrinsics.areEqual(this.mediaRecycled, mediaCol.mediaRecycled) && Intrinsics.areEqual(this.mediaRightMargin, mediaCol.mediaRightMargin) && Intrinsics.areEqual(this.mediaSize, mediaCol.mediaSize) && Intrinsics.areEqual(this.mediaSizeName, mediaCol.mediaSizeName) && Intrinsics.areEqual(this.mediaSource, mediaCol.mediaSource) && Intrinsics.areEqual(this.mediaSourceProperties, mediaCol.mediaSourceProperties) && Intrinsics.areEqual(this.mediaThickness, mediaCol.mediaThickness) && Intrinsics.areEqual(this.mediaTooth, mediaCol.mediaTooth) && Intrinsics.areEqual(this.mediaTopMargin, mediaCol.mediaTopMargin) && Intrinsics.areEqual(this.mediaTopOffset, mediaCol.mediaTopOffset) && Intrinsics.areEqual(this.mediaTracking, mediaCol.mediaTracking) && Intrinsics.areEqual(this.mediaType, mediaCol.mediaType) && Intrinsics.areEqual(this.mediaWeightMetric, mediaCol.mediaWeightMetric);
    }

    public int hashCode() {
        KeywordOrName keywordOrName = this.mediaBackCoating;
        int iHashCode = (keywordOrName != null ? keywordOrName.hashCode() : 0) * 31;
        Integer num = this.mediaBottomMargin;
        int iHashCode2 = (iHashCode + (num != null ? num.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName2 = this.mediaColor;
        int iHashCode3 = (iHashCode2 + (keywordOrName2 != null ? keywordOrName2.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName3 = this.mediaFrontCoating;
        int iHashCode4 = (iHashCode3 + (keywordOrName3 != null ? keywordOrName3.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName4 = this.mediaGrain;
        int iHashCode5 = (iHashCode4 + (keywordOrName4 != null ? keywordOrName4.hashCode() : 0)) * 31;
        Integer num2 = this.mediaHoleCount;
        int iHashCode6 = (iHashCode5 + (num2 != null ? num2.hashCode() : 0)) * 31;
        String str = this.mediaInfo;
        int iHashCode7 = (iHashCode6 + (str != null ? str.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName5 = this.mediaKey;
        int iHashCode8 = (iHashCode7 + (keywordOrName5 != null ? keywordOrName5.hashCode() : 0)) * 31;
        Integer num3 = this.mediaLeftMargin;
        int iHashCode9 = (iHashCode8 + (num3 != null ? num3.hashCode() : 0)) * 31;
        Integer num4 = this.mediaOrderCount;
        int iHashCode10 = (iHashCode9 + (num4 != null ? num4.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName6 = this.mediaPrePrinted;
        int iHashCode11 = (iHashCode10 + (keywordOrName6 != null ? keywordOrName6.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName7 = this.mediaRecycled;
        int iHashCode12 = (iHashCode11 + (keywordOrName7 != null ? keywordOrName7.hashCode() : 0)) * 31;
        Integer num5 = this.mediaRightMargin;
        int iHashCode13 = (iHashCode12 + (num5 != null ? num5.hashCode() : 0)) * 31;
        MediaSize mediaSize2 = this.mediaSize;
        int iHashCode14 = (iHashCode13 + (mediaSize2 != null ? mediaSize2.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName8 = this.mediaSizeName;
        int iHashCode15 = (iHashCode14 + (keywordOrName8 != null ? keywordOrName8.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName9 = this.mediaSource;
        int iHashCode16 = (iHashCode15 + (keywordOrName9 != null ? keywordOrName9.hashCode() : 0)) * 31;
        MediaSourceProperties mediaSourceProperties2 = this.mediaSourceProperties;
        int iHashCode17 = (iHashCode16 + (mediaSourceProperties2 != null ? mediaSourceProperties2.hashCode() : 0)) * 31;
        Integer num6 = this.mediaThickness;
        int iHashCode18 = (iHashCode17 + (num6 != null ? num6.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName10 = this.mediaTooth;
        int iHashCode19 = (iHashCode18 + (keywordOrName10 != null ? keywordOrName10.hashCode() : 0)) * 31;
        Integer num7 = this.mediaTopMargin;
        int iHashCode20 = (iHashCode19 + (num7 != null ? num7.hashCode() : 0)) * 31;
        Integer num8 = this.mediaTopOffset;
        int iHashCode21 = (iHashCode20 + (num8 != null ? num8.hashCode() : 0)) * 31;
        String str2 = this.mediaTracking;
        int iHashCode22 = (iHashCode21 + (str2 != null ? str2.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName11 = this.mediaType;
        int iHashCode23 = (iHashCode22 + (keywordOrName11 != null ? keywordOrName11.hashCode() : 0)) * 31;
        Integer num9 = this.mediaWeightMetric;
        return iHashCode23 + (num9 != null ? num9.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public MediaCol(KeywordOrName keywordOrName, Integer num, KeywordOrName keywordOrName2, KeywordOrName keywordOrName3, KeywordOrName keywordOrName4, Integer num2, String str, KeywordOrName keywordOrName5, Integer num3, Integer num4, KeywordOrName keywordOrName6, KeywordOrName keywordOrName7, Integer num5, MediaSize mediaSize2, KeywordOrName keywordOrName8, KeywordOrName keywordOrName9, MediaSourceProperties mediaSourceProperties2, Integer num6, KeywordOrName keywordOrName10, Integer num7, Integer num8, String str2, KeywordOrName keywordOrName11, Integer num9) {
        this.mediaBackCoating = keywordOrName;
        this.mediaBottomMargin = num;
        this.mediaColor = keywordOrName2;
        this.mediaFrontCoating = keywordOrName3;
        this.mediaGrain = keywordOrName4;
        this.mediaHoleCount = num2;
        this.mediaInfo = str;
        this.mediaKey = keywordOrName5;
        this.mediaLeftMargin = num3;
        this.mediaOrderCount = num4;
        this.mediaPrePrinted = keywordOrName6;
        this.mediaRecycled = keywordOrName7;
        this.mediaRightMargin = num5;
        this.mediaSize = mediaSize2;
        this.mediaSizeName = keywordOrName8;
        this.mediaSource = keywordOrName9;
        this.mediaSourceProperties = mediaSourceProperties2;
        this.mediaThickness = num6;
        this.mediaTooth = keywordOrName10;
        this.mediaTopMargin = num7;
        this.mediaTopOffset = num8;
        this.mediaTracking = str2;
        this.mediaType = keywordOrName11;
        this.mediaWeightMetric = num9;
    }

    public MediaCol(KeywordOrName keywordOrName, Integer num, KeywordOrName keywordOrName2, KeywordOrName keywordOrName3, KeywordOrName keywordOrName4, Integer num2, String str, KeywordOrName keywordOrName5, Integer num3, Integer num4, KeywordOrName keywordOrName6, KeywordOrName keywordOrName7, Integer num5, MediaSize mediaSize2, KeywordOrName keywordOrName8, KeywordOrName keywordOrName9, MediaSourceProperties mediaSourceProperties2, Integer num6, KeywordOrName keywordOrName10, Integer num7, Integer num8, String str2, KeywordOrName keywordOrName11, Integer num9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        KeywordOrName keywordOrName12;
        Integer num10;
        KeywordOrName keywordOrName13;
        KeywordOrName keywordOrName14;
        KeywordOrName keywordOrName15;
        Integer num11;
        String str3;
        KeywordOrName keywordOrName16;
        Integer num12;
        Integer num13;
        KeywordOrName keywordOrName17;
        KeywordOrName keywordOrName18;
        Integer num14;
        MediaSize mediaSize3;
        KeywordOrName keywordOrName19;
        KeywordOrName keywordOrName20;
        MediaSourceProperties mediaSourceProperties3;
        Integer num15;
        KeywordOrName keywordOrName21;
        Integer num16;
        Integer num17;
        String str4;
        KeywordOrName keywordOrName22;
        Integer num18 = null;
        if ((i & 1) != 0) {
            keywordOrName12 = null;
        } else {
            keywordOrName12 = keywordOrName;
        }
        if ((i & 2) != 0) {
            num10 = null;
        } else {
            num10 = num;
        }
        if ((i & 4) != 0) {
            keywordOrName13 = null;
        } else {
            keywordOrName13 = keywordOrName2;
        }
        if ((i & 8) != 0) {
            keywordOrName14 = null;
        } else {
            keywordOrName14 = keywordOrName3;
        }
        if ((i & 16) != 0) {
            keywordOrName15 = null;
        } else {
            keywordOrName15 = keywordOrName4;
        }
        if ((i & 32) != 0) {
            num11 = null;
        } else {
            num11 = num2;
        }
        if ((i & 64) != 0) {
            str3 = null;
        } else {
            str3 = str;
        }
        if ((i & 128) != 0) {
            keywordOrName16 = null;
        } else {
            keywordOrName16 = keywordOrName5;
        }
        if ((i & 256) != 0) {
            num12 = null;
        } else {
            num12 = num3;
        }
        if ((i & 512) != 0) {
            num13 = null;
        } else {
            num13 = num4;
        }
        if ((i & 1024) != 0) {
            keywordOrName17 = null;
        } else {
            keywordOrName17 = keywordOrName6;
        }
        if ((i & 2048) != 0) {
            keywordOrName18 = null;
        } else {
            keywordOrName18 = keywordOrName7;
        }
        if ((i & 4096) != 0) {
            num14 = null;
        } else {
            num14 = num5;
        }
        if ((i & 8192) != 0) {
            mediaSize3 = null;
        } else {
            mediaSize3 = mediaSize2;
        }
        MediaSize mediaSize4 = mediaSize3;
        if ((i & 16384) != 0) {
            keywordOrName19 = null;
        } else {
            keywordOrName19 = keywordOrName8;
        }
        if ((i & 32768) != 0) {
            keywordOrName20 = null;
        } else {
            keywordOrName20 = keywordOrName9;
        }
        if ((i & 65536) != 0) {
            mediaSourceProperties3 = null;
        } else {
            mediaSourceProperties3 = mediaSourceProperties2;
        }
        if ((i & 131072) != 0) {
            num15 = null;
        } else {
            num15 = num6;
        }
        if ((i & 262144) != 0) {
            keywordOrName21 = null;
        } else {
            keywordOrName21 = keywordOrName10;
        }
        if ((i & 524288) != 0) {
            num16 = null;
        } else {
            num16 = num7;
        }
        if ((i & 1048576) != 0) {
            num17 = null;
        } else {
            num17 = num8;
        }
        if ((i & 2097152) != 0) {
            str4 = null;
        } else {
            str4 = str2;
        }
        if ((i & 4194304) != 0) {
            keywordOrName22 = null;
        } else {
            keywordOrName22 = keywordOrName11;
        }
        if ((i & 8388608) != 0) {
        } else {
            num18 = num9;
        }
        this(keywordOrName12, num10, keywordOrName13, keywordOrName14, keywordOrName15, num11, str3, keywordOrName16, num12, num13, keywordOrName17, keywordOrName18, num14, mediaSize4, keywordOrName19, keywordOrName20, mediaSourceProperties3, num15, keywordOrName21, num16, num17, str4, keywordOrName22, num18);
    }

    public final KeywordOrName getMediaBackCoating() {
        return this.mediaBackCoating;
    }

    public final void setMediaBackCoating(KeywordOrName keywordOrName) {
        this.mediaBackCoating = keywordOrName;
    }

    public final Integer getMediaBottomMargin() {
        return this.mediaBottomMargin;
    }

    public final void setMediaBottomMargin(Integer num) {
        this.mediaBottomMargin = num;
    }

    public final KeywordOrName getMediaColor() {
        return this.mediaColor;
    }

    public final void setMediaColor(KeywordOrName keywordOrName) {
        this.mediaColor = keywordOrName;
    }

    public final KeywordOrName getMediaFrontCoating() {
        return this.mediaFrontCoating;
    }

    public final void setMediaFrontCoating(KeywordOrName keywordOrName) {
        this.mediaFrontCoating = keywordOrName;
    }

    public final KeywordOrName getMediaGrain() {
        return this.mediaGrain;
    }

    public final void setMediaGrain(KeywordOrName keywordOrName) {
        this.mediaGrain = keywordOrName;
    }

    public final Integer getMediaHoleCount() {
        return this.mediaHoleCount;
    }

    public final void setMediaHoleCount(Integer num) {
        this.mediaHoleCount = num;
    }

    public final String getMediaInfo() {
        return this.mediaInfo;
    }

    public final void setMediaInfo(String str) {
        this.mediaInfo = str;
    }

    public final KeywordOrName getMediaKey() {
        return this.mediaKey;
    }

    public final void setMediaKey(KeywordOrName keywordOrName) {
        this.mediaKey = keywordOrName;
    }

    public final Integer getMediaLeftMargin() {
        return this.mediaLeftMargin;
    }

    public final void setMediaLeftMargin(Integer num) {
        this.mediaLeftMargin = num;
    }

    public final Integer getMediaOrderCount() {
        return this.mediaOrderCount;
    }

    public final void setMediaOrderCount(Integer num) {
        this.mediaOrderCount = num;
    }

    public final KeywordOrName getMediaPrePrinted() {
        return this.mediaPrePrinted;
    }

    public final void setMediaPrePrinted(KeywordOrName keywordOrName) {
        this.mediaPrePrinted = keywordOrName;
    }

    public final KeywordOrName getMediaRecycled() {
        return this.mediaRecycled;
    }

    public final void setMediaRecycled(KeywordOrName keywordOrName) {
        this.mediaRecycled = keywordOrName;
    }

    public final Integer getMediaRightMargin() {
        return this.mediaRightMargin;
    }

    public final void setMediaRightMargin(Integer num) {
        this.mediaRightMargin = num;
    }

    public final MediaSize getMediaSize() {
        return this.mediaSize;
    }

    public final void setMediaSize(MediaSize mediaSize2) {
        this.mediaSize = mediaSize2;
    }

    public final KeywordOrName getMediaSizeName() {
        return this.mediaSizeName;
    }

    public final void setMediaSizeName(KeywordOrName keywordOrName) {
        this.mediaSizeName = keywordOrName;
    }

    public final KeywordOrName getMediaSource() {
        return this.mediaSource;
    }

    public final void setMediaSource(KeywordOrName keywordOrName) {
        this.mediaSource = keywordOrName;
    }

    public final MediaSourceProperties getMediaSourceProperties() {
        return this.mediaSourceProperties;
    }

    public final void setMediaSourceProperties(MediaSourceProperties mediaSourceProperties2) {
        this.mediaSourceProperties = mediaSourceProperties2;
    }

    public final Integer getMediaThickness() {
        return this.mediaThickness;
    }

    public final void setMediaThickness(Integer num) {
        this.mediaThickness = num;
    }

    public final KeywordOrName getMediaTooth() {
        return this.mediaTooth;
    }

    public final void setMediaTooth(KeywordOrName keywordOrName) {
        this.mediaTooth = keywordOrName;
    }

    public final Integer getMediaTopMargin() {
        return this.mediaTopMargin;
    }

    public final void setMediaTopMargin(Integer num) {
        this.mediaTopMargin = num;
    }

    public final Integer getMediaTopOffset() {
        return this.mediaTopOffset;
    }

    public final void setMediaTopOffset(Integer num) {
        this.mediaTopOffset = num;
    }

    public final String getMediaTracking() {
        return this.mediaTracking;
    }

    public final void setMediaTracking(String str) {
        this.mediaTracking = str;
    }

    public final KeywordOrName getMediaType() {
        return this.mediaType;
    }

    public final void setMediaType(KeywordOrName keywordOrName) {
        this.mediaType = keywordOrName;
    }

    public final Integer getMediaWeightMetric() {
        return this.mediaWeightMetric;
    }

    public final void setMediaWeightMetric(Integer num) {
        this.mediaWeightMetric = num;
    }

    public MediaCol() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[24];
        KeywordOrName keywordOrName = this.mediaBackCoating;
        attributeArr[0] = keywordOrName != null ? mediaBackCoating.mo418of(keywordOrName) : null;
        Integer num = this.mediaBottomMargin;
        attributeArr[1] = num != null ? mediaBottomMargin.mo418of(Integer.valueOf(num.intValue())) : null;
        KeywordOrName keywordOrName2 = this.mediaColor;
        attributeArr[2] = keywordOrName2 != null ? mediaColor.mo418of(keywordOrName2) : null;
        KeywordOrName keywordOrName3 = this.mediaFrontCoating;
        attributeArr[3] = keywordOrName3 != null ? mediaFrontCoating.mo418of(keywordOrName3) : null;
        KeywordOrName keywordOrName4 = this.mediaGrain;
        attributeArr[4] = keywordOrName4 != null ? mediaGrain.mo418of(keywordOrName4) : null;
        Integer num2 = this.mediaHoleCount;
        attributeArr[5] = num2 != null ? mediaHoleCount.mo418of(Integer.valueOf(num2.intValue())) : null;
        String str = this.mediaInfo;
        attributeArr[6] = str != null ? mediaInfo.m442of(str) : null;
        KeywordOrName keywordOrName5 = this.mediaKey;
        attributeArr[7] = keywordOrName5 != null ? mediaKey.mo418of(keywordOrName5) : null;
        Integer num3 = this.mediaLeftMargin;
        attributeArr[8] = num3 != null ? mediaLeftMargin.mo418of(Integer.valueOf(num3.intValue())) : null;
        Integer num4 = this.mediaOrderCount;
        attributeArr[9] = num4 != null ? mediaOrderCount.mo418of(Integer.valueOf(num4.intValue())) : null;
        KeywordOrName keywordOrName6 = this.mediaPrePrinted;
        attributeArr[10] = keywordOrName6 != null ? mediaPrePrinted.mo418of(keywordOrName6) : null;
        KeywordOrName keywordOrName7 = this.mediaRecycled;
        attributeArr[11] = keywordOrName7 != null ? mediaRecycled.mo418of(keywordOrName7) : null;
        Integer num5 = this.mediaRightMargin;
        attributeArr[12] = num5 != null ? mediaRightMargin.mo418of(Integer.valueOf(num5.intValue())) : null;
        MediaSize mediaSize2 = this.mediaSize;
        attributeArr[13] = mediaSize2 != null ? mediaSize.mo418of(mediaSize2) : null;
        KeywordOrName keywordOrName8 = this.mediaSizeName;
        attributeArr[14] = keywordOrName8 != null ? mediaSizeName.mo418of(keywordOrName8) : null;
        KeywordOrName keywordOrName9 = this.mediaSource;
        attributeArr[15] = keywordOrName9 != null ? mediaSource.mo418of(keywordOrName9) : null;
        MediaSourceProperties mediaSourceProperties2 = this.mediaSourceProperties;
        attributeArr[16] = mediaSourceProperties2 != null ? mediaSourceProperties.mo418of(mediaSourceProperties2) : null;
        Integer num6 = this.mediaThickness;
        attributeArr[17] = num6 != null ? mediaThickness.mo418of(Integer.valueOf(num6.intValue())) : null;
        KeywordOrName keywordOrName10 = this.mediaTooth;
        attributeArr[18] = keywordOrName10 != null ? mediaTooth.mo418of(keywordOrName10) : null;
        Integer num7 = this.mediaTopMargin;
        attributeArr[19] = num7 != null ? mediaTopMargin.mo418of(Integer.valueOf(num7.intValue())) : null;
        Integer num8 = this.mediaTopOffset;
        attributeArr[20] = num8 != null ? mediaTopOffset.mo418of(Integer.valueOf(num8.intValue())) : null;
        String str2 = this.mediaTracking;
        attributeArr[21] = str2 != null ? mediaTracking.mo418of(str2) : null;
        KeywordOrName keywordOrName11 = this.mediaType;
        attributeArr[22] = keywordOrName11 != null ? mediaType.mo418of(keywordOrName11) : null;
        Integer num9 = this.mediaWeightMetric;
        attributeArr[23] = num9 != null ? mediaWeightMetric.mo418of(Integer.valueOf(num9.intValue())) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010)\u001a\u00020\u00022\u0010\u0010*\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030,0+H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u00020&8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, m1293d2 = {"Lcom/hp/jipp/model/MediaCol$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/MediaCol;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "mediaBackCoating", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "mediaBottomMargin", "Lcom/hp/jipp/encoding/IntType;", "mediaColor", "mediaFrontCoating", "mediaGrain", "mediaHoleCount", "mediaInfo", "Lcom/hp/jipp/encoding/TextType;", "mediaKey", "mediaLeftMargin", "mediaOrderCount", "mediaPrePrinted", "mediaRecycled", "mediaRightMargin", "mediaSize", "Lcom/hp/jipp/encoding/AttributeCollection$Type;", "Lcom/hp/jipp/model/MediaCol$MediaSize;", "mediaSizeName", "mediaSource", "mediaSourceProperties", "Lcom/hp/jipp/model/MediaCol$MediaSourceProperties;", "mediaThickness", "mediaTooth", "mediaTopMargin", "mediaTopOffset", "mediaTracking", "Lcom/hp/jipp/encoding/KeywordType;", "mediaType", "mediaWeightMetric", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<MediaCol> {
        @Deprecated(message = "Remove this symbol")
        public static void getTypes$annotations() {
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override
        public <T> Attribute<T> coerced(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return AttributeCollection.Converter.DefaultImpls.coerced(this, attributes, type);
        }

        @Override
        public AttributeCollection convert(List list) {
            return convert((List<? extends Attribute<?>>) list);
        }

        @Override
        public <T> List<T> extractAll(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return AttributeCollection.Converter.DefaultImpls.extractAll(this, attributes, type);
        }

        @Override
        public <T> T extractOne(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return (T) AttributeCollection.Converter.DefaultImpls.extractOne(this, attributes, type);
        }

        @Override
        public MediaCol convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            KeywordOrName keywordOrName = (KeywordOrName) extractOne(attributes, MediaCol.mediaBackCoating);
            Integer num = (Integer) extractOne(attributes, MediaCol.mediaBottomMargin);
            KeywordOrName keywordOrName2 = (KeywordOrName) extractOne(attributes, MediaCol.mediaColor);
            KeywordOrName keywordOrName3 = (KeywordOrName) extractOne(attributes, MediaCol.mediaFrontCoating);
            KeywordOrName keywordOrName4 = (KeywordOrName) extractOne(attributes, MediaCol.mediaGrain);
            Integer num2 = (Integer) extractOne(attributes, MediaCol.mediaHoleCount);
            Text text = (Text) extractOne(attributes, MediaCol.mediaInfo);
            return new MediaCol(keywordOrName, num, keywordOrName2, keywordOrName3, keywordOrName4, num2, text != null ? text.getValue() : null, (KeywordOrName) extractOne(attributes, MediaCol.mediaKey), (Integer) extractOne(attributes, MediaCol.mediaLeftMargin), (Integer) extractOne(attributes, MediaCol.mediaOrderCount), (KeywordOrName) extractOne(attributes, MediaCol.mediaPrePrinted), (KeywordOrName) extractOne(attributes, MediaCol.mediaRecycled), (Integer) extractOne(attributes, MediaCol.mediaRightMargin), (MediaSize) extractOne(attributes, MediaCol.mediaSize), (KeywordOrName) extractOne(attributes, MediaCol.mediaSizeName), (KeywordOrName) extractOne(attributes, MediaCol.mediaSource), (MediaSourceProperties) extractOne(attributes, MediaCol.mediaSourceProperties), (Integer) extractOne(attributes, MediaCol.mediaThickness), (KeywordOrName) extractOne(attributes, MediaCol.mediaTooth), (Integer) extractOne(attributes, MediaCol.mediaTopMargin), (Integer) extractOne(attributes, MediaCol.mediaTopOffset), (String) extractOne(attributes, MediaCol.mediaTracking), (KeywordOrName) extractOne(attributes, MediaCol.mediaType), (Integer) extractOne(attributes, MediaCol.mediaWeightMetric));
        }

        @Override
        public Class<MediaCol> getCls() {
            return MediaCol.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = MediaCol.class;
        Types = companion;
        mediaBackCoating = new KeywordOrNameType("media-back-coating");
        mediaBottomMargin = new IntType("media-bottom-margin");
        mediaColor = new KeywordOrNameType("media-color");
        mediaFrontCoating = new KeywordOrNameType("media-front-coating");
        mediaGrain = new KeywordOrNameType("media-grain");
        mediaHoleCount = new IntType("media-hole-count");
        mediaInfo = new TextType("media-info");
        mediaKey = new KeywordOrNameType("media-key");
        mediaLeftMargin = new IntType("media-left-margin");
        mediaOrderCount = new IntType("media-order-count");
        mediaPrePrinted = new KeywordOrNameType("media-pre-printed");
        mediaRecycled = new KeywordOrNameType("media-recycled");
        mediaRightMargin = new IntType("media-right-margin");
        mediaSize = new AttributeCollection.Type<>("media-size", MediaSize.INSTANCE);
        mediaSizeName = new KeywordOrNameType("media-size-name");
        mediaSource = new KeywordOrNameType("media-source");
        mediaSourceProperties = new AttributeCollection.Type<>("media-source-properties", MediaSourceProperties.INSTANCE);
        mediaThickness = new IntType("media-thickness");
        mediaTooth = new KeywordOrNameType("media-tooth");
        mediaTopMargin = new IntType("media-top-margin");
        mediaTopOffset = new IntType("media-top-offset");
        mediaTracking = new KeywordType("media-tracking");
        mediaType = new KeywordOrNameType("media-type");
        mediaWeightMetric = new IntType("media-weight-metric");
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000  2\u00020\u0001:\u0001 B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\rJ&\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0004HÖ\u0001J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u001e\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000f¨\u0006!"}, m1293d2 = {"Lcom/hp/jipp/model/MediaCol$MediaSize;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "xDimension", "", "yDimension", "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getXDimension", "()Ljava/lang/Integer;", "setXDimension", "(Ljava/lang/Integer;)V", "xDimension$1", "Ljava/lang/Integer;", "getYDimension", "setYDimension", "yDimension$1", "component1", "component2", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/hp/jipp/model/MediaCol$MediaSize;", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class MediaSize implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        private static final Class<MediaSize> cls;
        public static final IntType xDimension;
        public static final IntType yDimension;

        private Integer xDimension;

        private Integer yDimension;

        public static MediaSize copy$default(MediaSize mediaSize, Integer num, Integer num2, int i, Object obj) {
            if ((i & 1) != 0) {
                num = mediaSize.xDimension;
            }
            if ((i & 2) != 0) {
                num2 = mediaSize.yDimension;
            }
            return mediaSize.copy(num, num2);
        }

        public final Integer getXDimension() {
            return this.xDimension;
        }

        public final Integer getYDimension() {
            return this.yDimension;
        }

        public final MediaSize copy(Integer xDimension2, Integer yDimension2) {
            return new MediaSize(xDimension2, yDimension2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MediaSize)) {
                return false;
            }
            MediaSize mediaSize = (MediaSize) other;
            return Intrinsics.areEqual(this.xDimension, mediaSize.xDimension) && Intrinsics.areEqual(this.yDimension, mediaSize.yDimension);
        }

        public int hashCode() {
            Integer num = this.xDimension;
            int iHashCode = (num != null ? num.hashCode() : 0) * 31;
            Integer num2 = this.yDimension;
            return iHashCode + (num2 != null ? num2.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public MediaSize(Integer num, Integer num2) {
            this.xDimension = num;
            this.yDimension = num2;
        }

        public MediaSize(Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                num = null;
            }
            if ((i & 2) != 0) {
                num2 = null;
            }
            this(num, num2);
        }

        public final Integer getXDimension() {
            return this.xDimension;
        }

        public final void setXDimension(Integer num) {
            this.xDimension = num;
        }

        public final Integer getYDimension() {
            return this.yDimension;
        }

        public final void setYDimension(Integer num) {
            this.yDimension = num;
        }

        public MediaSize() {
            this(null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute<Integer> attributeOf;
            Attribute[] attributeArr = new Attribute[2];
            Integer num = this.xDimension;
            Attribute<Integer> attributeOf2 = null;
            if (num != null) {
                attributeOf = xDimension.mo418of(Integer.valueOf(num.intValue()));
            } else {
                attributeOf = null;
            }
            attributeArr[0] = attributeOf;
            Integer num2 = this.yDimension;
            if (num2 != null) {
                attributeOf2 = yDimension.mo418of(Integer.valueOf(num2.intValue()));
            }
            attributeArr[1] = attributeOf2;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\r\u001a\u00020\u00022\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000fH\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m1293d2 = {"Lcom/hp/jipp/model/MediaCol$MediaSize$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/MediaCol$MediaSize;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "xDimension", "Lcom/hp/jipp/encoding/IntType;", "yDimension", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<MediaSize> {
            @Deprecated(message = "Remove this symbol")
            public static void getTypes$annotations() {
            }

            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @Override
            public <T> Attribute<T> coerced(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return AttributeCollection.Converter.DefaultImpls.coerced(this, attributes, type);
            }

            @Override
            public AttributeCollection convert(List list) {
                return convert((List<? extends Attribute<?>>) list);
            }

            @Override
            public <T> List<T> extractAll(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return AttributeCollection.Converter.DefaultImpls.extractAll(this, attributes, type);
            }

            @Override
            public <T> T extractOne(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return (T) AttributeCollection.Converter.DefaultImpls.extractOne(this, attributes, type);
            }

            @Override
            public MediaSize convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new MediaSize((Integer) extractOne(attributes, MediaSize.xDimension), (Integer) extractOne(attributes, MediaSize.yDimension));
            }

            @Override
            public Class<MediaSize> getCls() {
                return MediaSize.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = MediaSize.class;
            Types = companion;
            xDimension = new IntType("x-dimension");
            yDimension = new IntType("y-dimension");
        }

        public String toString() {
            return "MediaSize(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003J!\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\b\u0010 \u001a\u00020\u0004H\u0016R\u001e\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\""}, m1293d2 = {"Lcom/hp/jipp/model/MediaCol$MediaSourceProperties;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "mediaSourceFeedDirection", "", "mediaSourceFeedOrientation", "Lcom/hp/jipp/model/Orientation;", "(Ljava/lang/String;Lcom/hp/jipp/model/Orientation;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getMediaSourceFeedDirection", "()Ljava/lang/String;", "setMediaSourceFeedDirection", "(Ljava/lang/String;)V", "mediaSourceFeedDirection$1", "getMediaSourceFeedOrientation", "()Lcom/hp/jipp/model/Orientation;", "setMediaSourceFeedOrientation", "(Lcom/hp/jipp/model/Orientation;)V", "mediaSourceFeedOrientation$1", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class MediaSourceProperties implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        private static final Class<MediaSourceProperties> cls;
        public static final KeywordType mediaSourceFeedDirection;
        public static final Orientation.Type mediaSourceFeedOrientation;

        private String mediaSourceFeedDirection;

        private Orientation mediaSourceFeedOrientation;

        public static MediaSourceProperties copy$default(MediaSourceProperties mediaSourceProperties, String str, Orientation orientation, int i, Object obj) {
            if ((i & 1) != 0) {
                str = mediaSourceProperties.mediaSourceFeedDirection;
            }
            if ((i & 2) != 0) {
                orientation = mediaSourceProperties.mediaSourceFeedOrientation;
            }
            return mediaSourceProperties.copy(str, orientation);
        }

        public final String getMediaSourceFeedDirection() {
            return this.mediaSourceFeedDirection;
        }

        public final Orientation getMediaSourceFeedOrientation() {
            return this.mediaSourceFeedOrientation;
        }

        public final MediaSourceProperties copy(String mediaSourceFeedDirection2, Orientation mediaSourceFeedOrientation2) {
            return new MediaSourceProperties(mediaSourceFeedDirection2, mediaSourceFeedOrientation2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MediaSourceProperties)) {
                return false;
            }
            MediaSourceProperties mediaSourceProperties = (MediaSourceProperties) other;
            return Intrinsics.areEqual(this.mediaSourceFeedDirection, mediaSourceProperties.mediaSourceFeedDirection) && Intrinsics.areEqual(this.mediaSourceFeedOrientation, mediaSourceProperties.mediaSourceFeedOrientation);
        }

        public int hashCode() {
            String str = this.mediaSourceFeedDirection;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            Orientation orientation = this.mediaSourceFeedOrientation;
            return iHashCode + (orientation != null ? orientation.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public MediaSourceProperties(String str, Orientation orientation) {
            this.mediaSourceFeedDirection = str;
            this.mediaSourceFeedOrientation = orientation;
        }

        public MediaSourceProperties(String str, Orientation orientation, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                str = null;
            }
            if ((i & 2) != 0) {
                orientation = null;
            }
            this(str, orientation);
        }

        public final String getMediaSourceFeedDirection() {
            return this.mediaSourceFeedDirection;
        }

        public final void setMediaSourceFeedDirection(String str) {
            this.mediaSourceFeedDirection = str;
        }

        public final Orientation getMediaSourceFeedOrientation() {
            return this.mediaSourceFeedOrientation;
        }

        public final void setMediaSourceFeedOrientation(Orientation orientation) {
            this.mediaSourceFeedOrientation = orientation;
        }

        public MediaSourceProperties() {
            this(null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute[] attributeArr = new Attribute[2];
            String str = this.mediaSourceFeedDirection;
            attributeArr[0] = str != null ? mediaSourceFeedDirection.mo418of(str) : null;
            Orientation orientation = this.mediaSourceFeedOrientation;
            attributeArr[1] = orientation != null ? mediaSourceFeedOrientation.mo418of((Object) orientation) : null;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000e\u001a\u00020\u00022\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m1293d2 = {"Lcom/hp/jipp/model/MediaCol$MediaSourceProperties$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/MediaCol$MediaSourceProperties;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "mediaSourceFeedDirection", "Lcom/hp/jipp/encoding/KeywordType;", "mediaSourceFeedOrientation", "Lcom/hp/jipp/model/Orientation$Type;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<MediaSourceProperties> {
            @Deprecated(message = "Remove this symbol")
            public static void getTypes$annotations() {
            }

            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @Override
            public <T> Attribute<T> coerced(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return AttributeCollection.Converter.DefaultImpls.coerced(this, attributes, type);
            }

            @Override
            public AttributeCollection convert(List list) {
                return convert((List<? extends Attribute<?>>) list);
            }

            @Override
            public <T> List<T> extractAll(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return AttributeCollection.Converter.DefaultImpls.extractAll(this, attributes, type);
            }

            @Override
            public <T> T extractOne(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return (T) AttributeCollection.Converter.DefaultImpls.extractOne(this, attributes, type);
            }

            @Override
            public MediaSourceProperties convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new MediaSourceProperties((String) extractOne(attributes, MediaSourceProperties.mediaSourceFeedDirection), (Orientation) extractOne(attributes, MediaSourceProperties.mediaSourceFeedOrientation));
            }

            @Override
            public Class<MediaSourceProperties> getCls() {
                return MediaSourceProperties.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = MediaSourceProperties.class;
            Types = companion;
            mediaSourceFeedDirection = new KeywordType("media-source-feed-direction");
            mediaSourceFeedOrientation = new Orientation.Type("media-source-feed-orientation");
        }

        public String toString() {
            return "MediaSourceProperties(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    public String toString() {
        return "MediaCol(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
