package com.p020hp.printsdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;

public interface InterfaceC1783x extends IInterface {

    public static class a implements InterfaceC1783x {
        @Override
        public ParcelFileDescriptor mo461a(C1773v c1773v, C1694f0 c1694f0) {
            return null;
        }

        @Override
        public C1700g0 mo462a(int i) {
            return null;
        }

        @Override
        public IBinder asBinder() {
            return null;
        }

        @Override
        public void close() {
        }

        @Override
        public int getPageCount() {
            return 0;
        }
    }

    public static abstract class b extends Binder implements InterfaceC1783x {

        public static class a implements InterfaceC1783x {

            public IBinder f1953a;

            public a(IBinder iBinder) {
                this.f1953a = iBinder;
            }

            @Override
            public ParcelFileDescriptor mo461a(C1773v c1773v, C1694f0 c1694f0) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.hp.bgp.ext.render.IDocument");
                    if (c1773v != null) {
                        parcelObtain.writeInt(1);
                        c1773v.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (c1694f0 != null) {
                        parcelObtain.writeInt(1);
                        c1694f0.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.f1953a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public C1700g0 mo462a(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.hp.bgp.ext.render.IDocument");
                    parcelObtain.writeInt(i);
                    this.f1953a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? C1700g0.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public IBinder asBinder() {
                return this.f1953a;
            }

            @Override
            public void close() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.hp.bgp.ext.render.IDocument");
                    this.f1953a.transact(4, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override
            public int getPageCount() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.hp.bgp.ext.render.IDocument");
                    this.f1953a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public b() {
            attachInterface(this, "com.hp.bgp.ext.render.IDocument");
        }

        public static InterfaceC1783x m681a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.hp.bgp.ext.render.IDocument");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC1783x)) ? new a(iBinder) : (InterfaceC1783x) iInterfaceQueryLocalInterface;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.hp.bgp.ext.render.IDocument");
                return true;
            }
            if (i == 1) {
                parcel.enforceInterface("com.hp.bgp.ext.render.IDocument");
                int pageCount = getPageCount();
                parcel2.writeNoException();
                parcel2.writeInt(pageCount);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.hp.bgp.ext.render.IDocument");
                C1700g0 c1700g0Mo462a = mo462a(parcel.readInt());
                parcel2.writeNoException();
                if (c1700g0Mo462a != null) {
                    parcel2.writeInt(1);
                    c1700g0Mo462a.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i != 3) {
                if (i != 4) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel.enforceInterface("com.hp.bgp.ext.render.IDocument");
                close();
                return true;
            }
            parcel.enforceInterface("com.hp.bgp.ext.render.IDocument");
            ParcelFileDescriptor parcelFileDescriptorMo461a = mo461a(parcel.readInt() != 0 ? C1773v.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? C1694f0.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            if (parcelFileDescriptorMo461a != null) {
                parcel2.writeInt(1);
                parcelFileDescriptorMo461a.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }
    }

    ParcelFileDescriptor mo461a(C1773v c1773v, C1694f0 c1694f0);

    C1700g0 mo462a(int i);

    void close();

    int getPageCount();
}
