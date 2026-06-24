package com.printer.sdk;

import android.content.Context;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.helpers.UtilLoggingLevel;

public abstract class AbstractC1840e implements PrintType {

    protected String f2164e;

    protected String f2165f;

    protected List<PrintOrder> f2176q;

    protected a f2179t;

    protected final String f2160a = "\r\n本SDK开发包由北京优利绚彩科技发展有限公司开发，版权所有。未经许可，不得以任何方式使用。";

    protected final String f2161b = "\r\nThis software is developed by Un-Colour International Inc. (www.uni-colour.com). All intelligence and rights belong to Uni-Colour. Any copy or application by another party, without written approval of Uni-Colour, will be treated as illegal actions.";

    protected final String f2162c = "\r\nSDK停止服务，除了”StartSvr”和”GetSvrStatus”命令外，不响应其它任何命令。";

    protected final String f2163d = "\r\nSDK stopped working, would not respond to any command but ‘StartSvr’&’GetSvrStatus’";

    protected OnStartSvrListen f2166g = null;

    protected OnStopSvrListen f2167h = null;

    protected OnStartPrnListen f2168i = null;

    protected OnStopPrnListen f2169j = null;

    protected OnGetInfoListen f2170k = null;

    protected OnSndImageListen f2171l = null;

    protected OnAddOrderListen f2172m = null;

    protected OnDelOrdersListen f2173n = null;

    protected OnLoadIccListen f2174o = null;

    protected C1843h f2175p = null;

    protected List<PrintDetail> f2177r = new ArrayList();

    protected PrintOrder f2178s = null;

    protected Object f2180u = new Object();

    protected String f2181v = "6x8";

    protected String f2182w = "CY";

    protected int f2183x = 0;

    protected int f2184y = 2;

    protected boolean f2185z = true;

    protected boolean f2158A = false;

    protected boolean f2159B = false;

    protected class a extends Thread {

        protected boolean f2186a = false;

        protected boolean f2187b = false;

        protected C1844i f2188c = null;

        protected a() {
        }

        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected int m756a(java.lang.String r17, java.lang.String r18) {
            /*
                Method dump skipped, instruction units count: 246
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.printer.sdk.AbstractC1840e.a.m756a(java.lang.String, java.lang.String):int");
        }

        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected int m757a(java.lang.String r13, java.lang.String r14, boolean r15) {
            /*
                Method dump skipped, instruction units count: 326
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.printer.sdk.AbstractC1840e.a.m757a(java.lang.String, java.lang.String, boolean):int");
        }

        protected int m758a(String str, boolean z) {
            int i;
            str.hashCode();
            byte b = -1;
            switch (str.hashCode()) {
                case 54706:
                    if (str.equals("5x5")) {
                        b = 0;
                    }
                    break;
                case 54708:
                    if (str.equals("5x7")) {
                        b = 1;
                    }
                    break;
                case 55666:
                    if (str.equals("6x4")) {
                        b = 2;
                    }
                    break;
                case 55668:
                    if (str.equals("6x6")) {
                        b = 3;
                    }
                    break;
                case 55670:
                    if (str.equals("6x8")) {
                        b = 4;
                    }
                    break;
                case 55671:
                    if (str.equals("6x9")) {
                        b = 5;
                    }
                    break;
                case 52572023:
                    if (str.equals("5x3.5")) {
                        b = 6;
                    }
                    break;
                case 53496505:
                    if (str.equals("6x4.5")) {
                        b = 7;
                    }
                    break;
            }
            switch (b) {
                case 0:
                case 2:
                case 3:
                case 6:
                case 7:
                default:
                    return 0;
                case 1:
                    if (!z) {
                        return 0;
                    }
                    i = 401;
                    break;
                    break;
                case 4:
                    if (!z) {
                        return 0;
                    }
                    i = 402;
                    break;
                    break;
                case 5:
                    if (!z) {
                        return 0;
                    }
                    i = UiPosIndexEnum.KEYBOARD_0;
                    break;
                    break;
            }
            return i;
        }

        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected com.printer.sdk.PrintDetail m759a(java.util.Iterator<com.printer.sdk.PrintTask> r19, boolean r20, java.lang.String r21, boolean r22, boolean r23, java.lang.String r24, java.lang.String r25) {
            /*
                Method dump skipped, instruction units count: 832
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.printer.sdk.AbstractC1840e.a.m759a(java.util.Iterator, boolean, java.lang.String, boolean, boolean, java.lang.String, java.lang.String):com.printer.sdk.PrintDetail");
        }

        protected Iterator<PrintTask> m760a(List<PrintTask> list) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                PrintTask printTask = list.get(i);
                for (int iM709a = C1837b.m709a(printTask.getFullPath()); iM709a < printTask.getCopy(); iM709a++) {
                    arrayList.add(printTask);
                }
            }
            return arrayList.iterator();
        }

        protected void m761a(int i, boolean z, PrintDetail printDetail) {
            for (int i2 = 0; i2 < printDetail.getTask().size(); i2++) {
                PrintTask printTask = printDetail.getTask().get(i2);
                String fullPath = printTask.getFullPath();
                int iM733b = z ? C1837b.m733b(fullPath, i) : C1837b.m709a(fullPath) + i;
                printTask.setPast(iM733b);
                if (iM733b >= printTask.getCopy()) {
                    printTask.setStus(2);
                }
            }
        }

        public boolean m762a() {
            return this.f2186a && this.f2187b;
        }

        protected boolean m763a(boolean z) {
            if (!this.f2186a) {
                AbstractC1840e.this.m752a(8, new PrintMsg(1001, PrintType.ER_Msg6));
                AbstractC1840e.this.f2175p.m805e();
                return true;
            }
            if (!this.f2187b) {
                AbstractC1840e.this.m752a(9, new PrintMsg(1001, PrintType.ER_Msg7));
                return true;
            }
            if (AbstractC1840e.this.f2185z || !z) {
                return false;
            }
            int iM768e = m768e();
            if (iM768e == -1) {
                AbstractC1840e.this.m752a(2, new PrintMsg(1001, PrintType.ER_Msg1));
                return true;
            }
            if (iM768e <= 999) {
                return false;
            }
            AbstractC1840e.this.m752a(2, new PrintMsg(1001, AbstractC1840e.this.f2175p.m794a(iM768e)));
            return true;
        }

        protected String[] m764a(Iterator<PrintTask> it, String str, int i, int i2, boolean z, PrintDetail printDetail) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            int i3 = 1;
            while (true) {
                String fullPath = "";
                if (i3 >= i) {
                    break;
                }
                if (it.hasNext()) {
                    PrintTask next = it.next();
                    printDetail.getTask().add(next);
                    fullPath = next.getFullPath();
                }
                arrayList.add(fullPath);
                i3++;
            }
            if (it.hasNext()) {
                z = true;
            }
            while (i3 < i2) {
                if (it.hasNext()) {
                    PrintTask next2 = it.next();
                    printDetail.getTask().add(next2);
                    arrayList.add(next2.getFullPath());
                } else if (z) {
                    arrayList.add("");
                }
                i3++;
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }

        public void m765b() {
            this.f2186a = false;
        }

        public void m766c() {
            this.f2187b = false;
        }

        protected void m767d() {
            this.f2186a = false;
            this.f2187b = false;
            AbstractC1840e.this.f2179t = null;
            C1844i c1844i = this.f2188c;
            if (c1844i != null) {
                c1844i.m833a();
                this.f2188c = null;
            }
        }

        protected int m768e() {
            int iM807f = AbstractC1840e.this.f2175p.m807f();
            if (iM807f != 900) {
                return iM807f;
            }
            if (AbstractC1840e.this.f2175p.m799c(UtilLoggingLevel.FINER_INT) < 0) {
                return -1;
            }
            return AbstractC1840e.this.f2175p.m807f();
        }

        protected PrintOrder m769f() {
            int i;
            synchronized (AbstractC1840e.this.f2176q) {
                int size = AbstractC1840e.this.f2176q.size();
                if (size > 0) {
                    while (i < size) {
                        PrintOrder printOrder = AbstractC1840e.this.f2176q.get(i);
                        i = (printOrder.getStus() == 0 || printOrder.getStus() == 1) ? 0 : i + 1;
                        PrintOrder printOrder2 = AbstractC1840e.this.f2176q.get(i);
                        printOrder2.setStus(1);
                        return printOrder2;
                    }
                }
                return null;
            }
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 604
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.printer.sdk.AbstractC1840e.a.run():void");
        }
    }

    protected AbstractC1840e(Context context) {
        this.f2164e = "";
        this.f2165f = "";
        this.f2176q = new ArrayList();
        this.f2179t = null;
        if (context.getResources().getConfiguration().locale.getLanguage().contains("en")) {
            this.f2164e = "\r\nThis software is developed by Un-Colour International Inc. (www.uni-colour.com). All intelligence and rights belong to Uni-Colour. Any copy or application by another party, without written approval of Uni-Colour, will be treated as illegal actions.";
            this.f2165f = "\r\nSDK stopped working, would not respond to any command but ‘StartSvr’&’GetSvrStatus’";
        } else {
            this.f2164e = "\r\n本SDK开发包由北京优利绚彩科技发展有限公司开发，版权所有。未经许可，不得以任何方式使用。";
            this.f2165f = "\r\nSDK停止服务，除了”StartSvr”和”GetSvrStatus”命令外，不响应其它任何命令。";
        }
        if (this.f2179t == null) {
            this.f2179t = new a();
        }
        C1837b.m738b();
        this.f2176q = C1837b.m723a();
    }

    public void SetPrinterDebug(boolean z) {
        this.f2185z = z;
    }

    public void SetPrinterMedia(String str) {
        this.f2181v = str;
    }

    public void SetPrinterMiddle(boolean z) {
        this.f2158A = z;
    }

    public void SetPrinterType(String str) {
        this.f2182w = str;
    }

    public void SetPrinterTypeAndMedia(String str, String str2) {
        this.f2182w = str;
        this.f2181v = str2;
    }

    protected int m751a() {
        int iM807f = this.f2175p.m807f();
        if (iM807f != 900) {
            return iM807f;
        }
        if (this.f2175p.m799c(UtilLoggingLevel.FINER_INT) < 0) {
            return -1;
        }
        return this.f2175p.m807f();
    }

    protected void m752a(int i, PrintMsg printMsg) {
        switch (i) {
            case 1:
                OnStartSvrListen onStartSvrListen = this.f2166g;
                if (onStartSvrListen != null) {
                    onStartSvrListen.Result(printMsg);
                }
                break;
            case 2:
                OnStartPrnListen onStartPrnListen = this.f2168i;
                if (onStartPrnListen != null) {
                    onStartPrnListen.Result(printMsg);
                }
                break;
            case 3:
                OnGetInfoListen onGetInfoListen = this.f2170k;
                if (onGetInfoListen != null) {
                    onGetInfoListen.Result(printMsg);
                }
                break;
            case 4:
                OnSndImageListen onSndImageListen = this.f2171l;
                if (onSndImageListen != null) {
                    onSndImageListen.Result(printMsg);
                }
                break;
            case 5:
                OnAddOrderListen onAddOrderListen = this.f2172m;
                if (onAddOrderListen != null) {
                    onAddOrderListen.Result(printMsg);
                }
                break;
            case 7:
                OnDelOrdersListen onDelOrdersListen = this.f2173n;
                if (onDelOrdersListen != null) {
                    onDelOrdersListen.Result(printMsg);
                }
                break;
            case 8:
                OnStopSvrListen onStopSvrListen = this.f2167h;
                if (onStopSvrListen != null) {
                    onStopSvrListen.Result(printMsg);
                }
                break;
            case 9:
                OnStopPrnListen onStopPrnListen = this.f2169j;
                if (onStopPrnListen != null) {
                    onStopPrnListen.Result(printMsg);
                }
                break;
            case 10:
                OnLoadIccListen onLoadIccListen = this.f2174o;
                if (onLoadIccListen != null) {
                    onLoadIccListen.Result(printMsg);
                }
                break;
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean m753a(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.String r0 = "5x3.5"
            boolean r1 = r4.contains(r0)
            r2 = 0
            if (r1 == 0) goto Lc
        L9:
            r3.f2181v = r0
            goto L42
        Lc:
            java.lang.String r0 = "5x7"
            boolean r1 = r4.contains(r0)
            if (r1 == 0) goto L15
            goto L9
        L15:
            java.lang.String r0 = "5x5"
            boolean r1 = r4.contains(r0)
            if (r1 == 0) goto L1e
            goto L9
        L1e:
            java.lang.String r0 = "6x4"
            boolean r1 = r4.contains(r0)
            if (r1 == 0) goto L27
            goto L9
        L27:
            java.lang.String r0 = "6x6"
            boolean r1 = r4.contains(r0)
            if (r1 == 0) goto L30
            goto L9
        L30:
            java.lang.String r0 = "6x8"
            boolean r1 = r4.contains(r0)
            if (r1 == 0) goto L39
            goto L9
        L39:
            java.lang.String r0 = "6x9"
            boolean r4 = r4.contains(r0)
            if (r4 == 0) goto L61
            goto L9
        L42:
            java.lang.String r4 = "CX-02"
            boolean r0 = r5.contains(r4)
            if (r0 == 0) goto L4d
        L4a:
            r3.f2182w = r4
            goto L5f
        L4d:
            java.lang.String r4 = "CX"
            boolean r0 = r5.contains(r4)
            if (r0 == 0) goto L56
            goto L4a
        L56:
            java.lang.String r4 = "CY"
            boolean r5 = r5.contains(r4)
            if (r5 == 0) goto L61
            goto L4a
        L5f:
            r4 = 1
            return r4
        L61:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.printer.sdk.AbstractC1840e.m753a(java.lang.String, java.lang.String):boolean");
    }

    protected int m754b() {
        return !m753a(this.f2175p.m815i(), this.f2175p.m813h()) ? -1 : 0;
    }

    protected PrintStus m755c() {
        PrintStus printStus = new PrintStus();
        int iM807f = this.f2175p.m807f();
        if (iM807f < 0) {
            return null;
        }
        printStus.setActPrintStatus(iM807f);
        printStus.setPrintStatus(this.f2175p.m794a(iM807f));
        String strM813h = this.f2175p.m813h();
        if (strM813h.length() == 0) {
            return null;
        }
        printStus.setPrintVersion(strM813h);
        printStus.setActPrintVersion(this.f2175p.m795a(strM813h));
        String strM797b = this.f2175p.m797b(strM813h.contains("CX-02") ? UtilLoggingLevel.FINER_INT : 1500);
        if (strM797b.length() == 0) {
            return null;
        }
        printStus.setPrintFreeBuff(strM797b);
        printStus.setActPrintFreeBuff(this.f2175p.m801d(strM797b));
        String strM815i = this.f2175p.m815i();
        if (strM815i.length() == 0) {
            return null;
        }
        printStus.setPrintMediaInfo(strM815i);
        printStus.setActPrintMediaInfo(this.f2175p.m798b(strM815i));
        String strM816j = this.f2175p.m816j();
        if (strM816j.length() == 0) {
            return null;
        }
        printStus.setPrintHorResolution(strM816j);
        String strM818l = this.f2175p.m818l();
        if (strM818l.length() == 0) {
            return null;
        }
        printStus.setPrintVerResolution(strM818l);
        printStus.setActPrintResolution(this.f2175p.m800c(strM818l));
        String strM822p = this.f2175p.m822p();
        if (strM822p.length() == 0) {
            return null;
        }
        printStus.setPrintInitMediaCount(strM822p);
        printStus.setActPrintInitMediaCount(this.f2175p.m804e(strM822p));
        String strM824r = this.f2175p.m824r();
        if (strM824r.length() == 0) {
            return null;
        }
        printStus.setPrintRemainQuantity(strM824r);
        printStus.setActPrintRemainQuantity(this.f2175p.m808f(strM824r));
        String strM826t = this.f2175p.m826t();
        if (strM826t.length() == 0) {
            return null;
        }
        printStus.setPrintSerialNO(strM826t);
        String strM829w = this.f2175p.m829w();
        if (strM829w.length() == 0) {
            return null;
        }
        printStus.setPrintVolumeLifeCounter(strM829w);
        printStus.setActPrintVolumeLifeCounter(this.f2175p.m810g(strM829w));
        String strM831y = this.f2175p.m831y();
        if (strM831y.length() == 0) {
            return null;
        }
        printStus.setPrintVolumeMatteCounter(strM831y);
        printStus.setActPrintVolumeMatteCounter(this.f2175p.m812h(strM831y));
        if (strM813h.contains("CX-02")) {
            String strM792A = this.f2175p.m792A();
            if (strM792A.length() == 0) {
                return null;
            }
            printStus.setPrintHalfSizeMediaCounter(strM792A);
            printStus.setActPrintHalfSizeMediaCounter(this.f2175p.m814i(strM792A));
        }
        String strM827u = this.f2175p.m827u();
        if (strM827u.length() == 0) {
            return null;
        }
        printStus.setPrintColorControlDataVersion(strM827u);
        String strM828v = this.f2175p.m828v();
        if (strM828v.length() == 0) {
            return null;
        }
        printStus.setPrintColorControlDataChecksum(strM828v);
        return printStus;
    }
}
