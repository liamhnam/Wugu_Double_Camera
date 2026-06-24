package p000a.p001a.p002a.p006d;

import android.content.Context;
import android.content.SharedPreferences;
import com.hiti.usb.utility.LogManager;

public abstract class AbstractC0010a implements InterfaceC0011b {

    protected SharedPreferences f75a;

    protected SharedPreferences f76b;

    private boolean f77c = false;

    protected LogManager f78d;

    public AbstractC0010a(Context context) {
        this.f78d = null;
        this.f78d = new LogManager(0);
    }

    public void m51a() {
        try {
            this.f78d.m404i("ClearGlobalVariable", "Entry");
            SharedPreferences.Editor editorEdit = this.f75a.edit();
            editorEdit.clear();
            if (editorEdit.commit()) {
                return;
            }
            this.f78d.m404i("ClearGlobalVariable", "commit fail");
        } catch (Exception e) {
            this.f78d.m404i("ClearGlobalVariable", "Error");
            e.printStackTrace();
        }
    }

    public void m52a(boolean z) {
        this.f77c = z;
    }

    public boolean m53b() {
        return this.f77c;
    }
}
