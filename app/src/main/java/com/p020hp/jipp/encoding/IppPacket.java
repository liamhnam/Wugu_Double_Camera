package com.p020hp.jipp.encoding;

import androidx.core.app.NotificationCompat;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.model.JobAccountType;
import com.p020hp.jipp.model.JobState;
import com.p020hp.jipp.model.Operation;
import com.p020hp.jipp.model.PrinterServiceType;
import com.p020hp.jipp.model.Status;
import com.p020hp.jipp.model.Types;
import com.p020hp.jipp.util.PrettyPrinter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 B2\u00020\u0001:\u0002ABB5\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b¢\u0006\u0002\u0010\tB+\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b¢\u0006\u0002\u0010\fB+\b\u0016\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b¢\u0006\u0002\u0010\u000fB/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011¢\u0006\u0002\u0010\u0012J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\b0\u0011HÆ\u0003J7\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011HÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0013\u0010(\u001a\u0004\u0018\u00010\b2\u0006\u0010)\u001a\u00020*H\u0086\u0002J(\u0010+\u001a\u0004\u0018\u00010,\"\b\b\u0000\u0010-*\u00020\u00012\u0006\u0010)\u001a\u00020*2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H-0/J,\u00100\u001a\b\u0012\u0004\u0012\u00020,0\u0011\"\b\b\u0000\u0010-*\u00020\u00012\u0006\u0010)\u001a\u00020*2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H-01J-\u00102\u001a\u0004\u0018\u0001H-\"\b\b\u0000\u0010-*\u00020\u00012\u0006\u0010)\u001a\u00020*2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H-0/¢\u0006\u0002\u00103J,\u00104\u001a\b\u0012\u0004\u0012\u0002H-0\u0011\"\b\b\u0000\u0010-*\u00020\u00012\u0006\u0010)\u001a\u00020*2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H-01J\t\u00105\u001a\u00020\u0003HÖ\u0001J\b\u00106\u001a\u00020,H\u0002J\u0016\u00107\u001a\u00020,2\u0006\u00108\u001a\u00020\u00032\u0006\u00109\u001a\u00020,J\u0010\u0010:\u001a\u00020,2\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010;\u001a\u00020,H\u0016J\u0014\u0010<\u001a\u00020\u00002\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011J\u0010\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@H\u0007R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\r\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u001b\u0010\n\u001a\u00020\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001a\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016¨\u0006C"}, m1293d2 = {"Lcom/hp/jipp/encoding/IppPacket;", "", "versionNumber", "", "code", "requestId", "groups", "", "Lcom/hp/jipp/encoding/AttributeGroup;", "(III[Lcom/hp/jipp/encoding/AttributeGroup;)V", NotificationCompat.CATEGORY_STATUS, "Lcom/hp/jipp/model/Status;", "(Lcom/hp/jipp/model/Status;I[Lcom/hp/jipp/encoding/AttributeGroup;)V", "operation", "Lcom/hp/jipp/model/Operation;", "(Lcom/hp/jipp/model/Operation;I[Lcom/hp/jipp/encoding/AttributeGroup;)V", "attributeGroups", "", "(IIILjava/util/List;)V", "getAttributeGroups", "()Ljava/util/List;", "getCode", "()I", "getOperation", "()Lcom/hp/jipp/model/Operation;", "operation$delegate", "Lkotlin/Lazy;", "getRequestId", "getStatus", "()Lcom/hp/jipp/model/Status;", "status$delegate", "getVersionNumber", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "equals", "", "other", "get", "groupDelimiter", "Lcom/hp/jipp/encoding/Tag;", "getString", "", "T", "type", "Lcom/hp/jipp/encoding/AttributeType;", "getStrings", "Lcom/hp/jipp/encoding/AttributeSetType;", "getValue", "(Lcom/hp/jipp/encoding/Tag;Lcom/hp/jipp/encoding/AttributeType;)Ljava/lang/Object;", "getValues", "hashCode", "prefix", "prettyPrint", "maxWidth", "indent", "statusOrOperationString", "toString", "withAttributeGroups", "write", "", "output", "Ljava/io/OutputStream;", "Builder", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class IppPacket {

    public static final Companion INSTANCE = new Companion(null);
    public static final String DEFAULT_CHARSET = "utf-8";
    public static final String DEFAULT_LANGUAGE = "en-us";
    public static final int DEFAULT_REQUEST_ID = 1;
    public static final int DEFAULT_VERSION_NUMBER = 512;
    private final List<AttributeGroup> attributeGroups;
    private final int code;

    private final Lazy operation;
    private final int requestId;

    private final Lazy status;
    private final int versionNumber;

    public IppPacket(int i, int i2, AttributeGroup... attributeGroupArr) {
        this(0, i, i2, attributeGroupArr, 1, (DefaultConstructorMarker) null);
    }

    @JvmStatic
    public static final Builder cancelJob(URI uri) {
        return INSTANCE.cancelJob(uri);
    }

    @JvmStatic
    public static final Builder cancelJob(URI uri, int i) {
        return INSTANCE.cancelJob(uri, i);
    }

    public static IppPacket copy$default(IppPacket ippPacket, int i, int i2, int i3, List list, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = ippPacket.versionNumber;
        }
        if ((i4 & 2) != 0) {
            i2 = ippPacket.code;
        }
        if ((i4 & 4) != 0) {
            i3 = ippPacket.requestId;
        }
        if ((i4 & 8) != 0) {
            list = ippPacket.attributeGroups;
        }
        return ippPacket.copy(i, i2, i3, list);
    }

    @JvmStatic
    public static final Builder createJob(URI uri) {
        return INSTANCE.createJob(uri);
    }

    @JvmStatic
    public static final Builder getJobAttributes(URI uri, int i, AttributeType<?>... attributeTypeArr) {
        return INSTANCE.getJobAttributes(uri, i, attributeTypeArr);
    }

    @JvmStatic
    public static final Builder getJobAttributes(URI uri, AttributeType<?>... attributeTypeArr) {
        return INSTANCE.getJobAttributes(uri, attributeTypeArr);
    }

    @JvmStatic
    public static final Builder getJobs(URI uri, AttributeType<?>... attributeTypeArr) {
        return INSTANCE.getJobs(uri, attributeTypeArr);
    }

    @JvmStatic
    public static final Builder getPrinterAttributes(URI uri, Iterable<? extends AttributeType<?>> iterable) {
        return INSTANCE.getPrinterAttributes(uri, iterable);
    }

    @JvmStatic
    public static final Builder getPrinterAttributes(URI uri, AttributeType<?>... attributeTypeArr) {
        return INSTANCE.getPrinterAttributes(uri, attributeTypeArr);
    }

    @JvmStatic
    public static final Builder jobResponse(Status status, int i, URI uri, JobState jobState) {
        return Companion.jobResponse$default(INSTANCE, status, i, uri, jobState, null, 16, null);
    }

    @JvmStatic
    public static final Builder jobResponse(Status status, int i, URI uri, JobState jobState, List<String> list) {
        return INSTANCE.jobResponse(status, i, uri, jobState, list);
    }

    @Deprecated(message = "use IppInputStream.readPacket()", replaceWith = @ReplaceWith(expression = "readPacket()", imports = {"com.hp.jipp.encoding.IppInputStream"}))
    @JvmStatic
    public static final IppPacket parse(InputStream inputStream) throws IOException {
        return INSTANCE.parse(inputStream);
    }

    @JvmStatic
    public static final Builder printJob(URI uri) {
        return INSTANCE.printJob(uri);
    }

    @Deprecated(message = "use IppInputStream.readPacket()", replaceWith = @ReplaceWith(expression = "readPacket()", imports = {"com.hp.jipp.encoding.IppInputStream"}))
    @JvmStatic
    public static final IppPacket read(InputStream inputStream) throws IOException {
        return INSTANCE.read(inputStream);
    }

    @JvmStatic
    public static final Builder response(Status status) {
        return INSTANCE.response(status);
    }

    @JvmStatic
    public static final Builder sendDocument(URI uri, int i) {
        return INSTANCE.sendDocument(uri, i);
    }

    @JvmStatic
    public static final Builder sendDocument(URI uri, AttributeType<?>... attributeTypeArr) {
        return INSTANCE.sendDocument(uri, attributeTypeArr);
    }

    @JvmStatic
    public static final Builder validateJob(URI uri) {
        return INSTANCE.validateJob(uri);
    }

    public final int getVersionNumber() {
        return this.versionNumber;
    }

    public final int getCode() {
        return this.code;
    }

    public final int getRequestId() {
        return this.requestId;
    }

    public final List<AttributeGroup> component4() {
        return this.attributeGroups;
    }

    public final IppPacket copy(int versionNumber, int code, int requestId, List<? extends AttributeGroup> attributeGroups) {
        Intrinsics.checkNotNullParameter(attributeGroups, "attributeGroups");
        return new IppPacket(versionNumber, code, requestId, attributeGroups);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IppPacket)) {
            return false;
        }
        IppPacket ippPacket = (IppPacket) other;
        return this.versionNumber == ippPacket.versionNumber && this.code == ippPacket.code && this.requestId == ippPacket.requestId && Intrinsics.areEqual(this.attributeGroups, ippPacket.attributeGroups);
    }

    public final Operation getOperation() {
        return (Operation) this.operation.getValue();
    }

    public final Status getStatus() {
        return (Status) this.status.getValue();
    }

    public int hashCode() {
        int iHashCode = ((((Integer.hashCode(this.versionNumber) * 31) + Integer.hashCode(this.code)) * 31) + Integer.hashCode(this.requestId)) * 31;
        List<AttributeGroup> list = this.attributeGroups;
        return iHashCode + (list != null ? list.hashCode() : 0);
    }

    public IppPacket(int i, int i2, int i3, List<? extends AttributeGroup> attributeGroups) {
        Intrinsics.checkNotNullParameter(attributeGroups, "attributeGroups");
        this.versionNumber = i;
        this.code = i2;
        this.requestId = i3;
        this.attributeGroups = attributeGroups;
        this.status = LazyKt.lazy(new Function0<Status>() {
            {
                super(0);
            }

            @Override
            public final Status invoke() {
                return Status.INSTANCE.get(this.this$0.getCode());
            }
        });
        this.operation = LazyKt.lazy(new Function0<Operation>() {
            {
                super(0);
            }

            @Override
            public final Operation invoke() {
                return Operation.INSTANCE.get(this.this$0.getCode());
            }
        });
    }

    public final int getVersionNumber() {
        return this.versionNumber;
    }

    public final int getCode() {
        return this.code;
    }

    public final int getRequestId() {
        return this.requestId;
    }

    public IppPacket(int i, int i2, int i3, List list, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 512 : i, i2, i3, (List<? extends AttributeGroup>) ((i4 & 8) != 0 ? CollectionsKt.emptyList() : list));
    }

    public final List<AttributeGroup> getAttributeGroups() {
        return this.attributeGroups;
    }

    public IppPacket(int i, int i2, int i3, AttributeGroup[] attributeGroupArr, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 512 : i, i2, i3, attributeGroupArr);
    }

    public IppPacket(int i, int i2, int i3, AttributeGroup... groups) {
        this(i, i2, i3, (List<? extends AttributeGroup>) ArraysKt.toList(groups));
        Intrinsics.checkNotNullParameter(groups, "groups");
    }

    public IppPacket(Status status, int i, AttributeGroup... groups) {
        this(0, status.getCode(), i, ArraysKt.toList(groups), 1, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(groups, "groups");
    }

    public IppPacket(Operation operation, int i, AttributeGroup... groups) {
        this(0, operation.getCode(), i, ArraysKt.toList(groups), 1, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(operation, "operation");
        Intrinsics.checkNotNullParameter(groups, "groups");
    }

    public final AttributeGroup get(Tag groupDelimiter) {
        Object next;
        Intrinsics.checkNotNullParameter(groupDelimiter, "groupDelimiter");
        Iterator<T> it = this.attributeGroups.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((AttributeGroup) next).getTag(), groupDelimiter)) {
                break;
            }
        }
        return (AttributeGroup) next;
    }

    public final <T> List<T> getValues(Tag groupDelimiter, AttributeSetType<T> type) {
        List<T> values;
        Intrinsics.checkNotNullParameter(groupDelimiter, "groupDelimiter");
        Intrinsics.checkNotNullParameter(type, "type");
        AttributeGroup attributeGroup = get(groupDelimiter);
        return (attributeGroup == null || (values = attributeGroup.getValues(type)) == null) ? CollectionsKt.emptyList() : values;
    }

    public final <T> T getValue(Tag groupDelimiter, AttributeType<T> type) {
        Attribute<T> attribute;
        Intrinsics.checkNotNullParameter(groupDelimiter, "groupDelimiter");
        Intrinsics.checkNotNullParameter(type, "type");
        AttributeGroup attributeGroup = get(groupDelimiter);
        if (attributeGroup == null || (attribute = attributeGroup.get(type)) == null) {
            return null;
        }
        return attribute.get(0);
    }

    public final <T> List<String> getStrings(Tag groupDelimiter, AttributeSetType<T> type) {
        List<String> strings;
        Intrinsics.checkNotNullParameter(groupDelimiter, "groupDelimiter");
        Intrinsics.checkNotNullParameter(type, "type");
        AttributeGroup attributeGroup = get(groupDelimiter);
        return (attributeGroup == null || (strings = attributeGroup.getStrings(type)) == null) ? CollectionsKt.emptyList() : strings;
    }

    public final <T> String getString(Tag groupDelimiter, AttributeType<T> type) {
        Intrinsics.checkNotNullParameter(groupDelimiter, "groupDelimiter");
        Intrinsics.checkNotNullParameter(type, "type");
        AttributeGroup attributeGroup = get(groupDelimiter);
        if (attributeGroup != null) {
            return attributeGroup.getString(type);
        }
        return null;
    }

    public final IppPacket withAttributeGroups(List<? extends AttributeGroup> attributeGroups) {
        Intrinsics.checkNotNullParameter(attributeGroups, "attributeGroups");
        return copy$default(this, 0, 0, 0, attributeGroups, 7, null);
    }

    @Deprecated(message = "use IppInputStream.write()", replaceWith = @ReplaceWith(expression = "write()", imports = {"com.hp.jipp.encoding.IppInputStream"}))
    public final void write(OutputStream output) throws IOException {
        Intrinsics.checkNotNullParameter(output, "output");
        new IppOutputStream(output).write(this);
    }

    public final String prettyPrint(int maxWidth, String indent) {
        Intrinsics.checkNotNullParameter(indent, "indent");
        return new PrettyPrinter(prefix(), PrettyPrinter.OBJECT, indent, maxWidth).addAll(this.attributeGroups).print();
    }

    private final String prefix() {
        return "IppPacket(v=0x" + Integer.toHexString(this.versionNumber) + ", c=" + statusOrOperationString(this.code) + ", r=0x" + Integer.toHexString(this.requestId) + ")";
    }

    private final String statusOrOperationString(int code) {
        Operation operation = Operation.all.get(Integer.valueOf(code));
        if (operation == null) {
            operation = Status.all.get(Integer.valueOf(code));
        }
        Object objValueOf = (Enum) operation;
        if (objValueOf == null) {
            objValueOf = Integer.valueOf(code);
        }
        return objValueOf.toString();
    }

    public String toString() {
        return prefix() + " " + this.attributeGroups;
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0006\u0018\u00002\u00020\u0001B#\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007B#\b\u0017\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\nB#\b\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ\u000e\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,JQ\u0010-\u001a\u00020\u00002\u0006\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u000e\b\u0002\u00103\u001a\b\u0012\u0004\u0012\u000205042\u001a\u00106\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030807\"\u0006\u0012\u0002\b\u000308H\u0007¢\u0006\u0002\u00109J\u0006\u0010:\u001a\u00020;J)\u0010<\u001a\u00020\u00132\u0006\u0010=\u001a\u00020>2\u0017\u0010?\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020A0@¢\u0006\u0002\bBH\u0007J\u000e\u0010+\u001a\u00020\u00132\u0006\u0010=\u001a\u00020>J/\u0010C\u001a\u00020\u00002\u0006\u0010=\u001a\u00020>2\u001a\u00106\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030807\"\u0006\u0012\u0002\b\u000308¢\u0006\u0002\u0010DJ \u0010C\u001a\u00020\u00002\u0006\u0010=\u001a\u00020>2\u0010\u00106\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003080EJ'\u0010F\u001a\u00020\u00002\u001a\u00106\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030807\"\u0006\u0012\u0002\b\u000308¢\u0006\u0002\u0010GJ\u0018\u0010F\u001a\u00020\u00002\u0010\u00106\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003080EJ'\u0010H\u001a\u00020\u00002\u001a\u00106\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030807\"\u0006\u0012\u0002\b\u000308¢\u0006\u0002\u0010GJ\u0018\u0010H\u001a\u00020\u00002\u0010\u00106\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003080EJ'\u0010I\u001a\u00020\u00002\u001a\u00106\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030807\"\u0006\u0012\u0002\b\u000308¢\u0006\u0002\u0010GJ\u0018\u0010I\u001a\u00020\u00002\u0010\u00106\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003080EJ'\u0010J\u001a\u00020\u00002\u001a\u00106\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030807\"\u0006\u0012\u0002\b\u000308¢\u0006\u0002\u0010GJ\u0018\u0010J\u001a\u00020\u00002\u0010\u00106\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003080EJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010$\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010)\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005R\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R$\u0010\b\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0016R\u0011\u0010\u001e\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u000e\"\u0004\b!\u0010\u0010R$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b'\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u000e\"\u0004\b)\u0010\u0010¨\u0006K"}, m1293d2 = {"Lcom/hp/jipp/encoding/IppPacket$Builder;", "", NotificationCompat.CATEGORY_STATUS, "Lcom/hp/jipp/model/Status;", "versionNumber", "", "requestId", "(Lcom/hp/jipp/model/Status;II)V", "operation", "Lcom/hp/jipp/model/Operation;", "(Lcom/hp/jipp/model/Operation;II)V", "code", "(III)V", "getCode", "()I", "setCode", "(I)V", "groups", "", "Lcom/hp/jipp/encoding/MutableAttributeGroup;", "jobAttributes", "getJobAttributes", "()Lcom/hp/jipp/encoding/MutableAttributeGroup;", "value", "getOperation", "()Lcom/hp/jipp/model/Operation;", "setOperation", "(Lcom/hp/jipp/model/Operation;)V", "operationAttributes", "getOperationAttributes", "printerAttributes", "getPrinterAttributes", "getRequestId", "setRequestId", "getStatus", "()Lcom/hp/jipp/model/Status;", "setStatus", "(Lcom/hp/jipp/model/Status;)V", "unsupportedAttributes", "getUnsupportedAttributes", "getVersionNumber", "setVersionNumber", "addGroup", JobAccountType.group, "Lcom/hp/jipp/encoding/AttributeGroup;", "addJobAttributesGroup", "jobId", "jobUri", "Ljava/net/URI;", "jobState", "Lcom/hp/jipp/model/JobState;", "jobStateReasons", "", "", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "(ILjava/net/URI;Lcom/hp/jipp/model/JobState;Ljava/util/List;[Lcom/hp/jipp/encoding/Attribute;)Lcom/hp/jipp/encoding/IppPacket$Builder;", "build", "Lcom/hp/jipp/encoding/IppPacket;", "extend", "tag", "Lcom/hp/jipp/encoding/DelimiterTag;", "func", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "putAttributes", "(Lcom/hp/jipp/encoding/DelimiterTag;[Lcom/hp/jipp/encoding/Attribute;)Lcom/hp/jipp/encoding/IppPacket$Builder;", "", "putJobAttributes", "([Lcom/hp/jipp/encoding/Attribute;)Lcom/hp/jipp/encoding/IppPacket$Builder;", "putOperationAttributes", "putPrinterAttributes", "putUnsupportedAttributes", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Builder {
        private int code;
        private final List<MutableAttributeGroup> groups;
        private int requestId;
        private int versionNumber;

        public Builder(int i) {
            this(i, 0, 0, 6, (DefaultConstructorMarker) null);
        }

        public Builder(int i, int i2) {
            this(i, i2, 0, 4, (DefaultConstructorMarker) null);
        }

        public Builder(Operation operation) {
            this(operation, 0, 0, 6, (DefaultConstructorMarker) null);
        }

        public Builder(Operation operation, int i) {
            this(operation, i, 0, 4, (DefaultConstructorMarker) null);
        }

        public Builder(Status status) {
            this(status, 0, 0, 6, (DefaultConstructorMarker) null);
        }

        public Builder(Status status, int i) {
            this(status, i, 0, 4, (DefaultConstructorMarker) null);
        }

        public final Builder addJobAttributesGroup(int i, URI uri, JobState jobState, Attribute<?>... attributeArr) {
            return addJobAttributesGroup$default(this, i, uri, jobState, null, attributeArr, 8, null);
        }

        public Builder(int i, int i2, int i3) {
            this.code = i;
            this.versionNumber = i2;
            this.requestId = i3;
            this.groups = new ArrayList();
            putOperationAttributes(Types.attributesCharset.mo418of(IppPacket.DEFAULT_CHARSET), Types.attributesNaturalLanguage.mo418of(IppPacket.DEFAULT_LANGUAGE));
        }

        public final int getCode() {
            return this.code;
        }

        public final void m1564setCode(int i) {
            this.code = i;
        }

        public final int getVersionNumber() {
            return this.versionNumber;
        }

        public final void m1568setVersionNumber(int i) {
            this.versionNumber = i;
        }

        public Builder(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, (i4 & 2) != 0 ? 512 : i2, (i4 & 4) != 0 ? 1 : i3);
        }

        public final int getRequestId() {
            return this.requestId;
        }

        public final void m1566setRequestId(int i) {
            this.requestId = i;
        }

        public Builder(Status status, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(status, (i3 & 2) != 0 ? 512 : i, (i3 & 4) != 0 ? 1 : i2);
        }

        public Builder(Status status, int i, int i2) {
            this(status.getCode(), i, i2);
            Intrinsics.checkNotNullParameter(status, "status");
        }

        public Builder(Operation operation, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(operation, (i3 & 2) != 0 ? 512 : i, (i3 & 4) != 0 ? 1 : i2);
        }

        public Builder(Operation operation, int i, int i2) {
            this(operation.getCode(), i, i2);
            Intrinsics.checkNotNullParameter(operation, "operation");
        }

        public final Status getStatus() {
            return Status.INSTANCE.get(this.code);
        }

        public final void m1567setStatus(Status value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this.code = value.getCode();
        }

        public final Builder setStatus(Status status) {
            Intrinsics.checkNotNullParameter(status, "status");
            m1567setStatus(status);
            return this;
        }

        public final Operation getOperation() {
            return Operation.INSTANCE.get(this.code);
        }

        public final void m1565setOperation(Operation value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this.code = value.getCode();
        }

        public final Builder setOperation(Operation operation) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            m1565setOperation(operation);
            return this;
        }

        public final Builder setVersionNumber(int versionNumber) {
            this.versionNumber = versionNumber;
            return this;
        }

        public final Builder setRequestId(int requestId) {
            this.requestId = requestId;
            return this;
        }

        public final Builder setCode(int code) {
            this.code = code;
            return this;
        }

        public final Builder addGroup(AttributeGroup group) {
            Intrinsics.checkNotNullParameter(group, "group");
            this.groups.add(group.toMutable());
            return this;
        }

        public final MutableAttributeGroup group(DelimiterTag tag) {
            List list;
            MutableAttributeGroup mutableAttributeGroupPrevious;
            Object[] objArr;
            Intrinsics.checkNotNullParameter(tag, "tag");
            List<MutableAttributeGroup> list2 = this.groups;
            ListIterator<MutableAttributeGroup> listIterator = list2.listIterator(list2.size());
            while (true) {
                list = null;
                objArr = 0;
                if (!listIterator.hasPrevious()) {
                    mutableAttributeGroupPrevious = null;
                    break;
                }
                mutableAttributeGroupPrevious = listIterator.previous();
                if (Intrinsics.areEqual(mutableAttributeGroupPrevious.getTag(), tag)) {
                    break;
                }
            }
            MutableAttributeGroup mutableAttributeGroup = mutableAttributeGroupPrevious;
            if (mutableAttributeGroup != null) {
                return mutableAttributeGroup;
            }
            MutableAttributeGroup mutableAttributeGroup2 = new MutableAttributeGroup(tag, list, 2, objArr == true ? 1 : 0);
            this.groups.add(mutableAttributeGroup2);
            return mutableAttributeGroup2;
        }

        public final MutableAttributeGroup getOperationAttributes() {
            return group(Tag.operationAttributes);
        }

        public final MutableAttributeGroup getPrinterAttributes() {
            return group(Tag.printerAttributes);
        }

        public final MutableAttributeGroup getJobAttributes() {
            return group(Tag.jobAttributes);
        }

        public final MutableAttributeGroup getUnsupportedAttributes() {
            return group(Tag.unsupportedAttributes);
        }

        @Deprecated(message = "Use .group", replaceWith = @ReplaceWith(expression = "group(tag, func)", imports = {}))
        public final MutableAttributeGroup extend(DelimiterTag tag, Function1<? super MutableAttributeGroup, Unit> func) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(func, "func");
            MutableAttributeGroup mutableAttributeGroupGroup = group(tag);
            func.invoke(mutableAttributeGroupGroup);
            return mutableAttributeGroupGroup;
        }

        public final Builder putAttributes(DelimiterTag tag, Iterable<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            group(tag).plusAssign(attributes);
            return this;
        }

        public final Builder putAttributes(DelimiterTag tag, Attribute<?>... attributes) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return putAttributes(tag, ArraysKt.toList(attributes));
        }

        public final Builder putOperationAttributes(Iterable<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return putAttributes(Tag.operationAttributes, attributes);
        }

        public final Builder putOperationAttributes(Attribute<?>... attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return putOperationAttributes(ArraysKt.toList(attributes));
        }

        public final Builder putJobAttributes(Iterable<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return putAttributes(Tag.jobAttributes, attributes);
        }

        public final Builder putJobAttributes(Attribute<?>... attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return putJobAttributes(ArraysKt.toList(attributes));
        }

        public final Builder putPrinterAttributes(Iterable<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return putAttributes(Tag.printerAttributes, attributes);
        }

        public final Builder putPrinterAttributes(Attribute<?>... attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return putPrinterAttributes(ArraysKt.toList(attributes));
        }

        public final Builder putUnsupportedAttributes(Iterable<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return putAttributes(Tag.unsupportedAttributes, attributes);
        }

        public final Builder putUnsupportedAttributes(Attribute<?>... attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return putUnsupportedAttributes(ArraysKt.toList(attributes));
        }

        public static Builder addJobAttributesGroup$default(Builder builder, int i, URI uri, JobState jobState, List list, Attribute[] attributeArr, int i2, Object obj) {
            if ((i2 & 8) != 0) {
                list = CollectionsKt.listOf("none");
            }
            return builder.addJobAttributesGroup(i, uri, jobState, list, attributeArr);
        }

        public final Builder addJobAttributesGroup(int jobId, URI jobUri, JobState jobState, List<String> jobStateReasons, Attribute<?>... attributes) {
            Intrinsics.checkNotNullParameter(jobUri, "jobUri");
            Intrinsics.checkNotNullParameter(jobState, "jobState");
            Intrinsics.checkNotNullParameter(jobStateReasons, "jobStateReasons");
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            addGroup(new MutableAttributeGroup(Tag.jobAttributes, CollectionsKt.plus((Collection) CollectionsKt.listOf((Object[]) new Attribute[]{Types.jobId.mo418of(Integer.valueOf(jobId)), Types.jobUri.mo418of(jobUri), Types.jobState.mo418of((Object) jobState), Types.jobStateReasons.mo417of((Iterable<? extends String>) jobStateReasons)}), (Iterable) ArraysKt.toList(attributes))));
            return this;
        }

        public final IppPacket build() {
            int i = this.versionNumber;
            int i2 = this.code;
            int i3 = this.requestId;
            List<MutableAttributeGroup> list = this.groups;
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                MutableAttributeGroup mutableAttributeGroup = (MutableAttributeGroup) obj;
                if (!((Intrinsics.areEqual(mutableAttributeGroup.getTag(), Tag.unsupportedAttributes) || Intrinsics.areEqual(mutableAttributeGroup.getTag(), Tag.jobAttributes)) && mutableAttributeGroup.isEmpty())) {
                    arrayList.add(obj);
                }
            }
            return new IppPacket(i, i2, i3, arrayList);
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0007H\u0007J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0007J1\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u001a\u0010\u0011\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012\"\u0006\u0012\u0002\b\u00030\u0013H\u0007¢\u0006\u0002\u0010\u0014J9\u0010\u0010\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00072\u001a\u0010\u0011\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012\"\u0006\u0012\u0002\b\u00030\u0013H\u0007¢\u0006\u0002\u0010\u0015J1\u0010\u0016\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u001a\u0010\u0011\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012\"\u0006\u0012\u0002\b\u00030\u0013H\u0007¢\u0006\u0002\u0010\u0014J1\u0010\u0017\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u001a\u0010\u0011\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012\"\u0006\u0012\u0002\b\u00030\u0013H\u0007¢\u0006\u0002\u0010\u0014J\"\u0010\u0017\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u0018H\u0007J8\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001d2\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u001fH\u0007J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0007J\u0010\u0010$\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0007J\u0010\u0010%\u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0007J\u0010\u0010&\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J1\u0010'\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u001a\u0010\u0011\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012\"\u0006\u0012\u0002\b\u00030\u0013H\u0007¢\u0006\u0002\u0010\u0014J\u0018\u0010'\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0007H\u0007J\u0010\u0010(\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0007J\u001e\u0010)\u001a\u00020\n*\u00020\n2\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u001fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006*"}, m1293d2 = {"Lcom/hp/jipp/encoding/IppPacket$Companion;", "", "()V", "DEFAULT_CHARSET", "", "DEFAULT_LANGUAGE", "DEFAULT_REQUEST_ID", "", "DEFAULT_VERSION_NUMBER", "cancelJob", "Lcom/hp/jipp/encoding/IppPacket$Builder;", "jobUri", "Ljava/net/URI;", "printerUri", "jobId", "createJob", "getJobAttributes", "types", "", "Lcom/hp/jipp/encoding/AttributeType;", "(Ljava/net/URI;[Lcom/hp/jipp/encoding/AttributeType;)Lcom/hp/jipp/encoding/IppPacket$Builder;", "(Ljava/net/URI;I[Lcom/hp/jipp/encoding/AttributeType;)Lcom/hp/jipp/encoding/IppPacket$Builder;", "getJobs", "getPrinterAttributes", "", "jobResponse", NotificationCompat.CATEGORY_STATUS, "Lcom/hp/jipp/model/Status;", "jobState", "Lcom/hp/jipp/model/JobState;", "jobStateReasons", "", "parse", "Lcom/hp/jipp/encoding/IppPacket;", "input", "Ljava/io/InputStream;", "printJob", "read", "response", "sendDocument", "validateJob", "putRequestedAttributes", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        @JvmStatic
        public final Builder jobResponse(Status status, int i, URI uri, JobState jobState) {
            return jobResponse$default(this, status, i, uri, jobState, null, 16, null);
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(message = "use IppInputStream.readPacket()", replaceWith = @ReplaceWith(expression = "readPacket()", imports = {"com.hp.jipp.encoding.IppInputStream"}))
        @JvmStatic
        public final IppPacket parse(InputStream input) throws IOException {
            Intrinsics.checkNotNullParameter(input, "input");
            IppInputStream ippInputStream = (IppInputStream) (!(input instanceof IppInputStream) ? null : input);
            if (ippInputStream == null) {
                ippInputStream = new IppInputStream(input);
            }
            return ippInputStream.readPacket();
        }

        @Deprecated(message = "use IppInputStream.readPacket()", replaceWith = @ReplaceWith(expression = "readPacket()", imports = {"com.hp.jipp.encoding.IppInputStream"}))
        @JvmStatic
        public final IppPacket read(InputStream input) throws IOException {
            Intrinsics.checkNotNullParameter(input, "input");
            IppInputStream ippInputStream = (IppInputStream) (!(input instanceof IppInputStream) ? null : input);
            if (ippInputStream == null) {
                ippInputStream = new IppInputStream(input);
            }
            return ippInputStream.readPacket();
        }

        @JvmStatic
        public final Builder getPrinterAttributes(URI printerUri, Iterable<? extends AttributeType<?>> types) {
            Intrinsics.checkNotNullParameter(printerUri, "printerUri");
            Intrinsics.checkNotNullParameter(types, "types");
            return putRequestedAttributes(new Builder(Operation.getPrinterAttributes.getCode(), 0, 0, 6, (DefaultConstructorMarker) null).putAttributes(Tag.operationAttributes, Types.printerUri.mo418of(printerUri)), CollectionsKt.toList(types));
        }

        @JvmStatic
        public final Builder getPrinterAttributes(URI printerUri, AttributeType<?>... types) {
            Intrinsics.checkNotNullParameter(printerUri, "printerUri");
            Intrinsics.checkNotNullParameter(types, "types");
            return getPrinterAttributes(printerUri, ArraysKt.toList(types));
        }

        private final Builder putRequestedAttributes(Builder builder, List<? extends AttributeType<?>> list) {
            if (!list.isEmpty()) {
                DelimiterTag delimiterTag = Tag.operationAttributes;
                Attribute<?>[] attributeArr = new Attribute[1];
                KeywordType.Set set = Types.requestedAttributes;
                List list2 = CollectionsKt.toList(list);
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AttributeType) it.next()).getName());
                }
                attributeArr[0] = set.mo417of((Iterable<? extends String>) arrayList);
                builder.putAttributes(delimiterTag, attributeArr);
            }
            return builder;
        }

        @JvmStatic
        public final Builder validateJob(URI printerUri) {
            Intrinsics.checkNotNullParameter(printerUri, "printerUri");
            return new Builder(Operation.validateJob.getCode(), 0, 0, 6, (DefaultConstructorMarker) null).putAttributes(Tag.operationAttributes, Types.printerUri.mo418of(printerUri));
        }

        @JvmStatic
        public final Builder printJob(URI printerUri) {
            Intrinsics.checkNotNullParameter(printerUri, "printerUri");
            return new Builder(Operation.printJob.getCode(), 0, 0, 6, (DefaultConstructorMarker) null).putAttributes(Tag.operationAttributes, Types.printerUri.mo418of(printerUri));
        }

        @JvmStatic
        public final Builder createJob(URI printerUri) {
            Intrinsics.checkNotNullParameter(printerUri, "printerUri");
            return new Builder(Operation.createJob.getCode(), 0, 0, 6, (DefaultConstructorMarker) null).putAttributes(Tag.operationAttributes, Types.printerUri.mo418of(printerUri));
        }

        @JvmStatic
        public final Builder getJobs(URI printerUri, AttributeType<?>... types) {
            Intrinsics.checkNotNullParameter(printerUri, "printerUri");
            Intrinsics.checkNotNullParameter(types, "types");
            return putRequestedAttributes(new Builder(Operation.getJobs.getCode(), 0, 0, 6, (DefaultConstructorMarker) null).putAttributes(Tag.operationAttributes, Types.printerUri.mo418of(printerUri)), ArraysKt.toList(types));
        }

        @JvmStatic
        public final Builder sendDocument(URI printerUri, int jobId) {
            Intrinsics.checkNotNullParameter(printerUri, "printerUri");
            return new Builder(Operation.sendDocument.getCode(), 0, 0, 6, (DefaultConstructorMarker) null).putAttributes(Tag.operationAttributes, Types.printerUri.mo418of(printerUri), Types.jobId.mo418of(Integer.valueOf(jobId)));
        }

        @JvmStatic
        public final Builder sendDocument(URI jobUri, AttributeType<?>... types) {
            Intrinsics.checkNotNullParameter(jobUri, "jobUri");
            Intrinsics.checkNotNullParameter(types, "types");
            return putRequestedAttributes(new Builder(Operation.sendDocument.getCode(), 0, 0, 6, (DefaultConstructorMarker) null).putAttributes(Tag.operationAttributes, Types.jobUri.mo418of(jobUri)), ArraysKt.toList(types));
        }

        @JvmStatic
        public final Builder getJobAttributes(URI printerUri, int jobId, AttributeType<?>... types) {
            Intrinsics.checkNotNullParameter(printerUri, "printerUri");
            Intrinsics.checkNotNullParameter(types, "types");
            return putRequestedAttributes(new Builder(Operation.getJobAttributes.getCode(), 0, 0, 6, (DefaultConstructorMarker) null).putAttributes(Tag.operationAttributes, Types.printerUri.mo418of(printerUri), Types.jobId.mo418of(Integer.valueOf(jobId))), ArraysKt.toList(types));
        }

        @JvmStatic
        public final Builder getJobAttributes(URI jobUri, AttributeType<?>... types) {
            Intrinsics.checkNotNullParameter(jobUri, "jobUri");
            Intrinsics.checkNotNullParameter(types, "types");
            return putRequestedAttributes(new Builder(Operation.getJobAttributes.getCode(), 0, 0, 6, (DefaultConstructorMarker) null).putAttributes(Tag.operationAttributes, Types.jobUri.mo418of(jobUri)), ArraysKt.toList(types));
        }

        @JvmStatic
        public final Builder cancelJob(URI printerUri, int jobId) {
            Intrinsics.checkNotNullParameter(printerUri, "printerUri");
            return new Builder(Operation.cancelJob.getCode(), 0, 0, 6, (DefaultConstructorMarker) null).putAttributes(Tag.operationAttributes, Types.printerUri.mo418of(printerUri), Types.jobId.mo418of(Integer.valueOf(jobId)));
        }

        @JvmStatic
        public final Builder cancelJob(URI jobUri) {
            Intrinsics.checkNotNullParameter(jobUri, "jobUri");
            return new Builder(Operation.cancelJob.getCode(), 0, 0, 6, (DefaultConstructorMarker) null).putAttributes(Tag.operationAttributes, Types.jobUri.mo418of(jobUri));
        }

        @JvmStatic
        public final Builder response(Status status) {
            Intrinsics.checkNotNullParameter(status, "status");
            return new Builder(status.getCode(), 0, 0, 6, (DefaultConstructorMarker) null).putAttributes(Tag.unsupportedAttributes, new Attribute[0]);
        }

        public static Builder jobResponse$default(Companion companion, Status status, int i, URI uri, JobState jobState, List list, int i2, Object obj) {
            if ((i2 & 16) != 0) {
                list = CollectionsKt.listOf("none");
            }
            return companion.jobResponse(status, i, uri, jobState, list);
        }

        @JvmStatic
        public final Builder jobResponse(Status status, int jobId, URI jobUri, JobState jobState, List<String> jobStateReasons) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(jobUri, "jobUri");
            Intrinsics.checkNotNullParameter(jobState, "jobState");
            Intrinsics.checkNotNullParameter(jobStateReasons, "jobStateReasons");
            return new Builder(status.getCode(), 0, 0, 6, (DefaultConstructorMarker) null).putAttributes(Tag.unsupportedAttributes, new Attribute[0]).addJobAttributesGroup(jobId, jobUri, jobState, jobStateReasons, new Attribute[0]);
        }
    }
}
