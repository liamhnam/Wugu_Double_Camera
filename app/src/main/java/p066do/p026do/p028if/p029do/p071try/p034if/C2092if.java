package p066do.p026do.p028if.p029do.p071try.p034if;

import com.p020hp.jipp.encoding.IppPacket;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;
import javax.xml.XMLConstants;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.p065v1.XmlPullParser;
import org.xmlpull.p065v1.XmlPullParserException;
import org.xmlpull.p065v1.XmlPullParserFactory;

public class C2092if {

    public HashSet<String> f4031break;

    public HashSet<Pattern> f4032case;

    public HashSet<String> f4033catch;

    public HashMap<String, String> f4035else;

    public InputStream f4036for;

    public HashMap<String, String> f4037goto;

    public StringReader f2562if;

    public String f4038new;

    public HashMap<String, Class> f4039this;

    public HashSet<String> f4040try;

    public final Logger f2561do = LoggerKt.logger(this);

    public JSONObject f4034class = m1266do();

    public static class Cdo {

        public StringReader f2563do;

        public InputStream f2564if;

        public String f4044for = IppPacket.DEFAULT_CHARSET;

        public HashSet<String> f4046new = new HashSet<>();

        public HashSet<Pattern> f4048try = new HashSet<>();

        public HashMap<String, String> f4042case = new HashMap<>();

        public HashMap<String, String> f4043else = new HashMap<>();

        public HashMap<String, Class> f4045goto = new HashMap<>();

        public HashSet<String> f4047this = new HashSet<>();

        public HashSet<String> f4041break = new HashSet<>();

        public Cdo(String str) {
            this.f2563do = new StringReader(str);
        }
    }

    public C2092if(Cdo cdo) {
        this.f4032case = new HashSet<>();
        this.f4031break = new HashSet<>();
        this.f4033catch = new HashSet<>();
        this.f2562if = cdo.f2563do;
        this.f4036for = cdo.f2564if;
        this.f4038new = cdo.f4044for;
        this.f4040try = cdo.f4046new;
        this.f4032case = cdo.f4048try;
        this.f4035else = cdo.f4042case;
        this.f4037goto = cdo.f4043else;
        this.f4039this = cdo.f4045goto;
        this.f4031break = cdo.f4047this;
        this.f4033catch = cdo.f4041break;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final org.json.JSONObject m1267do(p066do.p026do.p028if.p029do.p071try.p034if.C2091do r8) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p071try.p034if.C2092if.m1267do(do.do.if.do.try.if.do):org.json.JSONObject");
    }

    public final void m1269do(String str, JSONObject jSONObject, String str2, String str3) {
        Class cls;
        try {
            cls = this.f4039this.get(str);
        } catch (JSONException unused) {
        }
        if (cls == null) {
            if (str3 == null) {
                str3 = "";
            }
            jSONObject.put(str2, str3);
            return;
        }
        if (cls == Integer.class) {
            try {
                jSONObject.put(str2, Integer.valueOf(Integer.parseInt(str3)));
                return;
            } catch (NumberFormatException unused2) {
                jSONObject.put(str2, 0);
                return;
            }
        }
        if (cls == Long.class) {
            try {
                jSONObject.put(str2, Long.valueOf(Long.parseLong(str3)));
            } catch (NumberFormatException unused3) {
                jSONObject.put(str2, 0L);
            }
        } else if (cls == Double.class) {
            try {
                jSONObject.put(str2, Double.valueOf(Double.parseDouble(str3)));
            } catch (NumberFormatException unused4) {
                jSONObject.put(str2, 0.0d);
            }
        } else if (cls == Boolean.class) {
            if (str3 != null) {
                if (str3.equalsIgnoreCase("true")) {
                    jSONObject.put(str2, true);
                    return;
                }
                str3.equalsIgnoreCase("false");
            }
            jSONObject.put(str2, false);
        }
    }

    public String toString() {
        JSONObject jSONObject = this.f4034class;
        if (jSONObject != null) {
            return jSONObject.toString();
        }
        return null;
    }

    public final JSONObject m1266do() {
        try {
            try {
                C2091do c2091do = new C2091do("", XMLConstants.XML_NS_PREFIX);
                XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
                xmlPullParserFactoryNewInstance.setNamespaceAware(true);
                XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
                StringReader stringReader = this.f2562if;
                if (stringReader != null) {
                    try {
                        xmlPullParserNewPullParser.setInput(stringReader);
                    } catch (XmlPullParserException e) {
                        e = e;
                        e.printStackTrace();
                    }
                } else {
                    try {
                        xmlPullParserNewPullParser.setInput(this.f4036for, this.f4038new);
                    } catch (XmlPullParserException e2) {
                        e = e2;
                        e.printStackTrace();
                    }
                }
                for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 0; eventType = xmlPullParserNewPullParser.next()) {
                }
                m1268do(c2091do, xmlPullParserNewPullParser);
                StringReader stringReader2 = this.f2562if;
                if (stringReader2 != null) {
                    stringReader2.close();
                }
                return m1267do(c2091do);
            } catch (XmlPullParserException e3) {
                e = e3;
                e.printStackTrace();
                return null;
            }
        } catch (IOException e4) {
            e = e4;
            e.printStackTrace();
            return null;
        }
    }

    public final void m1268do(C2091do c2091do, XmlPullParser xmlPullParser) {
        int next;
        do {
            try {
                next = xmlPullParser.next();
                if (next == 2) {
                    String name = xmlPullParser.getName();
                    String str = c2091do.f2559do + MqttTopic.TOPIC_LEVEL_SEPARATOR + name;
                    boolean zContains = this.f4033catch.contains(str);
                    C2091do c2091do2 = new C2091do(str, name);
                    if (!zContains) {
                        c2091do.f4029for.add(c2091do2);
                    }
                    int attributeCount = xmlPullParser.getAttributeCount();
                    for (int i = 0; i < attributeCount; i++) {
                        String attributeName = xmlPullParser.getAttributeName(i);
                        String attributeValue = xmlPullParser.getAttributeValue(i);
                        String str2 = c2091do.f2559do + MqttTopic.TOPIC_LEVEL_SEPARATOR + c2091do2.f2560if + MqttTopic.TOPIC_LEVEL_SEPARATOR + attributeName;
                        if (!this.f4031break.contains(str2)) {
                            String str3 = this.f4035else.get(str2);
                            if (str3 != null) {
                                attributeName = str3;
                            }
                            C2091do c2091do3 = new C2091do(str2, attributeName);
                            c2091do3.m1265do(attributeValue);
                            c2091do2.f4029for.add(c2091do3);
                        }
                    }
                    m1268do(c2091do2, xmlPullParser);
                } else if (next == 4) {
                    c2091do.m1265do(xmlPullParser.getText());
                } else if (next == 3 || next == 1) {
                    return;
                } else {
                    this.f2561do.m449i("unknown xml eventType " + next);
                }
            } catch (IOException | NullPointerException | XmlPullParserException e) {
                e.printStackTrace();
                return;
            }
        } while (next != 1);
    }
}
