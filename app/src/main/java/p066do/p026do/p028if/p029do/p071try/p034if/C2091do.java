package p066do.p026do.p028if.p029do.p071try.p034if;

import java.util.ArrayList;

public class C2091do {

    public String f2559do;

    public ArrayList<C2091do> f4029for = new ArrayList<>();

    public String f2560if;

    public String f4030new;

    public C2091do(String str, String str2) {
        this.f2559do = str;
        this.f2560if = str2;
    }

    public void m1265do(String str) {
        boolean z = false;
        if (str != null) {
            int i = 0;
            while (true) {
                if (i < str.length()) {
                    char cCharAt = str.charAt(i);
                    if (cCharAt != ' ' && cCharAt != '\n') {
                        z = true;
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
        }
        if (z) {
            this.f4030new = str;
        }
    }

    public String toString() {
        return "Tag: " + this.f2560if + ", " + this.f4029for.size() + " children, Content: " + this.f4030new;
    }
}
