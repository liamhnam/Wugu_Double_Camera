package com.faceunity.core.entity;

import com.tom_roush.fontbox.ttf.NamingTable;
import java.io.File;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0016\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\u0000H\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u000b"}, m1293d2 = {"Lcom/faceunity/core/entity/FUBundleData;", "", "path", "", NamingTable.TAG, "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getPath", "clone", "Companion", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public class FUBundleData {

    public static final Companion INSTANCE = new Companion(null);
    private final String name;
    private final String path;

    public FUBundleData(String str) {
        this(str, null, 2, 0 == true ? 1 : 0);
    }

    public FUBundleData(String path, String name) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.path = path;
        this.name = name;
    }

    public final String getPath() {
        return this.path;
    }

    public FUBundleData(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? INSTANCE.getFileName(str) : str2);
    }

    public final String getName() {
        return this.name;
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, m1293d2 = {"Lcom/faceunity/core/entity/FUBundleData$Companion;", "", "()V", "getFileName", "", "path", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getFileName(String path) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            String string = StringsKt.trim((CharSequence) path).toString();
            String str = File.separator;
            Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
            int iLastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) string, str, 0, false, 6, (Object) null) + 1;
            if (string == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = string.substring(iLastIndexOf$default);
            Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
            String str2 = strSubstring;
            if (!StringsKt.contains$default((CharSequence) str2, (CharSequence) ".bundle", false, 2, (Object) null)) {
                return strSubstring;
            }
            int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str2, ".bundle", 0, false, 6, (Object) null);
            if (strSubstring == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring2 = strSubstring.substring(0, iIndexOf$default);
            Intrinsics.checkExpressionValueIsNotNull(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return strSubstring2;
        }
    }

    public FUBundleData clone() {
        return new FUBundleData(this.path, this.name);
    }
}
