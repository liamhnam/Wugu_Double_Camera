package com.faceunity.core.entity;

import cc.uling.usdk.constants.ErrorConst;
import com.arthenica.ffmpegkit.StreamInformation;
import com.google.android.exoplayer2.extractor.p018ts.PsExtractor;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.p020hp.jipp.model.PrinterServiceType;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0011\u0012B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0010R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, m1293d2 = {"Lcom/faceunity/core/entity/FURenderOutputData;", "", "texture", "Lcom/faceunity/core/entity/FURenderOutputData$FUTexture;", TtmlNode.TAG_IMAGE, "Lcom/faceunity/core/entity/FURenderOutputData$FUImageBuffer;", "(Lcom/faceunity/core/entity/FURenderOutputData$FUTexture;Lcom/faceunity/core/entity/FURenderOutputData$FUImageBuffer;)V", "getImage", "()Lcom/faceunity/core/entity/FURenderOutputData$FUImageBuffer;", "setImage", "(Lcom/faceunity/core/entity/FURenderOutputData$FUImageBuffer;)V", "getTexture", "()Lcom/faceunity/core/entity/FURenderOutputData$FUTexture;", "setTexture", "(Lcom/faceunity/core/entity/FURenderOutputData$FUTexture;)V", "printMsg", "", "FUImageBuffer", "FUTexture", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FURenderOutputData {
    private FUImageBuffer image;
    private FUTexture texture;

    public FURenderOutputData() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public FURenderOutputData(FUTexture fUTexture, FUImageBuffer fUImageBuffer) {
        this.texture = fUTexture;
        this.image = fUImageBuffer;
    }

    public FURenderOutputData(FUTexture fUTexture, FUImageBuffer fUImageBuffer, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            fUTexture = null;
        }
        if ((i & 2) != 0) {
            fUImageBuffer = null;
        }
        this(fUTexture, fUImageBuffer);
    }

    public final FUTexture getTexture() {
        return this.texture;
    }

    public final void setTexture(FUTexture fUTexture) {
        this.texture = fUTexture;
    }

    public final FUImageBuffer getImage() {
        return this.image;
    }

    public final void setImage(FUImageBuffer fUImageBuffer) {
        this.image = fUImageBuffer;
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, m1293d2 = {"Lcom/faceunity/core/entity/FURenderOutputData$FUTexture;", "", "texId", "", StreamInformation.KEY_WIDTH, StreamInformation.KEY_HEIGHT, "(III)V", "getHeight", "()I", "setHeight", "(I)V", "getTexId", "setTexId", "getWidth", "setWidth", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "hashCode", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class FUTexture {
        private int height;
        private int texId;
        private int width;

        public static FUTexture copy$default(FUTexture fUTexture, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = fUTexture.texId;
            }
            if ((i4 & 2) != 0) {
                i2 = fUTexture.width;
            }
            if ((i4 & 4) != 0) {
                i3 = fUTexture.height;
            }
            return fUTexture.copy(i, i2, i3);
        }

        public final int getTexId() {
            return this.texId;
        }

        public final int getWidth() {
            return this.width;
        }

        public final int getHeight() {
            return this.height;
        }

        public final FUTexture copy(int texId, int width, int height) {
            return new FUTexture(texId, width, height);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FUTexture)) {
                return false;
            }
            FUTexture fUTexture = (FUTexture) other;
            return this.texId == fUTexture.texId && this.width == fUTexture.width && this.height == fUTexture.height;
        }

        public int hashCode() {
            return (((Integer.hashCode(this.texId) * 31) + Integer.hashCode(this.width)) * 31) + Integer.hashCode(this.height);
        }

        public String toString() {
            return "FUTexture(texId=" + this.texId + ", width=" + this.width + ", height=" + this.height + ")";
        }

        public FUTexture(int i, int i2, int i3) {
            this.texId = i;
            this.width = i2;
            this.height = i3;
        }

        public final int getTexId() {
            return this.texId;
        }

        public final void setTexId(int i) {
            this.texId = i;
        }

        public final int getWidth() {
            return this.width;
        }

        public final void setWidth(int i) {
            this.width = i;
        }

        public final int getHeight() {
            return this.height;
        }

        public final void setHeight(int i) {
            this.height = i;
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BY\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J_\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020\u0003HÖ\u0001J\t\u0010.\u001a\u00020/HÖ\u0001R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u0018R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0016\"\u0004\b \u0010\u0018¨\u00060"}, m1293d2 = {"Lcom/faceunity/core/entity/FURenderOutputData$FUImageBuffer;", "", StreamInformation.KEY_WIDTH, "", StreamInformation.KEY_HEIGHT, "buffer", "", "buffer1", "buffer2", "stride", "stride1", "stride2", "(II[B[B[BIII)V", "getBuffer", "()[B", "setBuffer", "([B)V", "getBuffer1", "setBuffer1", "getBuffer2", "setBuffer2", "getHeight", "()I", "setHeight", "(I)V", "getStride", "setStride", "getStride1", "setStride1", "getStride2", "setStride2", "getWidth", "setWidth", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", PrinterServiceType.copy, "equals", "", "other", "hashCode", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class FUImageBuffer {
        private byte[] buffer;
        private byte[] buffer1;
        private byte[] buffer2;
        private int height;
        private int stride;
        private int stride1;
        private int stride2;
        private int width;

        public FUImageBuffer(int i, int i2) {
            this(i, i2, null, null, null, 0, 0, 0, ErrorConst.MDB_ERR_CANT_OPEN, null);
        }

        public FUImageBuffer(int i, int i2, byte[] bArr) {
            this(i, i2, bArr, null, null, 0, 0, 0, 248, null);
        }

        public FUImageBuffer(int i, int i2, byte[] bArr, byte[] bArr2) {
            this(i, i2, bArr, bArr2, null, 0, 0, 0, PsExtractor.VIDEO_STREAM_MASK, null);
        }

        public FUImageBuffer(int i, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this(i, i2, bArr, bArr2, bArr3, 0, 0, 0, 224, null);
        }

        public FUImageBuffer(int i, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, int i3) {
            this(i, i2, bArr, bArr2, bArr3, i3, 0, 0, 192, null);
        }

        public FUImageBuffer(int i, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, int i3, int i4) {
            this(i, i2, bArr, bArr2, bArr3, i3, i4, 0, 128, null);
        }

        public final int getWidth() {
            return this.width;
        }

        public final int getHeight() {
            return this.height;
        }

        public final byte[] getBuffer() {
            return this.buffer;
        }

        public final byte[] getBuffer1() {
            return this.buffer1;
        }

        public final byte[] getBuffer2() {
            return this.buffer2;
        }

        public final int getStride() {
            return this.stride;
        }

        public final int getStride1() {
            return this.stride1;
        }

        public final int getStride2() {
            return this.stride2;
        }

        public final FUImageBuffer copy(int width, int height, byte[] buffer, byte[] buffer1, byte[] buffer2, int stride, int stride1, int stride2) {
            return new FUImageBuffer(width, height, buffer, buffer1, buffer2, stride, stride1, stride2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FUImageBuffer)) {
                return false;
            }
            FUImageBuffer fUImageBuffer = (FUImageBuffer) other;
            return this.width == fUImageBuffer.width && this.height == fUImageBuffer.height && Intrinsics.areEqual(this.buffer, fUImageBuffer.buffer) && Intrinsics.areEqual(this.buffer1, fUImageBuffer.buffer1) && Intrinsics.areEqual(this.buffer2, fUImageBuffer.buffer2) && this.stride == fUImageBuffer.stride && this.stride1 == fUImageBuffer.stride1 && this.stride2 == fUImageBuffer.stride2;
        }

        public int hashCode() {
            int iHashCode = ((Integer.hashCode(this.width) * 31) + Integer.hashCode(this.height)) * 31;
            byte[] bArr = this.buffer;
            int iHashCode2 = (iHashCode + (bArr != null ? Arrays.hashCode(bArr) : 0)) * 31;
            byte[] bArr2 = this.buffer1;
            int iHashCode3 = (iHashCode2 + (bArr2 != null ? Arrays.hashCode(bArr2) : 0)) * 31;
            byte[] bArr3 = this.buffer2;
            return ((((((iHashCode3 + (bArr3 != null ? Arrays.hashCode(bArr3) : 0)) * 31) + Integer.hashCode(this.stride)) * 31) + Integer.hashCode(this.stride1)) * 31) + Integer.hashCode(this.stride2);
        }

        public String toString() {
            return "FUImageBuffer(width=" + this.width + ", height=" + this.height + ", buffer=" + Arrays.toString(this.buffer) + ", buffer1=" + Arrays.toString(this.buffer1) + ", buffer2=" + Arrays.toString(this.buffer2) + ", stride=" + this.stride + ", stride1=" + this.stride1 + ", stride2=" + this.stride2 + ")";
        }

        public FUImageBuffer(int i, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, int i3, int i4, int i5) {
            this.width = i;
            this.height = i2;
            this.buffer = bArr;
            this.buffer1 = bArr2;
            this.buffer2 = bArr3;
            this.stride = i3;
            this.stride1 = i4;
            this.stride2 = i5;
        }

        public final int getWidth() {
            return this.width;
        }

        public final void setWidth(int i) {
            this.width = i;
        }

        public final int getHeight() {
            return this.height;
        }

        public final void setHeight(int i) {
            this.height = i;
        }

        public FUImageBuffer(int i, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, int i3, int i4, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
            byte[] bArr4;
            byte[] bArr5;
            byte[] bArr6;
            if ((i6 & 4) != 0) {
                bArr4 = null;
            } else {
                bArr4 = bArr;
            }
            if ((i6 & 8) != 0) {
                bArr5 = null;
            } else {
                bArr5 = bArr2;
            }
            if ((i6 & 16) != 0) {
                bArr6 = null;
            } else {
                bArr6 = bArr3;
            }
            this(i, i2, bArr4, bArr5, bArr6, (i6 & 32) != 0 ? 0 : i3, (i6 & 64) != 0 ? 0 : i4, (i6 & 128) != 0 ? 0 : i5);
        }

        public final byte[] getBuffer() {
            return this.buffer;
        }

        public final void setBuffer(byte[] bArr) {
            this.buffer = bArr;
        }

        public final byte[] getBuffer1() {
            return this.buffer1;
        }

        public final void setBuffer1(byte[] bArr) {
            this.buffer1 = bArr;
        }

        public final byte[] getBuffer2() {
            return this.buffer2;
        }

        public final void setBuffer2(byte[] bArr) {
            this.buffer2 = bArr;
        }

        public final int getStride() {
            return this.stride;
        }

        public final void setStride(int i) {
            this.stride = i;
        }

        public final int getStride1() {
            return this.stride1;
        }

        public final void setStride1(int i) {
            this.stride1 = i;
        }

        public final int getStride2() {
            return this.stride2;
        }

        public final void setStride2(int i) {
            this.stride2 = i;
        }
    }

    public final String printMsg() {
        StringBuilder sb = new StringBuilder();
        if (this.texture == null) {
            sb.append("texture is null");
        } else {
            StringBuilder sb2 = new StringBuilder("texId:");
            FUTexture fUTexture = this.texture;
            if (fUTexture == null) {
                Intrinsics.throwNpe();
            }
            StringBuilder sbAppend = sb2.append(fUTexture.getTexId()).append("  texWdith:");
            FUTexture fUTexture2 = this.texture;
            if (fUTexture2 == null) {
                Intrinsics.throwNpe();
            }
            StringBuilder sbAppend2 = sbAppend.append(fUTexture2.getWidth()).append("  texHeight:");
            FUTexture fUTexture3 = this.texture;
            if (fUTexture3 == null) {
                Intrinsics.throwNpe();
            }
            sb.append(sbAppend2.append(fUTexture3.getHeight()).toString());
        }
        if (this.image == null) {
            sb.append("    image is null");
        } else {
            StringBuilder sb3 = new StringBuilder("    imgWdith:");
            FUImageBuffer fUImageBuffer = this.image;
            if (fUImageBuffer == null) {
                Intrinsics.throwNpe();
            }
            StringBuilder sbAppend3 = sb3.append(fUImageBuffer.getWidth()).append("  imgHeight:");
            FUImageBuffer fUImageBuffer2 = this.image;
            if (fUImageBuffer2 == null) {
                Intrinsics.throwNpe();
            }
            StringBuilder sbAppend4 = sbAppend3.append(fUImageBuffer2.getHeight()).append("  buffer Size:");
            FUImageBuffer fUImageBuffer3 = this.image;
            if (fUImageBuffer3 == null) {
                Intrinsics.throwNpe();
            }
            byte[] buffer = fUImageBuffer3.getBuffer();
            StringBuilder sbAppend5 = sbAppend4.append(buffer != null ? Integer.valueOf(buffer.length) : null).append(" buffer1 Size:");
            FUImageBuffer fUImageBuffer4 = this.image;
            if (fUImageBuffer4 == null) {
                Intrinsics.throwNpe();
            }
            byte[] buffer1 = fUImageBuffer4.getBuffer1();
            StringBuilder sbAppend6 = sbAppend5.append(buffer1 != null ? Integer.valueOf(buffer1.length) : null).append("   buffer2 Size:");
            FUImageBuffer fUImageBuffer5 = this.image;
            if (fUImageBuffer5 == null) {
                Intrinsics.throwNpe();
            }
            byte[] buffer2 = fUImageBuffer5.getBuffer2();
            StringBuilder sbAppend7 = sbAppend6.append(buffer2 != null ? Integer.valueOf(buffer2.length) : null).append("   stride:");
            FUImageBuffer fUImageBuffer6 = this.image;
            StringBuilder sbAppend8 = sbAppend7.append(fUImageBuffer6 != null ? Integer.valueOf(fUImageBuffer6.getStride()) : null).append("    stride1:");
            FUImageBuffer fUImageBuffer7 = this.image;
            StringBuilder sbAppend9 = sbAppend8.append(fUImageBuffer7 != null ? Integer.valueOf(fUImageBuffer7.getStride1()) : null).append("    stride2:");
            FUImageBuffer fUImageBuffer8 = this.image;
            sb.append(sbAppend9.append(fUImageBuffer8 != null ? Integer.valueOf(fUImageBuffer8.getStride2()) : null).toString());
        }
        String string = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "buffer.toString()");
        return string;
    }
}
