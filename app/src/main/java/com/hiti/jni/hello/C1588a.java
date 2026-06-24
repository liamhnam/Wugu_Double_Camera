package com.hiti.jni.hello;

import android.content.Context;

public class C1588a {
    public static String m401a(Context context, int i) {
        return new HelloJni().SayGoodBye(context, context.getResources().getAssets(), i);
    }

    public static String m402b(Context context, int i) {
        return new HelloJni().SayHello(context, context.getResources().getAssets(), i);
    }
}
