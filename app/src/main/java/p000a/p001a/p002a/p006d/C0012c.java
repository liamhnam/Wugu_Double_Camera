package p000a.p001a.p002a.p006d;

import android.content.Context;

public class C0012c extends AbstractC0010a {

    private String f79e;

    private int f80f;

    public C0012c(Context context) {
        super(context);
        this.f79e = "";
        this.f80f = 2;
        this.f76b = context.getSharedPreferences("pref_fgv_app_info", 0);
        this.f78d.m404i("GlobalVariable_AppInfo", "Create");
    }

    public void m54c() {
        try {
            this.f79e = this.f76b.getString("GV_M_APP_VERSION", "");
            this.f78d.m404i("m_AppVersion", this.f79e);
            this.f80f = this.f76b.getInt("GV_M_APP_MODE", 2);
            this.f78d.m404i("m_AppMode", String.valueOf(this.f80f));
            m52a(false);
            this.f78d.m404i("GlobalVariable_AppInfo", "RestoreGlobalVariable");
        } catch (Exception e) {
            this.f78d.m403e("GlobalVariable_AppInfo", "RestoreGlobalVariable Fail");
            e.printStackTrace();
        }
    }
}
