package com.wugu.doublecamera.listener;

public interface IDownFileListener {
    void fail(int i);

    void progress(int i);

    void success();
}
