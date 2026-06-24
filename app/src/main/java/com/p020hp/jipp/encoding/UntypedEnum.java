package com.p020hp.jipp.encoding;

import com.p020hp.jipp.model.PrinterServiceType;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0012\u001a\u00020\bH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, m1293d2 = {"Lcom/hp/jipp/encoding/UntypedEnum;", "Lcom/hp/jipp/encoding/Enum;", "code", "", "(I)V", "getCode", "()I", NamingTable.TAG, "", "getName", "()Ljava/lang/String;", "component1", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class UntypedEnum extends Enum {
    private final int code;
    private final String name = "???";

    public static UntypedEnum copy$default(UntypedEnum untypedEnum, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = untypedEnum.getCode();
        }
        return untypedEnum.copy(i);
    }

    public final int component1() {
        return getCode();
    }

    public final UntypedEnum copy(int code) {
        return new UntypedEnum(code);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof UntypedEnum) && getCode() == ((UntypedEnum) other).getCode();
        }
        return true;
    }

    public int hashCode() {
        return Integer.hashCode(getCode());
    }

    public UntypedEnum(int i) {
        this.code = i;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "enum(" + getCode() + ')';
    }
}
