package org.apache.log4j.chainsaw;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

class LoggingReceiver extends Thread {
    private static final Logger LOG;
    static Class class$org$apache$log4j$chainsaw$LoggingReceiver;
    private MyTableModel mModel;
    private ServerSocket mSvrSock;

    static {
        Class clsClass$ = class$org$apache$log4j$chainsaw$LoggingReceiver;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.chainsaw.LoggingReceiver");
            class$org$apache$log4j$chainsaw$LoggingReceiver = clsClass$;
        }
        LOG = Logger.getLogger(clsClass$);
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    private class Slurper implements Runnable {
        private final Socket mClient;

        Slurper(Socket socket) {
            this.mClient = socket;
        }

        @Override
        public void run() {
            LoggingReceiver.LOG.debug("Starting to get data");
            try {
                while (true) {
                    LoggingReceiver.this.mModel.addEvent(new EventDetails((LoggingEvent) new ObjectInputStream(this.mClient.getInputStream()).readObject()));
                }
            } catch (EOFException unused) {
                LoggingReceiver.LOG.info("Reached EOF, closing connection");
                try {
                    this.mClient.close();
                } catch (IOException e) {
                    LoggingReceiver.LOG.warn("Error closing connection", e);
                }
            } catch (SocketException unused2) {
                LoggingReceiver.LOG.info("Caught SocketException, closing connection");
                this.mClient.close();
            } catch (IOException e2) {
                LoggingReceiver.LOG.warn("Got IOException, closing connection", e2);
                this.mClient.close();
            } catch (ClassNotFoundException e3) {
                LoggingReceiver.LOG.warn("Got ClassNotFoundException, closing connection", e3);
                this.mClient.close();
            }
        }
    }

    LoggingReceiver(MyTableModel myTableModel, int i) throws IOException {
        setDaemon(true);
        this.mModel = myTableModel;
        this.mSvrSock = new ServerSocket(i);
    }

    @Override
    public void run() {
        LOG.info("Thread started");
        while (true) {
            try {
                Logger logger = LOG;
                logger.debug("Waiting for a connection");
                Socket socketAccept = this.mSvrSock.accept();
                logger.debug(new StringBuffer().append("Got a connection from ").append(socketAccept.getInetAddress().getHostName()).toString());
                Thread thread = new Thread(new Slurper(socketAccept));
                thread.setDaemon(true);
                thread.start();
            } catch (IOException e) {
                LOG.error("Error in accepting connections, stopping.", e);
                return;
            }
        }
    }
}
