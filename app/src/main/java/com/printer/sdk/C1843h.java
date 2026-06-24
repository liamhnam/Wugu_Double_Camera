package com.printer.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpContextBasisFace;
import com.tom_roush.fontbox.afm.AFMParser;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.util.Arrays;

public class C1843h extends AbstractC1842g {

    private Object f2203a;

    private b f2204b;

    private a f2205c;

    private String f2206d;

    public interface a {
        boolean mo706a(PrintMsg printMsg);
    }

    public interface b {
        boolean mo772a(int i, int i2);
    }

    public C1843h(Context context, a aVar) {
        super(context);
        this.f2203a = new Object();
        this.f2206d = "";
        this.f2205c = aVar;
    }

    private String m789C() {
        return this.f2206d.length() == 0 ? m795a(m813h()) : this.f2206d;
    }

    private boolean m790D() {
        boolean zA;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("CNTRL".getBytes(), 0, bArr, 2, 5);
        System.arraycopy("START".getBytes(), 0, bArr, 8, 5);
        synchronized (this.f2203a) {
            zA = m784a(bArr);
        }
        return zA;
    }

    private boolean m791a(int i, byte[] bArr, int i2) {
        byte[] bArr2 = new byte[32];
        Arrays.fill(bArr2, (byte) 32);
        bArr2[0] = 27;
        bArr2[1] = 80;
        System.arraycopy("IMAGE".getBytes(), 0, bArr2, 2, 5);
        bArr2[8] = (byte) i;
        System.arraycopy("PLANE".getBytes(), 0, bArr2, 9, 5);
        System.arraycopy(String.format("%08d", Integer.valueOf(i2)).getBytes(), 0, bArr2, 24, 8);
        synchronized (this.f2203a) {
            return m784a(bArr2) && m785a(bArr, this.f2204b);
        }
    }

    public String m792A() {
        String strB;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("INFO".getBytes(), 0, bArr, 2, 4);
        System.arraycopy("RQTY".getBytes(), 0, bArr, 8, 4);
        synchronized (this.f2203a) {
            strB = m786b(bArr);
        }
        return strB;
    }

    public int m793B() {
        String strM792A = m792A();
        if (strM792A.length() < 3) {
            return -1;
        }
        return Integer.parseInt(strM792A.substring(4, 8));
    }

    public String m794a(int i) {
        if (i < 0) {
            i = m807f();
        }
        if (i == 0) {
            return "Idling";
        }
        if (i == 1) {
            return "Printing";
        }
        switch (i) {
            case 500:
                return "Head cooling down";
            case TypedValues.PositionType.TYPE_POSITION_TYPE:
                return "Cooling the paper winding motor";
            case 900:
                return "Standby Mode";
            case 1000:
                return "Cover is open";
            case 1010:
                return "No Scrap box";
            case 1100:
                return "Paper End";
            case UiPosIndexEnum.PE_BG_ROOT:
                return "Ribbon End";
            case SnmpContextBasisFace.MSS:
                return "Paper jam";
            case 1400:
                return "Ribbon errors (detect error, ribbon break)";
            case 1500:
                return "Paper Definition Error (The setting is different from printer setting)";
            case 1600:
                return "Data error (improper data)";
            case 2000:
                return "Head voltage error";
            case 2100:
                return "Head position error";
            case 2200:
                return "Power supply fun stopped";
            case 2300:
                return "Cutter error (Cut jamming etc.)";
            case 2400:
                return "Pinch roller position error";
            case 2500:
                return "Abnormal head temperature";
            case 2600:
                return "Abnormal media temperature";
            case 2610:
                return "Abnormal temperature of paper winding motor";
            case 2700:
                return "Ribbon tension error";
            case 2800:
                return "RF-ID module error";
            case 3000:
                return "System error";
            case 5017:
                return "Paper jam in duplex unit paper supply";
            case 5019:
                return "Paper jam in duplex unit upper paper feed area";
            case 5023:
                return "Paper jam in area of duplex unit shell";
            case 5027:
                return "Paper jam in area of duplex unit eject slot";
            case 5049:
                return "Duplex unit paper delivery motor malfunction";
            case 5065:
                return "Duplex unit reversing block (shell) internal feed motor malfunction";
            case 5081:
                return "Duplex unit pinch malfunction";
            case 5097:
                return "Duplex unit act pass guide malfunction";
            case 5113:
                return "Duplex unit side guide malfunction";
            case 5129:
                return "Duplex unit skew correction malfunction";
            case 5145:
                return "Duplex unit reverse block (shell) malfunction";
            case 5161:
                return "Duplex unit feed tray malfunction";
            case 5177:
                return "Duplex unit cut operation malfunction";
            case 5193:
                return "Duplex unit tray error";
            case 5209:
                return "Duplex unit maintenance cover open";
            case 5241:
                return "Duplex unit system error";
            default:
                return "";
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String m795a(java.lang.String r3) {
        /*
            r2 = this;
            java.lang.String r0 = "CX-02"
            boolean r1 = r3.contains(r0)
            if (r1 == 0) goto Lb
        L8:
            r2.f2206d = r0
            goto L21
        Lb:
            java.lang.String r0 = "CX"
            boolean r1 = r3.contains(r0)
            if (r1 == 0) goto L14
            goto L8
        L14:
            java.lang.String r0 = "CY"
            boolean r3 = r3.contains(r0)
            if (r3 == 0) goto L1d
            goto L8
        L1d:
            java.lang.String r3 = ""
            r2.f2206d = r3
        L21:
            java.lang.String r3 = r2.f2206d
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.printer.sdk.C1843h.m795a(java.lang.String):java.lang.String");
    }

    @Override
    public void mo783a(PrintMsg printMsg) {
        a aVar = this.f2205c;
        if (aVar != null) {
            aVar.mo706a(printMsg);
        }
    }

    public boolean m796a(Bitmap bitmap, int i, int i2, int i3, b bVar) {
        C1841f c1841fM720a = C1837b.m720a(bitmap);
        if (c1841fM720a != null) {
            return (i2 <= 0 || m803d(i2)) && m809f(i) && m791a(89, c1841fM720a.m774a(), c1841fM720a.m774a().length) && m791a(77, c1841fM720a.m776b(), c1841fM720a.m776b().length) && m791a(67, c1841fM720a.m778c(), c1841fM720a.m778c().length) && m806e(i3) && m790D();
        }
        return false;
    }

    public String m797b(int i) {
        String strA;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("INFO".getBytes(), 0, bArr, 2, 4);
        System.arraycopy("FREE_PBUFFER".getBytes(), 0, bArr, 8, 12);
        synchronized (this.f2203a) {
            strA = m781a(bArr, i);
        }
        return strA;
    }

    public String m798b(String str) {
        return str.contains("5x3.5") ? "5x3.5" : str.contains("5x7") ? "5x7" : str.contains("5x5") ? "5x5" : str.contains("6x4") ? "6x4" : str.contains("6x6") ? "6x6" : str.contains("6x8") ? "6x8" : str.contains("6x9") ? "6x9" : "";
    }

    public int m799c(int i) {
        String strM797b = m797b(i);
        if (strM797b.length() < 3) {
            return -1;
        }
        return Integer.parseInt(strM797b.substring(3, 5));
    }

    String m800c(String str) {
        return str.contains("300") ? "300x300" : str.contains("600") ? "300x600" : "";
    }

    int m801d(String str) {
        return Integer.parseInt(str.substring(3, 5));
    }

    public void m802d() {
        synchronized (this.f2203a) {
            m782a();
        }
    }

    public boolean m803d(int i) {
        boolean zA;
        byte[] bArr = new byte[40];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("IMAGE".getBytes(), 0, bArr, 2, 5);
        System.arraycopy("MULTICUT".getBytes(), 0, bArr, 8, 8);
        System.arraycopy("00000008".getBytes(), 0, bArr, 24, 8);
        System.arraycopy(String.format("%08d", Integer.valueOf(i)).getBytes(), 0, bArr, 32, 8);
        synchronized (this.f2203a) {
            zA = m784a(bArr);
        }
        return zA;
    }

    int m804e(String str) {
        return Integer.parseInt(str.substring(4, 8));
    }

    public void m805e() {
        synchronized (this.f2203a) {
            m788c();
        }
    }

    public boolean m806e(int i) {
        boolean zA;
        byte[] bArr = new byte[40];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        int i2 = 1;
        bArr[1] = 80;
        System.arraycopy("CNTRL".getBytes(), 0, bArr, 2, 5);
        System.arraycopy("CUTTER".getBytes(), 0, bArr, 8, 6);
        System.arraycopy("00000008".getBytes(), 0, bArr, 24, 8);
        if (i == 0) {
            i2 = 0;
        } else if (i != 1) {
            if (i != 2) {
                return false;
            }
            i2 = 120;
        }
        System.arraycopy(String.format("%08d", Integer.valueOf(i2)).getBytes(), 0, bArr, 32, 8);
        synchronized (this.f2203a) {
            zA = m784a(bArr);
        }
        return zA;
    }

    public int m807f() {
        String strB;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("STATUS".getBytes(), 0, bArr, 2, 6);
        synchronized (this.f2203a) {
            strB = m786b(bArr);
        }
        if (strB.length() < 3) {
            return -1;
        }
        return Integer.parseInt(strB.substring(0, 5));
    }

    public int m808f(String str) {
        String str2;
        String str3;
        int i = Integer.parseInt(str.substring(4, 8));
        String strM789C = m789C();
        if (strM789C.contains("CX-02")) {
            str2 = strM789C + "|" + i;
            str3 = "intRemainQuantityInt";
        } else {
            i -= 50;
            str2 = strM789C + "|" + i;
            str3 = "tRemainQuantityInt(-50)";
        }
        Log.w(str3, str2);
        return i;
    }

    public boolean m809f(int i) {
        boolean zA;
        byte[] bArr = new byte[40];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        int i2 = 1;
        bArr[1] = 80;
        System.arraycopy("CNTRL".getBytes(), 0, bArr, 2, 5);
        System.arraycopy("OVERCOAT".getBytes(), 0, bArr, 8, 8);
        System.arraycopy("00000008".getBytes(), 0, bArr, 24, 8);
        if (i == 0) {
            i2 = 0;
        } else if (i != 1) {
            return false;
        }
        System.arraycopy(String.format("%08d", Integer.valueOf(i2)).getBytes(), 0, bArr, 32, 8);
        synchronized (this.f2203a) {
            zA = m784a(bArr);
        }
        return zA;
    }

    int m810g(String str) {
        return Integer.parseInt(str.substring(2, 9));
    }

    public String m811g() {
        return m794a(-1);
    }

    int m812h(String str) {
        return Integer.parseInt(str.substring(4, 11));
    }

    public String m813h() {
        String strB;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("INFO".getBytes(), 0, bArr, 2, 4);
        System.arraycopy("FVER".getBytes(), 0, bArr, 8, 4);
        synchronized (this.f2203a) {
            strB = m786b(bArr);
        }
        return strB.length() < 5 ? "" : strB.replace('\r', ' ');
    }

    int m814i(String str) {
        return Integer.parseInt(str.substring(4, 8));
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String m815i() {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.printer.sdk.C1843h.m815i():java.lang.String");
    }

    public String m816j() {
        String strB;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("INFO".getBytes(), 0, bArr, 2, 4);
        System.arraycopy("RESOLUTION_H".getBytes(), 0, bArr, 8, 12);
        synchronized (this.f2203a) {
            strB = m786b(bArr);
        }
        return strB.replace('\r', ' ');
    }

    public int m817k() {
        String strM816j = m816j();
        if (strM816j.length() < 3) {
            return -1;
        }
        return Integer.parseInt(strM816j.substring(2, 6));
    }

    public String m818l() {
        String strB;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("INFO".getBytes(), 0, bArr, 2, 4);
        System.arraycopy("RESOLUTION_V".getBytes(), 0, bArr, 8, 12);
        synchronized (this.f2203a) {
            strB = m786b(bArr);
        }
        return strB.replace('\r', ' ');
    }

    public int m819m() {
        String strM818l = m818l();
        if (strM818l.length() < 3) {
            return -1;
        }
        return Integer.parseInt(strM818l.substring(2, 6));
    }

    public String m820n() {
        String strB;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("INFO".getBytes(), 0, bArr, 2, 4);
        System.arraycopy("FREE_PBUFFER".getBytes(), 0, bArr, 8, 12);
        synchronized (this.f2203a) {
            strB = m786b(bArr);
        }
        return strB;
    }

    public int m821o() {
        String strM820n = m820n();
        if (strM820n.length() < 3) {
            return -1;
        }
        return Integer.parseInt(strM820n.substring(3, 5));
    }

    public String m822p() {
        String strB;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("INFO".getBytes(), 0, bArr, 2, 4);
        System.arraycopy("MQTY_DEFAULT".getBytes(), 0, bArr, 8, 12);
        synchronized (this.f2203a) {
            strB = m786b(bArr);
        }
        return strB;
    }

    public int m823q() {
        String strM822p = m822p();
        if (strM822p.length() < 3) {
            return -1;
        }
        return Integer.parseInt(strM822p.substring(4, 8));
    }

    public String m824r() {
        String strB;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("INFO".getBytes(), 0, bArr, 2, 4);
        System.arraycopy("MQTY".getBytes(), 0, bArr, 8, 4);
        synchronized (this.f2203a) {
            strB = m786b(bArr);
        }
        return strB;
    }

    public int m825s() {
        String str;
        String str2;
        String strM824r = m824r();
        if (strM824r.length() < 3) {
            return -1;
        }
        int i = Integer.parseInt(strM824r.substring(4, 8));
        String strM789C = m789C();
        if (strM789C.contains("CX-02")) {
            str = strM789C + "|" + i;
            str2 = "RemainQuantityInt";
        } else {
            i -= 50;
            str = strM789C + "|" + i;
            str2 = "quantityInt(-50)";
        }
        Log.w(str2, str);
        return i;
    }

    public String m826t() {
        String strB;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("INFO".getBytes(), 0, bArr, 2, 4);
        System.arraycopy("SERIAL_NUMBER".getBytes(), 0, bArr, 8, 13);
        synchronized (this.f2203a) {
            strB = m786b(bArr);
        }
        return strB.replace('\r', ' ');
    }

    public String m827u() {
        String strB;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("TBL_RD".getBytes(), 0, bArr, 2, 6);
        System.arraycopy(AFMParser.VERSION.getBytes(), 0, bArr, 8, 7);
        synchronized (this.f2203a) {
            strB = m786b(bArr);
        }
        return strB.replace('\r', ' ');
    }

    public String m828v() {
        String strB;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("MNT_RD".getBytes(), 0, bArr, 2, 6);
        System.arraycopy("CTRLD_CHKSUM".getBytes(), 0, bArr, 8, 12);
        synchronized (this.f2203a) {
            strB = m786b(bArr);
        }
        return strB.replace('\r', ' ');
    }

    public String m829w() {
        String strB;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("MNT_RD".getBytes(), 0, bArr, 2, 6);
        System.arraycopy("COUNTER_LIFE".getBytes(), 0, bArr, 8, 12);
        synchronized (this.f2203a) {
            strB = m786b(bArr);
        }
        return strB;
    }

    public int m830x() {
        String strM829w = m829w();
        if (strM829w.length() < 3) {
            return -1;
        }
        return Integer.parseInt(strM829w.substring(2, 9));
    }

    public String m831y() {
        String strB;
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 32);
        bArr[0] = 27;
        bArr[1] = 80;
        System.arraycopy("MNT_RD".getBytes(), 0, bArr, 2, 6);
        System.arraycopy("COUNTER_MATTE".getBytes(), 0, bArr, 8, 13);
        synchronized (this.f2203a) {
            strB = m786b(bArr);
        }
        return strB;
    }

    public int m832z() {
        String strM831y = m831y();
        if (strM831y.length() < 3) {
            return -1;
        }
        return Integer.parseInt(strM831y.substring(4, 11));
    }
}
