package org.apache.log4j.net;

import com.tom_roush.fontbox.ttf.KerningTable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.SyslogQuietWriter;
import org.apache.log4j.helpers.SyslogWriter;
import org.apache.log4j.spi.LoggingEvent;

public class SyslogAppender extends AppenderSkeleton {
    protected static final int FACILITY_OI = 1;
    public static final int LOG_AUTH = 32;
    public static final int LOG_AUTHPRIV = 80;
    public static final int LOG_CRON = 72;
    public static final int LOG_DAEMON = 24;
    public static final int LOG_FTP = 88;
    public static final int LOG_KERN = 0;
    public static final int LOG_LOCAL0 = 128;
    public static final int LOG_LOCAL1 = 136;
    public static final int LOG_LOCAL2 = 144;
    public static final int LOG_LOCAL3 = 152;
    public static final int LOG_LOCAL4 = 160;
    public static final int LOG_LOCAL5 = 168;
    public static final int LOG_LOCAL6 = 176;
    public static final int LOG_LOCAL7 = 184;
    public static final int LOG_LPR = 48;
    public static final int LOG_MAIL = 16;
    public static final int LOG_NEWS = 56;
    public static final int LOG_SYSLOG = 40;
    public static final int LOG_USER = 8;
    public static final int LOG_UUCP = 64;
    protected static final int SYSLOG_HOST_OI = 0;
    static final String TAB = "    ";
    private final SimpleDateFormat dateFormat;
    boolean facilityPrinting;
    String facilityStr;
    private boolean header;
    private boolean layoutHeaderChecked;
    private String localHostname;
    SyslogQuietWriter sqw;
    int syslogFacility;
    String syslogHost;

    public static String getFacilityString(int i) {
        switch (i) {
            case 0:
                return KerningTable.TAG;
            case 8:
                return "user";
            case 16:
                return "mail";
            case 24:
                return "daemon";
            case 32:
                return "auth";
            case 40:
                return "syslog";
            case 48:
                return "lpr";
            case 56:
                return "news";
            case 64:
                return "uucp";
            case 72:
                return "cron";
            case 80:
                return "authpriv";
            case 88:
                return "ftp";
            case 128:
                return "local0";
            case LOG_LOCAL1:
                return "local1";
            case LOG_LOCAL2:
                return "local2";
            case LOG_LOCAL3:
                return "local3";
            case 160:
                return "local4";
            case LOG_LOCAL5:
                return "local5";
            case 176:
                return "local6";
            case LOG_LOCAL7:
                return "local7";
            default:
                return null;
        }
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }

    public SyslogAppender() {
        this.syslogFacility = 8;
        this.facilityPrinting = false;
        this.header = false;
        this.dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss ", Locale.ENGLISH);
        this.layoutHeaderChecked = false;
        initSyslogFacilityStr();
    }

    public SyslogAppender(Layout layout, int i) {
        this.syslogFacility = 8;
        this.facilityPrinting = false;
        this.header = false;
        this.dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss ", Locale.ENGLISH);
        this.layoutHeaderChecked = false;
        this.layout = layout;
        this.syslogFacility = i;
        initSyslogFacilityStr();
    }

    public SyslogAppender(Layout layout, String str, int i) {
        this(layout, i);
        setSyslogHost(str);
    }

    @Override
    public synchronized void close() {
        this.closed = true;
        if (this.sqw != null) {
            try {
                if (this.layoutHeaderChecked && this.layout != null && this.layout.getFooter() != null) {
                    sendLayoutMessage(this.layout.getFooter());
                }
                this.sqw.close();
                this.sqw = null;
            } catch (InterruptedIOException unused) {
                Thread.currentThread().interrupt();
                this.sqw = null;
            } catch (IOException unused2) {
                this.sqw = null;
            }
        }
    }

    private void initSyslogFacilityStr() {
        String facilityString = getFacilityString(this.syslogFacility);
        this.facilityStr = facilityString;
        if (facilityString == null) {
            System.err.println(new StringBuffer("\"").append(this.syslogFacility).append("\" is an unknown syslog facility. Defaulting to \"USER\".").toString());
            this.syslogFacility = 8;
            this.facilityStr = "user:";
            return;
        }
        this.facilityStr = new StringBuffer().append(this.facilityStr).append(":").toString();
    }

    public static int getFacility(String str) {
        if (str != null) {
            str = str.trim();
        }
        if ("KERN".equalsIgnoreCase(str)) {
            return 0;
        }
        if ("USER".equalsIgnoreCase(str)) {
            return 8;
        }
        if ("MAIL".equalsIgnoreCase(str)) {
            return 16;
        }
        if ("DAEMON".equalsIgnoreCase(str)) {
            return 24;
        }
        if ("AUTH".equalsIgnoreCase(str)) {
            return 32;
        }
        if ("SYSLOG".equalsIgnoreCase(str)) {
            return 40;
        }
        if ("LPR".equalsIgnoreCase(str)) {
            return 48;
        }
        if ("NEWS".equalsIgnoreCase(str)) {
            return 56;
        }
        if ("UUCP".equalsIgnoreCase(str)) {
            return 64;
        }
        if ("CRON".equalsIgnoreCase(str)) {
            return 72;
        }
        if ("AUTHPRIV".equalsIgnoreCase(str)) {
            return 80;
        }
        if ("FTP".equalsIgnoreCase(str)) {
            return 88;
        }
        if ("LOCAL0".equalsIgnoreCase(str)) {
            return 128;
        }
        if ("LOCAL1".equalsIgnoreCase(str)) {
            return LOG_LOCAL1;
        }
        if ("LOCAL2".equalsIgnoreCase(str)) {
            return LOG_LOCAL2;
        }
        if ("LOCAL3".equalsIgnoreCase(str)) {
            return LOG_LOCAL3;
        }
        if ("LOCAL4".equalsIgnoreCase(str)) {
            return 160;
        }
        if ("LOCAL5".equalsIgnoreCase(str)) {
            return LOG_LOCAL5;
        }
        if ("LOCAL6".equalsIgnoreCase(str)) {
            return 176;
        }
        if ("LOCAL7".equalsIgnoreCase(str)) {
            return LOG_LOCAL7;
        }
        return -1;
    }

    private void splitPacket(String str, String str2) {
        if (str2.getBytes().length <= 1019) {
            this.sqw.write(str2);
            return;
        }
        int length = str.length() + ((str2.length() - str.length()) / 2);
        splitPacket(str, new StringBuffer().append(str2.substring(0, length)).append("...").toString());
        splitPacket(str, new StringBuffer().append(str).append("...").append(str2.substring(length)).toString());
    }

    @Override
    public void append(LoggingEvent loggingEvent) {
        String string;
        String[] throwableStrRep;
        if (isAsSevereAsThreshold(loggingEvent.getLevel())) {
            if (this.sqw == null) {
                this.errorHandler.error(new StringBuffer("No syslog host is set for SyslogAppedender named \"").append(this.name).append("\".").toString());
                return;
            }
            if (!this.layoutHeaderChecked) {
                if (this.layout != null && this.layout.getHeader() != null) {
                    sendLayoutMessage(this.layout.getHeader());
                }
                this.layoutHeaderChecked = true;
            }
            String packetHeader = getPacketHeader(loggingEvent.timeStamp);
            if (this.layout == null) {
                string = String.valueOf(loggingEvent.getMessage());
            } else {
                string = this.layout.format(loggingEvent);
            }
            if (this.facilityPrinting || packetHeader.length() > 0) {
                StringBuffer stringBuffer = new StringBuffer(packetHeader);
                if (this.facilityPrinting) {
                    stringBuffer.append(this.facilityStr);
                }
                stringBuffer.append(string);
                string = stringBuffer.toString();
            }
            this.sqw.setLevel(loggingEvent.getLevel().getSyslogEquivalent());
            if (string.length() > 256) {
                splitPacket(packetHeader, string);
            } else {
                this.sqw.write(string);
            }
            if ((this.layout == null || this.layout.ignoresThrowable()) && (throwableStrRep = loggingEvent.getThrowableStrRep()) != null) {
                for (int i = 0; i < throwableStrRep.length; i++) {
                    if (throwableStrRep[i].startsWith("\t")) {
                        this.sqw.write(new StringBuffer().append(packetHeader).append(TAB).append(throwableStrRep[i].substring(1)).toString());
                    } else {
                        this.sqw.write(new StringBuffer().append(packetHeader).append(throwableStrRep[i]).toString());
                    }
                }
            }
        }
    }

    @Override
    public void activateOptions() {
        if (this.header) {
            getLocalHostname();
        }
        if (this.layout != null && this.layout.getHeader() != null) {
            sendLayoutMessage(this.layout.getHeader());
        }
        this.layoutHeaderChecked = true;
    }

    public void setSyslogHost(String str) {
        this.sqw = new SyslogQuietWriter(new SyslogWriter(str), this.syslogFacility, this.errorHandler);
        this.syslogHost = str;
    }

    public String getSyslogHost() {
        return this.syslogHost;
    }

    public void setFacility(String str) {
        if (str == null) {
            return;
        }
        int facility = getFacility(str);
        this.syslogFacility = facility;
        if (facility == -1) {
            System.err.println(new StringBuffer("[").append(str).append("] is an unknown syslog facility. Defaulting to [USER].").toString());
            this.syslogFacility = 8;
        }
        initSyslogFacilityStr();
        SyslogQuietWriter syslogQuietWriter = this.sqw;
        if (syslogQuietWriter != null) {
            syslogQuietWriter.setSyslogFacility(this.syslogFacility);
        }
    }

    public String getFacility() {
        return getFacilityString(this.syslogFacility);
    }

    public void setFacilityPrinting(boolean z) {
        this.facilityPrinting = z;
    }

    public boolean getFacilityPrinting() {
        return this.facilityPrinting;
    }

    public final boolean getHeader() {
        return this.header;
    }

    public final void setHeader(boolean z) {
        this.header = z;
    }

    private String getLocalHostname() {
        if (this.localHostname == null) {
            try {
                this.localHostname = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException unused) {
                this.localHostname = "UNKNOWN_HOST";
            }
        }
        return this.localHostname;
    }

    private String getPacketHeader(long j) {
        if (!this.header) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(this.dateFormat.format(new Date(j)));
        if (stringBuffer.charAt(4) == '0') {
            stringBuffer.setCharAt(4, ' ');
        }
        stringBuffer.append(getLocalHostname());
        stringBuffer.append(' ');
        return stringBuffer.toString();
    }

    private void sendLayoutMessage(String str) {
        if (this.sqw != null) {
            String packetHeader = getPacketHeader(new Date().getTime());
            if (this.facilityPrinting || packetHeader.length() > 0) {
                StringBuffer stringBuffer = new StringBuffer(packetHeader);
                if (this.facilityPrinting) {
                    stringBuffer.append(this.facilityStr);
                }
                stringBuffer.append(str);
                str = stringBuffer.toString();
            }
            this.sqw.setLevel(6);
            this.sqw.write(str);
        }
    }
}
