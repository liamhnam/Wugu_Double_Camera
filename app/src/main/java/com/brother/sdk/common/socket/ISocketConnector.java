package com.brother.sdk.common.socket;

import com.brother.sdk.common.IConnector;

public interface ISocketConnector extends IConnector {

    public static final String f506ID = "ISocketConnector";

    ISocket createConnectionSocket(SocketClient socketClient);
}
