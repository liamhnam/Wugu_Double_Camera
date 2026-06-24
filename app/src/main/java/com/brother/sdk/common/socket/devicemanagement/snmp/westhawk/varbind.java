package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

public class varbind {
    private AsnObjectId name;
    private AsnObject value;

    public varbind(varbind varbindVar) {
        this.name = varbindVar.name;
        this.value = varbindVar.value;
    }

    public varbind(String str) {
        this(new AsnObjectId(str), new AsnNull());
    }

    public varbind(AsnObjectId asnObjectId) {
        this(asnObjectId, new AsnNull());
    }

    public varbind(String str, AsnObject asnObject) {
        this(new AsnObjectId(str), asnObject);
    }

    public varbind(AsnObjectId asnObjectId, AsnObject asnObject) {
        this.name = asnObjectId;
        this.value = asnObject;
    }

    public varbind(AsnSequence asnSequence) {
        this.name = (AsnObjectId) asnSequence.getObj(0);
        this.value = asnSequence.getObj(1);
    }

    public AsnObjectId getOid() {
        return this.name;
    }

    public AsnObject getValue() {
        return this.value;
    }

    Object setValue(AsnSequence asnSequence) {
        this.name = (AsnObjectId) asnSequence.getObj(0);
        AsnObject obj = asnSequence.getObj(1);
        this.value = obj;
        return obj;
    }

    public String toString() {
        return this.name.toString() + ": " + this.value.toString();
    }
}
