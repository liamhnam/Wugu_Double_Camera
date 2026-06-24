package org.simpleframework.xml.stream;

class IdentityStyle implements Style {
    @Override
    public String getAttribute(String str) {
        return str;
    }

    @Override
    public String getElement(String str) {
        return str;
    }

    IdentityStyle() {
    }
}
