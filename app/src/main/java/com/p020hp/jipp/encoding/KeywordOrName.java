package com.p020hp.jipp.encoding;

import com.p020hp.jipp.model.PrinterServiceType;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u000f\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u0019\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\tJ\b\u0010\u0016\u001a\u00020\u0004H\u0016J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0004HÆ\u0003J!\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\b\u0010\u001f\u001a\u00020\u0004H\u0016R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006 "}, m1293d2 = {"Lcom/hp/jipp/encoding/KeywordOrName;", "Lcom/hp/jipp/encoding/TaggedValue;", "Lcom/hp/jipp/encoding/Stringable;", "keyword", "", "(Ljava/lang/String;)V", NamingTable.TAG, "Lcom/hp/jipp/encoding/Name;", "(Lcom/hp/jipp/encoding/Name;)V", "(Lcom/hp/jipp/encoding/Name;Ljava/lang/String;)V", "getKeyword", "()Ljava/lang/String;", "getName", "()Lcom/hp/jipp/encoding/Name;", "tag", "Lcom/hp/jipp/encoding/ValueTag;", "getTag", "()Lcom/hp/jipp/encoding/ValueTag;", "value", "", "getValue", "()Ljava/lang/Object;", "asString", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class KeywordOrName implements TaggedValue, Stringable {
    private final String keyword;
    private final Name name;
    private final ValueTag tag;
    private final Object value;

    public static KeywordOrName copy$default(KeywordOrName keywordOrName, Name name, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            name = keywordOrName.name;
        }
        if ((i & 2) != 0) {
            str = keywordOrName.keyword;
        }
        return keywordOrName.copy(name, str);
    }

    public final Name getName() {
        return this.name;
    }

    public final String getKeyword() {
        return this.keyword;
    }

    public final KeywordOrName copy(Name name, String keyword) {
        return new KeywordOrName(name, keyword);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KeywordOrName)) {
            return false;
        }
        KeywordOrName keywordOrName = (KeywordOrName) other;
        return Intrinsics.areEqual(this.name, keywordOrName.name) && Intrinsics.areEqual(this.keyword, keywordOrName.keyword);
    }

    public int hashCode() {
        Name name = this.name;
        int iHashCode = (name != null ? name.hashCode() : 0) * 31;
        String str = this.keyword;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public KeywordOrName(Name name, String str) {
        ValueTag tag;
        this.name = name;
        this.keyword = str;
        if ((name != null ? name : str) == null) {
            throw new IllegalArgumentException("both .name and .keyword are null");
        }
        if (name != null && str != null) {
            throw new IllegalArgumentException("both .name and .keyword are present");
        }
        this.tag = (name == null || (tag = name.getTag()) == null) ? Tag.keyword : tag;
        Object obj = name;
        if (name == null) {
            Intrinsics.checkNotNull(str);
            obj = str;
        }
        this.value = obj;
    }

    public final String getKeyword() {
        return this.keyword;
    }

    public final Name getName() {
        return this.name;
    }

    public KeywordOrName(String keyword) {
        this(null, keyword);
        Intrinsics.checkNotNullParameter(keyword, "keyword");
    }

    public KeywordOrName(Name name) {
        this(name, null);
        Intrinsics.checkNotNullParameter(name, "name");
    }

    @Override
    public ValueTag getTag() {
        return this.tag;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public String asString() {
        String strAsString;
        Name name = this.name;
        if (name != null && (strAsString = name.asString()) != null) {
            return strAsString;
        }
        String str = this.keyword;
        Intrinsics.checkNotNull(str);
        return str;
    }

    public String toString() {
        String string;
        Name name = this.name;
        if (name != null && (string = name.toString()) != null) {
            return string;
        }
        String str = this.keyword;
        Intrinsics.checkNotNull(str);
        return str;
    }
}
