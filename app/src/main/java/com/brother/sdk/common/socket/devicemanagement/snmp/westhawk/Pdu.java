package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

import java.util.Observable;
import java.util.Vector;
import org.apache.log4j.spi.Configurator;

public abstract class Pdu extends Observable {
    private static final String TIMED_OUT = "Timed out";
    private static final String[] errorStrings = {"No error", "Value too big error", "No such name error", "Bad value error", "Read only error", "General error", "No access error", "Wrong type error", "Wrong length error", "Wrong encoding error", "Wrong value error", "No creation error", "Inconsistent value error", "Resource unavailable error", "Commit failed error", "Undo failed error", "Authorization error", "Not writable error", "Inconsistent name error"};
    private static int next_id = 1;
    protected boolean answered;
    protected SnmpContextBasisFace context;
    protected byte msg_type;
    protected Vector<varbind> reqVarbinds;
    protected Vector<varbind> respVarbinds = null;
    protected byte[] encodedPacket = null;
    protected boolean added = false;
    private PduException respException = null;
    private int req_id = next_id;
    protected int errstat = 0;
    protected int errind = 0;

    protected abstract void new_value(int i, varbind varbindVar);

    private static void countUpID() {
        next_id++;
    }

    public Pdu(SnmpContextBasisFace snmpContextBasisFace) {
        this.reqVarbinds = null;
        this.context = snmpContextBasisFace;
        countUpID();
        this.reqVarbinds = new Vector<>(1, 1);
        setMsgType(SnmpConstants.GET_REQ_MSG);
        this.answered = true;
    }

    public SnmpContextBasisFace getContext() {
        return this.context;
    }

    public void addOid(String str) {
        addOid(new varbind(str));
    }

    public void addOid(AsnObjectId asnObjectId) {
        addOid(new varbind(asnObjectId));
    }

    public void addOid(String str, AsnObject asnObject) {
        addOid(new varbind(str, asnObject));
    }

    public void addOid(AsnObjectId asnObjectId, AsnObject asnObject) {
        addOid(new varbind(asnObjectId, asnObject));
    }

    public void addOid(varbind varbindVar) {
        this.reqVarbinds.addElement(varbindVar);
    }

    public varbind[] getRequestVarbinds() {
        varbind[] varbindVarArr = new varbind[this.reqVarbinds.size()];
        this.reqVarbinds.copyInto(varbindVarArr);
        return varbindVarArr;
    }

    public varbind[] getResponseVarbinds() throws PduException {
        PduException pduException = this.respException;
        if (pduException != null) {
            throw pduException;
        }
        Vector<varbind> vector = this.respVarbinds;
        if (vector == null) {
            return null;
        }
        varbind[] varbindVarArr = new varbind[vector.size()];
        this.respVarbinds.copyInto(varbindVarArr);
        return varbindVarArr;
    }

    void setResponseException(PduException pduException) {
        if (AsnObject.debug > 6) {
            System.out.println("Pdu.setResponseException(): " + pduException.getMessage());
        }
        this.respException = pduException;
    }

    public int getReqId() {
        return this.req_id;
    }

    public int getErrorIndex() {
        return this.errind;
    }

    public int getErrorStatus() {
        return this.errstat;
    }

    public String getErrorStatusString() {
        int i = this.errstat;
        if (i < 0) {
            return "";
        }
        String[] strArr = errorStrings;
        if (i < strArr.length) {
            return i == 5 ? TIMED_OUT : strArr[i];
        }
        PduException pduException = this.respException;
        return pduException != null ? pduException.getMessage() : "Decoding Exception";
    }

    public String toString() {
        return toString(false);
    }

    protected String toString(boolean z) {
        StringBuffer stringBuffer = new StringBuffer(getClass().getName());
        stringBuffer.append("[context=");
        stringBuffer.append(this.context);
        stringBuffer.append(", reqId=").append(this.req_id);
        stringBuffer.append(", msgType=0x").append(SnmpUtilities.toHex(this.msg_type));
        stringBuffer.append(", reqVarbinds=");
        Vector<varbind> vector = this.reqVarbinds;
        if (vector != null) {
            int size = vector.size();
            stringBuffer.append("[");
            for (int i = 0; i < size; i++) {
                stringBuffer.append(this.reqVarbinds.elementAt(i).toString()).append(", ");
            }
            stringBuffer.append("]");
        } else {
            stringBuffer.append(Configurator.NULL);
        }
        if (z) {
            stringBuffer.append(", respVarbinds=");
            Vector<varbind> vector2 = this.respVarbinds;
            if (vector2 != null) {
                int size2 = vector2.size();
                stringBuffer.append("[");
                for (int i2 = 0; i2 < size2; i2++) {
                    stringBuffer.append(this.respVarbinds.elementAt(i2).toString()).append(", ");
                }
            } else {
                stringBuffer.append(Configurator.NULL);
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public byte getMsgType() {
        return this.msg_type;
    }

    protected void setMsgType(byte b) {
        this.msg_type = b;
    }

    protected void setErrorStatus(int i) {
        this.errstat = i;
        if (AsnObject.debug > 6) {
            System.out.println("Pdu.setErrorStatus(): " + this.errstat);
        }
    }

    protected void setErrorStatus(int i, PduException pduException) {
        this.errstat = i;
        setResponseException(pduException);
    }

    protected void setErrorIndex(int i) {
        this.errind = i;
    }

    void fillin(AsnPduSequence asnPduSequence) {
        if (this.answered) {
            if (AsnObject.debug > 6) {
                System.out.println("Pdu.fillin(): Got a second answer to request " + this.req_id);
                return;
            }
            return;
        }
        if (asnPduSequence != null) {
            if (asnPduSequence.isCorrect) {
                int i = -1;
                try {
                    this.req_id = asnPduSequence.getReqId();
                    setErrorStatus(asnPduSequence.getWhatError());
                    setErrorIndex(asnPduSequence.getWhereError());
                    AsnSequence varBind = asnPduSequence.getVarBind();
                    int objCount = varBind.getObjCount();
                    this.respVarbinds = new Vector<>(objCount, 1);
                    i = 0;
                    while (i < objCount) {
                        varbind varbindVar = new varbind((AsnSequence) varBind.getObj(i));
                        this.respVarbinds.addElement(varbindVar);
                        new_value(i, varbindVar);
                        i++;
                    }
                } catch (Exception unused) {
                    setErrorStatus(20, new DecodingException("Incorrect varbind list, element " + i));
                }
            } else {
                setErrorStatus(21, new DecodingException("Incorrect packet. No of bytes received less than packet length."));
            }
        }
        setChanged();
        clearChanged();
    }
}
