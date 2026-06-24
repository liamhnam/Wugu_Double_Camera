package org.apache.log4j.p044or;

class DefaultRenderer implements ObjectRenderer {
    DefaultRenderer() {
    }

    @Override
    public String doRender(Object obj) {
        try {
            return obj.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }
}
