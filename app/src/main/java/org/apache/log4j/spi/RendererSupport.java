package org.apache.log4j.spi;

import org.apache.log4j.p044or.ObjectRenderer;
import org.apache.log4j.p044or.RendererMap;

public interface RendererSupport {
    RendererMap getRendererMap();

    void setRenderer(Class cls, ObjectRenderer objectRenderer);
}
