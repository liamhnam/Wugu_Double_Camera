package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Enum;
import com.p020hp.jipp.encoding.EnumType;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0086\b\u0018\u0000 \u00152\u00020\u0001:\u0004\u0014\u0015\u0016\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0013\u001a\u00020\u0005H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, m1293d2 = {"Lcom/hp/jipp/model/Operation;", "Lcom/hp/jipp/encoding/Enum;", "code", "", NamingTable.TAG, "", "(ILjava/lang/String;)V", "getCode", "()I", "getName", "()Ljava/lang/String;", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "toString", StandardStructureTypes.CODE, "Companion", "SetType", "Type", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class Operation extends Enum {

    public static final Companion INSTANCE = new Companion(null);
    public static final Operation acknowledgeDocument;
    public static final Operation acknowledgeIdentifyPrinter;
    public static final Operation acknowledgeJob;
    public static final Operation activatePrinter;
    public static final Operation addDocumentImages;
    public static final Map<Integer, Operation> all;
    public static final Operation allocatePrinterResources;
    public static final Operation cancelCurrentJob;
    public static final Operation cancelDocument;
    public static final Operation cancelJob;
    public static final Operation cancelJobs;
    public static final Operation cancelMyJobs;
    public static final Operation cancelResource;
    public static final Operation cancelSubscription;
    public static final Operation closeJob;
    public static final Operation createJob;
    public static final Operation createJobSubscriptions;
    public static final Operation createPrinter;
    public static final Operation createPrinterSubscriptions;
    public static final Operation createResource;
    public static final Operation createResourceSubscriptions;
    public static final Operation createSystemSubscriptions;
    public static final Operation deactivatePrinter;
    public static final Operation deallocatePrinterResources;
    public static final Operation deletePrinter;
    public static final Operation deregisterOutputDevice;
    public static final Operation disableAllPrinters;
    public static final Operation disablePrinter;
    public static final Operation enableAllPrinters;
    public static final Operation enablePrinter;
    public static final Operation fetchDocument;
    public static final Operation fetchJob;
    public static final Operation getDocumentAttributes;
    public static final Operation getDocuments;
    public static final Operation getJobAttributes;
    public static final Operation getJobs;
    public static final Operation getNextDocumentData;
    public static final Operation getNotifications;
    public static final Operation getOutputDeviceAttributes;
    public static final Operation getPrinterAttributes;
    public static final Operation getPrinterResources;
    public static final Operation getPrinterSupportedValues;
    public static final Operation getPrinters;
    public static final Operation getResourceAttributes;
    public static final Operation getResources;
    public static final Operation getSubscriptionAttributes;
    public static final Operation getSubscriptions;
    public static final Operation getSystemAttributes;
    public static final Operation getSystemSupportedValues;
    public static final Operation getUserPrinterAttributes;
    public static final Operation holdJob;
    public static final Operation holdNewJobs;
    public static final Operation identifyPrinter;
    public static final Operation installResource;
    public static final Operation pauseAllPrinters;
    public static final Operation pauseAllPrintersAfterCurrentJob;
    public static final Operation pausePrinter;
    public static final Operation pausePrinterAfterCurrentJob;
    public static final Operation printJob;
    public static final Operation printUri;
    public static final Operation promoteJob;
    public static final Operation purgeJobs;
    public static final Operation registerOutputDevice;
    public static final Operation releaseHeldNewJobs;
    public static final Operation releaseJob;
    public static final Operation renewSubscription;
    public static final Operation reprocessJob;
    public static final Operation restartJob;
    public static final Operation restartOnePrinter;
    public static final Operation restartPrinter;
    public static final Operation restartSystem;
    public static final Operation resubmitJob;
    public static final Operation resumeAllPrinters;
    public static final Operation resumeJob;
    public static final Operation resumePrinter;
    public static final Operation scheduleJobAfter;
    public static final Operation sendDocument;
    public static final Operation sendResourceData;
    public static final Operation sendUri;
    public static final Operation setDocumentAttributes;
    public static final Operation setJobAttributes;
    public static final Operation setPrinterAttributes;
    public static final Operation setResourceAttributes;
    public static final Operation setSystemAttributes;
    public static final Operation shutdownAllPrinters;
    public static final Operation shutdownOnePrinter;
    public static final Operation shutdownPrinter;
    public static final Operation startupAllPrinters;
    public static final Operation startupOnePrinter;
    public static final Operation startupPrinter;
    public static final Operation suspendCurrentJob;
    public static final Operation updateActiveJobs;
    public static final Operation updateDocumentStatus;
    public static final Operation updateJobStatus;
    public static final Operation updateOutputDeviceAttributes;
    public static final Operation validateDocument;
    public static final Operation validateJob;
    private final int code;
    private final String name;

    public static Operation copy$default(Operation operation, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = operation.getCode();
        }
        if ((i2 & 2) != 0) {
            str = operation.getName();
        }
        return operation.copy(i, str);
    }

    public final int component1() {
        return getCode();
    }

    public final String component2() {
        return getName();
    }

    public final Operation copy(int code, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new Operation(code, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Operation)) {
            return false;
        }
        Operation operation = (Operation) other;
        return getCode() == operation.getCode() && Intrinsics.areEqual(getName(), operation.getName());
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(getCode()) * 31;
        String name = getName();
        return iHashCode + (name != null ? name.hashCode() : 0);
    }

    public Operation(int i, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.code = i;
        this.name = name;
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
        return super.toString();
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/jipp/model/Operation$Type;", "Lcom/hp/jipp/encoding/EnumType;", "Lcom/hp/jipp/model/Operation;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Type extends EnumType<Operation> {
        public Type(String name) {
            super(name, new Function1<Integer, Operation>() {
                public final Operation invoke(int i) {
                    return Operation.INSTANCE.get(i);
                }

                @Override
                public Operation invoke(Integer num) {
                    return invoke(num.intValue());
                }
            });
            Intrinsics.checkNotNullParameter(name, "name");
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/jipp/model/Operation$SetType;", "Lcom/hp/jipp/encoding/EnumType$Set;", "Lcom/hp/jipp/model/Operation;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class SetType extends EnumType.Set<Operation> {
        public SetType(String name) {
            super(name, new Function1<Integer, Operation>() {
                public final Operation invoke(int i) {
                    return Operation.INSTANCE.get(i);
                }

                @Override
                public Operation invoke(Integer num) {
                    return invoke(num.intValue());
                }
            });
            Intrinsics.checkNotNullParameter(name, "name");
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b`\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010_\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006d"}, m1293d2 = {"Lcom/hp/jipp/model/Operation$Code;", "", "()V", "acknowledgeDocument", "", "acknowledgeIdentifyPrinter", "acknowledgeJob", "activatePrinter", "addDocumentImages", "allocatePrinterResources", "cancelCurrentJob", "cancelDocument", "cancelJob", "cancelJobs", "cancelMyJobs", "cancelResource", "cancelSubscription", "closeJob", "createJob", "createJobSubscriptions", "createPrinter", "createPrinterSubscriptions", "createResource", "createResourceSubscriptions", "createSystemSubscriptions", "deactivatePrinter", "deallocatePrinterResources", "deletePrinter", "deregisterOutputDevice", "disableAllPrinters", "disablePrinter", "enableAllPrinters", "enablePrinter", "fetchDocument", "fetchJob", "getDocumentAttributes", "getDocuments", "getJobAttributes", "getJobs", "getNextDocumentData", "getNotifications", "getOutputDeviceAttributes", "getPrinterAttributes", "getPrinterResources", "getPrinterSupportedValues", "getPrinters", "getResourceAttributes", "getResources", "getSubscriptionAttributes", "getSubscriptions", "getSystemAttributes", "getSystemSupportedValues", "getUserPrinterAttributes", "holdJob", "holdNewJobs", "identifyPrinter", "installResource", "pauseAllPrinters", "pauseAllPrintersAfterCurrentJob", "pausePrinter", "pausePrinterAfterCurrentJob", "printJob", "printUri", "promoteJob", "purgeJobs", "registerOutputDevice", "releaseHeldNewJobs", "releaseJob", "renewSubscription", "reprocessJob", "restartJob", "restartOnePrinter", "restartPrinter", "restartSystem", "resubmitJob", "resumeAllPrinters", "resumeJob", "resumePrinter", "scheduleJobAfter", "sendDocument", "sendResourceData", "sendUri", "setDocumentAttributes", "setJobAttributes", "setPrinterAttributes", "setResourceAttributes", "setSystemAttributes", "shutdownAllPrinters", "shutdownOnePrinter", "shutdownPrinter", "startupAllPrinters", "startupOnePrinter", "startupPrinter", "suspendCurrentJob", "updateActiveJobs", "updateDocumentStatus", "updateJobStatus", "updateOutputDeviceAttributes", "validateDocument", "validateJob", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Code {
        public static final Code INSTANCE = new Code();
        public static final int acknowledgeDocument = 63;
        public static final int acknowledgeIdentifyPrinter = 64;
        public static final int acknowledgeJob = 65;
        public static final int activatePrinter = 40;
        public static final int addDocumentImages = 62;
        public static final int allocatePrinterResources = 75;
        public static final int cancelCurrentJob = 45;
        public static final int cancelDocument = 51;
        public static final int cancelJob = 8;
        public static final int cancelJobs = 56;
        public static final int cancelMyJobs = 57;
        public static final int cancelResource = 82;
        public static final int cancelSubscription = 27;
        public static final int closeJob = 58;
        public static final int createJob = 5;
        public static final int createJobSubscriptions = 23;
        public static final int createPrinter = 76;
        public static final int createPrinterSubscriptions = 22;
        public static final int createResource = 83;
        public static final int createResourceSubscriptions = 87;
        public static final int createSystemSubscriptions = 88;
        public static final int deactivatePrinter = 39;
        public static final int deallocatePrinterResources = 77;
        public static final int deletePrinter = 78;
        public static final int deregisterOutputDevice = 70;
        public static final int disableAllPrinters = 89;
        public static final int disablePrinter = 35;
        public static final int enableAllPrinters = 90;
        public static final int enablePrinter = 34;
        public static final int fetchDocument = 66;
        public static final int fetchJob = 67;
        public static final int getDocumentAttributes = 52;
        public static final int getDocuments = 53;
        public static final int getJobAttributes = 9;
        public static final int getJobs = 10;
        public static final int getNextDocumentData = 74;
        public static final int getNotifications = 28;
        public static final int getOutputDeviceAttributes = 68;
        public static final int getPrinterAttributes = 11;
        public static final int getPrinterResources = 101;
        public static final int getPrinterSupportedValues = 21;
        public static final int getPrinters = 79;
        public static final int getResourceAttributes = 30;
        public static final int getResources = 32;
        public static final int getSubscriptionAttributes = 24;
        public static final int getSubscriptions = 25;
        public static final int getSystemAttributes = 91;
        public static final int getSystemSupportedValues = 92;
        public static final int getUserPrinterAttributes = 102;
        public static final int holdJob = 12;
        public static final int holdNewJobs = 37;
        public static final int identifyPrinter = 60;
        public static final int installResource = 84;
        public static final int pauseAllPrinters = 93;
        public static final int pauseAllPrintersAfterCurrentJob = 94;
        public static final int pausePrinter = 16;
        public static final int pausePrinterAfterCurrentJob = 36;
        public static final int printJob = 2;
        public static final int printUri = 3;
        public static final int promoteJob = 48;
        public static final int purgeJobs = 18;
        public static final int registerOutputDevice = 95;
        public static final int releaseHeldNewJobs = 38;
        public static final int releaseJob = 13;
        public static final int renewSubscription = 26;
        public static final int reprocessJob = 44;
        public static final int restartJob = 14;
        public static final int restartOnePrinter = 103;
        public static final int restartPrinter = 41;
        public static final int restartSystem = 96;
        public static final int resubmitJob = 59;
        public static final int resumeAllPrinters = 97;
        public static final int resumeJob = 47;
        public static final int resumePrinter = 17;
        public static final int scheduleJobAfter = 49;
        public static final int sendDocument = 6;
        public static final int sendResourceData = 85;
        public static final int sendUri = 7;
        public static final int setDocumentAttributes = 55;
        public static final int setJobAttributes = 20;
        public static final int setPrinterAttributes = 19;
        public static final int setResourceAttributes = 86;
        public static final int setSystemAttributes = 98;
        public static final int shutdownAllPrinters = 99;
        public static final int shutdownOnePrinter = 80;
        public static final int shutdownPrinter = 42;
        public static final int startupAllPrinters = 100;
        public static final int startupOnePrinter = 81;
        public static final int startupPrinter = 43;
        public static final int suspendCurrentJob = 46;
        public static final int updateActiveJobs = 69;
        public static final int updateDocumentStatus = 71;
        public static final int updateJobStatus = 72;
        public static final int updateOutputDeviceAttributes = 73;
        public static final int validateDocument = 61;
        public static final int validateJob = 4;

        private Code() {
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b^\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010g\u001a\u00020\u00042\u0006\u0010h\u001a\u00020\u000bH\u0086\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00109\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010;\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010=\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010B\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010C\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010E\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010F\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010G\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010H\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010I\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010J\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010K\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010L\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010M\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010N\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010P\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010Q\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010R\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010S\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010T\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010U\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010V\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010W\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010X\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010Y\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010Z\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010[\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\\\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010]\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010^\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010_\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010`\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010a\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010c\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010d\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010e\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010f\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006i"}, m1293d2 = {"Lcom/hp/jipp/model/Operation$Companion;", "", "()V", "acknowledgeDocument", "Lcom/hp/jipp/model/Operation;", "acknowledgeIdentifyPrinter", "acknowledgeJob", "activatePrinter", "addDocumentImages", "all", "", "", "allocatePrinterResources", "cancelCurrentJob", "cancelDocument", "cancelJob", "cancelJobs", "cancelMyJobs", "cancelResource", "cancelSubscription", "closeJob", "createJob", "createJobSubscriptions", "createPrinter", "createPrinterSubscriptions", "createResource", "createResourceSubscriptions", "createSystemSubscriptions", "deactivatePrinter", "deallocatePrinterResources", "deletePrinter", "deregisterOutputDevice", "disableAllPrinters", "disablePrinter", "enableAllPrinters", "enablePrinter", "fetchDocument", "fetchJob", "getDocumentAttributes", "getDocuments", "getJobAttributes", "getJobs", "getNextDocumentData", "getNotifications", "getOutputDeviceAttributes", "getPrinterAttributes", "getPrinterResources", "getPrinterSupportedValues", "getPrinters", "getResourceAttributes", "getResources", "getSubscriptionAttributes", "getSubscriptions", "getSystemAttributes", "getSystemSupportedValues", "getUserPrinterAttributes", "holdJob", "holdNewJobs", "identifyPrinter", "installResource", "pauseAllPrinters", "pauseAllPrintersAfterCurrentJob", "pausePrinter", "pausePrinterAfterCurrentJob", "printJob", "printUri", "promoteJob", "purgeJobs", "registerOutputDevice", "releaseHeldNewJobs", "releaseJob", "renewSubscription", "reprocessJob", "restartJob", "restartOnePrinter", "restartPrinter", "restartSystem", "resubmitJob", "resumeAllPrinters", "resumeJob", "resumePrinter", "scheduleJobAfter", "sendDocument", "sendResourceData", "sendUri", "setDocumentAttributes", "setJobAttributes", "setPrinterAttributes", "setResourceAttributes", "setSystemAttributes", "shutdownAllPrinters", "shutdownOnePrinter", "shutdownPrinter", "startupAllPrinters", "startupOnePrinter", "startupPrinter", "suspendCurrentJob", "updateActiveJobs", "updateDocumentStatus", "updateJobStatus", "updateOutputDeviceAttributes", "validateDocument", "validateJob", "get", "value", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Operation get(int value) {
            Operation operation = Operation.all.get(Integer.valueOf(value));
            return operation != null ? operation : new Operation(value, "???");
        }
    }

    static {
        Operation operation = new Operation(2, "Print-Job");
        printJob = operation;
        Operation operation2 = new Operation(3, "Print-URI");
        printUri = operation2;
        Operation operation3 = new Operation(4, "Validate-Job");
        validateJob = operation3;
        Operation operation4 = new Operation(5, "Create-Job");
        createJob = operation4;
        Operation operation5 = new Operation(6, "Send-Document");
        sendDocument = operation5;
        Operation operation6 = new Operation(7, "Send-URI");
        sendUri = operation6;
        Operation operation7 = new Operation(8, "Cancel-Job");
        cancelJob = operation7;
        Operation operation8 = new Operation(9, "Get-Job-Attributes");
        getJobAttributes = operation8;
        Operation operation9 = new Operation(10, "Get-Jobs");
        getJobs = operation9;
        Operation operation10 = new Operation(11, "Get-Printer-Attributes");
        getPrinterAttributes = operation10;
        Operation operation11 = new Operation(12, "Hold-Job");
        holdJob = operation11;
        Operation operation12 = new Operation(13, "Release-Job");
        releaseJob = operation12;
        Operation operation13 = new Operation(14, "Restart-Job");
        restartJob = operation13;
        Operation operation14 = new Operation(16, "Pause-Printer");
        pausePrinter = operation14;
        Operation operation15 = new Operation(17, "Resume-Printer");
        resumePrinter = operation15;
        Operation operation16 = new Operation(18, "Purge-Jobs");
        purgeJobs = operation16;
        Operation operation17 = new Operation(19, "Set-Printer-Attributes");
        setPrinterAttributes = operation17;
        Operation operation18 = new Operation(20, "Set-Job-Attributes");
        setJobAttributes = operation18;
        Operation operation19 = new Operation(21, "Get-Printer-Supported-Values");
        getPrinterSupportedValues = operation19;
        Operation operation20 = new Operation(22, "Create-Printer-Subscriptions");
        createPrinterSubscriptions = operation20;
        Operation operation21 = new Operation(23, "Create-Job-Subscriptions");
        createJobSubscriptions = operation21;
        Operation operation22 = new Operation(24, "Get-Subscription-Attributes");
        getSubscriptionAttributes = operation22;
        Operation operation23 = new Operation(25, "Get-Subscriptions");
        getSubscriptions = operation23;
        Operation operation24 = new Operation(26, "Renew-Subscription");
        renewSubscription = operation24;
        Operation operation25 = new Operation(27, "Cancel-Subscription");
        cancelSubscription = operation25;
        Operation operation26 = new Operation(28, "Get-Notifications");
        getNotifications = operation26;
        Operation operation27 = new Operation(30, "Get-Resource-Attributes");
        getResourceAttributes = operation27;
        Operation operation28 = new Operation(32, "Get-Resources");
        getResources = operation28;
        Operation operation29 = new Operation(34, "Enable-Printer");
        enablePrinter = operation29;
        Operation operation30 = new Operation(35, "Disable-Printer");
        disablePrinter = operation30;
        Operation operation31 = new Operation(36, "Pause-Printer-After-Current-Job");
        pausePrinterAfterCurrentJob = operation31;
        Operation operation32 = new Operation(37, "Hold-New-Jobs");
        holdNewJobs = operation32;
        Operation operation33 = new Operation(38, "Release-Held-New-Jobs");
        releaseHeldNewJobs = operation33;
        Operation operation34 = new Operation(39, "Deactivate-Printer");
        deactivatePrinter = operation34;
        Operation operation35 = new Operation(40, "Activate-Printer");
        activatePrinter = operation35;
        Operation operation36 = new Operation(41, "Restart-Printer");
        restartPrinter = operation36;
        Operation operation37 = new Operation(42, "Shutdown-Printer");
        shutdownPrinter = operation37;
        Operation operation38 = new Operation(43, "Startup-Printer");
        startupPrinter = operation38;
        Operation operation39 = new Operation(44, "Reprocess-Job");
        reprocessJob = operation39;
        Operation operation40 = new Operation(45, "Cancel-Current-Job");
        cancelCurrentJob = operation40;
        Operation operation41 = new Operation(46, "Suspend-Current-Job");
        suspendCurrentJob = operation41;
        Operation operation42 = new Operation(47, "Resume-Job");
        resumeJob = operation42;
        Operation operation43 = new Operation(48, "Promote-Job");
        promoteJob = operation43;
        Operation operation44 = new Operation(49, "Schedule-Job-After");
        scheduleJobAfter = operation44;
        Operation operation45 = new Operation(51, "Cancel-Document");
        cancelDocument = operation45;
        Operation operation46 = new Operation(52, "Get-Document-Attributes");
        getDocumentAttributes = operation46;
        Operation operation47 = new Operation(53, "Get-Documents");
        getDocuments = operation47;
        Operation operation48 = new Operation(55, "Set-Document-Attributes");
        setDocumentAttributes = operation48;
        Operation operation49 = new Operation(56, "Cancel-Jobs");
        cancelJobs = operation49;
        Operation operation50 = new Operation(57, "Cancel-My-Jobs");
        cancelMyJobs = operation50;
        Operation operation51 = new Operation(58, "Close-Job");
        closeJob = operation51;
        Operation operation52 = new Operation(59, "Resubmit-Job");
        resubmitJob = operation52;
        Operation operation53 = new Operation(60, "Identify-Printer");
        identifyPrinter = operation53;
        Operation operation54 = new Operation(61, "Validate-Document");
        validateDocument = operation54;
        Operation operation55 = new Operation(62, "Add-Document-Images");
        addDocumentImages = operation55;
        Operation operation56 = new Operation(63, "Acknowledge-Document");
        acknowledgeDocument = operation56;
        Operation operation57 = new Operation(64, "Acknowledge-Identify-Printer");
        acknowledgeIdentifyPrinter = operation57;
        Operation operation58 = new Operation(65, "Acknowledge-Job");
        acknowledgeJob = operation58;
        Operation operation59 = new Operation(66, "Fetch-Document");
        fetchDocument = operation59;
        Operation operation60 = new Operation(67, "Fetch-Job");
        fetchJob = operation60;
        Operation operation61 = new Operation(68, "Get-Output-Device-Attributes");
        getOutputDeviceAttributes = operation61;
        Operation operation62 = new Operation(69, "Update-Active-Jobs");
        updateActiveJobs = operation62;
        Operation operation63 = new Operation(70, "Deregister-Output-Device");
        deregisterOutputDevice = operation63;
        Operation operation64 = new Operation(71, "Update-Document-Status");
        updateDocumentStatus = operation64;
        Operation operation65 = new Operation(72, "Update-Job-Status");
        updateJobStatus = operation65;
        Operation operation66 = new Operation(73, "Update-Output-Device-Attributes");
        updateOutputDeviceAttributes = operation66;
        Operation operation67 = new Operation(74, "Get-Next-Document-Data");
        getNextDocumentData = operation67;
        Operation operation68 = new Operation(75, "Allocate-Printer-Resources");
        allocatePrinterResources = operation68;
        Operation operation69 = new Operation(76, "Create-Printer");
        createPrinter = operation69;
        Operation operation70 = new Operation(77, "Deallocate-Printer-Resources");
        deallocatePrinterResources = operation70;
        Operation operation71 = new Operation(78, "Delete-Printer");
        deletePrinter = operation71;
        Operation operation72 = new Operation(79, "Get-Printers");
        getPrinters = operation72;
        Operation operation73 = new Operation(80, "Shutdown-One-Printer");
        shutdownOnePrinter = operation73;
        Operation operation74 = new Operation(81, "Startup-One-Printer");
        startupOnePrinter = operation74;
        Operation operation75 = new Operation(82, "Cancel-Resource");
        cancelResource = operation75;
        Operation operation76 = new Operation(83, "Create-Resource");
        createResource = operation76;
        Operation operation77 = new Operation(84, "Install-Resource");
        installResource = operation77;
        Operation operation78 = new Operation(85, "Send-Resource-Data");
        sendResourceData = operation78;
        Operation operation79 = new Operation(86, "Set-Resource-Attributes");
        setResourceAttributes = operation79;
        Operation operation80 = new Operation(87, "Create-Resource-Subscriptions");
        createResourceSubscriptions = operation80;
        Operation operation81 = new Operation(88, "Create-System-Subscriptions");
        createSystemSubscriptions = operation81;
        Operation operation82 = new Operation(89, "Disable-All-Printers");
        disableAllPrinters = operation82;
        Operation operation83 = new Operation(90, "Enable-All-Printers");
        enableAllPrinters = operation83;
        Operation operation84 = new Operation(91, "Get-System-Attributes");
        getSystemAttributes = operation84;
        Operation operation85 = new Operation(92, "Get-System-Supported-Values");
        getSystemSupportedValues = operation85;
        Operation operation86 = new Operation(93, "Pause-All-Printers");
        pauseAllPrinters = operation86;
        Operation operation87 = new Operation(94, "Pause-All-Printers-After-Current-Job");
        pauseAllPrintersAfterCurrentJob = operation87;
        Operation operation88 = new Operation(95, "Register-Output-Device");
        registerOutputDevice = operation88;
        Operation operation89 = new Operation(96, "Restart-System");
        restartSystem = operation89;
        Operation operation90 = new Operation(97, "Resume-All-Printers");
        resumeAllPrinters = operation90;
        Operation operation91 = new Operation(98, "Set-System-Attributes");
        setSystemAttributes = operation91;
        Operation operation92 = new Operation(99, "Shutdown-All-Printers");
        shutdownAllPrinters = operation92;
        Operation operation93 = new Operation(100, "Startup-All-Printers");
        startupAllPrinters = operation93;
        Operation operation94 = new Operation(101, "Get-Printer-Resources");
        getPrinterResources = operation94;
        Operation operation95 = new Operation(102, "Get-User-Printer-Attributes");
        getUserPrinterAttributes = operation95;
        Operation operation96 = new Operation(103, "Restart-One-Printer");
        restartOnePrinter = operation96;
        List<Operation> listListOf = CollectionsKt.listOf((Object[]) new Operation[]{operation, operation2, operation3, operation4, operation5, operation6, operation7, operation8, operation9, operation10, operation11, operation12, operation13, operation14, operation15, operation16, operation17, operation18, operation19, operation20, operation21, operation22, operation23, operation24, operation25, operation26, operation27, operation28, operation29, operation30, operation31, operation32, operation33, operation34, operation35, operation36, operation37, operation38, operation39, operation40, operation41, operation42, operation43, operation44, operation45, operation46, operation47, operation48, operation49, operation50, operation51, operation52, operation53, operation54, operation55, operation56, operation57, operation58, operation59, operation60, operation61, operation62, operation63, operation64, operation65, operation66, operation67, operation68, operation69, operation70, operation71, operation72, operation73, operation74, operation75, operation76, operation77, operation78, operation79, operation80, operation81, operation82, operation83, operation84, operation85, operation86, operation87, operation88, operation89, operation90, operation91, operation92, operation93, operation94, operation95, operation96});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        for (Operation operation97 : listListOf) {
            arrayList.add(TuplesKt.m1300to(Integer.valueOf(operation97.getCode()), operation97));
        }
        all = MapsKt.toMap(arrayList);
    }
}
