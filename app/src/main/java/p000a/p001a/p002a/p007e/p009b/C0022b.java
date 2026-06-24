package p000a.p001a.p002a.p007e.p009b;

import android.content.Context;
import com.hiti.jni.hello.C1588a;
import com.hiti.usb.utility.EncryptAndDecryptAES;
import com.hiti.usb.utility.MobileInfo;
import com.hiti.usb.utility.web.WebPostRequest;
import com.tom_roush.fontbox.afm.AFMParser;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class C0022b {

    private Context f110a;

    public C0022b(Context context) {
        this.f110a = context;
    }

    private String m93a(String str, String str2) {
        return "<PrintOut" + str + ">" + str2 + "</PrintOut" + str + ">";
    }

    private String m94a(Element element, String str) {
        NodeList elementsByTagName = element.getElementsByTagName(str);
        if (elementsByTagName != null) {
            try {
                Element element2 = (Element) elementsByTagName.item(0);
                return (element2 == null || element2.getFirstChild() == null || element2.getFirstChild().getNodeValue() == null) ? "" : element2.getFirstChild().getNodeValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public C0021a m95a(String str) {
        DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
        C0021a c0021a = new C0021a();
        try {
            NodeList elementsByTagName = documentBuilderFactoryNewInstance.newDocumentBuilder().parse(new InputSource(new StringReader(str))).getDocumentElement().getElementsByTagName("Result");
            for (int i = 0; i < elementsByTagName.getLength(); i++) {
                Element element = (Element) elementsByTagName.item(i);
                c0021a.f104a = m94a(element, "Response");
                m94a(element, AFMParser.VERSION);
                m94a(element, "UpdateID");
                m94a(element, "ElementID");
                m94a(element, "TimeStamp");
                m94a(element, "Signature");
                c0021a.f105b = m94a(element, "NewsetVersion");
                m94a(element, "Info");
                c0021a.f106c = m94a(element, "FTP");
                c0021a.f107d = m94a(element, "Path");
                c0021a.f108e = m94a(element, "UserName");
                c0021a.f109f = m94a(element, "Password");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
        } catch (SAXException e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return c0021a;
    }

    public String m96a(String str, String str2, String str3, String str4) {
        String str5 = MobileInfo.GetTimeStamp() + MobileInfo.MakeRandString(11);
        return new WebPostRequest().PostSOAP("http://tempuri.org/", "https://update.hiti.com/UpdateService/Service.asmx", "GetUpdateInfo", "http://tempuri.org/GetUpdateInfo", "<xml>" + ("<?xml version=\"1.0\" encoding=\"UTF-8\"?><HiTi><Command><Version>" + str + "</Version><UpdateID>" + str2 + "</UpdateID><ElementID>" + str3 + "</ElementID><TimeStamp>" + str5 + "</TimeStamp><Signature>" + EncryptAndDecryptAES.MakeMD5(str5 + C1588a.m402b(this.f110a, UiPosIndexEnum.PE_WATER_MARK)) + "</Signature><ReleaseFlag>" + str4 + "</ReleaseFlag></Command></HiTi>").replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;") + "</xml>");
    }

    public String m97a(String str, String str2, HashMap<String, String> map, String str3, String str4) {
        String str5 = MobileInfo.GetTimeStamp() + MobileInfo.MakeRandString(11);
        String strMakeMD5 = EncryptAndDecryptAES.MakeMD5(str5 + C1588a.m402b(this.f110a, UiPosIndexEnum.PE_WATER_MARK));
        String str6 = "";
        for (String str7 : map.keySet()) {
            str6 = str6 + m93a(str7, map.get(str7));
        }
        return new WebPostRequest().PostSOAP("http://tempuri.org/", "https://update.hiti.com/UpdateService/PrinbizService.asmx", "SendPrinterInfo", "http://tempuri.org/SendPrinterInfo", "<xml>" + ("<?xml version=\"1.0\" encoding=\"UTF-8\"?><HiTi><Command><Version>1</Version><Platform>2</Platform><ElementID>" + str + "</ElementID><SerialsNumber>" + str2 + "</SerialsNumber>" + str6 + "<Latitude>" + str3 + "</Latitude><Longitude>" + str4 + "</Longitude><TimeStamp>" + str5 + "</TimeStamp><Signature>" + strMakeMD5 + "</Signature></Command></HiTi>").replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;") + "</xml>");
    }

    public String m98b(String str) {
        int iIndexOf;
        int iIndexOf2 = str.indexOf("<?xml version='1.0' encoding='UTF-8'?><HiTi><Result>");
        return (iIndexOf2 == -1 || (iIndexOf = str.indexOf("</Result></HiTi>")) == -1) ? "" : str.substring(iIndexOf2, iIndexOf + new String("</Result></HiTi>").length());
    }
}
