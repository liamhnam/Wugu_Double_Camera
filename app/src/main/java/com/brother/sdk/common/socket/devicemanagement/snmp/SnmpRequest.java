package com.brother.sdk.common.socket.devicemanagement.snmp;

import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnEncoder;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnInteger;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnOctets;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnSequence;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpContext;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.varbind;
import java.io.ByteArrayOutputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class SnmpRequest {
    private String community;
    private int errind;
    private int errstat;
    private byte msg_type;
    private Vector<varbind> reqVarbinds;
    private int req_id;
    private int version;

    public SnmpRequest() {
        this(SnmpConstants.GET_REQ_MSG, 0, 0, SnmpContext.Default_Community);
    }

    public SnmpRequest(byte b, int i, int i2, String str) {
        this.msg_type = b;
        this.req_id = i;
        this.version = i2;
        this.community = str;
        this.errstat = 0;
        this.errind = 0;
        this.reqVarbinds = new Vector<>();
    }

    public void addVarbind(List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            addVarbind(it.next());
        }
    }

    public void addVarbind(String str) {
        this.reqVarbinds.addElement(new varbind(str));
    }

    public byte[] makePacket() {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            Enumeration<varbind> enumerationElements = this.reqVarbinds.elements();
            AsnSequence asnSequence = new AsnSequence();
            asnSequence.add(new AsnInteger(this.version));
            asnSequence.add(new AsnOctets(this.community));
            asnSequence.add(new AsnEncoder().EncodePdu(this.msg_type, this.req_id, this.errstat, this.errind, enumerationElements));
            byteArrayOutputStream = new ByteArrayOutputStream();
            asnSequence.write(byteArrayOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            byteArrayOutputStream = null;
        }
        if (byteArrayOutputStream != null) {
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    public String getCommunity() {
        return this.community;
    }
}
