package com.p020hp.jipp.encoding;

import com.p020hp.jipp.model.PrinterServiceType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0007J\b\u0010\u000f\u001a\u00020\u0004H\u0016J\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u001f\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\b\u0010\u0019\u001a\u00020\u0004H\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\t¨\u0006\u001a"}, m1293d2 = {"Lcom/hp/jipp/encoding/Name;", "Lcom/hp/jipp/encoding/TaggedValue;", "Lcom/hp/jipp/encoding/Stringable;", "value", "", "(Ljava/lang/String;)V", "lang", "(Ljava/lang/String;Ljava/lang/String;)V", "getLang", "()Ljava/lang/String;", "tag", "Lcom/hp/jipp/encoding/ValueTag;", "getTag", "()Lcom/hp/jipp/encoding/ValueTag;", "getValue", "asString", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class Name implements TaggedValue, Stringable {
    private final String lang;
    private final ValueTag tag;
    private final String value;

    public static Name copy$default(Name name, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = name.getValue();
        }
        if ((i & 2) != 0) {
            str2 = name.lang;
        }
        return name.copy(str, str2);
    }

    public final String component1() {
        return getValue();
    }

    public final String getLang() {
        return this.lang;
    }

    public final Name copy(String value, String lang) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new Name(value, lang);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Name)) {
            return false;
        }
        Name name = (Name) other;
        return Intrinsics.areEqual(getValue(), name.getValue()) && Intrinsics.areEqual(this.lang, name.lang);
    }

    public int hashCode() {
        String value = getValue();
        int iHashCode = (value != null ? value.hashCode() : 0) * 31;
        String str = this.lang;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public Name(String value, String str) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
        this.lang = str;
        this.tag = str == null ? Tag.nameWithoutLanguage : Tag.nameWithLanguage;
    }

    public final String getLang() {
        return this.lang;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public Name(String value) {
        this(value, null);
        Intrinsics.checkNotNullParameter(value, "value");
    }

    @Override
    public ValueTag getTag() {
        return this.tag;
    }

    @Override
    public String asString() {
        return getValue();
    }

    public String toString() {
        if (this.lang == null) {
            return "\"" + getValue() + "\" (name)";
        }
        return "\"" + getValue() + "\" (" + this.lang + " name)";
    }
}
