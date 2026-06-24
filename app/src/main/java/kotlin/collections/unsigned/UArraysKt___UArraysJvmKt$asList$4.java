package kotlin.collections.unsigned;

import com.arthenica.ffmpegkit.MediaInformation;
import com.arthenica.ffmpegkit.StreamInformation;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;

@Metadata(m1292d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u001b\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0006H\u0096\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\b\u0010\u0015\u001a\u00020\nH\u0016J\u001a\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0018"}, m1293d2 = {"kotlin/collections/unsigned/UArraysKt___UArraysJvmKt$asList$4", "Lkotlin/collections/AbstractList;", "Lkotlin/UShort;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", MediaInformation.KEY_SIZE, "", "getSize", "()I", "contains", "", "element", "contains-xj2QHRw", "(S)Z", "get", StreamInformation.KEY_INDEX, "get-Mh2AYeg", "(I)S", "indexOf", "indexOf-xj2QHRw", "(S)I", "isEmpty", "lastIndexOf", "lastIndexOf-xj2QHRw", "kotlin-stdlib"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public final class UArraysKt___UArraysJvmKt$asList$4 extends AbstractList<UShort> implements RandomAccess {
    final short[] $this_asList;

    UArraysKt___UArraysJvmKt$asList$4(short[] sArr) {
        this.$this_asList = sArr;
    }

    @Override
    public final boolean contains(Object obj) {
        if (obj instanceof UShort) {
            return m2291containsxj2QHRw(((UShort) obj).getData());
        }
        return false;
    }

    @Override
    public Object get(int i) {
        return UShort.m2047boximpl(m2292getMh2AYeg(i));
    }

    @Override
    public final int indexOf(Object obj) {
        if (obj instanceof UShort) {
            return m2293indexOfxj2QHRw(((UShort) obj).getData());
        }
        return -1;
    }

    @Override
    public final int lastIndexOf(Object obj) {
        if (obj instanceof UShort) {
            return m2294lastIndexOfxj2QHRw(((UShort) obj).getData());
        }
        return -1;
    }

    @Override
    public int getSize() {
        return UShortArray.m2112getSizeimpl(this.$this_asList);
    }

    @Override
    public boolean isEmpty() {
        return UShortArray.m2114isEmptyimpl(this.$this_asList);
    }

    public boolean m2291containsxj2QHRw(short element) {
        return UShortArray.m2107containsxj2QHRw(this.$this_asList, element);
    }

    public short m2292getMh2AYeg(int index) {
        return UShortArray.m2111getMh2AYeg(this.$this_asList, index);
    }

    public int m2293indexOfxj2QHRw(short element) {
        return ArraysKt.indexOf(this.$this_asList, element);
    }

    public int m2294lastIndexOfxj2QHRw(short element) {
        return ArraysKt.lastIndexOf(this.$this_asList, element);
    }
}
