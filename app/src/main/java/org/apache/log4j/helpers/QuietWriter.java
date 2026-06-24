package org.apache.log4j.helpers;

import java.io.FilterWriter;
import java.io.Writer;
import org.apache.log4j.spi.ErrorHandler;

public class QuietWriter extends FilterWriter {
    protected ErrorHandler errorHandler;

    public QuietWriter(Writer writer, ErrorHandler errorHandler) {
        super(writer);
        setErrorHandler(errorHandler);
    }

    @Override
    public void write(String str) {
        if (str != null) {
            try {
                this.out.write(str);
            } catch (Exception e) {
                this.errorHandler.error(new StringBuffer("Failed to write [").append(str).append("].").toString(), e, 1);
            }
        }
    }

    @Override
    public void flush() {
        try {
            this.out.flush();
        } catch (Exception e) {
            this.errorHandler.error("Failed to flush writer,", e, 2);
        }
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        if (errorHandler == null) {
            throw new IllegalArgumentException("Attempted to set null ErrorHandler.");
        }
        this.errorHandler = errorHandler;
    }
}
