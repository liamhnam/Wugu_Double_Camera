package com.printer.sdk;

import java.util.LinkedList;

public class C1844i extends Thread implements PrintType {

    private boolean f2207a = true;

    private LinkedList<Integer> f2208b = new LinkedList<>();

    private LinkedList<String> f2209c = new LinkedList<>();

    private a f2210d = null;

    public interface a {
        void mo770a(int i, String str);

        void mo771b(int i, String str);
    }

    public void m833a() {
        this.f2207a = false;
    }

    public void m834a(a aVar) {
        this.f2210d = aVar;
    }

    public void m835a(Integer num, String str) {
        this.f2208b.addFirst(num);
        this.f2209c.addFirst(str);
    }

    @Override
    public void run() {
        while (this.f2207a) {
            if (this.f2208b.isEmpty()) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException unused) {
                }
            } else {
                Integer numRemoveLast = this.f2208b.removeLast();
                String strRemoveLast = this.f2209c.removeLast();
                a aVar = this.f2210d;
                if (aVar != null) {
                    aVar.mo770a(numRemoveLast.intValue(), strRemoveLast);
                }
                if (numRemoveLast != null) {
                    for (int i = 0; i < 1000; i++) {
                        try {
                            Thread.sleep(1L);
                        } catch (InterruptedException unused2) {
                        }
                        if (!this.f2207a) {
                            break;
                        }
                    }
                }
                a aVar2 = this.f2210d;
                if (aVar2 != null) {
                    aVar2.mo771b(numRemoveLast.intValue(), strRemoveLast);
                }
            }
        }
    }
}
