package android.serialport;

import android.util.Log;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Iterator;
import java.util.Vector;

public class SerialPortFinder {
    private static final String TAG = "SerialPort";
    private Vector<Driver> mDrivers = null;

    public class Driver {
        private String mDeviceRoot;
        Vector<File> mDevices = null;
        private String mDriverName;

        public Driver(String str, String str2) {
            this.mDriverName = str;
            this.mDeviceRoot = str2;
        }

        public Vector<File> getDevices() {
            if (this.mDevices == null) {
                this.mDevices = new Vector<>();
                File[] fileArrListFiles = new File("/dev").listFiles();
                if (fileArrListFiles != null) {
                    for (int i = 0; i < fileArrListFiles.length; i++) {
                        if (fileArrListFiles[i].getAbsolutePath().startsWith(this.mDeviceRoot)) {
                            Log.d(SerialPortFinder.TAG, "Found new device: " + fileArrListFiles[i]);
                            this.mDevices.add(fileArrListFiles[i]);
                        }
                    }
                }
            }
            return this.mDevices;
        }

        public String getName() {
            return this.mDriverName;
        }
    }

    public String[] getAllDevices() {
        Vector vector = new Vector();
        try {
            for (Driver driver : getDrivers()) {
                Iterator<File> it = driver.getDevices().iterator();
                while (it.hasNext()) {
                    vector.add(String.format("%s (%s)", it.next().getName(), driver.getName()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (String[]) vector.toArray(new String[vector.size()]);
    }

    public String[] getAllDevicesPath() {
        Vector vector = new Vector();
        try {
            Iterator<Driver> it = getDrivers().iterator();
            while (it.hasNext()) {
                Iterator<File> it2 = it.next().getDevices().iterator();
                while (it2.hasNext()) {
                    vector.add(it2.next().getAbsolutePath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (String[]) vector.toArray(new String[vector.size()]);
    }

    Vector<Driver> getDrivers() throws IOException {
        if (this.mDrivers == null) {
            this.mDrivers = new Vector<>();
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader("/proc/tty/drivers"));
            while (true) {
                String line = lineNumberReader.readLine();
                if (line == null) {
                    break;
                }
                String strTrim = line.substring(0, 21).trim();
                String[] strArrSplit = line.split(" +");
                if (strArrSplit.length >= 5 && strArrSplit[strArrSplit.length - 1].equals("serial")) {
                    Log.d(TAG, "Found new driver " + strTrim + " on " + strArrSplit[strArrSplit.length - 4]);
                    this.mDrivers.add(new Driver(strTrim, strArrSplit[strArrSplit.length - 4]));
                }
            }
            lineNumberReader.close();
        }
        return this.mDrivers;
    }
}
