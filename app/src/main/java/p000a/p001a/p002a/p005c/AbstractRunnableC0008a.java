package p000a.p001a.p002a.p005c;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class AbstractRunnableC0008a implements Runnable {
    public static long EXEC_ONCE;
    private long initDelay = 0;
    private long period = EXEC_ONCE;
    private String execTime = null;
    public TimeUnit periodUnit = TimeUnit.MILLISECONDS;

    public static long getTimeMillis(String str) {
        try {
            return new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yy-MM-dd").format(new Date()) + " " + str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public long getInitDelay() {
        String str = this.execTime;
        if (str != null) {
            long timeMillis = getTimeMillis(str) - System.currentTimeMillis();
            this.initDelay = timeMillis;
            if (timeMillis <= 0) {
                timeMillis += 86400000;
            }
            this.initDelay = timeMillis;
        }
        return this.initDelay;
    }

    public long getPeriod() {
        return this.period;
    }

    @Override
    public abstract void run();

    public AbstractRunnableC0008a setInitDelay(long j) {
        this.initDelay = j;
        return this;
    }

    public AbstractRunnableC0008a setTimer(long j, long j2) {
        this.initDelay = j;
        this.period = j2;
        return this;
    }

    public AbstractRunnableC0008a setTimer(String str) {
        this.execTime = str;
        this.period = 86400000L;
        return this;
    }
}
