package com.faceunity.core.entity;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.enumeration.FUExternalInputEnum;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.FUInputTextureEnum;
import com.faceunity.core.enumeration.FUTransformMatrixEnum;
import com.p020hp.jipp.model.PrinterServiceType;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001:\u0003)*+B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u001e\u001a\u00020\u0000J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u001d\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0003HÖ\u0001J\u0006\u0010&\u001a\u00020'J\t\u0010(\u001a\u00020'HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0007\"\u0004\b\u001d\u0010\t¨\u0006,"}, m1293d2 = {"Lcom/faceunity/core/entity/FURenderInputData;", "", StreamInformation.KEY_WIDTH, "", StreamInformation.KEY_HEIGHT, "(II)V", "getHeight", "()I", "setHeight", "(I)V", "imageBuffer", "Lcom/faceunity/core/entity/FURenderInputData$FUImageBuffer;", "getImageBuffer", "()Lcom/faceunity/core/entity/FURenderInputData$FUImageBuffer;", "setImageBuffer", "(Lcom/faceunity/core/entity/FURenderInputData$FUImageBuffer;)V", "renderConfig", "Lcom/faceunity/core/entity/FURenderInputData$FURenderConfig;", "getRenderConfig", "()Lcom/faceunity/core/entity/FURenderInputData$FURenderConfig;", "setRenderConfig", "(Lcom/faceunity/core/entity/FURenderInputData$FURenderConfig;)V", "texture", "Lcom/faceunity/core/entity/FURenderInputData$FUTexture;", "getTexture", "()Lcom/faceunity/core/entity/FURenderInputData$FUTexture;", "setTexture", "(Lcom/faceunity/core/entity/FURenderInputData$FUTexture;)V", "getWidth", "setWidth", "clone", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "hashCode", "printMsg", "", "toString", "FUImageBuffer", "FURenderConfig", "FUTexture", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FURenderInputData {
    private int height;
    private FUImageBuffer imageBuffer;
    private FURenderConfig renderConfig = new FURenderConfig(null, 0, 0, null, null, null, false, false, false, FrameMetricsAggregator.EVERY_DURATION, null);
    private FUTexture texture;
    private int width;

    public static FURenderInputData copy$default(FURenderInputData fURenderInputData, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = fURenderInputData.width;
        }
        if ((i3 & 2) != 0) {
            i2 = fURenderInputData.height;
        }
        return fURenderInputData.copy(i, i2);
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final FURenderInputData copy(int width, int height) {
        return new FURenderInputData(width, height);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FURenderInputData)) {
            return false;
        }
        FURenderInputData fURenderInputData = (FURenderInputData) other;
        return this.width == fURenderInputData.width && this.height == fURenderInputData.height;
    }

    public int hashCode() {
        return (Integer.hashCode(this.width) * 31) + Integer.hashCode(this.height);
    }

    public String toString() {
        return "FURenderInputData(width=" + this.width + ", height=" + this.height + ")";
    }

    public FURenderInputData(int i, int i2) {
        this.width = i;
        this.height = i2;
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

    public final FUTexture getTexture() {
        return this.texture;
    }

    public final void setTexture(FUTexture fUTexture) {
        this.texture = fUTexture;
    }

    public final FUImageBuffer getImageBuffer() {
        return this.imageBuffer;
    }

    public final void setImageBuffer(FUImageBuffer fUImageBuffer) {
        this.imageBuffer = fUImageBuffer;
    }

    public final FURenderConfig getRenderConfig() {
        return this.renderConfig;
    }

    public final void setRenderConfig(FURenderConfig fURenderConfig) {
        Intrinsics.checkParameterIsNotNull(fURenderConfig, "<set-?>");
        this.renderConfig = fURenderConfig;
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, m1293d2 = {"Lcom/faceunity/core/entity/FURenderInputData$FUTexture;", "", "inputTextureType", "Lcom/faceunity/core/enumeration/FUInputTextureEnum;", "texId", "", "(Lcom/faceunity/core/enumeration/FUInputTextureEnum;I)V", "getInputTextureType", "()Lcom/faceunity/core/enumeration/FUInputTextureEnum;", "setInputTextureType", "(Lcom/faceunity/core/enumeration/FUInputTextureEnum;)V", "getTexId", "()I", "setTexId", "(I)V", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "hashCode", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class FUTexture {
        private FUInputTextureEnum inputTextureType;
        private int texId;

        public static FUTexture copy$default(FUTexture fUTexture, FUInputTextureEnum fUInputTextureEnum, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                fUInputTextureEnum = fUTexture.inputTextureType;
            }
            if ((i2 & 2) != 0) {
                i = fUTexture.texId;
            }
            return fUTexture.copy(fUInputTextureEnum, i);
        }

        public final FUInputTextureEnum getInputTextureType() {
            return this.inputTextureType;
        }

        public final int getTexId() {
            return this.texId;
        }

        public final FUTexture copy(FUInputTextureEnum inputTextureType, int texId) {
            Intrinsics.checkParameterIsNotNull(inputTextureType, "inputTextureType");
            return new FUTexture(inputTextureType, texId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FUTexture)) {
                return false;
            }
            FUTexture fUTexture = (FUTexture) other;
            return Intrinsics.areEqual(this.inputTextureType, fUTexture.inputTextureType) && this.texId == fUTexture.texId;
        }

        public int hashCode() {
            FUInputTextureEnum fUInputTextureEnum = this.inputTextureType;
            return ((fUInputTextureEnum != null ? fUInputTextureEnum.hashCode() : 0) * 31) + Integer.hashCode(this.texId);
        }

        public String toString() {
            return "FUTexture(inputTextureType=" + this.inputTextureType + ", texId=" + this.texId + ")";
        }

        public FUTexture(FUInputTextureEnum inputTextureType, int i) {
            Intrinsics.checkParameterIsNotNull(inputTextureType, "inputTextureType");
            this.inputTextureType = inputTextureType;
            this.texId = i;
        }

        public final FUInputTextureEnum getInputTextureType() {
            return this.inputTextureType;
        }

        public final void setInputTextureType(FUInputTextureEnum fUInputTextureEnum) {
            Intrinsics.checkParameterIsNotNull(fUInputTextureEnum, "<set-?>");
            this.inputTextureType = fUInputTextureEnum;
        }

        public final int getTexId() {
            return this.texId;
        }

        public final void setTexId(int i) {
            this.texId = i;
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B3\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J7\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006!"}, m1293d2 = {"Lcom/faceunity/core/entity/FURenderInputData$FUImageBuffer;", "", "inputBufferType", "Lcom/faceunity/core/enumeration/FUInputBufferEnum;", "buffer", "", "buffer1", "buffer2", "(Lcom/faceunity/core/enumeration/FUInputBufferEnum;[B[B[B)V", "getBuffer", "()[B", "setBuffer", "([B)V", "getBuffer1", "setBuffer1", "getBuffer2", "setBuffer2", "getInputBufferType", "()Lcom/faceunity/core/enumeration/FUInputBufferEnum;", "setInputBufferType", "(Lcom/faceunity/core/enumeration/FUInputBufferEnum;)V", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class FUImageBuffer {
        private byte[] buffer;
        private byte[] buffer1;
        private byte[] buffer2;
        private FUInputBufferEnum inputBufferType;

        public FUImageBuffer(FUInputBufferEnum fUInputBufferEnum) {
            this(fUInputBufferEnum, null, null, null, 14, null);
        }

        public FUImageBuffer(FUInputBufferEnum fUInputBufferEnum, byte[] bArr) {
            this(fUInputBufferEnum, bArr, null, null, 12, null);
        }

        public FUImageBuffer(FUInputBufferEnum fUInputBufferEnum, byte[] bArr, byte[] bArr2) {
            this(fUInputBufferEnum, bArr, bArr2, null, 8, null);
        }

        public static FUImageBuffer copy$default(FUImageBuffer fUImageBuffer, FUInputBufferEnum fUInputBufferEnum, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, Object obj) {
            if ((i & 1) != 0) {
                fUInputBufferEnum = fUImageBuffer.inputBufferType;
            }
            if ((i & 2) != 0) {
                bArr = fUImageBuffer.buffer;
            }
            if ((i & 4) != 0) {
                bArr2 = fUImageBuffer.buffer1;
            }
            if ((i & 8) != 0) {
                bArr3 = fUImageBuffer.buffer2;
            }
            return fUImageBuffer.copy(fUInputBufferEnum, bArr, bArr2, bArr3);
        }

        public final FUInputBufferEnum getInputBufferType() {
            return this.inputBufferType;
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

        public final FUImageBuffer copy(FUInputBufferEnum inputBufferType, byte[] buffer, byte[] buffer1, byte[] buffer2) {
            Intrinsics.checkParameterIsNotNull(inputBufferType, "inputBufferType");
            return new FUImageBuffer(inputBufferType, buffer, buffer1, buffer2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FUImageBuffer)) {
                return false;
            }
            FUImageBuffer fUImageBuffer = (FUImageBuffer) other;
            return Intrinsics.areEqual(this.inputBufferType, fUImageBuffer.inputBufferType) && Intrinsics.areEqual(this.buffer, fUImageBuffer.buffer) && Intrinsics.areEqual(this.buffer1, fUImageBuffer.buffer1) && Intrinsics.areEqual(this.buffer2, fUImageBuffer.buffer2);
        }

        public int hashCode() {
            FUInputBufferEnum fUInputBufferEnum = this.inputBufferType;
            int iHashCode = (fUInputBufferEnum != null ? fUInputBufferEnum.hashCode() : 0) * 31;
            byte[] bArr = this.buffer;
            int iHashCode2 = (iHashCode + (bArr != null ? Arrays.hashCode(bArr) : 0)) * 31;
            byte[] bArr2 = this.buffer1;
            int iHashCode3 = (iHashCode2 + (bArr2 != null ? Arrays.hashCode(bArr2) : 0)) * 31;
            byte[] bArr3 = this.buffer2;
            return iHashCode3 + (bArr3 != null ? Arrays.hashCode(bArr3) : 0);
        }

        public String toString() {
            return "FUImageBuffer(inputBufferType=" + this.inputBufferType + ", buffer=" + Arrays.toString(this.buffer) + ", buffer1=" + Arrays.toString(this.buffer1) + ", buffer2=" + Arrays.toString(this.buffer2) + ")";
        }

        public FUImageBuffer(FUInputBufferEnum inputBufferType, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            Intrinsics.checkParameterIsNotNull(inputBufferType, "inputBufferType");
            this.inputBufferType = inputBufferType;
            this.buffer = bArr;
            this.buffer1 = bArr2;
            this.buffer2 = bArr3;
        }

        public final FUInputBufferEnum getInputBufferType() {
            return this.inputBufferType;
        }

        public final void setInputBufferType(FUInputBufferEnum fUInputBufferEnum) {
            Intrinsics.checkParameterIsNotNull(fUInputBufferEnum, "<set-?>");
            this.inputBufferType = fUInputBufferEnum;
        }

        public FUImageBuffer(FUInputBufferEnum fUInputBufferEnum, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 2) != 0) {
                bArr = null;
            }
            if ((i & 4) != 0) {
                bArr2 = null;
            }
            if ((i & 8) != 0) {
                bArr3 = null;
            }
            this(fUInputBufferEnum, bArr, bArr2, bArr3);
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
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\"\u0018\u00002\u00020\u0001Ba\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\r¢\u0006\u0002\u0010\u0010R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0016\"\u0004\b\"\u0010\u0018R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001e\"\u0004\b$\u0010 R\u001a\u0010\u000f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010%\"\u0004\b&\u0010'R\u001a\u0010\u000e\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010%\"\u0004\b(\u0010'R$\u0010*\u001a\u00020\n2\u0006\u0010)\u001a\u00020\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001e\"\u0004\b,\u0010 R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010%\"\u0004\b.\u0010'¨\u0006/"}, m1293d2 = {"Lcom/faceunity/core/entity/FURenderInputData$FURenderConfig;", "", "externalInputType", "Lcom/faceunity/core/enumeration/FUExternalInputEnum;", "inputOrientation", "", "deviceOrientation", "cameraFacing", "Lcom/faceunity/core/enumeration/CameraFacingEnum;", "inputTextureMatrix", "Lcom/faceunity/core/enumeration/FUTransformMatrixEnum;", "inputBufferMatrix", "outputMatrixEnable", "", "isRenderFaceBeautyOnly", "isNeedBufferReturn", "(Lcom/faceunity/core/enumeration/FUExternalInputEnum;IILcom/faceunity/core/enumeration/CameraFacingEnum;Lcom/faceunity/core/enumeration/FUTransformMatrixEnum;Lcom/faceunity/core/enumeration/FUTransformMatrixEnum;ZZZ)V", "getCameraFacing", "()Lcom/faceunity/core/enumeration/CameraFacingEnum;", "setCameraFacing", "(Lcom/faceunity/core/enumeration/CameraFacingEnum;)V", "getDeviceOrientation", "()I", "setDeviceOrientation", "(I)V", "getExternalInputType", "()Lcom/faceunity/core/enumeration/FUExternalInputEnum;", "setExternalInputType", "(Lcom/faceunity/core/enumeration/FUExternalInputEnum;)V", "getInputBufferMatrix", "()Lcom/faceunity/core/enumeration/FUTransformMatrixEnum;", "setInputBufferMatrix", "(Lcom/faceunity/core/enumeration/FUTransformMatrixEnum;)V", "getInputOrientation", "setInputOrientation", "getInputTextureMatrix", "setInputTextureMatrix", "()Z", "setNeedBufferReturn", "(Z)V", "setRenderFaceBeautyOnly", "value", "outputMatrix", "getOutputMatrix", "setOutputMatrix", "getOutputMatrixEnable", "setOutputMatrixEnable", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class FURenderConfig {
        private CameraFacingEnum cameraFacing;
        private int deviceOrientation;
        private FUExternalInputEnum externalInputType;
        private FUTransformMatrixEnum inputBufferMatrix;
        private int inputOrientation;
        private FUTransformMatrixEnum inputTextureMatrix;
        private boolean isNeedBufferReturn;
        private boolean isRenderFaceBeautyOnly;
        private FUTransformMatrixEnum outputMatrix;
        private boolean outputMatrixEnable;

        public FURenderConfig() {
            this(null, 0, 0, null, null, null, false, false, false, FrameMetricsAggregator.EVERY_DURATION, null);
        }

        public FURenderConfig(FUExternalInputEnum fUExternalInputEnum) {
            this(fUExternalInputEnum, 0, 0, null, null, null, false, false, false, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
        }

        public FURenderConfig(FUExternalInputEnum fUExternalInputEnum, int i) {
            this(fUExternalInputEnum, i, 0, null, null, null, false, false, false, TypedValues.PositionType.TYPE_CURVE_FIT, null);
        }

        public FURenderConfig(FUExternalInputEnum fUExternalInputEnum, int i, int i2) {
            this(fUExternalInputEnum, i, i2, null, null, null, false, false, false, 504, null);
        }

        public FURenderConfig(FUExternalInputEnum fUExternalInputEnum, int i, int i2, CameraFacingEnum cameraFacingEnum) {
            this(fUExternalInputEnum, i, i2, cameraFacingEnum, null, null, false, false, false, 496, null);
        }

        public FURenderConfig(FUExternalInputEnum fUExternalInputEnum, int i, int i2, CameraFacingEnum cameraFacingEnum, FUTransformMatrixEnum fUTransformMatrixEnum) {
            this(fUExternalInputEnum, i, i2, cameraFacingEnum, fUTransformMatrixEnum, null, false, false, false, 480, null);
        }

        public FURenderConfig(FUExternalInputEnum fUExternalInputEnum, int i, int i2, CameraFacingEnum cameraFacingEnum, FUTransformMatrixEnum fUTransformMatrixEnum, FUTransformMatrixEnum fUTransformMatrixEnum2) {
            this(fUExternalInputEnum, i, i2, cameraFacingEnum, fUTransformMatrixEnum, fUTransformMatrixEnum2, false, false, false, 448, null);
        }

        public FURenderConfig(FUExternalInputEnum fUExternalInputEnum, int i, int i2, CameraFacingEnum cameraFacingEnum, FUTransformMatrixEnum fUTransformMatrixEnum, FUTransformMatrixEnum fUTransformMatrixEnum2, boolean z) {
            this(fUExternalInputEnum, i, i2, cameraFacingEnum, fUTransformMatrixEnum, fUTransformMatrixEnum2, z, false, false, 384, null);
        }

        public FURenderConfig(FUExternalInputEnum fUExternalInputEnum, int i, int i2, CameraFacingEnum cameraFacingEnum, FUTransformMatrixEnum fUTransformMatrixEnum, FUTransformMatrixEnum fUTransformMatrixEnum2, boolean z, boolean z2) {
            this(fUExternalInputEnum, i, i2, cameraFacingEnum, fUTransformMatrixEnum, fUTransformMatrixEnum2, z, z2, false, 256, null);
        }

        public FURenderConfig(FUExternalInputEnum externalInputType, int i, int i2, CameraFacingEnum cameraFacing, FUTransformMatrixEnum inputTextureMatrix, FUTransformMatrixEnum inputBufferMatrix, boolean z, boolean z2, boolean z3) {
            Intrinsics.checkParameterIsNotNull(externalInputType, "externalInputType");
            Intrinsics.checkParameterIsNotNull(cameraFacing, "cameraFacing");
            Intrinsics.checkParameterIsNotNull(inputTextureMatrix, "inputTextureMatrix");
            Intrinsics.checkParameterIsNotNull(inputBufferMatrix, "inputBufferMatrix");
            this.externalInputType = externalInputType;
            this.inputOrientation = i;
            this.deviceOrientation = i2;
            this.cameraFacing = cameraFacing;
            this.inputTextureMatrix = inputTextureMatrix;
            this.inputBufferMatrix = inputBufferMatrix;
            this.outputMatrixEnable = z;
            this.isRenderFaceBeautyOnly = z2;
            this.isNeedBufferReturn = z3;
            this.outputMatrix = FUTransformMatrixEnum.CCROT0;
        }

        public FURenderConfig(FUExternalInputEnum fUExternalInputEnum, int i, int i2, CameraFacingEnum cameraFacingEnum, FUTransformMatrixEnum fUTransformMatrixEnum, FUTransformMatrixEnum fUTransformMatrixEnum2, boolean z, boolean z2, boolean z3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? FUExternalInputEnum.EXTERNAL_INPUT_TYPE_CAMERA : fUExternalInputEnum, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 0 : i2, (i3 & 8) != 0 ? CameraFacingEnum.CAMERA_FRONT : cameraFacingEnum, (i3 & 16) != 0 ? FUTransformMatrixEnum.CCROT0 : fUTransformMatrixEnum, (i3 & 32) != 0 ? FUTransformMatrixEnum.CCROT0 : fUTransformMatrixEnum2, (i3 & 64) != 0 ? false : z, (i3 & 128) != 0 ? false : z2, (i3 & 256) == 0 ? z3 : false);
        }

        public final FUExternalInputEnum getExternalInputType() {
            return this.externalInputType;
        }

        public final void setExternalInputType(FUExternalInputEnum fUExternalInputEnum) {
            Intrinsics.checkParameterIsNotNull(fUExternalInputEnum, "<set-?>");
            this.externalInputType = fUExternalInputEnum;
        }

        public final int getInputOrientation() {
            return this.inputOrientation;
        }

        public final void setInputOrientation(int i) {
            this.inputOrientation = i;
        }

        public final int getDeviceOrientation() {
            return this.deviceOrientation;
        }

        public final void setDeviceOrientation(int i) {
            this.deviceOrientation = i;
        }

        public final CameraFacingEnum getCameraFacing() {
            return this.cameraFacing;
        }

        public final void setCameraFacing(CameraFacingEnum cameraFacingEnum) {
            Intrinsics.checkParameterIsNotNull(cameraFacingEnum, "<set-?>");
            this.cameraFacing = cameraFacingEnum;
        }

        public final FUTransformMatrixEnum getInputTextureMatrix() {
            return this.inputTextureMatrix;
        }

        public final void setInputTextureMatrix(FUTransformMatrixEnum fUTransformMatrixEnum) {
            Intrinsics.checkParameterIsNotNull(fUTransformMatrixEnum, "<set-?>");
            this.inputTextureMatrix = fUTransformMatrixEnum;
        }

        public final FUTransformMatrixEnum getInputBufferMatrix() {
            return this.inputBufferMatrix;
        }

        public final void setInputBufferMatrix(FUTransformMatrixEnum fUTransformMatrixEnum) {
            Intrinsics.checkParameterIsNotNull(fUTransformMatrixEnum, "<set-?>");
            this.inputBufferMatrix = fUTransformMatrixEnum;
        }

        public final boolean getOutputMatrixEnable() {
            return this.outputMatrixEnable;
        }

        public final void setOutputMatrixEnable(boolean z) {
            this.outputMatrixEnable = z;
        }

        public final boolean getIsRenderFaceBeautyOnly() {
            return this.isRenderFaceBeautyOnly;
        }

        public final void setRenderFaceBeautyOnly(boolean z) {
            this.isRenderFaceBeautyOnly = z;
        }

        public final boolean getIsNeedBufferReturn() {
            return this.isNeedBufferReturn;
        }

        public final void setNeedBufferReturn(boolean z) {
            this.isNeedBufferReturn = z;
        }

        public final FUTransformMatrixEnum getOutputMatrix() {
            return this.outputMatrix;
        }

        public final void setOutputMatrix(FUTransformMatrixEnum value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            this.outputMatrix = value;
            this.outputMatrixEnable = true;
        }
    }

    public final String printMsg() {
        StringBuilder sb = new StringBuilder();
        sb.append("width:" + this.width + "  height:" + this.height);
        if (this.texture == null) {
            sb.append("    texture is null");
        } else {
            StringBuilder sb2 = new StringBuilder("texId:");
            FUTexture fUTexture = this.texture;
            if (fUTexture == null) {
                Intrinsics.throwNpe();
            }
            StringBuilder sbAppend = sb2.append(fUTexture.getTexId()).append("  inputTextureType:");
            FUTexture fUTexture2 = this.texture;
            if (fUTexture2 == null) {
                Intrinsics.throwNpe();
            }
            sb.append(sbAppend.append(fUTexture2.getInputTextureType()).toString());
        }
        if (this.imageBuffer == null) {
            sb.append("    image is null");
        } else {
            StringBuilder sb3 = new StringBuilder("    inputBufferType:");
            FUImageBuffer fUImageBuffer = this.imageBuffer;
            if (fUImageBuffer == null) {
                Intrinsics.throwNpe();
            }
            StringBuilder sbAppend2 = sb3.append(fUImageBuffer.getInputBufferType()).append("  buffer Size:");
            FUImageBuffer fUImageBuffer2 = this.imageBuffer;
            if (fUImageBuffer2 == null) {
                Intrinsics.throwNpe();
            }
            byte[] buffer = fUImageBuffer2.getBuffer();
            StringBuilder sbAppend3 = sbAppend2.append(buffer != null ? Integer.valueOf(buffer.length) : null).append("  buffer1 Size:");
            FUImageBuffer fUImageBuffer3 = this.imageBuffer;
            if (fUImageBuffer3 == null) {
                Intrinsics.throwNpe();
            }
            byte[] buffer1 = fUImageBuffer3.getBuffer1();
            StringBuilder sbAppend4 = sbAppend3.append(buffer1 != null ? Integer.valueOf(buffer1.length) : null).append(" buffer2 Size:");
            FUImageBuffer fUImageBuffer4 = this.imageBuffer;
            if (fUImageBuffer4 == null) {
                Intrinsics.throwNpe();
            }
            byte[] buffer2 = fUImageBuffer4.getBuffer2();
            sb.append(sbAppend4.append(buffer2 != null ? Integer.valueOf(buffer2.length) : null).toString());
        }
        sb.append("    externalInputType:" + this.renderConfig.getExternalInputType());
        sb.append("    inputOrientation:" + this.renderConfig.getInputOrientation());
        sb.append("    deviceOrientation:" + this.renderConfig.getDeviceOrientation());
        sb.append("    cameraFacing:" + this.renderConfig.getCameraFacing());
        sb.append("    inputTextureMatrix:" + this.renderConfig.getInputTextureMatrix());
        sb.append("    inputBufferMatrix:" + this.renderConfig.getInputBufferMatrix());
        sb.append("    outputMatrix:" + this.renderConfig.getOutputMatrix());
        sb.append("    isRenderFaceBeautyOnly:" + this.renderConfig.getIsRenderFaceBeautyOnly());
        sb.append("    isNeedBufferReturn:" + this.renderConfig.getIsNeedBufferReturn());
        String string = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "buffer.toString()");
        return string;
    }

    public final FURenderInputData clone() {
        FURenderInputData fURenderInputData = new FURenderInputData(this.width, this.height);
        FUTexture fUTexture = this.texture;
        if (fUTexture != null) {
            fURenderInputData.texture = new FUTexture(fUTexture.getInputTextureType(), fUTexture.getTexId());
        }
        FUImageBuffer fUImageBuffer = this.imageBuffer;
        if (fUImageBuffer != null) {
            fURenderInputData.imageBuffer = new FUImageBuffer(fUImageBuffer.getInputBufferType(), fUImageBuffer.getBuffer(), fUImageBuffer.getBuffer1(), fUImageBuffer.getBuffer2());
        }
        fURenderInputData.renderConfig.setExternalInputType(this.renderConfig.getExternalInputType());
        fURenderInputData.renderConfig.setInputOrientation(this.renderConfig.getInputOrientation());
        fURenderInputData.renderConfig.setDeviceOrientation(this.renderConfig.getDeviceOrientation());
        fURenderInputData.renderConfig.setCameraFacing(this.renderConfig.getCameraFacing());
        fURenderInputData.renderConfig.setInputTextureMatrix(this.renderConfig.getInputTextureMatrix());
        fURenderInputData.renderConfig.setInputBufferMatrix(this.renderConfig.getInputBufferMatrix());
        fURenderInputData.renderConfig.setOutputMatrixEnable(this.renderConfig.getOutputMatrixEnable());
        fURenderInputData.renderConfig.setOutputMatrix(this.renderConfig.getOutputMatrix());
        fURenderInputData.renderConfig.setRenderFaceBeautyOnly(this.renderConfig.getIsRenderFaceBeautyOnly());
        fURenderInputData.renderConfig.setNeedBufferReturn(this.renderConfig.getIsNeedBufferReturn());
        return fURenderInputData;
    }
}
