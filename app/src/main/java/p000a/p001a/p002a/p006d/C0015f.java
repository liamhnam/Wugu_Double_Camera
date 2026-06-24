package p000a.p001a.p002a.p006d;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;

public class C0015f extends AbstractC0010a {

    private String f84e;

    private String f85f;

    private HashMap<String, String> f86g;

    private int f87h;

    public C0015f(Context context, String str) {
        super(context);
        this.f84e = "";
        this.f85f = "";
        this.f86g = null;
        this.f87h = -1;
        this.f75a = context.getSharedPreferences("PREF_GV_TOTAL_PRINTED_RECORD" + str, 0);
        this.f84e = str;
        this.f86g = new HashMap<>();
        this.f78d.m404i("GlobalVariable_TotalPrintedRecord", "Create");
    }

    private void m64b(String str) {
        String string = this.f75a.getString("GV_M_PRINTER_PRINTOUT" + str, "");
        if (string.isEmpty()) {
            return;
        }
        this.f86g.put(str, string);
        this.f78d.m404i("Get_" + str, String.valueOf(string));
    }

    public void m65a(String str) {
        this.f85f = str;
        m52a(true);
    }

    public void m66a(HashMap<String, String> map) {
        if (map != null) {
            for (String str : map.keySet()) {
                this.f86g.put(str, map.get(str));
            }
        }
        m52a(true);
    }

    public HashMap<String, String> m67c() {
        HashMap<String, String> map = new HashMap<>();
        for (String str : this.f86g.keySet()) {
            map.put(str, this.f86g.get(str));
        }
        return map;
    }

    public String m68d() {
        return this.f85f;
    }

    public boolean m69e() {
        this.f78d.m404i("TotalPrintedRecord_IsEmpty", String.valueOf(this.f86g.isEmpty()));
        return this.f86g.isEmpty();
    }

    public void m70f() {
        try {
            this.f85f = this.f75a.getString("GV_M_SERIAL_NUMBER", "");
            this.f86g.clear();
            m64b("2x3");
            m64b("4x6");
            m64b("5x7");
            m64b("6x8");
            this.f78d.m404i("m_ProductId", this.f84e);
            this.f78d.m404i("m_PrintOut", String.valueOf(this.f86g));
            m52a(false);
            this.f78d.m404i("TotalPrintedRecord", "RestoreGlobalVariable");
        } catch (Exception e) {
            this.f78d.m403e("GlobalVariable_TotalPrintedRecord", "RestoreGlobalVariable Fail");
            e.printStackTrace();
        }
    }

    public void m71g() {
        m72h();
    }

    public void m72h() {
        if (m53b()) {
            try {
                SharedPreferences.Editor editorEdit = this.f75a.edit();
                editorEdit.clear();
                editorEdit.putString("GV_M_SERIAL_NUMBER", this.f85f);
                editorEdit.putInt("GV_M_TOTAL_PRINTED_RECORD", this.f87h);
                for (String str : this.f86g.keySet()) {
                    editorEdit.putString("GV_M_PRINTER_PRINTOUT" + str, this.f86g.get(str));
                    this.f78d.m404i("GV_M_PRINTER_PRINTOUT" + str, this.f86g.get(str));
                }
                if (!editorEdit.commit()) {
                    this.f78d.m403e("GlobalVariable_UploadInfo", "SaveGlobalVariableForever Fail");
                }
                m52a(false);
                this.f78d.m404i("GlobalVariable_UploadInfo", "SaveGlobalVariableForever");
            } catch (Exception e) {
                this.f78d.m403e("GlobalVariable_UploadInfo", "SaveGlobalVariableForever Fail");
                e.printStackTrace();
            }
        }
    }
}
