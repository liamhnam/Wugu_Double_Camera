package com.faceunity.core.controller.prop;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m1293d2 = {"<anonymous>", "", "invoke"}, m1294k = 3, m1295mv = {1, 1, 16})
final class PropContainerController$setItemParamGL$unit$1 extends Lambda implements Function0<Unit> {
    final String $key;
    final long $propId;
    final Object $value;
    final PropContainerController this$0;

    PropContainerController$setItemParamGL$unit$1(PropContainerController propContainerController, long j, String str, Object obj) {
        super(0);
        this.this$0 = propContainerController;
        this.$propId = j;
        this.$key = str;
        this.$value = obj;
    }

    @Override
    public Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    public final void invoke2() {
        final Integer num = this.this$0.getPropIdMap().get(Long.valueOf(this.$propId));
        if (num != null) {
            num.intValue();
            this.this$0.doGLThreadAction(new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.this$0.this$0.itemSetParam(num.intValue(), this.this$0.$key, this.this$0.$value);
                }
            });
        }
    }
}
