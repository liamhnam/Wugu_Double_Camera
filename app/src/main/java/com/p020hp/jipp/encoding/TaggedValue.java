package com.p020hp.jipp.encoding;

import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, m1293d2 = {"Lcom/hp/jipp/encoding/TaggedValue;", "", "tag", "Lcom/hp/jipp/encoding/ValueTag;", "getTag", "()Lcom/hp/jipp/encoding/ValueTag;", "value", "getValue", "()Ljava/lang/Object;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public interface TaggedValue {
    ValueTag getTag();

    Object getValue();
}
