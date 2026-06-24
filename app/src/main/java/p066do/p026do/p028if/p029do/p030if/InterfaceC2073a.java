package p066do.p026do.p028if.p029do.p030if;

import javax.net.SocketFactory;

public interface InterfaceC2073a {

    public static final Cdo f2509do = Cdo.f2510do;

    public static final class Cdo {

        public static final Cdo f2510do = new Cdo();

        public static final class C3379do implements InterfaceC2073a {
            @Override
            public SocketFactory mo1213do() {
                return SocketFactory.getDefault();
            }
        }

        public final InterfaceC2073a m1214do() {
            return new C3379do();
        }
    }

    SocketFactory mo1213do();
}
