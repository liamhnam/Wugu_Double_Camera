package p000a.p001a.p002a.p006d;

import android.content.Context;
import android.content.SharedPreferences;

public class C0017h extends AbstractC0010a {

    private int f98e;

    private int f99f;

    private int f100g;

    private int f101h;

    public C0017h(Context context) {
        super(context);
        this.f98e = 0;
        this.f99f = 0;
        this.f100g = 0;
        this.f101h = 0;
        this.f76b = context.getSharedPreferences("pref_fgv_user_info", 0);
    }

    public void m85a(int i) {
        this.f98e = i;
        m52a(true);
    }

    public int m86c() {
        return this.f98e;
    }

    public void m87d() {
        try {
            this.f98e = this.f76b.getInt("GV_M_VERIFY", 0);
            this.f99f = this.f76b.getInt("GV_M_VERIFY_PRINT_COUNT", 0);
            this.f100g = this.f76b.getInt("GV_M_SHOW_EDIT_HINT", 0);
            this.f101h = this.f76b.getInt("GV_M_SHOW_SNOW_GLOBE_HINT", 0);
            m52a(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m88e() {
        if (m53b()) {
            try {
                SharedPreferences.Editor editorEdit = this.f76b.edit();
                editorEdit.putInt("GV_M_VERIFY", this.f98e);
                editorEdit.putInt("GV_M_VERIFY_PRINT_COUNT", this.f99f);
                editorEdit.putInt("GV_M_SHOW_EDIT_HINT", this.f100g);
                editorEdit.putInt("GV_M_SHOW_SNOW_GLOBE_HINT", this.f101h);
                editorEdit.commit();
                m52a(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
