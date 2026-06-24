package com.brother.sdk.common.socket.print.lpr;

import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.print.PrintState;
import java.io.IOException;

public abstract class LprCommand {
    abstract void cancel() throws IOException;

    abstract PrintState send(ISocket iSocket) throws IOException;

    LprCommand() {
    }
}
