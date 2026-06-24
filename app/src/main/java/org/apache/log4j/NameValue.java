package org.apache.log4j;

class NameValue {
    String key;
    String value;

    public NameValue(String str, String str2) {
        this.key = str;
        this.value = str2;
    }

    public String toString() {
        return new StringBuffer().append(this.key).append("=").append(this.value).toString();
    }
}
