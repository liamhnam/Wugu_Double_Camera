package p066do.p026do.p027do;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ccase {

    public static final SimpleDateFormat f2463do = new SimpleDateFormat("yyyy-MM-dd");

    public static long m1186do() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            SimpleDateFormat simpleDateFormat = f2463do;
            return simpleDateFormat.parse(simpleDateFormat.format(new Date(jCurrentTimeMillis))).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }
}
