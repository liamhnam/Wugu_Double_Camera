package com.tom_roush.pdfbox.contentstream.operator.state;

import java.io.IOException;

public final class EmptyGraphicsStackException extends IOException {
    private static final long serialVersionUID = 1;

    EmptyGraphicsStackException() {
        super("Cannot execute restore, the graphics stack is empty");
    }
}
