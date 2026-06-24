package p000a.p001a.p002a.p006d;

import android.content.Context;
import android.content.SharedPreferences;

public class C0013d extends AbstractC0010a {

    private static final String f81f = "d";

    private long f82e;

    public C0013d(Context context) {
        super(context);
        this.f76b = context.getSharedPreferences("pref_fgv_network_sdk", 0);
    }

    public void m55a(long j) {
        this.f82e = j;
        m52a(true);
    }

    public void m56c() {
        try {
            this.f82e = this.f76b.getLong("GV_M_LATEST_EXE_TIME", 0L);
            m52a(false);
            this.f78d.m404i(f81f, "RestoreGlobalVariable");
        } catch (Exception e) {
            this.f78d.m403e(f81f, "RestoreGlobalVariable Fail");
            e.printStackTrace();
        }
    }

    public void m57d() {
        m58e();
    }

    public void m58e() {
        if (m53b()) {
            try {
                SharedPreferences.Editor editorEdit = this.f76b.edit();
                editorEdit.putLong("GV_M_LATEST_EXE_TIME", this.f82e);
                if (!editorEdit.commit()) {
                    this.f78d.m403e(f81f, "SaveGlobalVariableForever Fail");
                }
                m52a(false);
                this.f78d.m404i(f81f, "SaveGlobalVariableForever");
            } catch (Exception e) {
                this.f78d.m403e(f81f, "SaveGlobalVariableForever Fail");
                e.printStackTrace();
            }
        }
    }

    public long m59f() {
        return this.f82e;
    }
}
