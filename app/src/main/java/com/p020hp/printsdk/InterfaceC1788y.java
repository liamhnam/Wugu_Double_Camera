package com.p020hp.printsdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.p020hp.printsdk.InterfaceC1783x;

public interface InterfaceC1788y extends IInterface {

    public static abstract class a extends Binder implements InterfaceC1788y {

        public static class C3375a implements InterfaceC1788y {

            public IBinder f2011a;

            public C3375a(IBinder iBinder) {
                this.f2011a = iBinder;
            }

            @Override
            public InterfaceC1783x mo405a(ParcelFileDescriptor parcelFileDescriptor) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.hp.bgp.ext.render.IPdfRender");
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.f2011a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return InterfaceC1783x.b.m681a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public IBinder asBinder() {
                return this.f2011a;
            }
        }

        public a() {
            attachInterface(this, "com.hp.bgp.ext.render.IPdfRender");
        }

        public static InterfaceC1788y m686a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.hp.bgp.ext.render.IPdfRender");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC1788y)) ? new C3375a(iBinder) : (InterfaceC1788y) iInterfaceQueryLocalInterface;
        }

        @Override
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.hp.bgp.ext.render.IPdfRender");
                return true;
            }
            parcel.enforceInterface("com.hp.bgp.ext.render.IPdfRender");
            InterfaceC1783x interfaceC1783xMo405a = mo405a(parcel.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            parcel2.writeStrongBinder(interfaceC1783xMo405a != null ? interfaceC1783xMo405a.asBinder() : null);
            return true;
        }
    }

    InterfaceC1783x mo405a(ParcelFileDescriptor parcelFileDescriptor);
}
