package com.wugu.doublecamera.device.camera.camerausb.canon1500d;

import java.nio.ByteBuffer;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.UShort;

public class DeviceInfo {
    public int[] captureFormats;
    public int[] devicePropertiesSupported;
    public String deviceVersion;
    public int[] eventsSupported;
    public short functionalMode;
    public int[] imageFormats;
    public String manufacture;
    public String model;
    public int[] operationsSupported;
    public String serialNumber;
    public short standardVersion;
    public String vendorExtensionDesc;
    public int vendorExtensionId;
    public short vendorExtensionVersion;

    public DeviceInfo(ByteBuffer byteBuffer) {
        decode(byteBuffer);
    }

    public DeviceInfo() {
    }

    public void decode(ByteBuffer byteBuffer) {
        this.standardVersion = byteBuffer.getShort();
        this.vendorExtensionId = byteBuffer.getInt();
        this.vendorExtensionVersion = byteBuffer.getShort();
        this.vendorExtensionDesc = readString(byteBuffer);
        this.functionalMode = byteBuffer.getShort();
        this.operationsSupported = readU16Array(byteBuffer);
        this.eventsSupported = readU16Array(byteBuffer);
        this.devicePropertiesSupported = readU16Array(byteBuffer);
        this.captureFormats = readU16Array(byteBuffer);
        this.imageFormats = readU16Array(byteBuffer);
        this.manufacture = readString(byteBuffer);
        this.model = readString(byteBuffer);
        this.deviceVersion = readString(byteBuffer);
        this.serialNumber = readString(byteBuffer);
    }

    public void encode(ByteBuffer byteBuffer) {
        byteBuffer.putShort(this.standardVersion);
        byteBuffer.putInt(this.vendorExtensionId);
        byteBuffer.putInt(this.vendorExtensionVersion);
        writeString(byteBuffer, "");
        byteBuffer.putShort(this.functionalMode);
        writeU16Array(byteBuffer, new int[0]);
        writeU16Array(byteBuffer, new int[0]);
        writeU16Array(byteBuffer, new int[0]);
        writeU16Array(byteBuffer, new int[0]);
        writeU16Array(byteBuffer, new int[0]);
        writeString(byteBuffer, "");
        writeString(byteBuffer, "");
        writeString(byteBuffer, "");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DeviceInfo\nStandardVersion: ");
        sb.append((int) this.standardVersion).append("\nVendorExtensionId: ");
        sb.append(this.vendorExtensionId).append("\nVendorExtensionVersion: ");
        sb.append((int) this.vendorExtensionVersion).append("\nVendorExtensionDesc: ");
        sb.append(this.vendorExtensionDesc).append("\nFunctionalMode: ");
        sb.append((int) this.functionalMode).append('\n');
        appendU16Array(sb, "OperationsSupported", Constants.class, this.operationsSupported);
        appendU16Array(sb, "EventsSupported", Constants.class, this.eventsSupported);
        appendU16Array(sb, "DevicePropertiesSupported", Constants.class, this.devicePropertiesSupported);
        appendU16Array(sb, "CaptureFormats", Constants.class, this.captureFormats);
        appendU16Array(sb, "ImageFormats", Constants.class, this.imageFormats);
        sb.append("Manufacture: ").append(this.manufacture).append('\n');
        sb.append("Model: ").append(this.model).append('\n');
        sb.append("DeviceVersion: ").append(this.deviceVersion).append('\n');
        sb.append("SerialNumber: ").append(this.serialNumber).append('\n');
        return sb.toString();
    }

    private static void appendU16Array(StringBuilder sb, String str, Class<?> cls, int[] iArr) {
        Arrays.sort(iArr);
        sb.append(str).append(":\n");
        for (int i : iArr) {
            sb.append("    ").append(Constants.constantToString(cls, i)).append('\n');
        }
    }

    private String readString(ByteBuffer byteBuffer) {
        int i = byteBuffer.get() & UByte.MAX_VALUE;
        if (i <= 0) {
            return "";
        }
        int i2 = i - 1;
        char[] cArr = new char[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i3] = byteBuffer.getChar();
        }
        byteBuffer.getChar();
        return String.copyValueOf(cArr);
    }

    private void writeU16Array(ByteBuffer byteBuffer, int[] iArr) {
        byteBuffer.putInt(iArr.length);
        for (int i : iArr) {
            byteBuffer.putShort((short) i);
        }
    }

    private void writeString(ByteBuffer byteBuffer, String str) {
        byteBuffer.put((byte) str.length());
        if (str.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                byteBuffer.putShort((short) str.charAt(i));
            }
            byteBuffer.putShort((short) 0);
        }
    }

    private int[] readU16Array(ByteBuffer byteBuffer) {
        int i = byteBuffer.getInt();
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = byteBuffer.getShort() & UShort.MAX_VALUE;
        }
        return iArr;
    }
}
