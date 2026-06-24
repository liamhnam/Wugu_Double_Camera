package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.KeyValuesType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m1293d2 = {"Lcom/hp/jipp/model/KeyValuesTypes;", "", "()V", "all", "", "", "Lcom/hp/jipp/encoding/KeyValuesType;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class KeyValuesTypes {
    public static final KeyValuesTypes INSTANCE = new KeyValuesTypes();
    public static final Map<String, KeyValuesType> all;

    static {
        List<KeyValuesType.Set> listListOf = CollectionsKt.listOf((Object[]) new KeyValuesType.Set[]{Types.documentMetadata, Types.printerAlert, Types.printerFinisher, Types.printerFinisherSupplies, Types.printerInputTray, Types.printerOutputTray, Types.printerSupply});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        for (KeyValuesType.Set set : listListOf) {
            arrayList.add(TuplesKt.m1300to(set.getName(), set));
        }
        all = MapsKt.toMap(arrayList);
    }

    private KeyValuesTypes() {
    }
}
