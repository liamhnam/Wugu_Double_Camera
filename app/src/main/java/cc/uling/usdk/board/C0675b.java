package cc.uling.usdk.board;

import cc.uling.usdk.constants.BoardConst;

public class C0675b extends Exception {

    public static final C0675b f241a = new C0675b(201, "串口打开失败");

    public static final C0675b f242b = new C0675b(202, "串口关闭失败");

    public static final C0675b f243c = new C0675b(203, "数据发送错误");

    public static final C0675b f244d = new C0675b(204, "数据返回超时");

    public static final C0675b f245e = new C0675b(205, "数据起始标志错误");

    public static final C0675b f246f = new C0675b(206, "返回数据长度过长");

    public static final C0675b f247g = new C0675b(207, "数据帧校验错误");

    public static final C0675b f248h = new C0675b(208, "数据帧命令错误");

    public static final C0675b f249i = new C0675b(213, "写读异常");

    public static final C0675b f250j = new C0675b(BoardConst.CODE_ERR_ADDR, "数据地址错误");

    public static final C0675b f251k = new C0675b(210, "任务进行中，发送失败");

    public static final C0675b f252l = new C0675b(BoardConst.CODE_NOT_OPENED, "串口未打开或已关闭");

    private int f253m;

    private String f254n;

    public C0675b(int i, String str) {
        super(str);
        this.f253m = i;
        this.f254n = str;
    }

    public C0675b(String str, Throwable th) {
        super(str, th);
        this.f254n = str;
    }

    public int m248a() {
        return this.f253m;
    }

    public void m249a(int i) {
        this.f253m = i;
    }

    public void m250a(String str) {
        this.f254n = str;
    }

    public String m251b() {
        return this.f254n;
    }
}
