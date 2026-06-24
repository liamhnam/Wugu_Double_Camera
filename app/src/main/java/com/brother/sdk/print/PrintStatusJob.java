package com.brother.sdk.print;

import com.brother.sdk.common.IConnector;
import com.brother.sdk.common.IUnknown;
import com.brother.sdk.common.Job;
import com.brother.sdk.common.device.Device;
import com.brother.sdk.common.socket.ISocketConnector;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpContext;
import com.brother.sdk.common.socket.print.lpr.LprClient;
import com.brother.sdk.print.info.MibControlException;
import com.brother.sdk.print.info.MibControlUnsupportedOperationException;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.WeakHashMap;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class PrintStatusJob extends Job {
    ISocketConnector iSocketConnector;
    private String ipAddress;
    private PrintStatusCallBack printStatusCallBack;
    private final Snmp snmp;
    private final String OID_COVER_STATE = "1.3.6.1.2.1.43.18.1.1.8.1.1";
    private LprClient mClient = null;
    private Device mDevice = null;
    private int retries = 6;
    private int timeoutMs = 100;
    private Map<InetAddress, CommunityTarget> targetMap = new WeakHashMap<InetAddress, CommunityTarget>() {
        @Override
        public CommunityTarget get(Object obj) {
            InetAddress inetAddress = (InetAddress) obj;
            CommunityTarget communityTarget = (CommunityTarget) super.get((Object) inetAddress);
            if (communityTarget != null) {
                return communityTarget;
            }
            CommunityTarget communityTargetCreateTarget0 = PrintStatusJob.this.createTarget0(inetAddress);
            super.put(inetAddress, communityTargetCreateTarget0);
            return communityTargetCreateTarget0;
        }
    };

    public interface PrintStatusCallBack {
        void call(PrintStatus printStatus);
    }

    @Override
    public void cancel() {
    }

    public PrintStatusJob(PrintStatusCallBack printStatusCallBack) {
        this.printStatusCallBack = printStatusCallBack;
        try {
            DefaultUdpTransportMapping defaultUdpTransportMapping = new DefaultUdpTransportMapping();
            defaultUdpTransportMapping.listen();
            this.snmp = new Snmp(defaultUdpTransportMapping);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Job.JobState commit() {
        try {
            if (this.ipAddress == null) {
                return Job.JobState.ErrorJobParameterInvalid;
            }
            try {
                OID oid = new OID("1.3.6.1.2.1.43.18.1.1.8.1.1");
                PDU pdu = new PDU();
                pdu.add(new VariableBinding(oid));
                pdu.setType(-96);
                PrintStatus printStatus = new PrintStatus();
                ResponseEvent responseEventTrySnmpGet = trySnmpGet(pdu, InetAddress.getByName(this.ipAddress));
                if (responseEventTrySnmpGet != null && responseEventTrySnmpGet.getPeerAddress() == null) {
                    printStatus.setPrintStatus("Offline");
                } else {
                    printStatus.setPrintStatus(new String(ByteBuffer.wrap(getLocalVariable(responseEventTrySnmpGet, oid).toByteArray()).array(), "UTF-8"));
                }
                PrintStatusCallBack printStatusCallBack = this.printStatusCallBack;
                if (printStatusCallBack != null) {
                    printStatusCallBack.call(printStatus);
                }
                return Job.JobState.SuccessJob;
            } catch (RuntimeException e) {
                throw new MibControlUnsupportedOperationException("Snmp4jAdapter(): maybe wrong OID", e);
            }
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    private Variable getLocalVariable(ResponseEvent responseEvent, OID oid) throws MibControlException {
        if (responseEvent == null) {
            throw new MibControlException("no response event.").setOidString(oid.toDottedString());
        }
        PDU response = responseEvent.getResponse();
        if (response == null) {
            throw new MibControlException("no response value, maybe timeout.").setOidString(oid.toDottedString());
        }
        if (response.getErrorStatus() != 0) {
            throw new MibControlException(response.getErrorStatusText()).setOidString(oid.toDottedString());
        }
        Variable variable = response.getVariable(oid);
        if (variable == null) {
            throw new MibControlException("response null").setOidString(oid.toDottedString());
        }
        if (variable.isException()) {
            throw new MibControlException("mib exception").setOidString(oid.toDottedString());
        }
        return variable;
    }

    public CommunityTarget createTarget0(InetAddress inetAddress) {
        UdpAddress udpAddress = new UdpAddress(inetAddress, 161);
        CommunityTarget communityTarget = new CommunityTarget();
        communityTarget.setCommunity(new OctetString(SnmpContext.Default_Community));
        communityTarget.setAddress(udpAddress);
        communityTarget.setVersion(0);
        communityTarget.setRetries(this.retries);
        communityTarget.setTimeout(this.timeoutMs);
        return communityTarget;
    }

    private ResponseEvent trySnmpGet(PDU pdu, InetAddress inetAddress) throws MibControlException {
        try {
            return this.snmp.get(pdu, this.targetMap.get(inetAddress));
        } catch (IOException e) {
            throw new MibControlException("SNMP4J ERROR", e);
        }
    }

    @Override
    public boolean bind(IConnector iConnector) {
        Device device;
        if (iConnector != null) {
            this.ipAddress = iConnector.getConnectorIdentifier().toString();
            this.mDevice = iConnector.getDevice();
            IUnknown iUnknownQueryInterface = iConnector.queryInterface(ISocketConnector.f506ID);
            this.iSocketConnector = (ISocketConnector) iUnknownQueryInterface;
            if (iUnknownQueryInterface != null && (device = this.mDevice) != null && device.printer != null) {
                return true;
            }
        }
        return false;
    }

    public class PrintStatus {
        String coverStatus;
        String printStatus;

        public PrintStatus() {
        }

        @Deprecated
        public String getCoverStatus() {
            return this.coverStatus;
        }

        public void setCoverStatus(String str) {
            this.coverStatus = str;
        }

        public String getPrintStatus() {
            return this.printStatus;
        }

        public void setPrintStatus(String str) {
            setCoverStatus(str);
            this.printStatus = str;
        }
    }
}
