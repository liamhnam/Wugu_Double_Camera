package org.apache.log4j.net;

import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class SocketAppender extends AppenderSkeleton {
    public static final int DEFAULT_PORT = 4560;
    static final int DEFAULT_RECONNECTION_DELAY = 30000;
    private static final int RESET_FREQUENCY = 1;
    public static final String ZONE = "_log4j_obj_tcpconnect_appender.local.";
    InetAddress address;
    private boolean advertiseViaMulticastDNS;
    private String application;
    private Connector connector;
    int counter;
    boolean locationInfo;
    ObjectOutputStream oos;
    int port;
    int reconnectionDelay;
    String remoteHost;
    private ZeroConfSupport zeroConf;

    @Override
    public boolean requiresLayout() {
        return false;
    }

    public SocketAppender() {
        this.port = DEFAULT_PORT;
        this.reconnectionDelay = 30000;
        this.locationInfo = false;
        this.counter = 0;
    }

    public SocketAppender(InetAddress inetAddress, int i) {
        this.port = DEFAULT_PORT;
        this.reconnectionDelay = 30000;
        this.locationInfo = false;
        this.counter = 0;
        this.address = inetAddress;
        this.remoteHost = inetAddress.getHostName();
        this.port = i;
        connect(inetAddress, i);
    }

    public SocketAppender(String str, int i) {
        this.reconnectionDelay = 30000;
        this.locationInfo = false;
        this.counter = 0;
        this.port = i;
        InetAddress addressByName = getAddressByName(str);
        this.address = addressByName;
        this.remoteHost = str;
        connect(addressByName, i);
    }

    @Override
    public void activateOptions() {
        if (this.advertiseViaMulticastDNS) {
            ZeroConfSupport zeroConfSupport = new ZeroConfSupport(ZONE, this.port, getName());
            this.zeroConf = zeroConfSupport;
            zeroConfSupport.advertise();
        }
        connect(this.address, this.port);
    }

    @Override
    public synchronized void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        if (this.advertiseViaMulticastDNS) {
            this.zeroConf.unadvertise();
        }
        cleanUp();
    }

    public void cleanUp() {
        ObjectOutputStream objectOutputStream = this.oos;
        if (objectOutputStream != null) {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error("Could not close oos.", e);
            }
            this.oos = null;
        }
        Connector connector = this.connector;
        if (connector != null) {
            connector.interrupted = true;
            this.connector = null;
        }
    }

    void connect(InetAddress inetAddress, int i) {
        String string;
        if (this.address == null) {
            return;
        }
        try {
            cleanUp();
            this.oos = new ObjectOutputStream(new Socket(inetAddress, i).getOutputStream());
        } catch (IOException e) {
            if (e instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            String string2 = new StringBuffer("Could not connect to remote log4j server at [").append(inetAddress.getHostName()).append("].").toString();
            if (this.reconnectionDelay > 0) {
                string = new StringBuffer().append(string2).append(" We will try again later.").toString();
                fireConnector();
            } else {
                string = new StringBuffer().append(string2).append(" We are not retrying.").toString();
                this.errorHandler.error(string, e, 0);
            }
            LogLog.error(string);
        }
    }

    @Override
    public void append(LoggingEvent loggingEvent) {
        if (loggingEvent == null) {
            return;
        }
        if (this.address == null) {
            this.errorHandler.error(new StringBuffer("No remote host is set for SocketAppender named \"").append(this.name).append("\".").toString());
            return;
        }
        if (this.oos != null) {
            try {
                if (this.locationInfo) {
                    loggingEvent.getLocationInformation();
                }
                String str = this.application;
                if (str != null) {
                    loggingEvent.setProperty(MimeTypes.BASE_TYPE_APPLICATION, str);
                }
                loggingEvent.getNDC();
                loggingEvent.getThreadName();
                loggingEvent.getMDCCopy();
                loggingEvent.getRenderedMessage();
                loggingEvent.getThrowableStrRep();
                this.oos.writeObject(loggingEvent);
                this.oos.flush();
                int i = this.counter + 1;
                this.counter = i;
                if (i >= 1) {
                    this.counter = 0;
                    this.oos.reset();
                }
            } catch (IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                this.oos = null;
                LogLog.warn(new StringBuffer("Detected problem with connection: ").append(e).toString());
                if (this.reconnectionDelay > 0) {
                    fireConnector();
                } else {
                    this.errorHandler.error("Detected problem with connection, not reconnecting.", e, 0);
                }
            }
        }
    }

    public void setAdvertiseViaMulticastDNS(boolean z) {
        this.advertiseViaMulticastDNS = z;
    }

    public boolean isAdvertiseViaMulticastDNS() {
        return this.advertiseViaMulticastDNS;
    }

    void fireConnector() {
        if (this.connector == null) {
            LogLog.debug("Starting a new connector thread.");
            Connector connector = new Connector();
            this.connector = connector;
            connector.setDaemon(true);
            this.connector.setPriority(1);
            this.connector.start();
        }
    }

    static InetAddress getAddressByName(String str) {
        try {
            return InetAddress.getByName(str);
        } catch (Exception e) {
            if ((e instanceof InterruptedIOException) || (e instanceof InterruptedException)) {
                Thread.currentThread().interrupt();
            }
            LogLog.error(new StringBuffer("Could not find address of [").append(str).append("].").toString(), e);
            return null;
        }
    }

    public void setRemoteHost(String str) {
        this.address = getAddressByName(str);
        this.remoteHost = str;
    }

    public String getRemoteHost() {
        return this.remoteHost;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public int getPort() {
        return this.port;
    }

    public void setLocationInfo(boolean z) {
        this.locationInfo = z;
    }

    public boolean getLocationInfo() {
        return this.locationInfo;
    }

    public void setApplication(String str) {
        this.application = str;
    }

    public String getApplication() {
        return this.application;
    }

    public void setReconnectionDelay(int i) {
        this.reconnectionDelay = i;
    }

    public int getReconnectionDelay() {
        return this.reconnectionDelay;
    }

    class Connector extends Thread {
        boolean interrupted = false;

        Connector() {
        }

        @Override
        public void run() {
            while (!this.interrupted) {
                try {
                    sleep(SocketAppender.this.reconnectionDelay);
                    LogLog.debug(new StringBuffer().append("Attempting connection to ").append(SocketAppender.this.address.getHostName()).toString());
                    Socket socket = new Socket(SocketAppender.this.address, SocketAppender.this.port);
                    synchronized (this) {
                        SocketAppender.this.oos = new ObjectOutputStream(socket.getOutputStream());
                        SocketAppender.this.connector = null;
                        LogLog.debug("Connection established. Exiting connector thread.");
                    }
                    return;
                } catch (ConnectException unused) {
                    LogLog.debug(new StringBuffer("Remote host ").append(SocketAppender.this.address.getHostName()).append(" refused connection.").toString());
                } catch (IOException e) {
                    if (e instanceof InterruptedIOException) {
                        Thread.currentThread().interrupt();
                    }
                    LogLog.debug(new StringBuffer("Could not connect to ").append(SocketAppender.this.address.getHostName()).append(". Exception is ").append(e).toString());
                } catch (InterruptedException unused2) {
                    LogLog.debug("Connector interrupted. Leaving loop.");
                    return;
                }
            }
        }
    }
}
