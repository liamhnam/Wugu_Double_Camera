package p000a.p001a.p002a.p006d;

import android.content.Context;
import android.content.SharedPreferences;
import hiti.p035a.EnumC2094b;
import java.util.HashMap;

public class C0014e extends AbstractC0010a {

    private HashMap<String, String> f83e;

    public C0014e(Context context) {
        super(context);
        this.f83e = null;
        this.f76b = context.getSharedPreferences("pref_fgv_sd_fw_info", 0);
        this.f83e = new HashMap<>();
        this.f78d.m404i("GlobalVariable_SDFWInfo", "Create");
    }

    public String m60a(EnumC2094b enumC2094b) {
        HashMap<String, String> map = this.f83e;
        if (map == null) {
            return null;
        }
        return map.get(enumC2094b.name());
    }

    public void m61a(EnumC2094b enumC2094b, String str) {
        this.f83e.put(enumC2094b.name(), str);
        m52a(true);
    }

    public void m62c() {
        try {
            for (EnumC2094b enumC2094b : EnumC2094b.values()) {
                this.f83e.put(enumC2094b.name(), this.f76b.getString("GV_M_SD_FW_VERSION" + enumC2094b.name(), ""));
            }
            m52a(false);
            this.f78d.m404i("GlobalVariable_SDFWInfo", "RestoreGlobalVariable");
        } catch (Exception e) {
            this.f78d.m403e("GlobalVariable_SDFWInfo", "RestoreGlobalVariable Fail");
            e.printStackTrace();
        }
    }

    public void m63d() {
        if (m53b()) {
            try {
                SharedPreferences.Editor editorEdit = this.f76b.edit();
                for (EnumC2094b enumC2094b : EnumC2094b.values()) {
                    editorEdit.putString("GV_M_SD_FW_VERSION" + enumC2094b.name(), this.f83e.get(enumC2094b.name()));
                }
                if (!editorEdit.commit()) {
                    this.f78d.m403e("GlobalVariable_SDFWInfo", "SaveGlobalVariableForever Fail");
                }
                m52a(false);
                this.f78d.m404i("GlobalVariable_SDFWInfo", "SaveGlobalVariableForever");
            } catch (Exception e) {
                this.f78d.m403e("GlobalVariable_SDFWInfo", "SaveGlobalVariableForever Fail");
                e.printStackTrace();
            }
        }
    }
}
