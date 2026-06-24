package com.p020hp.jipp.encoding;

import com.p020hp.jipp.util.BuildError;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0003H\u0016J\b\u0010\u0010\u001a\u00020\u0005H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, m1293d2 = {"Lcom/hp/jipp/encoding/DelimiterTag;", "Lcom/hp/jipp/encoding/Tag;", "code", "", NamingTable.TAG, "", "(ILjava/lang/String;)V", "getCode", "()I", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class DelimiterTag extends Tag {
    private final int code;
    private final String name;

    public DelimiterTag(int i, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.code = i;
        this.name = name;
        if (!isDelimiter()) {
            throw new BuildError("Group tag " + this + " code must be in the delimiter range");
        }
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return Integer.hashCode(getCode());
    }

    public boolean equals(Object other) {
        if (other instanceof Tag) {
            return ((Tag) other).getCode() == getCode();
        }
        return super.equals(other);
    }

    @Override
    public String toString() {
        return getName();
    }
}
