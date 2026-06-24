package org.apache.log4j.net;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class TelnetAppender extends AppenderSkeleton {
    private int port = 23;

    private SocketHandler f2797sh;

    @Override
    public boolean requiresLayout() {
        return true;
    }

    @Override
    public void activateOptions() {
        try {
            SocketHandler socketHandler = new SocketHandler(this.port);
            this.f2797sh = socketHandler;
            socketHandler.start();
        } catch (InterruptedIOException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (RuntimeException e3) {
            e3.printStackTrace();
        }
        super.activateOptions();
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        this.port = i;
    }

    @Override
    public void close() {
        SocketHandler socketHandler = this.f2797sh;
        if (socketHandler != null) {
            socketHandler.close();
            try {
                this.f2797sh.join();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        String[] throwableStrRep;
        SocketHandler socketHandler = this.f2797sh;
        if (socketHandler != null) {
            socketHandler.send(this.layout.format(loggingEvent));
            if (!this.layout.ignoresThrowable() || (throwableStrRep = loggingEvent.getThrowableStrRep()) == null) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (String str : throwableStrRep) {
                stringBuffer.append(str);
                stringBuffer.append("\r\n");
            }
            this.f2797sh.send(stringBuffer.toString());
        }
    }

    protected class SocketHandler extends Thread {
        private ServerSocket serverSocket;
        private Vector writers = new Vector();
        private Vector connections = new Vector();
        private int MAX_CONNECTIONS = 20;

        public void finalize() {
            close();
        }

        public void close() {
            synchronized (this) {
                Enumeration enumerationElements = this.connections.elements();
                while (enumerationElements.hasMoreElements()) {
                    try {
                        ((Socket) enumerationElements.nextElement()).close();
                    } catch (InterruptedIOException unused) {
                        Thread.currentThread().interrupt();
                    } catch (IOException | RuntimeException unused2) {
                    }
                }
            }
            try {
                this.serverSocket.close();
            } catch (InterruptedIOException unused3) {
                Thread.currentThread().interrupt();
            } catch (IOException | RuntimeException unused4) {
            }
        }

        public synchronized void send(String str) {
            Iterator it = this.connections.iterator();
            Iterator it2 = this.writers.iterator();
            while (it2.hasNext()) {
                it.next();
                PrintWriter printWriter = (PrintWriter) it2.next();
                printWriter.print(str);
                if (printWriter.checkError()) {
                    it.remove();
                    it2.remove();
                }
            }
        }

        @Override
        public void run() {
            while (!this.serverSocket.isClosed()) {
                try {
                    Socket socketAccept = this.serverSocket.accept();
                    PrintWriter printWriter = new PrintWriter(socketAccept.getOutputStream());
                    if (this.connections.size() < this.MAX_CONNECTIONS) {
                        synchronized (this) {
                            this.connections.addElement(socketAccept);
                            this.writers.addElement(printWriter);
                            printWriter.print(new StringBuffer().append("TelnetAppender v1.0 (").append(this.connections.size()).append(" active connections)\r\n\r\n").toString());
                            printWriter.flush();
                        }
                    } else {
                        printWriter.print("Too many connections.\r\n");
                        printWriter.flush();
                        socketAccept.close();
                    }
                } catch (Exception e) {
                    if ((e instanceof InterruptedIOException) || (e instanceof InterruptedException)) {
                        Thread.currentThread().interrupt();
                    }
                    if (!this.serverSocket.isClosed()) {
                        LogLog.error("Encountered error while in SocketHandler loop.", e);
                    }
                }
            }
            try {
                this.serverSocket.close();
            } catch (InterruptedIOException unused) {
                Thread.currentThread().interrupt();
            } catch (IOException unused2) {
            }
        }

        public SocketHandler(int i) throws IOException {
            this.serverSocket = new ServerSocket(i);
            setName(new StringBuffer("TelnetAppender-").append(getName()).append("-").append(i).toString());
        }
    }
}
