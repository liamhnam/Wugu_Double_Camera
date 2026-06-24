package cc.uling.usdk.board;

import cc.uling.usdk.board.p016wz.para.AOReplyPara;
import cc.uling.usdk.board.p016wz.para.ASReplyPara;
import cc.uling.usdk.board.p016wz.para.BSReplyPara;
import cc.uling.usdk.board.p016wz.para.CXReplyPara;
import cc.uling.usdk.board.p016wz.para.CYReplyPara;
import cc.uling.usdk.board.p016wz.para.DCReplyPara;
import cc.uling.usdk.board.p016wz.para.DSReplyPara;
import cc.uling.usdk.board.p016wz.para.HCReplyPara;
import cc.uling.usdk.board.p016wz.para.IOReplyPara;
import cc.uling.usdk.board.p016wz.para.MTReplyPara;
import cc.uling.usdk.board.p016wz.para.PXReplyPara;
import cc.uling.usdk.board.p016wz.para.PYReplyPara;
import cc.uling.usdk.board.p016wz.para.RMReplyPara;
import cc.uling.usdk.board.p016wz.para.ResetReplyPara;
import cc.uling.usdk.board.p016wz.para.SReplyPara;
import cc.uling.usdk.board.p016wz.para.SSReplyPara;
import cc.uling.usdk.board.p016wz.para.SVReplyPara;
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

public interface InterfaceC0678e {
    void ArrayOutput(AOReplyPara aOReplyPara);

    void DriveOutput(DCReplyPara dCReplyPara);

    void GetArrayStatus(ASReplyPara aSReplyPara);

    void GetBoxStatus(BSReplyPara bSReplyPara);

    void GetDropStatus(DSReplyPara dSReplyPara);

    void GetIOStatus(IOReplyPara iOReplyPara);

    void GetShipmentStatus(SSReplyPara sSReplyPara);

    void GetSoftwareVersion(SVReplyPara sVReplyPara);

    void GetXIOStatus(XioReplyPara xioReplyPara);

    void GetXPos(CXReplyPara cXReplyPara);

    void GetXPos(XPReplyPara xPReplyPara);

    void GetXStatus(XSReplyPara xSReplyPara);

    void GetYIOStatus(YioReplyPara yioReplyPara);

    void GetYPos(CYReplyPara cYReplyPara);

    void GetYPos(YPReplyPara yPReplyPara);

    void GetYStatus(YSReplyPara ySReplyPara);

    void MotoTimeout(MTReplyPara mTReplyPara);

    void ReadHardwareConfig(HCReplyPara hCReplyPara);

    void ReadTemp(TempReplyPara tempReplyPara);

    void ResetLift(ResetReplyPara resetReplyPara);

    void RunMoto(RMReplyPara rMReplyPara);

    void SeXPos(SXPReplyPara sXPReplyPara);

    void SeYPos(SYPReplyPara sYPReplyPara);

    void SetPickX(PXReplyPara pXReplyPara);

    void SetPickY(PYReplyPara pYReplyPara);

    void Shipment(SReplyPara sReplyPara);

    void ToX(TXReplyPara tXReplyPara);

    void ToY(TYReplyPara tYReplyPara);
}
