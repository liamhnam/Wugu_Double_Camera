package cc.uling.usdk.abs;

import cc.uling.usdk.para.BaseClsPara;

public interface ActionRunnable<T extends BaseClsPara> {
    T getValue();

    void todo(T t);
}
