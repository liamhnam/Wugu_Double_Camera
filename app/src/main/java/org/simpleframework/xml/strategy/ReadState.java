package org.simpleframework.xml.strategy;

import org.simpleframework.xml.util.WeakCache;

class ReadState extends WeakCache<ReadGraph> {
    private final Contract contract;
    private final Loader loader = new Loader();

    public ReadState(Contract contract) {
        this.contract = contract;
    }

    public ReadGraph find(Object obj) throws Exception {
        ReadGraph readGraphFetch = fetch(obj);
        return readGraphFetch != null ? readGraphFetch : create(obj);
    }

    private ReadGraph create(Object obj) throws Exception {
        ReadGraph readGraphFetch = fetch(obj);
        if (readGraphFetch != null) {
            return readGraphFetch;
        }
        ReadGraph readGraph = new ReadGraph(this.contract, this.loader);
        cache(obj, readGraph);
        return readGraph;
    }
}
