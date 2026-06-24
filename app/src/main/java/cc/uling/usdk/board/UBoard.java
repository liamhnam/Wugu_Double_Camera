package cc.uling.usdk.board;

import cc.uling.usdk.board.mdb.para.ACReplyPara;
import cc.uling.usdk.board.mdb.para.ARReplyPara;
import cc.uling.usdk.board.mdb.para.ASReplyPara;
import cc.uling.usdk.board.mdb.para.AVReplyPara;
import cc.uling.usdk.board.mdb.para.BBReplyPara;
import cc.uling.usdk.board.mdb.para.BRReplyPara;
import cc.uling.usdk.board.mdb.para.CBReplyPara;
import cc.uling.usdk.board.mdb.para.CSReplyPara;
import cc.uling.usdk.board.mdb.para.HCReplyPara;
import cc.uling.usdk.board.mdb.para.IPReplyPara;
import cc.uling.usdk.board.mdb.para.MPReplyPara;
import cc.uling.usdk.board.mdb.para.PBReplyPara;
import cc.uling.usdk.board.mdb.para.PCReplyPara;
import cc.uling.usdk.board.mdb.para.PMReplyPara;
import cc.uling.usdk.board.mdb.para.PayReplyPara;
import cc.uling.usdk.board.mdb.para.ResultReplyPara;
import cc.uling.usdk.board.mdb.para.STReplyPara;
import cc.uling.usdk.board.mdb.para.SVReplyPara;
import cc.uling.usdk.board.mdb.para.WMReplyPara;
import cc.uling.usdk.board.p016wz.para.AOReplyPara;
import cc.uling.usdk.board.p016wz.para.BSReplyPara;
import cc.uling.usdk.board.p016wz.para.CXReplyPara;
import cc.uling.usdk.board.p016wz.para.CYReplyPara;
import cc.uling.usdk.board.p016wz.para.DCReplyPara;
import cc.uling.usdk.board.p016wz.para.DSReplyPara;
import cc.uling.usdk.board.p016wz.para.IOReplyPara;
import cc.uling.usdk.board.p016wz.para.MTReplyPara;
import cc.uling.usdk.board.p016wz.para.PXReplyPara;
import cc.uling.usdk.board.p016wz.para.PYReplyPara;
import cc.uling.usdk.board.p016wz.para.RMReplyPara;
import cc.uling.usdk.board.p016wz.para.ResetReplyPara;
import cc.uling.usdk.board.p016wz.para.SReplyPara;
import cc.uling.usdk.board.p016wz.para.SSReplyPara;
import cc.uling.usdk.board.p016wz.para.SXPReplyPara;
import cc.uling.usdk.board.p016wz.para.SYPReplyPara;
import cc.uling.usdk.board.p016wz.para.TXReplyPara;
import cc.uling.usdk.board.p016wz.para.TYReplyPara;
import cc.uling.usdk.board.p016wz.para.TempReplyPara;
import cc.uling.usdk.board.p016wz.para.XPReplyPara;
import cc.uling.usdk.board.p016wz.para.XSReplyPara;
import cc.uling.usdk.board.p016wz.para.XioReplyPara;
import cc.uling.usdk.board.p016wz.para.YPReplyPara;
import cc.uling.usdk.board.p016wz.para.YSReplyPara;
import cc.uling.usdk.board.p016wz.para.YioReplyPara;
import cc.uling.usdk.constants.MdbAddr;
import cc.uling.usdk.p015a.C0672a;
import cc.uling.usdk.para.BaseClsPara;
import cc.uling.usdk.para.C0682a;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import kotlin.UByte;

public class UBoard extends BaseDrvBoard implements InterfaceC0677d, InterfaceC0678e {
    private C0682a m198a(BaseClsPara baseClsPara) {
        byte[] bArr = new byte[2024];
        short s = (short) 1;
        bArr[0] = baseClsPara.getAddr();
        short s2 = (short) (s + 1);
        bArr[s] = baseClsPara.getFun();
        byte[] bArrM166a = C0672a.m166a(baseClsPara.getReg(), true);
        System.arraycopy(bArrM166a, 0, bArr, s2, bArrM166a.length);
        short length = (short) (s2 + bArrM166a.length);
        byte[] bArrM166a2 = C0672a.m166a(baseClsPara.getReadLen(), true);
        short s3 = (short) (length + 1);
        bArr[length] = bArrM166a2[0];
        short s4 = (short) (s3 + 1);
        bArr[s3] = bArrM166a2[1];
        if (baseClsPara.getFun() == 16 && baseClsPara.getExtData() != null) {
            short length2 = (short) baseClsPara.getExtData().length;
            short s5 = (short) (s4 + 1);
            bArr[s4] = C0672a.m166a(length2, true)[1];
            System.arraycopy(baseClsPara.getExtData(), 0, bArr, s5, length2);
            s4 = (short) (s5 + length2);
        }
        byte[] bArrM167a = C0672a.m167a(bArr, s4);
        short s6 = (short) (s4 + 1);
        bArr[s4] = bArrM167a[0];
        int i = (short) (s6 + 1);
        bArr[s6] = bArrM167a[1];
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        C0682a c0682a = new C0682a();
        c0682a.m262a(baseClsPara.getAddr());
        c0682a.m266b(baseClsPara.getFun());
        c0682a.m264a(bArr2);
        return c0682a;
    }

    private InputStream m199a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (byte b : bArr) {
            byteArrayOutputStream.write(b);
        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    public static Object m200a(ACReplyPara aCReplyPara, byte[] bArr) {
        aCReplyPara.setChannels(C0672a.m174d(C0672a.m162a(new byte[]{bArr[4], bArr[5]})));
        return aCReplyPara;
    }

    public Object m201a(ARReplyPara aRReplyPara, byte[] bArr) {
        aRReplyPara.setStatus(bArr[m188a() + 2]);
        return aRReplyPara;
    }

    public static Object m202a(ASReplyPara aSReplyPara, byte[] bArr) {
        return aSReplyPara;
    }

    public Object m203a(AVReplyPara aVReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1 + 1;
        double dPow = ((double) bArr[iA]) * Math.pow(10.0d, -bArr[r1]);
        aVReplyPara.setMinValue(Double.valueOf(dPow));
        int i2 = i + 1;
        aVReplyPara.setValue1(Double.valueOf(((double) bArr[i]) * dPow));
        int i3 = i2 + 1;
        aVReplyPara.setValue2(Double.valueOf(((double) bArr[i2]) * dPow));
        int i4 = i3 + 1;
        aVReplyPara.setValue3(Double.valueOf(((double) bArr[i3]) * dPow));
        int i5 = i4 + 1;
        aVReplyPara.setValue4(Double.valueOf(((double) bArr[i4]) * dPow));
        int i6 = i5 + 1;
        aVReplyPara.setValue5(Double.valueOf(((double) bArr[i5]) * dPow));
        int i7 = i6 + 1;
        aVReplyPara.setValue6(Double.valueOf(((double) bArr[i6]) * dPow));
        int i8 = i7 + 1;
        aVReplyPara.setValue7(Double.valueOf(((double) bArr[i7]) * dPow));
        int i9 = i8 + 1;
        aVReplyPara.setValue8(Double.valueOf(((double) bArr[i8]) * dPow));
        int i10 = i9 + 1;
        aVReplyPara.setValue9(Double.valueOf(((double) bArr[i9]) * dPow));
        int i11 = i10 + 1;
        aVReplyPara.setValue10(Double.valueOf(((double) bArr[i10]) * dPow));
        int i12 = i11 + 1;
        aVReplyPara.setValue11(Double.valueOf(((double) bArr[i11]) * dPow));
        int i13 = i12 + 1;
        aVReplyPara.setValue12(Double.valueOf(((double) bArr[i12]) * dPow));
        int i14 = i13 + 1;
        aVReplyPara.setValue13(Double.valueOf(((double) bArr[i13]) * dPow));
        int i15 = i14 + 1;
        aVReplyPara.setValue14(Double.valueOf(((double) bArr[i14]) * dPow));
        aVReplyPara.setValue15(Double.valueOf(((double) bArr[i15]) * dPow));
        aVReplyPara.setValue16(Double.valueOf(((double) bArr[i15 + 1]) * dPow));
        return aVReplyPara;
    }

    public Object m204a(BBReplyPara bBReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        bBReplyPara.setValue1(bArr[iA]);
        int i2 = i + 1;
        bBReplyPara.setValue2(bArr[i]);
        int i3 = i2 + 1;
        bBReplyPara.setValue3(bArr[i2]);
        int i4 = i3 + 1;
        bBReplyPara.setValue4(bArr[i3]);
        int i5 = i4 + 1;
        bBReplyPara.setValue5(bArr[i4]);
        int i6 = i5 + 1;
        bBReplyPara.setValue6(bArr[i5]);
        int i7 = i6 + 1;
        bBReplyPara.setValue7(bArr[i6]);
        int i8 = i7 + 1;
        bBReplyPara.setValue8(bArr[i7]);
        int i9 = i8 + 1;
        bBReplyPara.setValue9(bArr[i8]);
        int i10 = i9 + 1;
        bBReplyPara.setValue10(bArr[i9]);
        int i11 = i10 + 1;
        bBReplyPara.setValue11(bArr[i10]);
        int i12 = i11 + 1;
        bBReplyPara.setValue12(bArr[i11]);
        int i13 = i12 + 1;
        bBReplyPara.setValue13(bArr[i12]);
        int i14 = i13 + 1;
        bBReplyPara.setValue14(bArr[i13]);
        bBReplyPara.setValue15(bArr[i14]);
        bBReplyPara.setValue16(bArr[i14 + 1]);
        return bBReplyPara;
    }

    public Object m205a(BRReplyPara bRReplyPara, byte[] bArr) {
        int i = bArr[m188a()] & UByte.MAX_VALUE;
        int iA = m188a() + 1;
        int i2 = 0;
        while (i2 < i) {
            i2++;
            bRReplyPara.setChannel(i2, bArr[iA] & UByte.MAX_VALUE);
            iA++;
        }
        return bRReplyPara;
    }

    public static Object m206a(CBReplyPara cBReplyPara, byte[] bArr) {
        return cBReplyPara;
    }

    public Object m207a(CSReplyPara cSReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        byte b = bArr[iA];
        int i2 = i + 1;
        int iM169b = C0672a.m169b(new byte[]{0, bArr[i], bArr[i2], bArr[i2 + 1]});
        cSReplyPara.setStatus(b);
        cSReplyPara.setMultiple(iM169b);
        return cSReplyPara;
    }

    public Object m208a(HCReplyPara hCReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        hCReplyPara.setVersion(bArr[iA]);
        int i2 = i + 1;
        byte b = bArr[i];
        byte[] bArrM170b = C0672a.m170b(b);
        m195b(C0672a.m161a(b) + "|" + ((int) bArrM170b[0]) + "|" + ((int) bArrM170b[1]) + "|" + ((int) bArrM170b[2]) + "|" + ((int) bArrM170b[3]) + "|" + ((int) bArrM170b[4]) + "|" + ((int) bArrM170b[5]) + "|" + ((int) bArrM170b[6]) + "|" + ((int) bArrM170b[7]));
        hCReplyPara.setWithCoin(bArrM170b[7] == 1);
        hCReplyPara.setWithCash(bArrM170b[6] == 1);
        hCReplyPara.setWithPOS(bArrM170b[5] == 1);
        hCReplyPara.setWithPulse(bArrM170b[4] == 1);
        hCReplyPara.setWithIdentify(bArrM170b[3] == 1);
        hCReplyPara.setCode(C0672a.m162a(new byte[]{bArr[i2], bArr[i2 + 1]}).trim());
        return hCReplyPara;
    }

    public static Object m209a(IPReplyPara iPReplyPara, byte[] bArr) {
        return iPReplyPara;
    }

    public Object m210a(MPReplyPara mPReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        int i2 = i + 1;
        int iM169b = C0672a.m169b(new byte[]{bArr[iA], bArr[i]});
        int iM169b2 = C0672a.m169b(new byte[]{bArr[i2], bArr[i2 + 1]});
        mPReplyPara.setValue(iM169b);
        mPReplyPara.setDecimal(iM169b2);
        return mPReplyPara;
    }

    public static Object m211a(PBReplyPara pBReplyPara, byte[] bArr) {
        return pBReplyPara;
    }

    public static Object m212a(PCReplyPara pCReplyPara, byte[] bArr) {
        return pCReplyPara;
    }

    public Object m213a(PMReplyPara pMReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        byte b = bArr[iA];
        int i2 = i + 1;
        pMReplyPara.setMultiple(C0672a.m169b(new byte[]{0, bArr[i], bArr[i2], bArr[i2 + 1]}));
        pMReplyPara.setPayType(b);
        return pMReplyPara;
    }

    public static Object m214a(PayReplyPara payReplyPara, byte[] bArr) {
        return payReplyPara;
    }

    public static Object m215a(ResultReplyPara resultReplyPara, byte[] bArr) {
        return resultReplyPara;
    }

    public static Object m216a(STReplyPara sTReplyPara, byte[] bArr) {
        return sTReplyPara;
    }

    public Object m217a(SVReplyPara sVReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        int i2 = i + 1;
        int i3 = i2 + 1;
        sVReplyPara.setVersion(C0672a.m161a(bArr[iA]) + "" + C0672a.m161a(bArr[i]) + "" + C0672a.m161a(bArr[i2]) + "" + C0672a.m161a(bArr[i3]));
        return sVReplyPara;
    }

    public static Object m218a(WMReplyPara wMReplyPara, byte[] bArr) {
        return wMReplyPara;
    }

    public static Object m219a(AOReplyPara aOReplyPara, byte[] bArr) {
        return aOReplyPara;
    }

    public Object m220a(cc.uling.usdk.board.p016wz.para.ASReplyPara aSReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        byte b = bArr[iA];
        int i2 = i + 1;
        byte b2 = bArr[i];
        int i3 = i2 + 1;
        byte b3 = bArr[i2];
        byte b4 = bArr[i3];
        C0672a.m170b(b);
        byte[] bArrM170b = C0672a.m170b(b2);
        byte[] bArrM170b2 = C0672a.m170b(b3);
        byte[] bArrM170b3 = C0672a.m170b(b4);
        aSReplyPara.setN0(bArrM170b3[7]);
        aSReplyPara.setN1(bArrM170b3[6]);
        aSReplyPara.setN2(bArrM170b3[5]);
        aSReplyPara.setN3(bArrM170b3[4]);
        aSReplyPara.setN4(bArrM170b3[3]);
        aSReplyPara.setN5(bArrM170b3[2]);
        aSReplyPara.setN6(bArrM170b3[1]);
        aSReplyPara.setN7(bArrM170b3[0]);
        aSReplyPara.setN8(bArrM170b2[7]);
        aSReplyPara.setN9(bArrM170b2[6]);
        aSReplyPara.setY0(bArrM170b2[5]);
        aSReplyPara.setY1(bArrM170b2[4]);
        aSReplyPara.setY2(bArrM170b2[3]);
        aSReplyPara.setY3(bArrM170b2[2]);
        aSReplyPara.setY4(bArrM170b2[1]);
        aSReplyPara.setY5(bArrM170b2[0]);
        aSReplyPara.setY6(bArrM170b[7]);
        aSReplyPara.setY7(bArrM170b[6]);
        aSReplyPara.setY8(bArrM170b[5]);
        aSReplyPara.setY9(bArrM170b[4]);
        return aSReplyPara;
    }

    public Object m221a(BSReplyPara bSReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        int iM169b = C0672a.m169b(new byte[]{0, bArr[iA]});
        int iM169b2 = C0672a.m169b(new byte[]{0, bArr[i]});
        bSReplyPara.setNo(iM169b);
        bSReplyPara.setStatus(iM169b2);
        return bSReplyPara;
    }

    public Object m222a(CXReplyPara cXReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        cXReplyPara.setValue(C0672a.m169b(new byte[]{bArr[iA], bArr[iA + 1]}));
        return cXReplyPara;
    }

    public Object m223a(CYReplyPara cYReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        cYReplyPara.setValue(C0672a.m169b(new byte[]{bArr[iA], bArr[iA + 1]}));
        return cYReplyPara;
    }

    public static Object m224a(DCReplyPara dCReplyPara, byte[] bArr) {
        return dCReplyPara;
    }

    public Object m225a(DSReplyPara dSReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        dSReplyPara.setStatus(C0672a.m169b(new byte[]{bArr[iA], bArr[iA + 1]}));
        return dSReplyPara;
    }

    public Object m226a(cc.uling.usdk.board.p016wz.para.HCReplyPara hCReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        String strM161a = C0672a.m161a(bArr[iA]);
        int i2 = i + 1;
        String strM161a2 = C0672a.m161a(bArr[i]);
        int i3 = i2 + 1;
        int iM169b = C0672a.m169b(new byte[]{0, bArr[i2]});
        int iM169b2 = C0672a.m169b(new byte[]{0, bArr[i3]});
        hCReplyPara.setVersion(strM161a + strM161a2);
        hCReplyPara.setRow(iM169b);
        hCReplyPara.setColumn(iM169b2);
        return hCReplyPara;
    }

    public Object m227a(IOReplyPara iOReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        byte b = bArr[iA];
        byte b2 = bArr[i];
        byte[] bArrM170b = C0672a.m170b(b2);
        byte[] bArrM170b2 = C0672a.m170b(b);
        m195b(C0672a.m161a(b2) + "|" + ((int) bArrM170b[0]) + "|" + ((int) bArrM170b[1]) + "|" + ((int) bArrM170b[2]) + "|" + ((int) bArrM170b[3]) + "|" + ((int) bArrM170b[4]) + "|" + ((int) bArrM170b[5]) + "|" + ((int) bArrM170b[6]) + "|" + ((int) bArrM170b[7]));
        iOReplyPara.setIo0(bArrM170b[7]);
        iOReplyPara.setIo1(bArrM170b[6]);
        iOReplyPara.setIo2(bArrM170b[5]);
        iOReplyPara.setIo3(bArrM170b[4]);
        iOReplyPara.setIo4(bArrM170b[3]);
        iOReplyPara.setIo5(bArrM170b[2]);
        iOReplyPara.setIo6(bArrM170b[1]);
        iOReplyPara.setIo7(bArrM170b[0]);
        iOReplyPara.setIo8(bArrM170b2[7]);
        iOReplyPara.setIo9(bArrM170b2[6]);
        iOReplyPara.setIo10(bArrM170b2[5]);
        iOReplyPara.setIo11(bArrM170b[4]);
        iOReplyPara.setIo12(bArrM170b2[3]);
        return iOReplyPara;
    }

    public static Object m228a(MTReplyPara mTReplyPara, byte[] bArr) {
        return mTReplyPara;
    }

    public static Object m229a(PXReplyPara pXReplyPara, byte[] bArr) {
        return pXReplyPara;
    }

    public static Object m230a(PYReplyPara pYReplyPara, byte[] bArr) {
        return pYReplyPara;
    }

    public static Object m231a(RMReplyPara rMReplyPara, byte[] bArr) {
        return rMReplyPara;
    }

    public static Object m232a(ResetReplyPara resetReplyPara, byte[] bArr) {
        return resetReplyPara;
    }

    public static Object m233a(SReplyPara sReplyPara, byte[] bArr) {
        return sReplyPara;
    }

    public Object m234a(SSReplyPara sSReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        int iM169b = C0672a.m169b(new byte[]{0, bArr[iA]});
        int iM169b2 = C0672a.m169b(new byte[]{0, bArr[i]});
        sSReplyPara.setRunStatus(iM169b);
        sSReplyPara.setFaultCode(iM169b2);
        return sSReplyPara;
    }

    public Object m235a(cc.uling.usdk.board.p016wz.para.SVReplyPara sVReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        int i2 = i + 1;
        int i3 = i2 + 1;
        sVReplyPara.setVersion(C0672a.m161a(bArr[iA]) + "" + C0672a.m161a(bArr[i]) + "" + C0672a.m161a(bArr[i2]) + "" + C0672a.m161a(bArr[i3]));
        return sVReplyPara;
    }

    public static Object m236a(SXPReplyPara sXPReplyPara, byte[] bArr) {
        return sXPReplyPara;
    }

    public static Object m237a(SYPReplyPara sYPReplyPara, byte[] bArr) {
        return sYPReplyPara;
    }

    public static Object m238a(TXReplyPara tXReplyPara, byte[] bArr) {
        return tXReplyPara;
    }

    public static Object m239a(TYReplyPara tYReplyPara, byte[] bArr) {
        return tYReplyPara;
    }

    public Object m240a(TempReplyPara tempReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        int i2 = i + 1;
        int iM169b = C0672a.m169b(new byte[]{bArr[iA], bArr[i]});
        int iM169b2 = C0672a.m169b(new byte[]{bArr[i2], bArr[i2 + 1]});
        tempReplyPara.setTemp(iM169b);
        tempReplyPara.setHumi(iM169b2);
        return tempReplyPara;
    }

    public Object m241a(XPReplyPara xPReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        int i2 = i + 1;
        int iM169b = C0672a.m169b(new byte[]{bArr[iA], bArr[i]});
        int i3 = i2 + 1;
        int i4 = i3 + 1;
        int iM169b2 = C0672a.m169b(new byte[]{bArr[i2], bArr[i3]});
        int i5 = i4 + 1;
        int i6 = i5 + 1;
        int iM169b3 = C0672a.m169b(new byte[]{bArr[i4], bArr[i5]});
        int i7 = i6 + 1;
        int i8 = i7 + 1;
        int iM169b4 = C0672a.m169b(new byte[]{bArr[i6], bArr[i7]});
        int i9 = i8 + 1;
        int i10 = i9 + 1;
        int iM169b5 = C0672a.m169b(new byte[]{bArr[i8], bArr[i9]});
        int i11 = i10 + 1;
        int i12 = i11 + 1;
        int iM169b6 = C0672a.m169b(new byte[]{bArr[i10], bArr[i11]});
        int i13 = i12 + 1;
        int i14 = i13 + 1;
        int iM169b7 = C0672a.m169b(new byte[]{bArr[i12], bArr[i13]});
        int i15 = i14 + 1;
        int i16 = i15 + 1;
        int iM169b8 = C0672a.m169b(new byte[]{bArr[i14], bArr[i15]});
        int i17 = i16 + 1;
        int i18 = i17 + 1;
        int iM169b9 = C0672a.m169b(new byte[]{bArr[i16], bArr[i17]});
        int iM169b10 = C0672a.m169b(new byte[]{bArr[i18], bArr[i18 + 1]});
        xPReplyPara.setX0(iM169b);
        xPReplyPara.setX1(iM169b2);
        xPReplyPara.setX2(iM169b3);
        xPReplyPara.setX3(iM169b4);
        xPReplyPara.setX4(iM169b5);
        xPReplyPara.setX5(iM169b6);
        xPReplyPara.setX6(iM169b7);
        xPReplyPara.setX7(iM169b8);
        xPReplyPara.setX8(iM169b9);
        xPReplyPara.setX9(iM169b10);
        return xPReplyPara;
    }

    public Object m242a(XSReplyPara xSReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        int iM169b = C0672a.m169b(new byte[]{0, bArr[iA]});
        int iM169b2 = C0672a.m169b(new byte[]{0, bArr[i]});
        xSReplyPara.setRunStatus(iM169b);
        xSReplyPara.setFaultCode(iM169b2);
        return xSReplyPara;
    }

    public Object m243a(XioReplyPara xioReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        byte b = bArr[iA];
        byte b2 = bArr[i];
        byte[] bArrM170b = C0672a.m170b(b2);
        m195b(C0672a.m161a(b2) + "|" + ((int) bArrM170b[0]) + "|" + ((int) bArrM170b[1]) + "|" + ((int) bArrM170b[2]) + "|" + ((int) bArrM170b[3]) + "|" + ((int) bArrM170b[4]) + "|" + ((int) bArrM170b[5]) + "|" + ((int) bArrM170b[6]) + "|" + ((int) bArrM170b[7]));
        xioReplyPara.setIo0(bArrM170b[7]);
        xioReplyPara.setIo1(bArrM170b[6]);
        xioReplyPara.setIo2(bArrM170b[5]);
        xioReplyPara.setIo3(bArrM170b[4]);
        xioReplyPara.setIo4(bArrM170b[3]);
        xioReplyPara.setIo5(bArrM170b[2]);
        xioReplyPara.setIo6(bArrM170b[1]);
        xioReplyPara.setIo7(bArrM170b[0]);
        return xioReplyPara;
    }

    public Object m244a(YPReplyPara yPReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        int i2 = i + 1;
        int iM169b = C0672a.m169b(new byte[]{bArr[iA], bArr[i]});
        int i3 = i2 + 1;
        int i4 = i3 + 1;
        int iM169b2 = C0672a.m169b(new byte[]{bArr[i2], bArr[i3]});
        int i5 = i4 + 1;
        int i6 = i5 + 1;
        int iM169b3 = C0672a.m169b(new byte[]{bArr[i4], bArr[i5]});
        int i7 = i6 + 1;
        int i8 = i7 + 1;
        int iM169b4 = C0672a.m169b(new byte[]{bArr[i6], bArr[i7]});
        int i9 = i8 + 1;
        int i10 = i9 + 1;
        int iM169b5 = C0672a.m169b(new byte[]{bArr[i8], bArr[i9]});
        int i11 = i10 + 1;
        int i12 = i11 + 1;
        int iM169b6 = C0672a.m169b(new byte[]{bArr[i10], bArr[i11]});
        int i13 = i12 + 1;
        int i14 = i13 + 1;
        int iM169b7 = C0672a.m169b(new byte[]{bArr[i12], bArr[i13]});
        int i15 = i14 + 1;
        int i16 = i15 + 1;
        int iM169b8 = C0672a.m169b(new byte[]{bArr[i14], bArr[i15]});
        int i17 = i16 + 1;
        int i18 = i17 + 1;
        int iM169b9 = C0672a.m169b(new byte[]{bArr[i16], bArr[i17]});
        int iM169b10 = C0672a.m169b(new byte[]{bArr[i18], bArr[i18 + 1]});
        yPReplyPara.setY0(iM169b);
        yPReplyPara.setY1(iM169b2);
        yPReplyPara.setY2(iM169b3);
        yPReplyPara.setY3(iM169b4);
        yPReplyPara.setY4(iM169b5);
        yPReplyPara.setY5(iM169b6);
        yPReplyPara.setY6(iM169b7);
        yPReplyPara.setY7(iM169b8);
        yPReplyPara.setY8(iM169b9);
        yPReplyPara.setY9(iM169b10);
        return yPReplyPara;
    }

    public Object m245a(YSReplyPara ySReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        int iM169b = C0672a.m169b(new byte[]{0, bArr[iA]});
        int iM169b2 = C0672a.m169b(new byte[]{0, bArr[i]});
        ySReplyPara.setRunStatus(iM169b);
        ySReplyPara.setFaultCode(iM169b2);
        return ySReplyPara;
    }

    public Object m246a(YioReplyPara yioReplyPara, byte[] bArr) {
        int iA = m188a() + 1;
        int i = iA + 1;
        byte b = bArr[iA];
        byte b2 = bArr[i];
        byte[] bArrM170b = C0672a.m170b(b2);
        m195b(C0672a.m161a(b2) + "|" + ((int) bArrM170b[0]) + "|" + ((int) bArrM170b[1]) + "|" + ((int) bArrM170b[2]) + "|" + ((int) bArrM170b[3]) + "|" + ((int) bArrM170b[4]) + "|" + ((int) bArrM170b[5]) + "|" + ((int) bArrM170b[6]) + "|" + ((int) bArrM170b[7]));
        yioReplyPara.setIo0(bArrM170b[7]);
        yioReplyPara.setIo1(bArrM170b[6]);
        yioReplyPara.setIo2(bArrM170b[5]);
        yioReplyPara.setIo3(bArrM170b[4]);
        yioReplyPara.setIo4(bArrM170b[3]);
        yioReplyPara.setIo5(bArrM170b[2]);
        yioReplyPara.setIo6(bArrM170b[1]);
        yioReplyPara.setIo7(bArrM170b[0]);
        return yioReplyPara;
    }

    @Override
    public void ArrayOutput(final AOReplyPara aOReplyPara) {
        m247a(aOReplyPara, "AO", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m219a(aOReplyPara, bArr);
            }
        });
    }

    @Override
    public void DriveOutput(final DCReplyPara dCReplyPara) {
        m247a(dCReplyPara, "DC", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m224a(dCReplyPara, bArr);
            }
        });
    }

    @Override
    public void GetArrayStatus(final cc.uling.usdk.board.p016wz.para.ASReplyPara aSReplyPara) {
        m247a(aSReplyPara, "AS", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m220a(aSReplyPara, bArr);
            }
        });
    }

    @Override
    public void GetBoxStatus(final BSReplyPara bSReplyPara) {
        m247a(bSReplyPara, "SV", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m221a(bSReplyPara, bArr);
            }
        });
    }

    @Override
    public void GetDropStatus(final DSReplyPara dSReplyPara) {
        m247a(dSReplyPara, "DS", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m225a(dSReplyPara, bArr);
            }
        });
    }

    @Override
    public void GetIOStatus(final IOReplyPara iOReplyPara) {
        m247a(iOReplyPara, "IO", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m227a(iOReplyPara, bArr);
            }
        });
    }

    @Override
    public void GetShipmentStatus(final SSReplyPara sSReplyPara) {
        m247a(sSReplyPara, "STATUS", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m234a(sSReplyPara, bArr);
            }
        });
    }

    @Override
    public void GetSoftwareVersion(final cc.uling.usdk.board.p016wz.para.SVReplyPara sVReplyPara) {
        m247a(sVReplyPara, "SV", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m235a(sVReplyPara, bArr);
            }
        });
    }

    @Override
    public void GetXIOStatus(final XioReplyPara xioReplyPara) {
        m247a(xioReplyPara, "XIO", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m243a(xioReplyPara, bArr);
            }
        });
    }

    @Override
    public void GetXPos(final CXReplyPara cXReplyPara) {
        m247a(cXReplyPara, "CX", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m222a(cXReplyPara, bArr);
            }
        });
    }

    @Override
    public void GetXPos(final XPReplyPara xPReplyPara) {
        m247a(xPReplyPara, "XP", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m241a(xPReplyPara, bArr);
            }
        });
    }

    @Override
    public void GetXStatus(final XSReplyPara xSReplyPara) {
        m247a(xSReplyPara, "XS", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m242a(xSReplyPara, bArr);
            }
        });
    }

    @Override
    public void GetYIOStatus(final YioReplyPara yioReplyPara) {
        m247a(yioReplyPara, "YIO", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m246a(yioReplyPara, bArr);
            }
        });
    }

    @Override
    public void GetYPos(final CYReplyPara cYReplyPara) {
        m247a(cYReplyPara, "CY", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m223a(cYReplyPara, bArr);
            }
        });
    }

    @Override
    public void GetYPos(final YPReplyPara yPReplyPara) {
        m247a(yPReplyPara, "YP", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m244a(yPReplyPara, bArr);
            }
        });
    }

    @Override
    public void GetYStatus(final YSReplyPara ySReplyPara) {
        m247a(ySReplyPara, "YS", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m245a(ySReplyPara, bArr);
            }
        });
    }

    @Override
    public void MotoTimeout(final MTReplyPara mTReplyPara) {
        m247a(mTReplyPara, "MT", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m228a(mTReplyPara, bArr);
            }
        });
    }

    @Override
    public void ReadHardwareConfig(final cc.uling.usdk.board.p016wz.para.HCReplyPara hCReplyPara) {
        m247a(hCReplyPara, "WZ", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m226a(hCReplyPara, bArr);
            }
        });
    }

    @Override
    public void ReadTemp(final TempReplyPara tempReplyPara) {
        m247a(tempReplyPara, "TMP", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m240a(tempReplyPara, bArr);
            }
        });
    }

    @Override
    public void ResetLift(final ResetReplyPara resetReplyPara) {
        m247a(resetReplyPara, "RESET", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m232a(resetReplyPara, bArr);
            }
        });
    }

    @Override
    public void RunMoto(final RMReplyPara rMReplyPara) {
        m247a(rMReplyPara, "RM", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m231a(rMReplyPara, bArr);
            }
        });
    }

    @Override
    public void SeXPos(final SXPReplyPara sXPReplyPara) {
        m247a(sXPReplyPara, "SXP", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m236a(sXPReplyPara, bArr);
            }
        });
    }

    @Override
    public void SeYPos(final SYPReplyPara sYPReplyPara) {
        m247a(sYPReplyPara, "SYP", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m237a(sYPReplyPara, bArr);
            }
        });
    }

    @Override
    public void SetPickX(final PXReplyPara pXReplyPara) {
        m247a(pXReplyPara, "PX", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m229a(pXReplyPara, bArr);
            }
        });
    }

    @Override
    public void SetPickY(final PYReplyPara pYReplyPara) {
        m247a(pYReplyPara, "PY", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m230a(pYReplyPara, bArr);
            }
        });
    }

    @Override
    public void Shipment(final SReplyPara sReplyPara) {
        m247a(sReplyPara, "SHIP", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m233a(sReplyPara, bArr);
            }
        });
    }

    @Override
    public void ToX(final TXReplyPara tXReplyPara) {
        m247a(tXReplyPara, "TY", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m238a(tXReplyPara, bArr);
            }
        });
    }

    @Override
    public void ToY(final TYReplyPara tYReplyPara) {
        m247a(tYReplyPara, "TY", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m239a(tYReplyPara, bArr);
            }
        });
    }

    protected void m247a(BaseClsPara baseClsPara, String str, InterfaceC0674a interfaceC0674a) {
        byte[] bArrA;
        byte[] bArr;
        C0682a c0682aM198a = m198a(baseClsPara);
        try {
            m191a(str, c0682aM198a);
            try {
                if (f231a) {
                    if (baseClsPara.getReg() == 1) {
                        bArr = new byte[]{MdbAddr.DEF_ADDR, 3, 4, 1, 3, 0, -122, 107, SnmpConstants.SET_REQ_MSG};
                    } else if (baseClsPara.getReg() == 2) {
                        bArr = new byte[]{MdbAddr.DEF_ADDR, 3, 4, 32, 32, 8, SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC, -41, -8};
                    } else if (baseClsPara.getReg() == 3) {
                        bArr = new byte[]{MdbAddr.DEF_ADDR, 3, 4, 1, 0, 0, 2, -101, SnmpConstants.ASN_PRIVATE};
                    } else if (baseClsPara.getReg() == 4) {
                        bArr = new byte[]{MdbAddr.DEF_ADDR, 3, 4, 0, 1, 0, 2, -53, -4};
                    } else if (baseClsPara.getReg() == 5) {
                        bArr = new byte[]{MdbAddr.DEF_ADDR, 3, 4, 3, 0, 0, 100, 26, 82};
                    } else {
                        if (baseClsPara.getReg() == 6) {
                            bArr = new byte[21];
                        } else if (baseClsPara.getReg() == 7) {
                            bArr = new byte[]{MdbAddr.DEF_ADDR, 3, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, 5, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -72, 102};
                        } else if (baseClsPara.getReg() == 8) {
                            bArr = new byte[]{MdbAddr.DEF_ADDR, 3, 16, 5, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 92};
                        } else if (baseClsPara.getReg() == 9) {
                            bArr = new byte[]{MdbAddr.DEF_ADDR, 3, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, 1, 0, 1, 5, 10, SnmpConstants.SNMP_ERR_DECODINGASN_EXC, 50, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 57, 43};
                        } else if (baseClsPara.getReg() == 10) {
                            bArr = new byte[21];
                        } else {
                            bArr = baseClsPara.getReg() == 11 ? new byte[]{MdbAddr.DEF_ADDR, 3, 2, 0, 3, 121, -109} : baseClsPara.getReg() == 4097 ? new byte[]{MdbAddr.DEF_ADDR, 6, 16, 1, 0, 1, 11, 106} : baseClsPara.getReg() == 4098 ? new byte[]{MdbAddr.DEF_ADDR, 6, 16, 2, 0, 1, -5, 106} : baseClsPara.getReg() == 4099 ? new byte[]{MdbAddr.DEF_ADDR, 6, 16, 3, 0, 0, 107, 106} : baseClsPara.getReg() == 4100 ? new byte[]{MdbAddr.DEF_ADDR, 6, 16, 4, 0, SnmpConstants.SNMP_VAR_NOSUCHINSTANCE, 26, -53} : baseClsPara.getReg() == 4101 ? new byte[]{MdbAddr.DEF_ADDR, 6, 16, 5, 0, SnmpConstants.ASN_EXTENSION_ID, -54, SnmpConstants.SET_REQ_MSG} : baseClsPara.getReg() == 4102 ? new byte[]{MdbAddr.DEF_ADDR, 6, 16, 6, 0, 1, -70, -85} : baseClsPara.getReg() == 4103 ? new byte[]{MdbAddr.DEF_ADDR, 6, 16, 7, 0, 0, 42, -85} : baseClsPara.getReg() == 4104 ? new byte[]{MdbAddr.DEF_ADDR, 6, 16, 8, 0, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, -102, SnmpConstants.GETBULK_REQ_MSG} : baseClsPara.getReg() == 8193 ? new byte[]{MdbAddr.DEF_ADDR, 16, 32, 1, 0, 2, 13, SnmpConstants.GET_RPRT_MSG} : baseClsPara.getReg() == 8194 ? new byte[]{MdbAddr.DEF_ADDR, 16, 32, 2, 0, 2, -3, SnmpConstants.GET_RPRT_MSG} : baseClsPara.getReg() == 8195 ? new byte[]{MdbAddr.DEF_ADDR, 16, 32, 3, 0, 2, -84, 104} : baseClsPara.getReg() == 8196 ? new byte[]{MdbAddr.DEF_ADDR, 16, 32, 4, 0, 3, -36, 105} : baseClsPara.getReg() == 8197 ? new byte[]{MdbAddr.DEF_ADDR, 16, 32, 5, 0, 4, -52, 107} : null;
                        }
                        
                        bArr[0] = -31;
                        bArr[1] = 3;
                        bArr[2] = 16;
                        bArr[3] = 20;
                        bArr[4] = 10;
                        bArr[5] = 5;
                        bArr[6] = 7;
                        bArr[7] = 0;
                        bArr[8] = 0;
                        bArr[9] = 0;
                        bArr[10] = 0;
                        bArr[11] = 0;
                        bArr[12] = 0;
                        bArr[13] = 0;
                        bArr[14] = 0;
                        bArr[15] = 0;
                        bArr[16] = 0;
                        bArr[17] = 0;
                        bArr[18] = 0;
                        bArr[19] = -40;
                        bArr[20] = -59;
                    }
                    bArrA = m192a(c0682aM198a, m199a(bArr), str);
                } else {
                    bArrA = m193a(c0682aM198a, str);
                }
                if (interfaceC0674a == null || bArrA == null) {
                    return;
                }
                interfaceC0674a.getVal(bArrA);
                return;
            } catch (C0675b e) {
                baseClsPara.setResultCode(e.m248a());
                baseClsPara.setErrorMsg(e.m251b());
                return;
            }
        } catch (C0675b e2) {
            e2.printStackTrace();
            baseClsPara.setResultCode(e2.m248a());
            baseClsPara.setErrorMsg(e2.m251b());
        }
        e2.printStackTrace();
        baseClsPara.setResultCode(e2.m248a());
        baseClsPara.setErrorMsg(e2.m251b());
    }

    @Override
    public void changeBalance(final CBReplyPara cBReplyPara) {
        m247a(cBReplyPara, "CB", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m206a(cBReplyPara, bArr);
            }
        });
    }

    @Override
    public void findChangeResult(final BRReplyPara bRReplyPara) {
        m247a(bRReplyPara, "BR", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m205a(bRReplyPara, bArr);
            }
        });
    }

    @Override
    public void getAcceptUnit(final AVReplyPara aVReplyPara) {
        m247a(aVReplyPara, "AV", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m203a(aVReplyPara, bArr);
            }
        });
    }

    @Override
    public void getAuthResult(final ARReplyPara aRReplyPara) {
        m247a(aRReplyPara, "AR", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m201a(aRReplyPara, bArr);
            }
        });
    }

    @Override
    public void getChangeStatus(final CSReplyPara cSReplyPara) {
        m247a(cSReplyPara, "CS", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m207a(cSReplyPara, bArr);
            }
        });
    }

    @Override
    public void getMinPayoutAmount(final MPReplyPara mPReplyPara) {
        m247a(mPReplyPara, "MP", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m210a(mPReplyPara, bArr);
            }
        });
    }

    @Override
    public void getMoneyBalance(final BBReplyPara bBReplyPara) {
        m247a(bBReplyPara, "BB", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m204a(bBReplyPara, bArr);
            }
        });
    }

    @Override
    public void getPayAmount(final PMReplyPara pMReplyPara) {
        m247a(pMReplyPara, "PM", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m213a(pMReplyPara, bArr);
            }
        });
    }

    @Override
    public void getSoftwareVersion(final SVReplyPara sVReplyPara) {
        m247a(sVReplyPara, "SV", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m217a(sVReplyPara, bArr);
            }
        });
    }

    @Override
    public void initPayment(final IPReplyPara iPReplyPara) {
        m247a(iPReplyPara, "IP", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m209a(iPReplyPara, bArr);
            }
        });
    }

    @Override
    public void notifyPayment(final PayReplyPara payReplyPara) {
        m247a(payReplyPara, "PP", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m214a(payReplyPara, bArr);
            }
        });
    }

    @Override
    public void notifyResult(final ResultReplyPara resultReplyPara) {
        m247a(resultReplyPara, "RR", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m215a(resultReplyPara, bArr);
            }
        });
    }

    @Override
    public void pulseBalance(final PBReplyPara pBReplyPara) {
        m247a(pBReplyPara, "PB", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m211a(pBReplyPara, bArr);
            }
        });
    }

    @Override
    public void readHardwareConfig(final HCReplyPara hCReplyPara) {
        m247a(hCReplyPara, "HC", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return this.f$0.m208a(hCReplyPara, bArr);
            }
        });
    }

    @Override
    public void setAcceptMoney(final ACReplyPara aCReplyPara) {
        m247a(aCReplyPara, "AC", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m200a(aCReplyPara, bArr);
            }
        });
    }

    @Override
    public void setAgeScope(final ASReplyPara aSReplyPara) {
        m247a(aSReplyPara, "AS", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m202a(aSReplyPara, bArr);
            }
        });
    }

    @Override
    public void setPayChannel(final PCReplyPara pCReplyPara) {
        m247a(pCReplyPara, "PC", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m212a(pCReplyPara, bArr);
            }
        });
    }

    @Override
    public void setWorkMode(final WMReplyPara wMReplyPara) {
        m247a(wMReplyPara, "WM", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m218a(wMReplyPara, bArr);
            }
        });
    }

    @Override
    public void syncSystemTime(final STReplyPara sTReplyPara) {
        m247a(sTReplyPara, "ST", new InterfaceC0674a() {
            @Override
            public final Object getVal(byte[] bArr) {
                return UBoard.m216a(sTReplyPara, bArr);
            }
        });
    }
}
