package com.p020hp.printsdk;

import com.p020hp.jipp.encoding.AttributeGroup;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.IppOutputStream;
import com.p020hp.jipp.encoding.IppPacket;
import com.p020hp.jipp.encoding.MutableAttributeGroup;
import com.p020hp.jipp.model.JobState;
import com.p020hp.jipp.model.Status;
import com.p020hp.jipp.model.Types;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class C1740o0 {

    public static final List<String> f1552a;

    static {
        List listListOf = CollectionsKt.listOf((Object[]) new AttributeType[]{Types.printerState, Types.printerStateMessage, Types.printerStateReasons});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        Iterator it = listListOf.iterator();
        while (it.hasNext()) {
            arrayList.add(((AttributeType) it.next()).getName());
        }
        f1552a = arrayList;
    }

    public static final int m596a(Status.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return 1000;
    }

    public static final AttributeGroup m597a(AttributeGroup attributeGroup, AttributeGroup attributeGroup2) {
        if (attributeGroup == null) {
            return attributeGroup2;
        }
        if (attributeGroup2 == null) {
            return attributeGroup;
        }
        MutableAttributeGroup mutable = attributeGroup.toMutable();
        mutable.putAll(attributeGroup2);
        return mutable;
    }

    public static final InputStream m598a(IppPacket ippPacket) {
        Intrinsics.checkNotNullParameter(ippPacket, "<this>");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new IppOutputStream(byteArrayOutputStream).write(ippPacket);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    public static final String m599a(UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "<this>");
        String string = uuid.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString()");
        return StringsKt.takeLast(string, 12);
    }

    public static final URI m600a(String str) {
        Object objM1772constructorimpl;
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            objM1772constructorimpl = Result.m1772constructorimpl(new URI(str));
        } catch (Throwable th) {
            objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1778isFailureimpl(objM1772constructorimpl)) {
            objM1772constructorimpl = null;
        }
        return (URI) objM1772constructorimpl;
    }

    public static final UUID m601a(URI uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        String string = uri.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString()");
        return m603b(string);
    }

    public static final boolean m602a(JobState jobState) {
        Intrinsics.checkNotNullParameter(jobState, "<this>");
        if (Intrinsics.areEqual(jobState, JobState.completed) ? true : Intrinsics.areEqual(jobState, JobState.canceled)) {
            return true;
        }
        return Intrinsics.areEqual(jobState, JobState.aborted);
    }

    public static final UUID m603b(String str) {
        Object objM1772constructorimpl;
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            objM1772constructorimpl = Result.m1772constructorimpl(UUID.fromString(StringsKt.removePrefix(str, (CharSequence) "urn:uuid:")));
        } catch (Throwable th) {
            objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1778isFailureimpl(objM1772constructorimpl)) {
            objM1772constructorimpl = null;
        }
        return (UUID) objM1772constructorimpl;
    }
}
