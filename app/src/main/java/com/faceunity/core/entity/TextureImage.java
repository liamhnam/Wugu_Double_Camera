package com.faceunity.core.entity;

import com.arthenica.ffmpegkit.StreamInformation;
import com.p020hp.jipp.model.PrinterServiceType;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0003H\u0016J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, m1293d2 = {"Lcom/faceunity/core/entity/TextureImage;", "", StreamInformation.KEY_WIDTH, "", StreamInformation.KEY_HEIGHT, "bytes", "", "(II[B)V", "getBytes", "()[B", "getHeight", "()I", "getWidth", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "hashCode", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class TextureImage {
    private final byte[] bytes;
    private final int height;
    private final int width;

    public static TextureImage copy$default(TextureImage textureImage, int i, int i2, byte[] bArr, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = textureImage.width;
        }
        if ((i3 & 2) != 0) {
            i2 = textureImage.height;
        }
        if ((i3 & 4) != 0) {
            bArr = textureImage.bytes;
        }
        return textureImage.copy(i, i2, bArr);
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final byte[] getBytes() {
        return this.bytes;
    }

    public final TextureImage copy(int width, int height, byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        return new TextureImage(width, height, bytes);
    }

    public String toString() {
        return "TextureImage(width=" + this.width + ", height=" + this.height + ", bytes=" + Arrays.toString(this.bytes) + ")";
    }

    public TextureImage(int i, int i2, byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        this.width = i;
        this.height = i2;
        this.bytes = bytes;
    }

    public final byte[] getBytes() {
        return this.bytes;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getWidth() {
        return this.width;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            TextureImage textureImage = (TextureImage) other;
            return this.width == textureImage.width && this.height == textureImage.height && Arrays.equals(this.bytes, textureImage.bytes);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.faceunity.core.entity.TextureImage");
    }

    public int hashCode() {
        return (((this.width * 31) + this.height) * 31) + Arrays.hashCode(this.bytes);
    }
}
