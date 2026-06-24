package cc.uling.usdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.SystemClock;
import cc.uling.usdk.mgr.LogManager;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.spi.Configurator;

public class C0673b implements Thread.UncaughtExceptionHandler {

    private static C0673b f227b = new C0673b();

    private Thread.UncaughtExceptionHandler f228a;

    private Context f229c;

    private Map<String, String> f230d = new HashMap();

    private C0673b() {
    }

    public static C0673b m179a() {
        return f227b;
    }

    public static String m180a(Exception exc) {
        StringBuffer stringBuffer = new StringBuffer("Exception:\n");
        if (exc != null) {
            stringBuffer.append(exc.getClass().getName() + ":" + exc.getMessage() + "\n");
            StackTraceElement[] stackTrace = exc.getStackTrace();
            if (stackTrace != null) {
                for (int i = 0; i < stackTrace.length; i++) {
                    stringBuffer.append("Class:" + stackTrace[i].getClassName() + ",");
                    stringBuffer.append("File:" + stackTrace[i].getFileName() + ",");
                    stringBuffer.append("Line:" + stackTrace[i].getLineNumber() + ",");
                    stringBuffer.append("Method:" + stackTrace[i].getMethodName() + "\n");
                }
            }
        } else {
            stringBuffer.append(Configurator.NULL);
        }
        return stringBuffer.toString();
    }

    public static String m181a(Throwable th) {
        if (th == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            while (true) {
                th = th.getCause();
                if (th == null) {
                    printWriter.flush();
                    printWriter.close();
                    return stringWriter.toString();
                }
                th.printStackTrace(printWriter);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void m182a(String str) {
        LogManager.write("crash", str);
    }

    private boolean m183b(Throwable th) {
        if (th == null) {
            return false;
        }
        try {
            new C0679c(this).start();
            m187b(this.f229c);
            m184c(th);
            SystemClock.sleep(3000L);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private String m184c(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            for (Map.Entry<String, String> entry : this.f230d.entrySet()) {
                stringBuffer.append(entry.getKey() + "=" + entry.getValue() + "｜");
            }
            stringBuffer.append(m181a(th));
            m182a(stringBuffer.toString());
            return null;
        } catch (Exception unused) {
            stringBuffer.append("an error occured while writing file...\r\n");
            m182a(stringBuffer.toString());
            return null;
        }
    }

    public void m185a(Context context) {
        this.f229c = context;
        this.f228a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void m186a(Thread thread, Throwable th) {
        if (this.f228a != null) {
            m183b(th);
            this.f228a.uncaughtException(thread, th);
        } else {
            SystemClock.sleep(3000L);
            Process.killProcess(Process.myPid());
            System.exit(1);
        }
    }

    public void m187b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                String str = packageInfo.versionName + "";
                String str2 = packageInfo.versionCode + "";
                this.f230d.put("versionName", str);
                this.f230d.put("versionCode", str2);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
