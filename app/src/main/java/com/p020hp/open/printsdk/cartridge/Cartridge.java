package com.p020hp.open.printsdk.cartridge;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u000b\f\r\u000e\u000f\u0010B\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006\u0082\u0001\u0006\u0011\u0012\u0013\u0014\u0015\u0016¨\u0006\u0017"}, m1293d2 = {"Lcom/hp/open/printsdk/cartridge/Cartridge;", "", "()V", "level", "", "getLevel", "()I", "maxCapacity", "getMaxCapacity", "toString", "", "Black", "Cyan", "ImagingDrum", "Magenta", "TriColor", "Yellow", "Lcom/hp/open/printsdk/cartridge/Cartridge$Black;", "Lcom/hp/open/printsdk/cartridge/Cartridge$TriColor;", "Lcom/hp/open/printsdk/cartridge/Cartridge$Cyan;", "Lcom/hp/open/printsdk/cartridge/Cartridge$Magenta;", "Lcom/hp/open/printsdk/cartridge/Cartridge$Yellow;", "Lcom/hp/open/printsdk/cartridge/Cartridge$ImagingDrum;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class Cartridge {

    @Metadata(m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, m1293d2 = {"Lcom/hp/open/printsdk/cartridge/Cartridge$Black;", "Lcom/hp/open/printsdk/cartridge/Cartridge;", "level", "", "maxCapacity", "(II)V", "getLevel", "()I", "getMaxCapacity", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Black extends Cartridge {

        public final int f815a;

        public final int f816b;

        public Black(int i, int i2) {
            super(null);
            this.f815a = i;
            this.f816b = i2;
        }

        @Override
        public int getF825a() {
            return this.f815a;
        }

        @Override
        public int getF826b() {
            return this.f816b;
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, m1293d2 = {"Lcom/hp/open/printsdk/cartridge/Cartridge$Cyan;", "Lcom/hp/open/printsdk/cartridge/Cartridge;", "level", "", "maxCapacity", "(II)V", "getLevel", "()I", "getMaxCapacity", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Cyan extends Cartridge {

        public final int f817a;

        public final int f818b;

        public Cyan(int i, int i2) {
            super(null);
            this.f817a = i;
            this.f818b = i2;
        }

        @Override
        public int getF825a() {
            return this.f817a;
        }

        @Override
        public int getF826b() {
            return this.f818b;
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, m1293d2 = {"Lcom/hp/open/printsdk/cartridge/Cartridge$ImagingDrum;", "Lcom/hp/open/printsdk/cartridge/Cartridge;", "level", "", "maxCapacity", "(II)V", "getLevel", "()I", "getMaxCapacity", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class ImagingDrum extends Cartridge {

        public final int f819a;

        public final int f820b;

        public ImagingDrum(int i, int i2) {
            super(null);
            this.f819a = i;
            this.f820b = i2;
        }

        @Override
        public int getF825a() {
            return this.f819a;
        }

        @Override
        public int getF826b() {
            return this.f820b;
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, m1293d2 = {"Lcom/hp/open/printsdk/cartridge/Cartridge$Magenta;", "Lcom/hp/open/printsdk/cartridge/Cartridge;", "level", "", "maxCapacity", "(II)V", "getLevel", "()I", "getMaxCapacity", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Magenta extends Cartridge {

        public final int f821a;

        public final int f822b;

        public Magenta(int i, int i2) {
            super(null);
            this.f821a = i;
            this.f822b = i2;
        }

        @Override
        public int getF825a() {
            return this.f821a;
        }

        @Override
        public int getF826b() {
            return this.f822b;
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, m1293d2 = {"Lcom/hp/open/printsdk/cartridge/Cartridge$TriColor;", "Lcom/hp/open/printsdk/cartridge/Cartridge;", "level", "", "maxCapacity", "(II)V", "getLevel", "()I", "getMaxCapacity", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class TriColor extends Cartridge {

        public final int f823a;

        public final int f824b;

        public TriColor(int i, int i2) {
            super(null);
            this.f823a = i;
            this.f824b = i2;
        }

        @Override
        public int getF825a() {
            return this.f823a;
        }

        @Override
        public int getF826b() {
            return this.f824b;
        }
    }

    @Metadata(m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, m1293d2 = {"Lcom/hp/open/printsdk/cartridge/Cartridge$Yellow;", "Lcom/hp/open/printsdk/cartridge/Cartridge;", "level", "", "maxCapacity", "(II)V", "getLevel", "()I", "getMaxCapacity", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Yellow extends Cartridge {

        public final int f825a;

        public final int f826b;

        public Yellow(int i, int i2) {
            super(null);
            this.f825a = i;
            this.f826b = i2;
        }

        @Override
        public int getF825a() {
            return this.f825a;
        }

        @Override
        public int getF826b() {
            return this.f826b;
        }
    }

    public Cartridge() {
    }

    public Cartridge(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract int getF825a();

    public abstract int getF826b();

    public String toString() {
        return getClass().getSimpleName() + "(level: " + getF825a() + ", maxCapacity: " + getF826b() + ')';
    }
}
