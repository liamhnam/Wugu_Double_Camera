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

public interface InterfaceC0677d {
    void changeBalance(CBReplyPara cBReplyPara);

    void findChangeResult(BRReplyPara bRReplyPara);

    void getAcceptUnit(AVReplyPara aVReplyPara);

    void getAuthResult(ARReplyPara aRReplyPara);

    void getChangeStatus(CSReplyPara cSReplyPara);

    void getMinPayoutAmount(MPReplyPara mPReplyPara);

    void getMoneyBalance(BBReplyPara bBReplyPara);

    void getPayAmount(PMReplyPara pMReplyPara);

    void getSoftwareVersion(SVReplyPara sVReplyPara);

    void initPayment(IPReplyPara iPReplyPara);

    void notifyPayment(PayReplyPara payReplyPara);

    void notifyResult(ResultReplyPara resultReplyPara);

    void pulseBalance(PBReplyPara pBReplyPara);

    void readHardwareConfig(HCReplyPara hCReplyPara);

    void setAcceptMoney(ACReplyPara aCReplyPara);

    void setAgeScope(ASReplyPara aSReplyPara);

    void setPayChannel(PCReplyPara pCReplyPara);

    void setWorkMode(WMReplyPara wMReplyPara);

    void syncSystemTime(STReplyPara sTReplyPara);
}
