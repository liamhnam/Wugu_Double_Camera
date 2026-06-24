package p000a.p001a.p002a.p006d;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;

public class C0016g extends AbstractC0010a {

    private int f88e;

    private String f89f;

    private String f90g;

    private String f91h;

    private int f92i;

    private int f93j;

    private int f94k;

    private int f95l;

    private ArrayList<String> f96m;

    private ArrayList<String> f97n;

    public C0016g(Context context) {
        super(context);
        this.f88e = 0;
        this.f89f = "";
        this.f90g = "";
        this.f91h = "";
        this.f92i = -1;
        this.f93j = 0;
        this.f94k = 0;
        this.f95l = 0;
        this.f96m = null;
        this.f97n = null;
        this.f76b = context.getSharedPreferences("pref_fgv_upload_info", 0);
        this.f96m = new ArrayList<>();
        this.f97n = new ArrayList<>();
        this.f78d.m404i("GlobalVariable_UploadInfo", "Create");
    }

    private void m73b(String str) {
        this.f96m.add(str);
        m52a(true);
    }

    private void m74c(String str) {
        this.f97n.add(str);
        m52a(true);
    }

    public void m75a(int i) {
        this.f94k = i;
        m52a(true);
    }

    public void m76a(String str) {
        this.f91h = str;
        m52a(true);
    }

    public void m77a(String str, String str2) {
        m73b(str);
        m74c(str2);
        m52a(true);
    }

    public int m78c() {
        return this.f92i;
    }

    public int m79d() {
        return this.f94k;
    }

    public int m80e() {
        return this.f96m.size();
    }

    public int m81f() {
        return this.f93j;
    }

    public String m82g() {
        return this.f91h;
    }

    public void m83h() {
        try {
            this.f90g = this.f76b.getString("GV_M_AESCOUNT", "");
            this.f89f = this.f76b.getString("GV_M_SERIAL_NUMBER", "");
            this.f91h = this.f76b.getString("GV_M_UPLOADER", "");
            this.f92i = this.f76b.getInt("GV_M_UPLOAD", -1);
            this.f93j = this.f76b.getInt("GV_M_UPLOAD_METHOD", 0);
            this.f94k = this.f76b.getInt("GV_M_UPLOAD_E03", 0);
            this.f88e = this.f76b.getInt("GV_M_VERSION", 0);
            this.f95l = this.f76b.getInt("GV_M_COUNTRY_CODE", 0);
            this.f96m.clear();
            this.f97n.clear();
            SharedPreferences sharedPreferences = this.f76b;
            String str = "GV_M_UPLOAD_PATH0";
            int i = 0;
            while (true) {
                String string = sharedPreferences.getString(str, "");
                if (string.compareTo("") == 0) {
                    break;
                }
                this.f96m.add(string);
                i++;
                sharedPreferences = this.f76b;
                str = "GV_M_UPLOAD_PATH" + i;
            }
            SharedPreferences sharedPreferences2 = this.f76b;
            String str2 = "GV_M_UPLOAD_TIME0";
            int i2 = 0;
            while (true) {
                String string2 = sharedPreferences2.getString(str2, "");
                if (string2.compareTo("") == 0) {
                    m52a(false);
                    this.f78d.m404i("GlobalVariable_UploadInfo", "RestoreGlobalVariable");
                    return;
                } else {
                    this.f97n.add(string2);
                    i2++;
                    sharedPreferences2 = this.f76b;
                    str2 = "GV_M_UPLOAD_TIME" + i2;
                }
            }
        } catch (Exception e) {
            this.f78d.m403e("GlobalVariable_UploadInfo", "RestoreGlobalVariable Fail");
            e.printStackTrace();
        }
    }

    public void m84i() {
        if (m53b()) {
            try {
                SharedPreferences.Editor editorEdit = this.f76b.edit();
                editorEdit.clear();
                editorEdit.putString("GV_M_AESCOUNT", this.f90g);
                editorEdit.putString("GV_M_SERIAL_NUMBER", this.f89f);
                editorEdit.putString("GV_M_UPLOADER", this.f91h);
                editorEdit.putInt("GV_M_UPLOAD", this.f92i);
                editorEdit.putInt("GV_M_UPLOAD_METHOD", this.f93j);
                editorEdit.putInt("GV_M_UPLOAD_E03", this.f94k);
                editorEdit.putInt("GV_M_VERSION", this.f88e);
                editorEdit.putInt("GV_M_COUNTRY_CODE", this.f95l);
                for (int i = 0; i < this.f96m.size(); i++) {
                    editorEdit.putString("GV_M_UPLOAD_PATH" + i, this.f96m.get(i));
                }
                for (int i2 = 0; i2 < this.f97n.size(); i2++) {
                    editorEdit.putString("GV_M_UPLOAD_TIME" + i2, this.f97n.get(i2));
                }
                this.f78d.m404i("save- m_Upload", String.valueOf(this.f92i));
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
