package cc.uling.usdk;

import android.os.Looper;
import android.widget.Toast;

class C0679c extends Thread {

    final C0673b f447a;

    C0679c(C0673b c0673b) {
        this.f447a = c0673b;
    }

    public void m254a() {
        Looper.prepare();
        Toast.makeText(this.f447a.f229c, "Sorry, the program encountered an exception and will restart shortly.", 1).show();
        Looper.loop();
    }
}
