package com.printer.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.printer.sdk.AbstractC1840e;
import com.printer.sdk.C1843h;
import java.util.Iterator;
import java.util.List;

public class PrintManage extends AbstractC1840e implements PrintType {

    private static PrintManage f2081C;

    private Context f2082D;

    private PrintManage(Context context) {
        super(context);
        this.f2082D = context;
        this.f2175p = new C1843h(context, new C1843h.a() {
            @Override
            public boolean mo706a(PrintMsg printMsg) {
                String msg;
                String str;
                int ret = printMsg.getRet();
                if (ret != 1001) {
                    if (ret == 1002) {
                        msg = printMsg.getMsg();
                        str = PrintManage.this.f2164e;
                    }
                    PrintManage.this.m752a(1, printMsg);
                    return false;
                }
                msg = printMsg.getMsg();
                str = PrintManage.this.f2165f;
                printMsg.setMsg(msg.concat(str));
                PrintManage.this.m752a(1, printMsg);
                return false;
            }
        });
    }

    private void m705a(final PrintUserOrder printUserOrder, OnAddOrderListen onAddOrderListen, final int i, final float[] fArr, final int i2, final String str) {
        this.f2172m = onAddOrderListen;
        if (printUserOrder == null || printUserOrder.getTask() == null || printUserOrder.getTask().size() == 0) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instruction units count: 972
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.printer.sdk.PrintManage.RunnableC18344.run():void");
            }
        }).start();
    }

    public static synchronized PrintManage instance() {
        return f2081C;
    }

    public static synchronized PrintManage instance(Context context) {
        if (f2081C == null) {
            f2081C = new PrintManage(context);
        }
        return f2081C;
    }

    public void AddOrder(PrintUserOrder printUserOrder, OnAddOrderListen onAddOrderListen) {
        m705a(printUserOrder, onAddOrderListen, 0, null, 0, null);
    }

    public void AddOrderAnyColor(PrintUserOrder printUserOrder, OnAddOrderListen onAddOrderListen, float[] fArr) {
        m705a(printUserOrder, onAddOrderListen, 2, fArr, 0, null);
    }

    public void AddOrderAnyColorIcc(PrintUserOrder printUserOrder, OnAddOrderListen onAddOrderListen, float[] fArr, int i, String str) {
        m705a(printUserOrder, onAddOrderListen, 5, fArr, i, str);
    }

    public void AddOrderAnyIcc(PrintUserOrder printUserOrder, OnAddOrderListen onAddOrderListen, int i, String str) {
        m705a(printUserOrder, onAddOrderListen, 3, null, i, str);
    }

    public void AddOrderDefColor(PrintUserOrder printUserOrder, OnAddOrderListen onAddOrderListen) {
        m705a(printUserOrder, onAddOrderListen, 1, null, 0, null);
    }

    public void AddOrderDefIcc(PrintUserOrder printUserOrder, OnAddOrderListen onAddOrderListen, int i) {
        String str = this.f2182w;
        String str2 = str.toLowerCase().indexOf("cy") > -1 ? "uci-cy-android.dat" : str.toLowerCase().indexOf("cx") > -1 ? "uci-cx-android.dat" : null;
        Log.i("iccMode", "srcIccMode is :" + i + ", dstIccFileName is : " + str2 + ", printerType is : " + str);
        m705a(printUserOrder, onAddOrderListen, 4, null, i, str2);
    }

    public Bitmap ColorTest(Bitmap bitmap, float[] fArr) {
        return C1837b.m712a(bitmap, fArr);
    }

    public synchronized void DelOrder(final PrintOrder printOrder, OnDelOrdersListen onDelOrdersListen) {
        this.f2173n = onDelOrdersListen;
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (PrintManage.this.f2176q) {
                    Iterator<PrintOrder> it = PrintManage.this.f2176q.iterator();
                    if (printOrder != null) {
                        while (it.hasNext()) {
                            PrintOrder next = it.next();
                            if (next.getName().equalsIgnoreCase(printOrder.getName()) && next.getStus() != 1) {
                                String name = printOrder.getName();
                                it.remove();
                                C1837b.m725a(next);
                                PrintManage.this.m752a(7, new PrintMsg(1005, next.getOrderid(), name));
                            }
                        }
                    } else {
                        while (it.hasNext()) {
                            PrintOrder next2 = it.next();
                            if (next2.getStus() != 1) {
                                String name2 = next2.getName();
                                it.remove();
                                C1837b.m725a(next2);
                                PrintManage.this.m752a(7, new PrintMsg(1005, next2.getOrderid(), name2));
                            }
                        }
                    }
                }
                synchronized (PrintManage.this.f2177r) {
                    if (printOrder != null) {
                        PrintManage.this.f2177r.clear();
                    }
                }
                PrintManage.this.m752a(7, new PrintMsg(1002, "删除成功"));
            }
        }).start();
    }

    public List<PrintDetail> GetPrintDetail() {
        List<PrintDetail> listM749b;
        synchronized (this.f2177r) {
            listM749b = C1838c.m749b(this.f2177r);
        }
        return listM749b;
    }

    public PrintOrder GetPrintOrders(long j) {
        synchronized (this.f2176q) {
            for (int i = 0; i < this.f2176q.size(); i++) {
                if (this.f2176q.get(i).getOrderid() == j) {
                    return (PrintOrder) C1838c.m747a(this.f2176q.get(i));
                }
            }
            return null;
        }
    }

    public List<PrintOrder> GetPrintOrders() {
        List<PrintOrder> listM748a;
        synchronized (this.f2176q) {
            listM748a = C1838c.m748a(this.f2176q);
        }
        return listM748a;
    }

    public void GetPrnInfo(OnGetInfoListen onGetInfoListen) {
        this.f2170k = onGetInfoListen;
        new Thread(new Runnable() {
            @Override
            public void run() {
                PrintStus printStusC = PrintManage.this.m755c();
                if (printStusC == null) {
                    PrintManage.this.m752a(3, new PrintMsg(1001, PrintType.ER_Msg4));
                } else if (PrintManage.this.m753a(printStusC.getPrintMediaInfo(), printStusC.getPrintVersion())) {
                    PrintManage.this.m752a(3, new PrintMsg(1002, printStusC));
                } else {
                    PrintManage.this.m752a(3, new PrintMsg(1001, PrintType.ER_Msg2));
                }
            }
        }).start();
    }

    public String GetSDKVersion() {
        return "Ver 2.1.2".concat(this.f2164e);
    }

    public void LoadIccFile(OnLoadIccListen onLoadIccListen) {
        this.f2174o = onLoadIccListen;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!C1837b.m742b(PrintManage.this.f2082D)) {
                    PrintManage.this.m752a(10, new PrintMsg(1001, PrintType.ER_MsgF));
                } else {
                    PrintManage.this.m752a(10, new PrintMsg(1002, C1837b.m732a(PrintManage.this.f2082D)));
                }
            }
        }).start();
    }

    @Override
    public void SetPrinterDebug(boolean z) {
        super.SetPrinterDebug(z);
    }

    @Override
    public void SetPrinterMiddle(boolean z) {
        super.SetPrinterMiddle(z);
    }

    @Override
    public void SetPrinterTypeAndMedia(String str, String str2) {
        super.SetPrinterTypeAndMedia(str, str2);
    }

    public void StartPrn(OnStartPrnListen onStartPrnListen) {
        PrintMsg printMsg;
        this.f2168i = onStartPrnListen;
        if (!this.f2185z) {
            int iA = m751a();
            if (iA == -1) {
                m752a(2, new PrintMsg(1001, PrintType.ER_Msg1.concat(this.f2165f)));
                return;
            }
            if (iA > 999) {
                m752a(2, new PrintMsg(1001, this.f2175p.m794a(iA).concat(this.f2165f)));
                return;
            }
            int iB = m754b();
            if (iB == -1) {
                m752a(2, new PrintMsg(1001, PrintType.ER_Msg2.concat(this.f2165f)));
                return;
            } else if (iB == -2) {
                m752a(2, new PrintMsg(1001, PrintType.ER_Msg3.concat(this.f2165f)));
                return;
            }
        }
        if (this.f2179t == null) {
            this.f2179t = new AbstractC1840e.a();
            this.f2179t.start();
            printMsg = new PrintMsg(1006, PrintType.OK_Msg3);
        } else if (this.f2179t.m762a()) {
            printMsg = new PrintMsg(1006, PrintType.OK_Msg4);
        } else {
            this.f2179t.start();
            printMsg = new PrintMsg(1006, PrintType.OK_Msg3);
        }
        m752a(2, printMsg);
    }

    public void StartSvr(OnStartSvrListen onStartSvrListen) {
        this.f2166g = onStartSvrListen;
        int iA = m751a();
        if (iA == -1) {
            this.f2175p.m802d();
        } else if (iA > 999) {
            m752a(1, new PrintMsg(1001, this.f2175p.m794a(iA).concat(this.f2165f)));
        } else {
            m752a(1, new PrintMsg(1002, PrintType.OK_Msg1.concat(this.f2164e)));
        }
    }

    public void StopPrn(OnStopPrnListen onStopPrnListen) {
        this.f2169j = onStopPrnListen;
        if (this.f2179t != null) {
            if (this.f2179t.m762a()) {
                this.f2179t.m766c();
            } else {
                m752a(9, new PrintMsg(1002, PrintType.OK_Msg5));
            }
        }
    }

    public void StopSvr(OnStopSvrListen onStopSvrListen) {
        this.f2167h = onStopSvrListen;
        if (this.f2179t != null && this.f2179t.m762a()) {
            this.f2179t.m765b();
        }
        this.f2175p.m805e();
        m752a(8, new PrintMsg(1002, PrintType.OK_Msg2));
    }

    public Bitmap TransTest(Bitmap bitmap, int i, String str) {
        return C1837b.m734b(bitmap, i, str);
    }

    public String getColorControlDataChecksum() {
        return this.f2175p.m828v();
    }

    public String getColorControlDataVersion() {
        return this.f2175p.m827u();
    }

    public int getPrintFreeBuffInt(int i) {
        return this.f2175p.m799c(i);
    }

    public int getPrintHalfSizeMediaCounterInt() {
        return this.f2175p.m793B();
    }

    public int getPrintHorResolutionInt() {
        return this.f2175p.m817k();
    }

    public int getPrintInitMediaCountInt() {
        return this.f2175p.m823q();
    }

    public String getPrintMediaInfo() {
        return this.f2175p.m815i();
    }

    public int getPrintRemainQuantityInt() {
        return this.f2175p.m825s();
    }

    public String getPrintSerialNO() {
        return this.f2175p.m826t();
    }

    public String getPrintStatus() {
        return this.f2175p.m811g();
    }

    public int getPrintStatusInt() {
        return this.f2175p.m807f();
    }

    public int getPrintVerResolutionInt() {
        return this.f2175p.m819m();
    }

    public int getPrintVolumeLifeCounterInt() {
        return this.f2175p.m830x();
    }

    public int getPrintVolumeMatteCounterInt() {
        return this.f2175p.m832z();
    }
}
