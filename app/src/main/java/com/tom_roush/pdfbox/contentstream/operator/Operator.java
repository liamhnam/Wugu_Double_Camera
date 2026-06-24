package com.tom_roush.pdfbox.contentstream.operator;

import com.tom_roush.pdfbox.cos.COSDictionary;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class Operator {
    private static final ConcurrentMap<String, Operator> operators = new ConcurrentHashMap();
    private byte[] imageData;
    private COSDictionary imageParameters;
    private final String theOperator;

    private Operator(String str) {
        this.theOperator = str;
        if (str.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
            throw new IllegalArgumentException("Operators are not allowed to start with / '" + str + "'");
        }
    }

    public static Operator getOperator(String str) {
        if (str.equals("ID") || str.equals("BI")) {
            return new Operator(str);
        }
        ConcurrentMap<String, Operator> concurrentMap = operators;
        Operator operator = concurrentMap.get(str);
        if (operator != null) {
            return operator;
        }
        Operator operatorPutIfAbsent = concurrentMap.putIfAbsent(str, new Operator(str));
        return operatorPutIfAbsent == null ? concurrentMap.get(str) : operatorPutIfAbsent;
    }

    public String getName() {
        return this.theOperator;
    }

    public String toString() {
        return "PDFOperator{" + this.theOperator + "}";
    }

    public byte[] getImageData() {
        return this.imageData;
    }

    public void setImageData(byte[] bArr) {
        this.imageData = bArr;
    }

    public COSDictionary getImageParameters() {
        return this.imageParameters;
    }

    public void setImageParameters(COSDictionary cOSDictionary) {
        this.imageParameters = cOSDictionary;
    }
}
