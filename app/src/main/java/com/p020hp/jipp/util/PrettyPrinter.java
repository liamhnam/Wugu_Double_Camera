package com.p020hp.jipp.util;

import com.tom_roush.pdfbox.pdmodel.interactive.action.PDWindowsLaunchParams;
import java.util.Iterator;
import java.util.Stack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.eclipse.paho.client.mqttv3.MqttTopic;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB'\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001f\u0010\r\u001a\u00020\u00002\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000f\"\u00020\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00002\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u0012J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0006\u0010\u0015\u001a\u00020\u0000J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0001H\u0002J\b\u0010\u0018\u001a\u00020\u0014H\u0002J\u001a\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007J\u0006\u0010\u001a\u001a\u00020\u0003R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m1293d2 = {"Lcom/hp/jipp/util/PrettyPrinter;", "", "prefix", "", "style", "Lcom/hp/jipp/util/PrettyPrinter$Companion$Style;", "indent", "maxWidth", "", "(Ljava/lang/String;Lcom/hp/jipp/util/PrettyPrinter$Companion$Style;Ljava/lang/String;I)V", "mGroups", "Ljava/util/Stack;", "Lcom/hp/jipp/util/PrettyPrinter$Companion$Group;", "add", "items", "", "([Ljava/lang/Object;)Lcom/hp/jipp/util/PrettyPrinter;", "addAll", "", "checkUnprinted", "", "close", "innerAdd", "item", "innerClose", PDWindowsLaunchParams.OPERATION_OPEN, "print", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PrettyPrinter {
    private static final int GROUP_SIZE_MIN_CLOSED = 2;
    private static final int GROUP_SIZE_MIN_OPEN = 3;
    private static final String NEWLINE = "\n";
    private static final int SHORT_DIVISOR = 3;
    private final String indent;
    private final Stack<Companion.Group> mGroups;
    private final int maxWidth;
    public static final Companion.Style ARRAY = new Companion.Style("[", "]", ",", " ");
    public static final Companion.Style OBJECT = new Companion.Style("{", "}", ",", " ");
    public static final Companion.Style KEY_VALUE = new Companion.Style(" ", "", MqttTopic.TOPIC_LEVEL_SEPARATOR, "");
    public static final Companion.Style SILENT = new Companion.Style("", "", ",", "");

    public final PrettyPrinter open(Companion.Style style) {
        return open$default(this, style, null, 2, null);
    }

    public PrettyPrinter(String prefix, Companion.Style style, String indent, int i) {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(indent, "indent");
        this.indent = indent;
        this.maxWidth = i;
        Stack<Companion.Group> stack = new Stack<>();
        this.mGroups = stack;
        stack.push(new Companion.Group(SILENT, "", i));
        stack.push(new Companion.Group(style, prefix, i));
    }

    public static PrettyPrinter open$default(PrettyPrinter prettyPrinter, Companion.Style style, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        return prettyPrinter.open(style, str);
    }

    public final PrettyPrinter open(Companion.Style style, String prefix) {
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        this.mGroups.push(new Companion.Group(style, prefix, this.maxWidth));
        return this;
    }

    public final PrettyPrinter close() {
        checkUnprinted();
        if (this.mGroups.size() < 3) {
            throw new IllegalArgumentException("nothing open to close");
        }
        innerClose();
        return this;
    }

    private final void innerClose() {
        Companion.Group groupPop = this.mGroups.pop();
        int size = this.mGroups.size() * (this.indent.length() - 1);
        if (groupPop.width$jipp_core() + size < this.maxWidth) {
            innerAdd(groupPop.compressed$jipp_core());
        } else {
            innerAdd(groupPop.expanded$jipp_core(size, StringsKt.replace$default(new String(new char[this.mGroups.size()]), "\u0000", this.indent, false, 4, (Object) null)));
        }
    }

    private final void checkUnprinted() {
        if (this.mGroups.size() < 2) {
            throw new IllegalArgumentException("print already called");
        }
    }

    public final PrettyPrinter addAll(Iterable<? extends Object> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        checkUnprinted();
        Iterator<? extends Object> it = items.iterator();
        while (it.hasNext()) {
            innerAdd(it.next());
        }
        return this;
    }

    public final PrettyPrinter add(Object... items) {
        Intrinsics.checkNotNullParameter(items, "items");
        checkUnprinted();
        for (Object obj : items) {
            innerAdd(obj);
        }
        return this;
    }

    private final void innerAdd(Object item) {
        Companion.Group groupPeek = this.mGroups.peek();
        if (item instanceof PrettyPrintable) {
            ((PrettyPrintable) item).print(this);
        } else {
            groupPeek.getItems().add(item);
        }
    }

    public final String print() {
        while (this.mGroups.size() > 1) {
            innerClose();
        }
        return this.mGroups.peek().getItems().get(0).toString();
    }
}
