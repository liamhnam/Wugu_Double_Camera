package com.brother.sdk.common.socket.devicemanagement.mib;

import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeviceMIBQueryObject extends ConnectorDescriptor.DeviceQueryObject {
    private static final int DEFAULT_QUERY_TIMEOUT = 200;
    private static final int DEFAULT_QUERY_TRYCOUNT = 10;
    private List<DeviceMIBQueryObject> mEntries;
    private MIBType mMibType;
    private String mOidString;
    protected DeviceMIBQueryObject mParent;
    private QueryCondition mQueryCondition;

    public enum MIBType {
        Scalar,
        TableEntry,
        Table
    }

    public static class QueryCondition {
        public int tryCount = 10;
        public int timeout = 200;
    }

    @Override
    public boolean canQuery(ConnectorDescriptor connectorDescriptor) {
        return true;
    }

    @Override
    public void initializeQuery() {
    }

    public DeviceMIBQueryObject(MIBType mIBType, String str) {
        this.mQueryCondition = new QueryCondition();
        this.mMibType = mIBType;
        if (mIBType == MIBType.Table) {
            this.mEntries = new ArrayList();
            this.mOidString = str;
            return;
        }
        this.mEntries = Arrays.asList(this);
        if (this.mMibType == MIBType.Scalar) {
            this.mOidString = str + ".0";
        } else {
            this.mOidString = str;
        }
    }

    public DeviceMIBQueryObject(String str, int i) {
        this(MIBType.Table, str);
        for (int i2 = 1; i2 < i + 1; i2++) {
            this.mEntries.add(new DeviceMIBQueryObject(str + "." + Integer.toString(i2), this) {
                @Override
                public void initializeQuery() {
                    this.mParent.initializeQuery();
                }

                @Override
                public boolean canQuery(ConnectorDescriptor connectorDescriptor) {
                    return this.mParent.canQuery(connectorDescriptor);
                }

                @Override
                public boolean endQuery(ConnectorDescriptor connectorDescriptor, String str2, AsnObject asnObject) {
                    return this.mParent.endQuery(connectorDescriptor, str2, asnObject);
                }
            });
        }
    }

    private DeviceMIBQueryObject(String str, DeviceMIBQueryObject deviceMIBQueryObject) {
        this.mQueryCondition = new QueryCondition();
        this.mParent = deviceMIBQueryObject;
        this.mMibType = MIBType.TableEntry;
        this.mOidString = str;
    }

    public QueryCondition getQueryCondition() {
        return this.mQueryCondition;
    }

    @Override
    public boolean endQuery(ConnectorDescriptor connectorDescriptor, String str, AsnObject asnObject) {
        if (connectorDescriptor == null) {
            return false;
        }
        connectorDescriptor.setConnectorValue(str, asnObject);
        return true;
    }

    public List<DeviceMIBQueryObject> getEntries() {
        if (this.mMibType == MIBType.Table) {
            return this.mEntries;
        }
        return Arrays.asList(this);
    }

    public String getEntryOID() {
        return this.mOidString;
    }

    public MIBType getType() {
        return this.mMibType;
    }
}
