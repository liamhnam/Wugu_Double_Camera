package org.apache.log4j.xml;

import org.apache.log4j.helpers.LogLog;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class SAXErrorHandler implements ErrorHandler {
    @Override
    public void error(SAXParseException sAXParseException) {
        emitMessage("Continuable parsing error ", sAXParseException);
    }

    @Override
    public void fatalError(SAXParseException sAXParseException) {
        emitMessage("Fatal parsing error ", sAXParseException);
    }

    @Override
    public void warning(SAXParseException sAXParseException) {
        emitMessage("Parsing warning ", sAXParseException);
    }

    private static void emitMessage(String str, SAXParseException sAXParseException) {
        LogLog.warn(new StringBuffer().append(str).append(sAXParseException.getLineNumber()).append(" and column ").append(sAXParseException.getColumnNumber()).toString());
        LogLog.warn(sAXParseException.getMessage(), sAXParseException.getException());
    }
}
