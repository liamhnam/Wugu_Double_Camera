package org.simpleframework.xml.strategy;

import java.util.Map;
import org.simpleframework.xml.stream.NodeMap;

public class CycleStrategy implements Strategy {
    private final Contract contract;
    private final ReadState read;
    private final WriteState write;

    public CycleStrategy() {
        this("id", Name.REFER);
    }

    public CycleStrategy(String str, String str2) {
        this(str, str2, Name.LABEL);
    }

    public CycleStrategy(String str, String str2, String str3) {
        this(str, str2, str3, Name.LENGTH);
    }

    public CycleStrategy(String str, String str2, String str3, String str4) {
        Contract contract = new Contract(str, str2, str3, str4);
        this.contract = contract;
        this.write = new WriteState(contract);
        this.read = new ReadState(contract);
    }

    @Override
    public Value read(Type type, NodeMap nodeMap, Map map) throws Exception {
        ReadGraph readGraphFind = this.read.find(map);
        if (readGraphFind != null) {
            return readGraphFind.read(type, nodeMap);
        }
        return null;
    }

    @Override
    public boolean write(Type type, Object obj, NodeMap nodeMap, Map map) {
        WriteGraph writeGraphFind = this.write.find(map);
        if (writeGraphFind != null) {
            return writeGraphFind.write(type, obj, nodeMap);
        }
        return false;
    }
}
