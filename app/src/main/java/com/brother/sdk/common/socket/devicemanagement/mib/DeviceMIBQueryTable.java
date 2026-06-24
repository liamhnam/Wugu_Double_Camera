package com.brother.sdk.common.socket.devicemanagement.mib;

import java.util.HashMap;

public abstract class DeviceMIBQueryTable {
    private HashMap<String, DeviceMIBQueryObject> mTable;

    protected abstract void initializeTable(HashMap<String, DeviceMIBQueryObject> map);

    protected DeviceMIBQueryTable() {
        HashMap<String, DeviceMIBQueryObject> map = new HashMap<>();
        this.mTable = map;
        initializeTable(map);
    }

    public DeviceMIBQueryObject getMIBObject(String str) {
        if (this.mTable.containsKey(str)) {
            return this.mTable.get(str);
        }
        return null;
    }
}
