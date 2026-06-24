package org.simpleframework.xml.stream;

import java.util.Iterator;
import java.util.LinkedHashMap;

class PrefixResolver extends LinkedHashMap<String, String> implements NamespaceMap {
    private final OutputNode source;

    public PrefixResolver(OutputNode outputNode) {
        this.source = outputNode;
    }

    @Override
    public String getPrefix() {
        return this.source.getPrefix();
    }

    @Override
    public String setReference(String str) {
        return setReference(str, "");
    }

    @Override
    public String setReference(String str, String str2) {
        if (resolvePrefix(str) != null) {
            return null;
        }
        return (String) put(str, str2);
    }

    @Override
    public String getPrefix(String str) {
        String str2;
        return (size() <= 0 || (str2 = get(str)) == null) ? resolvePrefix(str) : str2;
    }

    @Override
    public String getReference(String str) {
        if (containsValue(str)) {
            for (String str2 : this) {
                String str3 = (String) get(str2);
                if (str3 != null && str3.equals(str)) {
                    return str2;
                }
            }
        }
        return resolveReference(str);
    }

    private String resolveReference(String str) {
        NamespaceMap namespaces = this.source.getNamespaces();
        if (namespaces != null) {
            return namespaces.getReference(str);
        }
        return null;
    }

    private String resolvePrefix(String str) {
        NamespaceMap namespaces = this.source.getNamespaces();
        if (namespaces == null) {
            return null;
        }
        String prefix = namespaces.getPrefix(str);
        if (containsValue(prefix)) {
            return null;
        }
        return prefix;
    }

    @Override
    public Iterator<String> iterator() {
        return keySet().iterator();
    }
}
