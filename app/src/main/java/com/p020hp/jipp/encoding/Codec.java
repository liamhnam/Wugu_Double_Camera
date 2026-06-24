package com.p020hp.jipp.encoding;

import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u0016*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\u001d\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\nH&¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0002H&J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0002H&R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, m1293d2 = {"Lcom/hp/jipp/encoding/Codec;", "T", "", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "handlesTag", "", "tag", "Lcom/hp/jipp/encoding/ValueTag;", "readValue", "input", "Lcom/hp/jipp/encoding/IppInputStream;", "startTag", "(Lcom/hp/jipp/encoding/IppInputStream;Lcom/hp/jipp/encoding/ValueTag;)Ljava/lang/Object;", "tagOf", "value", "writeValue", "", "output", "Lcom/hp/jipp/encoding/IppOutputStream;", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public interface Codec<T> {

    public static final Companion INSTANCE = Companion.$$INSTANCE;

    Class<T> getCls();

    boolean handlesTag(ValueTag tag);

    T readValue(IppInputStream input, ValueTag startTag);

    ValueTag tagOf(Object value);

    void writeValue(IppOutputStream output, Object value);

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0094\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\n\b\u0001\u0010\u0005\u0018\u0001*\u00020\u00062\u0014\b\u0004\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2.\b\u0004\u0010\u000b\u001a(\u0012\u0004\u0012\u00020\r\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00050\f¢\u0006\u0002\b\u00112.\b\u0004\u0010\u0012\u001a(\u0012\u0004\u0012\u00020\u0013\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\f¢\u0006\u0002\b\u0011H\u0086\nø\u0001\u0000J\u0082\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0006\b\u0001\u0010\u0005\u0018\u00012\u0006\u0010\u0016\u001a\u00020\t2.\b\u0004\u0010\u000b\u001a(\u0012\u0004\u0012\u00020\r\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00050\f¢\u0006\u0002\b\u00112.\b\u0004\u0010\u0012\u001a(\u0012\u0004\u0012\u00020\u0013\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\f¢\u0006\u0002\b\u0011H\u0086\nø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0017"}, m1293d2 = {"Lcom/hp/jipp/encoding/Codec$Companion;", "", "()V", "invoke", "Lcom/hp/jipp/encoding/Codec;", "T", "Lcom/hp/jipp/encoding/TaggedValue;", "handlesTagFunc", "Lkotlin/Function1;", "Lcom/hp/jipp/encoding/ValueTag;", "", "readAttrFunc", "Lkotlin/Function2;", "Lcom/hp/jipp/encoding/IppInputStream;", "Lkotlin/ParameterName;", NamingTable.TAG, "startTag", "Lkotlin/ExtensionFunctionType;", "writeAttrFunc", "Lcom/hp/jipp/encoding/IppOutputStream;", "value", "", "valueTag", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        static final Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final <T extends TaggedValue> Codec<T> invoke(final Function1<? super ValueTag, Boolean> handlesTagFunc, final Function2<? super IppInputStream, ? super ValueTag, ? extends T> readAttrFunc, final Function2<? super IppOutputStream, ? super T, Unit> writeAttrFunc) {
            Intrinsics.checkNotNullParameter(handlesTagFunc, "handlesTagFunc");
            Intrinsics.checkNotNullParameter(readAttrFunc, "readAttrFunc");
            Intrinsics.checkNotNullParameter(writeAttrFunc, "writeAttrFunc");
            Intrinsics.needClassReification();
            return new Codec<T>() {
                private final Class<T> cls;

                {
                    Intrinsics.reifiedOperationMarker(4, "T");
                    this.cls = TaggedValue.class;
                }

                @Override
                public Class<T> getCls() {
                    return this.cls;
                }

                @Override
                public ValueTag tagOf(Object value) {
                    Intrinsics.checkNotNullParameter(value, "value");
                    Intrinsics.reifiedOperationMarker(1, "T");
                    return ((TaggedValue) value).getTag();
                }

                @Override
                public boolean handlesTag(ValueTag tag) {
                    Intrinsics.checkNotNullParameter(tag, "tag");
                    return ((Boolean) handlesTagFunc.invoke(tag)).booleanValue();
                }

                @Override
                public TaggedValue readValue(IppInputStream input, ValueTag startTag) {
                    Intrinsics.checkNotNullParameter(input, "input");
                    Intrinsics.checkNotNullParameter(startTag, "startTag");
                    return (TaggedValue) readAttrFunc.invoke(input, startTag);
                }

                @Override
                public void writeValue(IppOutputStream output, Object value) {
                    Intrinsics.checkNotNullParameter(output, "output");
                    Intrinsics.checkNotNullParameter(value, "value");
                    Function2 function2 = writeAttrFunc;
                    Intrinsics.reifiedOperationMarker(1, "T");
                    function2.invoke(output, (TaggedValue) value);
                }
            };
        }

        public final <T> Codec<T> invoke(final ValueTag valueTag, final Function2<? super IppInputStream, ? super ValueTag, ? extends T> readAttrFunc, final Function2<? super IppOutputStream, ? super T, Unit> writeAttrFunc) {
            Intrinsics.checkNotNullParameter(valueTag, "valueTag");
            Intrinsics.checkNotNullParameter(readAttrFunc, "readAttrFunc");
            Intrinsics.checkNotNullParameter(writeAttrFunc, "writeAttrFunc");
            Intrinsics.needClassReification();
            return new Codec<T>() {
                private final Class<T> cls;

                {
                    Intrinsics.reifiedOperationMarker(4, "T");
                    this.cls = Object.class;
                }

                @Override
                public Class<T> getCls() {
                    return this.cls;
                }

                @Override
                public ValueTag tagOf(Object value) {
                    Intrinsics.checkNotNullParameter(value, "value");
                    return valueTag;
                }

                @Override
                public boolean handlesTag(ValueTag tag) {
                    Intrinsics.checkNotNullParameter(tag, "tag");
                    return Intrinsics.areEqual(valueTag, tag);
                }

                @Override
                public T readValue(IppInputStream input, ValueTag startTag) {
                    Intrinsics.checkNotNullParameter(input, "input");
                    Intrinsics.checkNotNullParameter(startTag, "startTag");
                    return (T) readAttrFunc.invoke(input, startTag);
                }

                @Override
                public void writeValue(IppOutputStream output, Object value) {
                    Intrinsics.checkNotNullParameter(output, "output");
                    Intrinsics.checkNotNullParameter(value, "value");
                    Function2 function2 = writeAttrFunc;
                    Intrinsics.reifiedOperationMarker(1, "T");
                    function2.invoke(output, value);
                }
            };
        }
    }
}
