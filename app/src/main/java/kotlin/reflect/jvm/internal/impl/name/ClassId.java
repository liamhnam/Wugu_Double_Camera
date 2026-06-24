package kotlin.reflect.jvm.internal.impl.name;

import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class ClassId {
    static final boolean $assertionsDisabled = false;
    private final boolean local;
    private final FqName packageFqName;
    private final FqName relativeClassName;

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void $$$reportNull$$$0(int r10) {
        /*
            Method dump skipped, instruction units count: 300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.name.ClassId.$$$reportNull$$$0(int):void");
    }

    public static ClassId topLevel(FqName fqName) {
        if (fqName == null) {
            $$$reportNull$$$0(0);
        }
        return new ClassId(fqName.parent(), fqName.shortName());
    }

    public ClassId(FqName fqName, FqName fqName2, boolean z) {
        if (fqName == null) {
            $$$reportNull$$$0(1);
        }
        if (fqName2 == null) {
            $$$reportNull$$$0(2);
        }
        this.packageFqName = fqName;
        this.relativeClassName = fqName2;
        this.local = z;
    }

    public ClassId(FqName fqName, Name name) {
        this(fqName, FqName.topLevel(name), false);
        if (fqName == null) {
            $$$reportNull$$$0(3);
        }
        if (name == null) {
            $$$reportNull$$$0(4);
        }
    }

    public FqName getPackageFqName() {
        FqName fqName = this.packageFqName;
        if (fqName == null) {
            $$$reportNull$$$0(5);
        }
        return fqName;
    }

    public FqName getRelativeClassName() {
        FqName fqName = this.relativeClassName;
        if (fqName == null) {
            $$$reportNull$$$0(6);
        }
        return fqName;
    }

    public Name getShortClassName() {
        Name nameShortName = this.relativeClassName.shortName();
        if (nameShortName == null) {
            $$$reportNull$$$0(7);
        }
        return nameShortName;
    }

    public boolean isLocal() {
        return this.local;
    }

    public ClassId createNestedClassId(Name name) {
        if (name == null) {
            $$$reportNull$$$0(8);
        }
        return new ClassId(getPackageFqName(), this.relativeClassName.child(name), this.local);
    }

    public ClassId getOuterClassId() {
        FqName fqNameParent = this.relativeClassName.parent();
        if (fqNameParent.isRoot()) {
            return null;
        }
        return new ClassId(getPackageFqName(), fqNameParent, this.local);
    }

    public boolean isNestedClass() {
        return !this.relativeClassName.parent().isRoot();
    }

    public FqName asSingleFqName() {
        if (!this.packageFqName.isRoot()) {
            return new FqName(this.packageFqName.asString() + "." + this.relativeClassName.asString());
        }
        FqName fqName = this.relativeClassName;
        if (fqName == null) {
            $$$reportNull$$$0(9);
        }
        return fqName;
    }

    public static ClassId fromString(String str) {
        if (str == null) {
            $$$reportNull$$$0(11);
        }
        return fromString(str, false);
    }

    public static ClassId fromString(String str, boolean z) {
        String str2;
        if (str == null) {
            $$$reportNull$$$0(12);
        }
        int iLastIndexOf = str.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        if (iLastIndexOf == -1) {
            str2 = "";
        } else {
            String strReplace = str.substring(0, iLastIndexOf).replace('/', '.');
            str = str.substring(iLastIndexOf + 1);
            str2 = strReplace;
        }
        return new ClassId(new FqName(str2), new FqName(str), z);
    }

    public String asString() {
        if (this.packageFqName.isRoot()) {
            String strAsString = this.relativeClassName.asString();
            if (strAsString == null) {
                $$$reportNull$$$0(13);
            }
            return strAsString;
        }
        String str = this.packageFqName.asString().replace('.', '/') + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.relativeClassName.asString();
        if (str == null) {
            $$$reportNull$$$0(14);
        }
        return str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ClassId classId = (ClassId) obj;
        return this.packageFqName.equals(classId.packageFqName) && this.relativeClassName.equals(classId.relativeClassName) && this.local == classId.local;
    }

    public int hashCode() {
        return (((this.packageFqName.hashCode() * 31) + this.relativeClassName.hashCode()) * 31) + Boolean.valueOf(this.local).hashCode();
    }

    public String toString() {
        return this.packageFqName.isRoot() ? MqttTopic.TOPIC_LEVEL_SEPARATOR + asString() : asString();
    }
}
