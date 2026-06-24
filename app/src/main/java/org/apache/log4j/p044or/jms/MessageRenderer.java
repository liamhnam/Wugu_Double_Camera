package org.apache.log4j.p044or.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.p044or.ObjectRenderer;

public class MessageRenderer implements ObjectRenderer {
    @Override
    public String doRender(Object obj) {
        if (obj instanceof Message) {
            StringBuffer stringBuffer = new StringBuffer();
            Message message = (Message) obj;
            try {
                stringBuffer.append("DeliveryMode=");
                int jMSDeliveryMode = message.getJMSDeliveryMode();
                if (jMSDeliveryMode == 1) {
                    stringBuffer.append("NON_PERSISTENT");
                } else if (jMSDeliveryMode == 2) {
                    stringBuffer.append("PERSISTENT");
                } else {
                    stringBuffer.append("UNKNOWN");
                }
                stringBuffer.append(", CorrelationID=");
                stringBuffer.append(message.getJMSCorrelationID());
                stringBuffer.append(", Destination=");
                stringBuffer.append(message.getJMSDestination());
                stringBuffer.append(", Expiration=");
                stringBuffer.append(message.getJMSExpiration());
                stringBuffer.append(", MessageID=");
                stringBuffer.append(message.getJMSMessageID());
                stringBuffer.append(", Priority=");
                stringBuffer.append(message.getJMSPriority());
                stringBuffer.append(", Redelivered=");
                stringBuffer.append(message.getJMSRedelivered());
                stringBuffer.append(", ReplyTo=");
                stringBuffer.append(message.getJMSReplyTo());
                stringBuffer.append(", Timestamp=");
                stringBuffer.append(message.getJMSTimestamp());
                stringBuffer.append(", Type=");
                stringBuffer.append(message.getJMSType());
            } catch (JMSException e) {
                LogLog.error("Could not parse Message.", e);
            }
            return stringBuffer.toString();
        }
        return obj.toString();
    }
}
