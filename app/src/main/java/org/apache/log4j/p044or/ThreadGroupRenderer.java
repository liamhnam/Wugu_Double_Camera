package org.apache.log4j.p044or;

import org.apache.log4j.Layout;

public class ThreadGroupRenderer implements ObjectRenderer {
    @Override
    public String doRender(Object obj) {
        if (obj instanceof ThreadGroup) {
            StringBuffer stringBuffer = new StringBuffer("java.lang.ThreadGroup[name=");
            ThreadGroup threadGroup = (ThreadGroup) obj;
            stringBuffer.append(threadGroup.getName());
            stringBuffer.append(", maxpri=");
            stringBuffer.append(threadGroup.getMaxPriority());
            stringBuffer.append("]");
            int iActiveCount = threadGroup.activeCount();
            Thread[] threadArr = new Thread[iActiveCount];
            threadGroup.enumerate(threadArr);
            for (int i = 0; i < iActiveCount; i++) {
                stringBuffer.append(Layout.LINE_SEP);
                stringBuffer.append("   Thread=[");
                stringBuffer.append(threadArr[i].getName());
                stringBuffer.append(",");
                stringBuffer.append(threadArr[i].getPriority());
                stringBuffer.append(",");
                stringBuffer.append(threadArr[i].isDaemon());
                stringBuffer.append("]");
            }
            return stringBuffer.toString();
        }
        try {
            return obj.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }
}
