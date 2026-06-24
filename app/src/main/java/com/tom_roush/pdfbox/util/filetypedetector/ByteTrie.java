package com.tom_roush.pdfbox.util.filetypedetector;

import java.util.HashMap;
import java.util.Map;

class ByteTrie<T> {
    private int maxDepth;
    private final ByteTrieNode<T> root = new ByteTrieNode<>();

    ByteTrie() {
    }

    static class ByteTrieNode<T> {
        private final Map<Byte, ByteTrieNode<T>> children = new HashMap();
        private T value = null;

        ByteTrieNode() {
        }

        public void setValue(T t) {
            if (this.value != null) {
                throw new IllegalStateException("Value already set for this trie node");
            }
            this.value = t;
        }

        public T getValue() {
            return this.value;
        }
    }

    public T find(byte[] bArr) {
        ByteTrieNode<T> byteTrieNode = this.root;
        T value = byteTrieNode.getValue();
        for (byte b : bArr) {
            byteTrieNode = (ByteTrieNode) ((ByteTrieNode) byteTrieNode).children.get(Byte.valueOf(b));
            if (byteTrieNode == null) {
                break;
            }
            if (byteTrieNode.getValue() != null) {
                value = byteTrieNode.getValue();
            }
        }
        return value;
    }

    public void addPath(T t, byte[]... bArr) {
        ByteTrieNode<T> byteTrieNode = this.root;
        int i = 0;
        for (byte[] bArr2 : bArr) {
            for (byte b : bArr2) {
                ByteTrieNode<T> byteTrieNode2 = (ByteTrieNode) ((ByteTrieNode) byteTrieNode).children.get(Byte.valueOf(b));
                if (byteTrieNode2 == null) {
                    byteTrieNode2 = new ByteTrieNode<>();
                    ((ByteTrieNode) byteTrieNode).children.put(Byte.valueOf(b), byteTrieNode2);
                }
                byteTrieNode = byteTrieNode2;
                i++;
            }
        }
        byteTrieNode.setValue(t);
        this.maxDepth = Math.max(this.maxDepth, i);
    }

    public void setDefaultValue(T t) {
        this.root.setValue(t);
    }

    public int getMaxDepth() {
        return this.maxDepth;
    }
}
