package com.hiti.usb.utility;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.lang.reflect.Field;

public class ResourceSearcher {

    static class C15991 {
        static final int[] $SwitchMap$com$hiti$usb$utility$ResourceSearcher$RS_TYPE;

        static {
            int[] iArr = new int[RS_TYPE.values().length];
            $SwitchMap$com$hiti$usb$utility$ResourceSearcher$RS_TYPE = iArr;
            try {
                iArr[RS_TYPE.ANIM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hiti$usb$utility$ResourceSearcher$RS_TYPE[RS_TYPE.ATTR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hiti$usb$utility$ResourceSearcher$RS_TYPE[RS_TYPE.COLOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hiti$usb$utility$ResourceSearcher$RS_TYPE[RS_TYPE.DIMEN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hiti$usb$utility$ResourceSearcher$RS_TYPE[RS_TYPE.DRAWABLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hiti$usb$utility$ResourceSearcher$RS_TYPE[RS_TYPE.ID.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hiti$usb$utility$ResourceSearcher$RS_TYPE[RS_TYPE.LAYOUT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$hiti$usb$utility$ResourceSearcher$RS_TYPE[RS_TYPE.MENU.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$hiti$usb$utility$ResourceSearcher$RS_TYPE[RS_TYPE.STRING.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$hiti$usb$utility$ResourceSearcher$RS_TYPE[RS_TYPE.STYLE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$hiti$usb$utility$ResourceSearcher$RS_TYPE[RS_TYPE.STYLEABLE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public enum RS_TYPE {
        ANIM,
        ATTR,
        COLOR,
        DIMEN,
        DRAWABLE,
        ID,
        LAYOUT,
        MENU,
        STRING,
        STYLE,
        STYLEABLE
    }

    private static String ConvertResourceString(RS_TYPE rs_type) {
        switch (C15991.$SwitchMap$com$hiti$usb$utility$ResourceSearcher$RS_TYPE[rs_type.ordinal()]) {
            case 1:
                return "anim";
            case 2:
                return "attr";
            case 3:
                return "color";
            case 4:
                return "dimen";
            case 5:
                return "drawable";
            case 6:
                return "id";
            case 7:
                return TtmlNode.TAG_LAYOUT;
            case 8:
                return "menu";
            case 9:
                return TypedValues.Custom.S_STRING;
            case 10:
                return "style";
            case 11:
                return "styleable";
            default:
                return null;
        }
    }

    public static int getId(Context context, RS_TYPE rs_type, String str) {
        return rs_type == RS_TYPE.STYLEABLE ? getResourceDeclareStyleableInt(context, str) : context.getResources().getIdentifier(str, ConvertResourceString(rs_type), context.getPackageName());
    }

    public static int[] getIds(Context context, RS_TYPE rs_type, String str) {
        if (rs_type == RS_TYPE.STYLEABLE) {
            return getResourceDeclareStyleableIntArray(context, str);
        }
        return null;
    }

    private static final int getResourceDeclareStyleableInt(Context context, String str) {
        int i;
        try {
        } catch (Throwable unused) {
        }
        for (Field field : Class.forName(context.getPackageName() + ".R$styleable").getFields()) {
            if (field.getName().equals(str)) {
                return field.getInt(null);
            }
            return 0;
        }
        return 0;
    }

    private static final int[] getResourceDeclareStyleableIntArray(Context context, String str) {
        int i;
        try {
        } catch (Throwable unused) {
        }
        for (Field field : Class.forName(context.getPackageName() + ".R$styleable").getFields()) {
            if (field.getName().equals(str)) {
                return (int[]) field.get(null);
            }
            return null;
        }
        return null;
    }
}
