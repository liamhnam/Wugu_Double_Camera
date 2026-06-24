package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Enum;
import com.p020hp.jipp.encoding.EnumType;
import com.p020hp.jipp.model.MediaCol;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00060\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/model/EnumTypes;", "", "()V", "all", "", "", "Lcom/hp/jipp/encoding/EnumType;", "Lcom/hp/jipp/encoding/Enum;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class EnumTypes {
    public static final EnumTypes INSTANCE = new EnumTypes();
    public static final Map<String, EnumType<? extends Enum>> all;

    static {
        List<EnumType> listListOf = CollectionsKt.listOf((Object[]) new EnumType[]{Types.documentState, Types.outputDeviceDocumentState, Types.finishings, Types.finishingsActual, Types.finishingsDefault, Types.finishingsReady, Types.finishingsSupported, Types.jobState, Types.outputDeviceJobState, Types.outputDeviceJobStates, Types.operationsSupported, MediaCol.MediaSourceProperties.mediaSourceFeedOrientation, InputAttributes.inputOrientationRequested, Types.inputOrientationRequestedSupported, Types.orientationRequested, Types.orientationRequestedActual, Types.orientationRequestedDefault, Types.orientationRequestedSupported, InputAttributes.inputQuality, Types.inputQualitySupported, Types.printQuality, Types.printQualityActual, Types.printQualityDefault, Types.printQualitySupported, SystemConfiguredPrinters.printerState, Types.printerState, SystemConfiguredResources.resourceState, Types.resourceState, Types.resourceStates, Types.systemState, DestinationStatuses.transmissionStatus, Types.fetchStatusCode, Types.notifyStatusCode});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        for (EnumType enumType : listListOf) {
            arrayList.add(TuplesKt.m1300to(enumType.getName(), enumType));
        }
        all = MapsKt.toMap(arrayList);
    }

    private EnumTypes() {
    }
}
