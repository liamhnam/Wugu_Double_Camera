package org.apache.log4j;

import java.util.Date;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class HTMLLayout extends Layout {
    public static final String LOCATION_INFO_OPTION = "LocationInfo";
    public static final String TITLE_OPTION = "Title";
    static String TRACE_PREFIX = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";
    protected final int BUF_SIZE = 256;
    protected final int MAX_CAPACITY = 1024;
    private StringBuffer sbuf = new StringBuffer(256);
    boolean locationInfo = false;
    String title = "Log4J Log Messages";

    @Override
    public void activateOptions() {
    }

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public boolean ignoresThrowable() {
        return false;
    }

    public void setLocationInfo(boolean z) {
        this.locationInfo = z;
    }

    public boolean getLocationInfo() {
        return this.locationInfo;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String format(LoggingEvent loggingEvent) {
        if (this.sbuf.capacity() > 1024) {
            this.sbuf = new StringBuffer(256);
        } else {
            this.sbuf.setLength(0);
        }
        this.sbuf.append(new StringBuffer().append(Layout.LINE_SEP).append("<tr>").append(Layout.LINE_SEP).toString());
        this.sbuf.append("<td>");
        this.sbuf.append(loggingEvent.timeStamp - LoggingEvent.getStartTime());
        this.sbuf.append(new StringBuffer("</td>").append(Layout.LINE_SEP).toString());
        String strEscapeTags = Transform.escapeTags(loggingEvent.getThreadName());
        this.sbuf.append(new StringBuffer("<td title=\"").append(strEscapeTags).append(" thread\">").toString());
        this.sbuf.append(strEscapeTags);
        this.sbuf.append(new StringBuffer("</td>").append(Layout.LINE_SEP).toString());
        this.sbuf.append("<td title=\"Level\">");
        if (loggingEvent.getLevel().equals(Level.DEBUG)) {
            this.sbuf.append("<font color=\"#339933\">");
            this.sbuf.append(Transform.escapeTags(String.valueOf(loggingEvent.getLevel())));
            this.sbuf.append("</font>");
        } else if (loggingEvent.getLevel().isGreaterOrEqual(Level.WARN)) {
            this.sbuf.append("<font color=\"#993300\"><strong>");
            this.sbuf.append(Transform.escapeTags(String.valueOf(loggingEvent.getLevel())));
            this.sbuf.append("</strong></font>");
        } else {
            this.sbuf.append(Transform.escapeTags(String.valueOf(loggingEvent.getLevel())));
        }
        this.sbuf.append(new StringBuffer("</td>").append(Layout.LINE_SEP).toString());
        String strEscapeTags2 = Transform.escapeTags(loggingEvent.getLoggerName());
        this.sbuf.append(new StringBuffer("<td title=\"").append(strEscapeTags2).append(" category\">").toString());
        this.sbuf.append(strEscapeTags2);
        this.sbuf.append(new StringBuffer("</td>").append(Layout.LINE_SEP).toString());
        if (this.locationInfo) {
            LocationInfo locationInformation = loggingEvent.getLocationInformation();
            this.sbuf.append("<td>");
            this.sbuf.append(Transform.escapeTags(locationInformation.getFileName()));
            this.sbuf.append(':');
            this.sbuf.append(locationInformation.getLineNumber());
            this.sbuf.append(new StringBuffer("</td>").append(Layout.LINE_SEP).toString());
        }
        this.sbuf.append("<td title=\"Message\">");
        this.sbuf.append(Transform.escapeTags(loggingEvent.getRenderedMessage()));
        this.sbuf.append(new StringBuffer("</td>").append(Layout.LINE_SEP).toString());
        this.sbuf.append(new StringBuffer("</tr>").append(Layout.LINE_SEP).toString());
        if (loggingEvent.getNDC() != null) {
            this.sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : xx-small;\" colspan=\"6\" title=\"Nested Diagnostic Context\">");
            this.sbuf.append(new StringBuffer("NDC: ").append(Transform.escapeTags(loggingEvent.getNDC())).toString());
            this.sbuf.append(new StringBuffer("</td></tr>").append(Layout.LINE_SEP).toString());
        }
        String[] throwableStrRep = loggingEvent.getThrowableStrRep();
        if (throwableStrRep != null) {
            this.sbuf.append("<tr><td bgcolor=\"#993300\" style=\"color:White; font-size : xx-small;\" colspan=\"6\">");
            appendThrowableAsHTML(throwableStrRep, this.sbuf);
            this.sbuf.append(new StringBuffer("</td></tr>").append(Layout.LINE_SEP).toString());
        }
        return this.sbuf.toString();
    }

    void appendThrowableAsHTML(String[] strArr, StringBuffer stringBuffer) {
        int length;
        if (strArr == null || (length = strArr.length) == 0) {
            return;
        }
        stringBuffer.append(Transform.escapeTags(strArr[0]));
        stringBuffer.append(Layout.LINE_SEP);
        for (int i = 1; i < length; i++) {
            stringBuffer.append(TRACE_PREFIX);
            stringBuffer.append(Transform.escapeTags(strArr[i]));
            stringBuffer.append(Layout.LINE_SEP);
        }
    }

    @Override
    public String getHeader() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new StringBuffer("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<html>").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<head>").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<title>").append(this.title).append("</title>").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<style type=\"text/css\">").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<!--").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("body, table {font-family: arial,sans-serif; font-size: x-small;}").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("th {background: #336699; color: #FFFFFF; text-align: left;}").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("-->").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("</style>").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("</head>").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<hr size=\"1\" noshade>").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("Log session start time ").append(new Date()).append("<br>").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<br>").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<tr>").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<th>Time</th>").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<th>Thread</th>").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<th>Level</th>").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<th>Category</th>").append(Layout.LINE_SEP).toString());
        if (this.locationInfo) {
            stringBuffer.append(new StringBuffer("<th>File:Line</th>").append(Layout.LINE_SEP).toString());
        }
        stringBuffer.append(new StringBuffer("<th>Message</th>").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("</tr>").append(Layout.LINE_SEP).toString());
        return stringBuffer.toString();
    }

    @Override
    public String getFooter() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new StringBuffer("</table>").append(Layout.LINE_SEP).toString());
        stringBuffer.append(new StringBuffer("<br>").append(Layout.LINE_SEP).toString());
        stringBuffer.append("</body></html>");
        return stringBuffer.toString();
    }
}
