package cc.uling.usdk.board;

import android.serialport.SerialPort;
import android.util.Log;
import cc.uling.usdk.USDK;
import cc.uling.usdk.constants.ErrorConst;
import cc.uling.usdk.mgr.LogManager;
import cc.uling.usdk.p015a.C0672a;
import cc.uling.usdk.para.C0682a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class BaseDrvBoard implements InterfaceC0676c {

    public static boolean f231a = false;

    protected FileInputStream f232b;

    protected FileOutputStream f233c;

    private String f236f;

    private SerialPort f237g;

    private boolean f234d = USDK.getInstance().isDebug();

    private final String f235e = "scd";

    private final int f238h = 0;

    private final int f239i = 1;

    private final int f240j = 2;

    @Override
    public int EF_CloseDev() {
        try {
            SerialPort serialPort = this.f237g;
            if (serialPort == null) {
                return 0;
            }
            serialPort.close();
            this.f233c = null;
            this.f232b = null;
            m190a(this.f236f + "|Close");
            return 0;
        } catch (SecurityException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int EF_OpenDev(String str, int i) {
        boolean z;
        StringBuilder sbAppend;
        String message;
        this.f236f = str;
        try {
            SerialPort serialPort = new SerialPort(new File(str), i, 0);
            this.f237g = serialPort;
            this.f233c = (FileOutputStream) serialPort.getOutputStream();
            this.f232b = (FileInputStream) this.f237g.getInputStream();
            z = true;
            try {
                m190a("Open|" + str + "|Succ");
            } catch (IOException e) {
                e = e;
                e.printStackTrace();
                sbAppend = new StringBuilder("Open|").append(str).append("|Fail|");
                message = e.getMessage();
                m190a(sbAppend.append(message).toString());
            } catch (SecurityException e2) {
                e = e2;
                e.printStackTrace();
                sbAppend = new StringBuilder("Open|").append(str).append("|Fail|SecurityException:");
                message = e.getMessage();
                m190a(sbAppend.append(message).toString());
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                sbAppend = new StringBuilder("Open|").append(str).append("|Fail|");
                message = e.getMessage();
                m190a(sbAppend.append(message).toString());
            }
        } catch (IOException e4) {
            e = e4;
            z = false;
        } catch (SecurityException e5) {
            e = e5;
            z = false;
        } catch (Exception e6) {
            e = e6;
            z = false;
        }
        if (z) {
            return 0;
        }
        return ErrorConst.MDB_ERR_CANT_OPEN;
    }

    @Override
    public boolean EF_Opened() {
        return (this.f237g == null || this.f233c == null || this.f232b == null) ? false : true;
    }

    protected int m188a() {
        return 2;
    }

    protected void m189a(int i, int i2, InputStream inputStream) {
        int i3 = 0;
        while (inputStream.available() < i2) {
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i3 += 50;
            if (i < i3) {
                return;
            }
        }
    }

    protected void m190a(String str) {
        if (this.f234d) {
            Log.i("scd", str);
            LogManager.write("scd", str);
        }
    }

    protected void m191a(String str, C0682a c0682a) throws C0675b {
        if (f231a) {
            if (this.f234d) {
                m190a(str + "|Send|" + C0672a.m162a(c0682a.m267c()));
                return;
            }
            return;
        }
        if (this.f232b == null || this.f233c == null) {
            throw C0675b.f252l;
        }
        if (c0682a == null || c0682a.m267c() == null) {
            return;
        }
        try {
            this.f232b.skip(r0.available());
            this.f233c.write(c0682a.m267c(), 0, c0682a.m267c().length);
            if (this.f234d) {
                m190a(str + "|Send|" + C0672a.m162a(c0682a.m267c()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw C0675b.f249i;
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected byte[] m192a(cc.uling.usdk.para.C0682a r22, java.io.InputStream r23, java.lang.String r24) throws cc.uling.usdk.board.C0675b {
        /*
            Method dump skipped, instruction units count: 897
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cc.uling.usdk.board.BaseDrvBoard.m192a(cc.uling.usdk.para.a, java.io.InputStream, java.lang.String):byte[]");
    }

    protected byte[] m193a(C0682a c0682a, String str) {
        return m192a(c0682a, this.f232b, str);
    }

    protected int m194b() {
        return 1;
    }

    protected void m195b(String str) {
        m190a(str);
    }

    protected int m196c() {
        return 0;
    }

    protected int m197d() {
        return 5;
    }
}
