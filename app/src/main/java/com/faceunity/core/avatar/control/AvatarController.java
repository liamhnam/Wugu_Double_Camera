package com.faceunity.core.avatar.control;

import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.avatar.listener.OnSceneListener;
import com.faceunity.core.entity.FUAnimationData;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUColorRGBData;
import com.faceunity.core.entity.FUCoordinate3DData;
import com.faceunity.core.entity.FUGroupAnimationData;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.utils.FULogger;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b0\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\b:\n\u0002\u0010\u0015\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J@\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J&\u0010\u0013\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u001a\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u001c\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u001d\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u001e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001d\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b\"JQ\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020%2\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'H\u0002¢\u0006\u0002\u0010(J\u001d\u0010)\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b*J\u001f\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020-2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0000¢\u0006\u0002\b.J\u001f\u0010/\u001a\u00020\u00042\u0006\u0010,\u001a\u00020-2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0000¢\u0006\u0002\b0JQ\u00101\u001a\u00020\u00042\u0006\u0010$\u001a\u00020%2\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'H\u0002¢\u0006\u0002\u0010(J\u001e\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000405H\u0002J=\u00106\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\b\b\u0002\u00107\u001a\u00020'2!\u00104\u001a\u001d\u0012\u0013\u0012\u00110%¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\u0004\u0012\u00020\u000408H\u0002J3\u0010<\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152!\u00104\u001a\u001d\u0012\u0013\u0012\u00110%¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\u0004\u0012\u00020\u000408H\u0002J8\u0010=\u001a\u00020\u00042\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002J\u001d\u0010>\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b?J\u0018\u0010@\u001a\u00020\u00042\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u001d\u0010A\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\bBJ\u0015\u0010C\u001a\u00020\u00042\u0006\u0010,\u001a\u00020-H\u0000¢\u0006\u0002\bDJ\u0018\u0010E\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020%2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J%\u0010F\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010G\u001a\u00020!2\u0006\u0010H\u001a\u00020!H\u0000¢\u0006\u0002\bIJ%\u0010J\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010G\u001a\u00020!2\u0006\u0010H\u001a\u00020!H\u0000¢\u0006\u0002\bKJ\u001d\u0010L\u001a\u00020\u00042\u0006\u0010G\u001a\u00020-2\u0006\u0010H\u001a\u00020-H\u0000¢\u0006\u0002\bMJ\u001e\u0010N\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000405H\u0002J=\u0010O\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u00107\u001a\u00020'2!\u00104\u001a\u001d\u0012\u0013\u0012\u00110%¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\u0004\u0012\u00020\u000408H\u0002J3\u0010P\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152!\u00104\u001a\u001d\u0012\u0013\u0012\u00110%¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\u0004\u0012\u00020\u000408H\u0002J \u0010Q\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010S\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010T\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010U\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010V\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J\u0016\u0010W\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'J \u0010X\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010Y\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010Z\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010[\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010\\\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010]\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010^\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010_\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010`\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010a\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010b\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010c\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J \u0010d\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010R\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J\u0016\u0010e\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010f\u001a\u00020'J(\u0010g\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010h\u001a\u00020\t2\u0006\u0010i\u001a\u00020j2\b\b\u0002\u00107\u001a\u00020'J\u0016\u0010k\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010f\u001a\u00020%J\u0016\u0010l\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010h\u001a\u00020\tJ\u0016\u0010m\u001a\u00020n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010h\u001a\u00020\tJ\u000e\u0010o\u001a\u00020n2\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010p\u001a\u00020%2\u0006\u00103\u001a\u00020\u00152\u0006\u0010h\u001a\u00020\tJ\u0016\u0010q\u001a\u00020n2\u0006\u00103\u001a\u00020\u00152\u0006\u0010h\u001a\u00020\tJ\u0016\u0010r\u001a\u00020n2\u0006\u00103\u001a\u00020\u00152\u0006\u0010h\u001a\u00020\tJ\u0016\u0010s\u001a\u00020%2\u0006\u00103\u001a\u00020\u00152\u0006\u0010t\u001a\u00020uJ\u0016\u0010v\u001a\u00020n2\u0006\u00103\u001a\u00020\u00152\u0006\u0010:\u001a\u00020wJ\u001e\u0010x\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010y\u001a\u00020%2\u0006\u0010t\u001a\u00020uJ\u000e\u0010z\u001a\u00020%2\u0006\u00103\u001a\u00020\u0015J \u0010{\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010|\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J\u001f\u0010}\u001a\u00020\u00042\u0006\u0010~\u001a\u00020%2\u0006\u0010\u007f\u001a\u00020n2\u0007\u0010\u0080\u0001\u001a\u00020nJ'\u0010\u0081\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010&\u001a\u0004\u0018\u00010'¢\u0006\u0003\u0010\u0082\u0001J\u0017\u0010\u0083\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010h\u001a\u00020\tJ\u0017\u0010\u0084\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010h\u001a\u00020\tJ/\u0010\u0085\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u00107\u001a\u00020'¢\u0006\u0003\u0010\u0086\u0001J\u0017\u0010\u0087\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010h\u001a\u00020\tJ\u0017\u0010\u0088\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010h\u001a\u00020\tJ\u0019\u0010\u0089\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u00107\u001a\u00020'J\u0019\u0010\u008a\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\b\b\u0002\u00107\u001a\u00020'J)\u0010\u008b\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010&\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J)\u0010\u008c\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010&\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J\u000f\u0010\u008d\u0001\u001a\u00020\u00042\u0006\u0010h\u001a\u00020\tJ\"\u0010\u008e\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0007\u0010\u008f\u0001\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J\u001f\u0010\u0090\u0001\u001a\u00020\u00042\u000e\u00104\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u000105H\u0010¢\u0006\u0003\b\u0091\u0001J\t\u0010\u0092\u0001\u001a\u00020\u0004H\u0002J\u0017\u0010\u0093\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0006J\u0017\u0010\u0094\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010h\u001a\u00020\tJ(\u0010\u0094\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0017\u0010\u0095\u0001\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nJ\u001f\u0010\u0096\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00107\u001a\u00020'J\u0010\u0010\u0097\u0001\u001a\u00020\u00042\u0007\u0010\u0098\u0001\u001a\u00020wJ\u0017\u0010\u0099\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010h\u001a\u00020\tJ\u0017\u0010\u009a\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010h\u001a\u00020\tJ \u0010\u009b\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00062\u0007\u0010\u009c\u0001\u001a\u00020\u0006J!\u0010\u009d\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0007\u0010\u009e\u0001\u001a\u00020\t2\u0007\u0010\u009f\u0001\u001a\u00020\tJA\u0010\u009d\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0017\u0010 \u0001\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0017\u0010¡\u0001\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nJA\u0010¢\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0017\u0010 \u0001\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0017\u0010¡\u0001\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nJ(\u0010£\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00062\u0007\u0010\u009c\u0001\u001a\u00020\u00062\u0006\u00107\u001a\u00020'J!\u0010¤\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0007\u0010\u009e\u0001\u001a\u00020\t2\u0007\u0010\u009f\u0001\u001a\u00020\tJ!\u0010¥\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0007\u0010\u009e\u0001\u001a\u00020\t2\u0007\u0010\u009f\u0001\u001a\u00020\tJ\u0019\u0010¦\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u00107\u001a\u00020'J\u0019\u0010§\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\b\b\u0002\u00107\u001a\u00020'J\"\u0010¨\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0007\u0010\u008f\u0001\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'J!\u0010©\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010i\u001a\u00020j2\b\b\u0002\u00107\u001a\u00020'J\"\u0010ª\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0007\u0010«\u0001\u001a\u00020n2\b\b\u0002\u00107\u001a\u00020'J\u0019\u0010¬\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u00107\u001a\u00020'J\"\u0010\u00ad\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0007\u0010«\u0001\u001a\u00020n2\b\b\u0002\u00107\u001a\u00020'J\"\u0010®\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0007\u0010¯\u0001\u001a\u00020u2\b\b\u0002\u00107\u001a\u00020'J#\u0010°\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\b\u0010±\u0001\u001a\u00030²\u00012\b\b\u0002\u00107\u001a\u00020'J#\u0010³\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\b\u0010±\u0001\u001a\u00030²\u00012\b\b\u0002\u00107\u001a\u00020'J)\u0010´\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010:\u001a\u00020w2\u0006\u0010i\u001a\u00020j2\b\b\u0002\u00107\u001a\u00020'J*\u0010µ\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010:\u001a\u00020w2\u0007\u0010¶\u0001\u001a\u00020n2\b\b\u0002\u00107\u001a\u00020'J*\u0010·\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010:\u001a\u00020w2\u0007\u0010¶\u0001\u001a\u00020n2\b\b\u0002\u00107\u001a\u00020'J\"\u0010¸\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0007\u0010¯\u0001\u001a\u00020u2\b\b\u0002\u00107\u001a\u00020'J\"\u0010¹\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0007\u0010¯\u0001\u001a\u00020u2\b\b\u0002\u00107\u001a\u00020'J*\u0010º\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0006\u0010:\u001a\u00020w2\u0007\u0010»\u0001\u001a\u00020n2\b\b\u0002\u00107\u001a\u00020'J*\u0010¼\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0007\u0010½\u0001\u001a\u00020n2\u0007\u0010¾\u0001\u001a\u00020n2\u0007\u0010¿\u0001\u001a\u00020nJ\"\u0010À\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0007\u0010»\u0001\u001a\u00020n2\b\b\u0002\u00107\u001a\u00020'J\"\u0010Á\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0007\u0010»\u0001\u001a\u00020n2\b\b\u0002\u00107\u001a\u00020'J\"\u0010Â\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0007\u0010Ã\u0001\u001a\u00020%2\b\b\u0002\u00107\u001a\u00020'J\"\u0010Ä\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0007\u0010»\u0001\u001a\u00020n2\b\b\u0002\u00107\u001a\u00020'J#\u0010Å\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\b\u0010Æ\u0001\u001a\u00030Ç\u00012\b\b\u0002\u00107\u001a\u00020'J\"\u0010È\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\u0007\u0010»\u0001\u001a\u00020n2\b\b\u0002\u00107\u001a\u00020'J\"\u0010É\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0007\u0010Ê\u0001\u001a\u00020n2\b\b\u0002\u00107\u001a\u00020'J\"\u0010Ë\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0007\u0010Ê\u0001\u001a\u00020n2\b\b\u0002\u00107\u001a\u00020'J\"\u0010Ì\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0007\u0010Í\u0001\u001a\u00020n2\b\b\u0002\u00107\u001a\u00020'J\"\u0010Î\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0007\u0010Ï\u0001\u001a\u00020n2\b\b\u0002\u00107\u001a\u00020'J\u0019\u0010Ð\u0001\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u00107\u001a\u00020'J\u0019\u0010Ñ\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\b\b\u0002\u00107\u001a\u00020'J\u0019\u0010Ò\u0001\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00152\b\b\u0002\u00107\u001a\u00020'J\u0011\u0010Ó\u0001\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002¨\u0006Ô\u0001"}, m1293d2 = {"Lcom/faceunity/core/avatar/control/AvatarController;", "Lcom/faceunity/core/avatar/control/BaseAvatarController;", "()V", "analyzeAnimationData", "", "animationData", "Lcom/faceunity/core/entity/FUAnimationData;", "propList", "Ljava/util/ArrayList;", "Lcom/faceunity/core/entity/FUBundleData;", "Lkotlin/collections/ArrayList;", "animationList", "applyAddAvatar", "compareData", "Lcom/faceunity/core/avatar/control/AvatarCompareData;", "applyAddAvatarBundle", "applyAddScene", "applyAddSceneBundle", "applyAvatarParams", "applyCompData", "sceneId", "", "listener", "Lcom/faceunity/core/avatar/listener/OnSceneListener;", "applyCreateBundle", "applyDestroyBundle", "applyRemoveAvatar", "applyRemoveAvatarBundle", "applyRemoveScene", "applyRemoveSceneBundle", "applySceneParams", "doAddAvatar", "avatar", "Lcom/faceunity/core/avatar/control/FUAAvatarData;", "doAddAvatar$fu_core_all_featureRelease", "doAddAvatarAnimation", "instanceId", "", "isLoop", "", "(ILjava/util/ArrayList;Ljava/util/ArrayList;Ljava/lang/Boolean;)V", "doAddAvatarGL", "doAddAvatarGL$fu_core_all_featureRelease", "doAddAvatarScene", "sceneData", "Lcom/faceunity/core/avatar/control/FUASceneData;", "doAddAvatarScene$fu_core_all_featureRelease", "doAddAvatarSceneGL", "doAddAvatarSceneGL$fu_core_all_featureRelease", "doAddCameraAnimation", "doAvatarActionBackground", "avatarId", "unit", "Lkotlin/Function0;", "doAvatarActionBackgroundGL", "needBackgroundThread", "Lkotlin/Function1;", "Lkotlin/ParameterName;", NamingTable.TAG, "id", "doAvatarActionGL", "doCreateAnimationBundle", "doRemoveAvatar", "doRemoveAvatar$fu_core_all_featureRelease", "doRemoveAvatarAnimation", "doRemoveAvatarGL", "doRemoveAvatarGL$fu_core_all_featureRelease", "doRemoveAvatarScene", "doRemoveAvatarScene$fu_core_all_featureRelease", "doRemoveCameraAnimation", "doReplaceAvatar", "oldAvatar", "newAvatar", "doReplaceAvatar$fu_core_all_featureRelease", "doReplaceAvatarGL", "doReplaceAvatarGL$fu_core_all_featureRelease", "doReplaceAvatarScene", "doReplaceAvatarScene$fu_core_all_featureRelease", "doSceneActionBackground", "doSceneActionBackgroundGL", "doSceneActionGL", "enableARMode", "enable", "enableBackgroundColor", "enableCameraAnimation", "enableCameraAnimationInternalLerp", "enableFaceProcessor", "enableHumanFollowMode", "enableHumanProcessor", "enableInstanceAnimationInternalLerp", "enableInstanceDynamicBone", "enableInstanceDynamicBoneRootRotationSpeedLimitMode", "enableInstanceDynamicBoneRootTranslationSpeedLimitMode", "enableInstanceDynamicBoneTeleportMode", "enableInstanceExpressionBlend", "enableInstanceFaceUpMode", "enableInstanceFocusEyeToCamera", "enableInstanceModelMatToBone", "enableLowQualityLighting", "enableRenderCamera", "enableShadow", "fuSetInstanceEnableHumanAnimDriver", "mode", "fuSetInstanceFaceBeautyColor", "bundle", "color", "Lcom/faceunity/core/entity/FUColorRGBData;", "fuSetInstanceRiggingRetargeterAvatarFollowMode", "getCameraAnimationFrameNumber", "getCameraAnimationProgress", "", "getCameraAnimationTransitionProgress", "getInstanceAnimationFrameNumber", "getInstanceAnimationProgress", "getInstanceAnimationTransitionProgress", "getInstanceFaceUpArray", "rect", "", "getInstanceFaceUpOriginalValue", "", "getInstanceFaceVertexScreenCoordinate", StreamInformation.KEY_INDEX, "getInstanceSkinColorIndex", "humanProcessorSet3DScene", "isFull", "humanProcessorSetAvatarAnimFilterParams", "nBufferFrames", "pos", "angle", "loadAvatarAnimationData", "(JLcom/faceunity/core/entity/FUAnimationData;Ljava/lang/Boolean;)V", "loadAvatarItemBundle", "loadAvatarItemBundleGL", "loadCameraAnimationData", "(JLcom/faceunity/core/entity/FUAnimationData;Ljava/lang/Boolean;Z)V", "loadSceneItemBundle", "loadSceneItemBundleGL", "pauseCameraAnimation", "pauseInstanceAnimation", "playCameraAnimation", "playInstanceAnimation", "preloadBundleUnThread", "refreshInstanceDynamicBone", "isImmediate", "release", "release$fu_core_all_featureRelease", "releaseAll", "removeAvatarAnimationData", "removeAvatarItemBundle", "bundles", "removeCameraAnimationData", "removePreLoadedBundle", "path", "removeSceneItemBundle", "removeSceneItemBundleGL", "replaceAvatarAnimationData", "targetAnimationData", "replaceAvatarItemBundle", "oldBundle", "newBundle", "oldBundles", "newBundles", "replaceAvatarItemBundleGL", "replaceCameraAnimationData", "replaceSceneItemBundle", "replaceSceneItemBundleGL", "resetCameraAnimation", "resetInstanceAnimation", "resetInstanceDynamicBone", "setBackgroundColor", "setCameraAnimationTransitionTime", "time", "setCurrentScene", "setInstanceAnimationTransitionTime", "setInstanceBlendExpression", "data", "setInstanceBodyInvisibleList", "visibleList", "", "setInstanceBodyVisibleList", "setInstanceColor", "setInstanceColorIntensity", "intensity", "setInstanceDeformation", "setInstanceExpressionWeight0", "setInstanceExpressionWeight1", "setInstanceFaceUp", "value", "setInstanceRiggingRetargeterAvatarFixModeTransScale", "x", "y", "z", "setInstanceRotDelta", "setInstanceScaleDelta", "setInstanceShadowPCFLevel", "level", "setInstanceTargetAngle", "setInstanceTargetPosition", "position", "Lcom/faceunity/core/entity/FUCoordinate3DData;", "setInstanceTranslateDelta", "setProjectionMatrixFov", "fov", "setProjectionMatrixOrthoSize", "setProjectionMatrixZfar", "far", "setProjectionMatrixZnear", "near", "startCameraAnimation", "startInstanceAnimation", "stopInstanceAnimation", "updateBackgroundSet", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class AvatarController extends BaseAvatarController {
    private final void doAvatarActionBackground(final long avatarId, final Function0<Unit> unit) {
        doBackgroundAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                if (AvatarController.this.getAvatarBackgroundSet().contains(Long.valueOf(avatarId))) {
                    unit.invoke();
                } else {
                    FULogger.m297w(AvatarController.this.getTAG(), "doAvatarActionBackground failed  avatarBackgroundSet not contains avatarId=" + avatarId);
                }
            }
        });
    }

    static void doAvatarActionBackgroundGL$default(AvatarController avatarController, long j, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        avatarController.doAvatarActionBackgroundGL(j, z, function1);
    }

    private final void doAvatarActionBackgroundGL(final long avatarId, boolean needBackgroundThread, final Function1<? super Integer, Unit> unit) {
        if (needBackgroundThread) {
            doBackgroundAction(new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    if (AvatarController.this.getAvatarBackgroundSet().contains(Long.valueOf(avatarId))) {
                        AvatarController.this.doAvatarActionGL(avatarId, unit);
                    } else {
                        FULogger.m297w(AvatarController.this.getTAG(), "doAvatarActionBackgroundGL failed  avatarBackgroundSet not contains avatarId=" + avatarId);
                    }
                }
            });
        } else {
            doAvatarActionGL(avatarId, unit);
        }
    }

    public final void doAvatarActionGL(final long avatarId, final Function1<? super Integer, Unit> unit) {
        doGLThreadAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                Integer num = AvatarController.this.getAvatarIdMap().get(Long.valueOf(avatarId));
                if (num != null) {
                    unit.invoke(Integer.valueOf(num.intValue()));
                } else {
                    FULogger.m297w(AvatarController.this.getTAG(), "doAvatarActionGL failed  avatarId=" + avatarId + "    id=" + num);
                }
            }
        });
    }

    private final void doSceneActionBackground(final long sceneId, final Function0<Unit> unit) {
        doBackgroundAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                if (AvatarController.this.getSceneBackgroundSet().contains(Long.valueOf(sceneId))) {
                    unit.invoke();
                } else {
                    FULogger.m297w(AvatarController.this.getTAG(), "doSceneActionBackground failed  sceneBackgroundSet not contains sceneId=" + sceneId);
                }
            }
        });
    }

    static void doSceneActionBackgroundGL$default(AvatarController avatarController, long j, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        avatarController.doSceneActionBackgroundGL(j, z, function1);
    }

    private final void doSceneActionBackgroundGL(final long sceneId, boolean needBackgroundThread, final Function1<? super Integer, Unit> unit) {
        if (needBackgroundThread) {
            doBackgroundAction(new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    if (AvatarController.this.getSceneBackgroundSet().contains(Long.valueOf(sceneId))) {
                        AvatarController.this.doSceneActionGL(sceneId, unit);
                    } else {
                        FULogger.m297w(AvatarController.this.getTAG(), "doSceneActionBackgroundGL failed  sceneBackgroundSet not contains sceneId=" + sceneId);
                    }
                }
            });
        } else {
            doSceneActionGL(sceneId, unit);
        }
    }

    public final void doSceneActionGL(final long sceneId, final Function1<? super Integer, Unit> unit) {
        doGLThreadAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                Integer num = AvatarController.this.getSceneIdMap().get(Long.valueOf(sceneId));
                if (num != null) {
                    unit.invoke(Integer.valueOf(num.intValue()));
                } else {
                    FULogger.m297w(AvatarController.this.getTAG(), "doSceneActionGL failed  sceneId=" + sceneId + "    id=" + num);
                }
            }
        });
    }

    public final void doAddAvatarScene$fu_core_all_featureRelease(final FUASceneData sceneData, final OnSceneListener listener) {
        Intrinsics.checkParameterIsNotNull(sceneData, "sceneData");
        doBackgroundAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() throws InterruptedException {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() throws InterruptedException {
                this.this$0.loadControllerBundle(sceneData);
                if (this.this$0.getMControllerBundleHandle() <= 0) {
                    return;
                }
                AvatarCompareData avatarCompareData = new AvatarCompareData();
                this.this$0.addScene(sceneData, avatarCompareData);
                this.this$0.applyCompData(avatarCompareData, sceneData.getId(), listener);
            }
        });
    }

    public final void doAddAvatarSceneGL$fu_core_all_featureRelease(final FUASceneData sceneData, final OnSceneListener listener) {
        Intrinsics.checkParameterIsNotNull(sceneData, "sceneData");
        doGLThreadAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() throws InterruptedException {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() throws InterruptedException {
                this.this$0.loadControllerBundle(sceneData);
                if (this.this$0.getMControllerBundleHandle() <= 0) {
                    return;
                }
                AvatarCompareData avatarCompareData = new AvatarCompareData();
                this.this$0.addScene(sceneData, avatarCompareData);
                this.this$0.applyCompData(avatarCompareData, sceneData.getId(), listener);
            }
        });
    }

    public final void doRemoveAvatarScene$fu_core_all_featureRelease(final FUASceneData sceneData) {
        Intrinsics.checkParameterIsNotNull(sceneData, "sceneData");
        doBackgroundAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() throws InterruptedException {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() throws InterruptedException {
                AvatarCompareData avatarCompareData = new AvatarCompareData();
                this.this$0.removeScene(sceneData, avatarCompareData);
                AvatarController.applyCompData$default(this.this$0, avatarCompareData, 0L, null, 6, null);
            }
        });
    }

    public final void doReplaceAvatarScene$fu_core_all_featureRelease(final FUASceneData oldAvatar, final FUASceneData newAvatar) {
        Intrinsics.checkParameterIsNotNull(oldAvatar, "oldAvatar");
        Intrinsics.checkParameterIsNotNull(newAvatar, "newAvatar");
        doBackgroundAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() throws InterruptedException {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() throws InterruptedException {
                AvatarCompareData avatarCompareData = new AvatarCompareData();
                this.this$0.replaceScene(oldAvatar, newAvatar, avatarCompareData);
                AvatarController.applyCompData$default(this.this$0, avatarCompareData, 0L, null, 6, null);
            }
        });
    }

    public final void doAddAvatar$fu_core_all_featureRelease(final long sceneId, final FUAAvatarData avatar) {
        Intrinsics.checkParameterIsNotNull(avatar, "avatar");
        doSceneActionBackground(sceneId, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() throws InterruptedException {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() throws InterruptedException {
                AvatarCompareData avatarCompareData = new AvatarCompareData();
                this.this$0.addAvatar(sceneId, avatar, avatarCompareData);
                AvatarController.applyCompData$default(this.this$0, avatarCompareData, 0L, null, 6, null);
            }
        });
    }

    public final void doAddAvatarGL$fu_core_all_featureRelease(final long sceneId, final FUAAvatarData avatar) {
        Intrinsics.checkParameterIsNotNull(avatar, "avatar");
        doSceneActionGL(sceneId, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) throws InterruptedException {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) throws InterruptedException {
                AvatarCompareData avatarCompareData = new AvatarCompareData();
                this.this$0.addAvatar(sceneId, avatar, avatarCompareData);
                AvatarController.applyCompData$default(this.this$0, avatarCompareData, 0L, null, 6, null);
            }
        });
    }

    public final void doRemoveAvatar$fu_core_all_featureRelease(final long sceneId, final FUAAvatarData avatar) {
        Intrinsics.checkParameterIsNotNull(avatar, "avatar");
        doSceneActionBackground(sceneId, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() throws InterruptedException {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() throws InterruptedException {
                AvatarCompareData avatarCompareData = new AvatarCompareData();
                this.this$0.removeAvatar(sceneId, avatar, avatarCompareData);
                AvatarController.applyCompData$default(this.this$0, avatarCompareData, 0L, null, 6, null);
            }
        });
    }

    public final void doRemoveAvatarGL$fu_core_all_featureRelease(final long sceneId, final FUAAvatarData avatar) {
        Intrinsics.checkParameterIsNotNull(avatar, "avatar");
        doSceneActionGL(sceneId, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) throws InterruptedException {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) throws InterruptedException {
                AvatarCompareData avatarCompareData = new AvatarCompareData();
                this.this$0.removeAvatar(sceneId, avatar, avatarCompareData);
                AvatarController.applyCompData$default(this.this$0, avatarCompareData, 0L, null, 6, null);
            }
        });
    }

    public final void doReplaceAvatar$fu_core_all_featureRelease(long sceneId, final FUAAvatarData oldAvatar, final FUAAvatarData newAvatar) {
        Intrinsics.checkParameterIsNotNull(oldAvatar, "oldAvatar");
        Intrinsics.checkParameterIsNotNull(newAvatar, "newAvatar");
        doSceneActionBackground(sceneId, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() throws InterruptedException {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() throws InterruptedException {
                AvatarCompareData avatarCompareData = new AvatarCompareData();
                this.this$0.replaceAvatar(oldAvatar, newAvatar, avatarCompareData);
                AvatarController.applyCompData$default(this.this$0, avatarCompareData, 0L, null, 6, null);
            }
        });
    }

    public final void doReplaceAvatarGL$fu_core_all_featureRelease(long sceneId, final FUAAvatarData oldAvatar, final FUAAvatarData newAvatar) {
        Intrinsics.checkParameterIsNotNull(oldAvatar, "oldAvatar");
        Intrinsics.checkParameterIsNotNull(newAvatar, "newAvatar");
        doSceneActionGL(sceneId, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) throws InterruptedException {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) throws InterruptedException {
                AvatarCompareData avatarCompareData = new AvatarCompareData();
                this.this$0.replaceAvatar(oldAvatar, newAvatar, avatarCompareData);
                AvatarController.applyCompData$default(this.this$0, avatarCompareData, 0L, null, 6, null);
            }
        });
    }

    @Override
    public void release$fu_core_all_featureRelease(Function0<Unit> unit) throws InterruptedException {
        super.release$fu_core_all_featureRelease(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                this.this$0.releaseAll();
            }
        });
    }

    public final void releaseAll() {
        Iterator<Map.Entry<Long, Integer>> it = getAvatarIdMap().entrySet().iterator();
        while (it.hasNext()) {
            SDKController.INSTANCE.destroyInstance$fu_core_all_featureRelease(it.next().getValue().intValue());
        }
        getAvatarIdMap().clear();
        Iterator<Map.Entry<Long, Integer>> it2 = getSceneIdMap().entrySet().iterator();
        while (it2.hasNext()) {
            SDKController.INSTANCE.destroyScene$fu_core_all_featureRelease(it2.next().getValue().intValue());
        }
        getSceneIdMap().clear();
        Iterator<Map.Entry<String, Integer>> it3 = getHandleReferenceCountMap().entrySet().iterator();
        while (it3.hasNext()) {
            destroyBundle(it3.next().getKey());
        }
        getHandleReferenceCountMap().clear();
    }

    static void applyCompData$default(AvatarController avatarController, AvatarCompareData avatarCompareData, long j, OnSceneListener onSceneListener, int i, Object obj) throws InterruptedException {
        if ((i & 2) != 0) {
            j = 0;
        }
        if ((i & 4) != 0) {
            onSceneListener = null;
        }
        avatarController.applyCompData(avatarCompareData, j, onSceneListener);
    }

    public final void applyCompData(final AvatarCompareData compareData, final long sceneId, final OnSceneListener listener) throws InterruptedException {
        updateBackgroundSet(compareData);
        applyCreateBundle(compareData);
        doGLThreadAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                AvatarController.this.applyRemoveAvatarBundle(compareData);
                AvatarController.this.applyRemoveAvatar(compareData);
                AvatarController.this.applyRemoveSceneBundle(compareData);
                AvatarController.this.applyRemoveScene(compareData);
                AvatarController.this.applyAddScene(compareData);
                AvatarController.this.applyAddSceneBundle(compareData);
                AvatarController.this.applyAddAvatar(compareData);
                AvatarController.this.applySceneParams(compareData);
                AvatarController.this.applyAddAvatarBundle(compareData);
                AvatarController.this.applyAvatarParams(compareData);
                AvatarController.this.applyDestroyBundle(compareData);
                OnSceneListener onSceneListener = listener;
                if (onSceneListener != null) {
                    onSceneListener.onSceneLoaded(sceneId);
                }
            }
        });
    }

    private final void updateBackgroundSet(AvatarCompareData compareData) {
        Iterator<T> it = compareData.getSceneRemoveList().iterator();
        while (it.hasNext()) {
            getSceneBackgroundSet().remove(Long.valueOf(((FUASceneData) it.next()).getId()));
        }
        Iterator<T> it2 = compareData.getSceneAddList().iterator();
        while (it2.hasNext()) {
            getSceneBackgroundSet().add(Long.valueOf(((FUASceneData) it2.next()).getId()));
        }
        Iterator<Map.Entry<Long, ArrayList<Long>>> it3 = compareData.getSceneUnbindAvatarMap().entrySet().iterator();
        while (it3.hasNext()) {
            Iterator<T> it4 = it3.next().getValue().iterator();
            while (it4.hasNext()) {
                getAvatarBackgroundSet().remove(Long.valueOf(((Number) it4.next()).longValue()));
            }
        }
        Iterator<Map.Entry<Long, ArrayList<Long>>> it5 = compareData.getSceneBindAvatarMap().entrySet().iterator();
        while (it5.hasNext()) {
            Iterator<T> it6 = it5.next().getValue().iterator();
            while (it6.hasNext()) {
                getAvatarBackgroundSet().add(Long.valueOf(((Number) it6.next()).longValue()));
            }
        }
        for (Map.Entry<Long, Long> entry : compareData.getSceneReplaceAvatarMap().entrySet()) {
            long jLongValue = entry.getKey().longValue();
            long jLongValue2 = entry.getValue().longValue();
            getAvatarBackgroundSet().remove(Long.valueOf(jLongValue));
            getAvatarBackgroundSet().add(Long.valueOf(jLongValue2));
        }
        for (Map.Entry<String, Integer> entry2 : compareData.getBundleAddMap().entrySet()) {
            addReferenceCount(getHandleReferenceCountMap(), entry2.getKey(), entry2.getValue().intValue());
        }
    }

    private final void applyCreateBundle(AvatarCompareData compareData) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(compareData.getBundleAddMap().size());
        Iterator<Map.Entry<String, Integer>> it = compareData.getBundleAddMap().entrySet().iterator();
        while (it.hasNext()) {
            final String key = it.next().getKey();
            getMCachedThreadPool().execute(new Runnable() {
                @Override
                public final void run() {
                    this.createBundle(key);
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
    }

    public final void applyRemoveAvatarBundle(AvatarCompareData compareData) {
        for (Map.Entry<Long, ArrayList<String>> entry : compareData.getAvatarUnbindHandleMap().entrySet()) {
            long jLongValue = entry.getKey().longValue();
            ArrayList<String> value = entry.getValue();
            if (getAvatarIdMap().containsKey(Long.valueOf(jLongValue))) {
                Integer num = getAvatarIdMap().get(Long.valueOf(jLongValue));
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(num, "avatarIdMap[id]!!");
                int iIntValue = num.intValue();
                ArrayList arrayList = new ArrayList();
                for (String str : value) {
                    int bundleHandle = getMBundleManager().getBundleHandle(str);
                    BaseAvatarController.removeReferenceCount$default(this, getHandleReferenceCountMap(), str, 0, 4, (Object) null);
                    if (bundleHandle > 0) {
                        arrayList.add(Integer.valueOf(bundleHandle));
                    }
                }
                SDKController.INSTANCE.unbindItemsFromInstance$fu_core_all_featureRelease(iIntValue, CollectionsKt.toIntArray(arrayList));
            }
        }
    }

    public final void applyRemoveAvatar(AvatarCompareData compareData) {
        for (Map.Entry<Long, ArrayList<Long>> entry : compareData.getSceneUnbindAvatarMap().entrySet()) {
            long jLongValue = entry.getKey().longValue();
            ArrayList<Long> value = entry.getValue();
            if (getSceneIdMap().containsKey(Long.valueOf(jLongValue))) {
                Iterator<T> it = value.iterator();
                while (it.hasNext()) {
                    long jLongValue2 = ((Number) it.next()).longValue();
                    if (getAvatarIdMap().containsKey(Long.valueOf(jLongValue2))) {
                        Integer num = getAvatarIdMap().get(Long.valueOf(jLongValue2));
                        if (num == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(num, "avatarIdMap[it]!!");
                        SDKController.INSTANCE.destroyInstance$fu_core_all_featureRelease(num.intValue());
                        getAvatarIdMap().remove(Long.valueOf(jLongValue2));
                    }
                }
            }
        }
        for (Map.Entry<Long, Long> entry2 : compareData.getSceneReplaceAvatarMap().entrySet()) {
            long jLongValue3 = entry2.getKey().longValue();
            long jLongValue4 = entry2.getValue().longValue();
            Integer it2 = getAvatarIdMap().get(Long.valueOf(jLongValue3));
            if (it2 != null) {
                ConcurrentHashMap<Long, Integer> avatarIdMap = getAvatarIdMap();
                Long lValueOf = Long.valueOf(jLongValue4);
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                avatarIdMap.put(lValueOf, it2);
                getAvatarIdMap().remove(Long.valueOf(jLongValue3));
            }
        }
    }

    public final void applyRemoveSceneBundle(AvatarCompareData compareData) {
        for (Map.Entry<Long, ArrayList<String>> entry : compareData.getSceneUnbindHandleMap().entrySet()) {
            long jLongValue = entry.getKey().longValue();
            ArrayList<String> value = entry.getValue();
            if (getSceneIdMap().containsKey(Long.valueOf(jLongValue))) {
                Integer num = getSceneIdMap().get(Long.valueOf(jLongValue));
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(num, "sceneIdMap[id]!!");
                int iIntValue = num.intValue();
                ArrayList arrayList = new ArrayList();
                for (String str : value) {
                    int bundleHandle = getMBundleManager().getBundleHandle(str);
                    BaseAvatarController.removeReferenceCount$default(this, getHandleReferenceCountMap(), str, 0, 4, (Object) null);
                    if (bundleHandle > 0) {
                        arrayList.add(Integer.valueOf(bundleHandle));
                    }
                }
                SDKController.INSTANCE.unbindItemsFromScene$fu_core_all_featureRelease(iIntValue, CollectionsKt.toIntArray(arrayList));
            }
        }
    }

    public final void applyRemoveScene(AvatarCompareData compareData) {
        for (FUASceneData fUASceneData : compareData.getSceneRemoveList()) {
            if (getSceneIdMap().containsKey(Long.valueOf(fUASceneData.getId()))) {
                Integer num = getSceneIdMap().get(Long.valueOf(fUASceneData.getId()));
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(num, "sceneIdMap[it.id]!!");
                SDKController.INSTANCE.destroyScene$fu_core_all_featureRelease(num.intValue());
                getSceneIdMap().remove(Long.valueOf(fUASceneData.getId()));
            }
        }
    }

    public final void applyAddScene(AvatarCompareData compareData) {
        for (FUASceneData fUASceneData : compareData.getSceneAddList()) {
            int iCreateScene$fu_core_all_featureRelease = SDKController.INSTANCE.createScene$fu_core_all_featureRelease();
            if (iCreateScene$fu_core_all_featureRelease > 0) {
                getSceneIdMap().put(Long.valueOf(fUASceneData.getId()), Integer.valueOf(iCreateScene$fu_core_all_featureRelease));
            }
        }
    }

    public final void applyAddSceneBundle(AvatarCompareData compareData) {
        for (Map.Entry<FUASceneData, ArrayList<String>> entry : compareData.getSceneBindHandleMap().entrySet()) {
            FUASceneData key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            if (getSceneIdMap().containsKey(Long.valueOf(key.getId()))) {
                Integer num = getSceneIdMap().get(Long.valueOf(key.getId()));
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(num, "sceneIdMap[scene.id]!!");
                int iIntValue = num.intValue();
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = value.iterator();
                while (it.hasNext()) {
                    int bundleHandle = getMBundleManager().getBundleHandle((String) it.next());
                    if (bundleHandle > 0) {
                        arrayList.add(Integer.valueOf(bundleHandle));
                    }
                }
                SDKController.INSTANCE.bindItemsToScene$fu_core_all_featureRelease(iIntValue, CollectionsKt.toIntArray(arrayList));
            }
        }
    }

    public final void applyAddAvatar(AvatarCompareData compareData) {
        for (Map.Entry<Long, ArrayList<Long>> entry : compareData.getSceneBindAvatarMap().entrySet()) {
            long jLongValue = entry.getKey().longValue();
            ArrayList<Long> value = entry.getValue();
            if (getSceneIdMap().containsKey(Long.valueOf(jLongValue))) {
                Integer num = getSceneIdMap().get(Long.valueOf(jLongValue));
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(num, "sceneIdMap[id]!!");
                int iIntValue = num.intValue();
                Iterator<T> it = value.iterator();
                while (it.hasNext()) {
                    long jLongValue2 = ((Number) it.next()).longValue();
                    int iCreateInstance$fu_core_all_featureRelease = SDKController.INSTANCE.createInstance$fu_core_all_featureRelease(iIntValue);
                    if (iCreateInstance$fu_core_all_featureRelease > 0) {
                        getAvatarIdMap().put(Long.valueOf(jLongValue2), Integer.valueOf(iCreateInstance$fu_core_all_featureRelease));
                    }
                }
            }
        }
    }

    public final void applySceneParams(AvatarCompareData compareData) {
        Iterator<T> it = compareData.getSceneAddList().iterator();
        while (it.hasNext()) {
            Iterator<Map.Entry<String, Function0<Unit>>> it2 = ((FUASceneData) it.next()).getParams().entrySet().iterator();
            while (it2.hasNext()) {
                it2.next().getValue().invoke();
            }
        }
    }

    public final void applyAddAvatarBundle(AvatarCompareData compareData) {
        for (Map.Entry<FUAAvatarData, ArrayList<String>> entry : compareData.getAvatarBindHandleMap().entrySet()) {
            FUAAvatarData key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            if (getAvatarIdMap().containsKey(Long.valueOf(key.getId()))) {
                Integer num = getAvatarIdMap().get(Long.valueOf(key.getId()));
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(num, "avatarIdMap[avatar.id]!!");
                int iIntValue = num.intValue();
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = value.iterator();
                while (it.hasNext()) {
                    int bundleHandle = getMBundleManager().getBundleHandle((String) it.next());
                    if (bundleHandle > 0) {
                        arrayList.add(Integer.valueOf(bundleHandle));
                    }
                }
                ArrayList arrayList2 = arrayList;
                if (!arrayList2.isEmpty()) {
                    SDKController.INSTANCE.bindItemsToInstance$fu_core_all_featureRelease(iIntValue, CollectionsKt.toIntArray(arrayList2));
                }
            }
        }
    }

    public final void applyAvatarParams(AvatarCompareData compareData) {
        for (Map.Entry<Long, LinkedHashMap<String, Function0<Unit>>> entry : compareData.getAvatarParamsMap().entrySet()) {
            long jLongValue = entry.getKey().longValue();
            LinkedHashMap<String, Function0<Unit>> value = entry.getValue();
            if (getAvatarIdMap().get(Long.valueOf(jLongValue)) != null) {
                Iterator<Map.Entry<String, Function0<Unit>>> it = value.entrySet().iterator();
                while (it.hasNext()) {
                    it.next().getValue().invoke();
                }
            }
        }
    }

    public final void applyDestroyBundle(AvatarCompareData compareData) {
        Iterator<Map.Entry<String, Integer>> it = compareData.getBundleRemoveMap().entrySet().iterator();
        while (it.hasNext()) {
            destroyBundle(it.next().getKey());
        }
    }

    public final void preloadBundleUnThread(FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        createBundle(bundle.getPath());
    }

    public final void removePreLoadedBundle(final String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        doBackgroundAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                AvatarController.this.doGLThreadAction(new Function0<Unit>() {
                    {
                        super(0);
                    }

                    @Override
                    public Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    public final void invoke2() {
                        AvatarController.this.destroyBundle(path);
                    }
                });
            }
        });
    }

    public final void loadSceneItemBundle(final long sceneId, final FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        doSceneActionBackground(sceneId, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                final int iCreateBundle = AvatarController.this.createBundle(bundle.getPath());
                if (iCreateBundle == 0) {
                    return;
                }
                AvatarController avatarController = AvatarController.this;
                BaseAvatarController.addReferenceCount$default(avatarController, avatarController.getHandleReferenceCountMap(), bundle.getPath(), 0, 4, (Object) null);
                AvatarController.this.doSceneActionGL(sceneId, new Function1<Integer, Unit>() {
                    {
                        super(1);
                    }

                    @Override
                    public Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i) {
                        SDKController.INSTANCE.bindItemsToScene$fu_core_all_featureRelease(i, new int[]{iCreateBundle});
                    }
                });
            }
        });
    }

    public final void loadSceneItemBundleGL(long sceneId, final FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        BaseAvatarController.addReferenceCount$default(this, getHandleReferenceCountMap(), bundle.getPath(), 0, 4, (Object) null);
        doSceneActionGL(sceneId, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                int iCreateBundle = AvatarController.this.createBundle(bundle.getPath());
                if (iCreateBundle == 0) {
                    return;
                }
                SDKController.INSTANCE.bindItemsToScene$fu_core_all_featureRelease(i, new int[]{iCreateBundle});
            }
        });
    }

    public final void removeSceneItemBundle(long sceneId, final FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        doSceneActionBackgroundGL$default(this, sceneId, false, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                int bundleHandle = AvatarController.this.getMBundleManager().getBundleHandle(bundle.getPath());
                if (bundleHandle > 0) {
                    AvatarController avatarController = AvatarController.this;
                    BaseAvatarController.removeReferenceCount$default(avatarController, avatarController.getHandleReferenceCountMap(), bundle.getPath(), 0, 4, (Object) null);
                    SDKController.INSTANCE.unbindItemsFromScene$fu_core_all_featureRelease(i, new int[]{bundleHandle});
                }
                AvatarController.this.destroyBundle(bundle.getPath());
            }
        }, 2, null);
    }

    public final void removeSceneItemBundleGL(long sceneId, final FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        doSceneActionGL(sceneId, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                int bundleHandle = AvatarController.this.getMBundleManager().getBundleHandle(bundle.getPath());
                if (bundleHandle > 0) {
                    AvatarController avatarController = AvatarController.this;
                    BaseAvatarController.removeReferenceCount$default(avatarController, avatarController.getHandleReferenceCountMap(), bundle.getPath(), 0, 4, (Object) null);
                    SDKController.INSTANCE.unbindItemsFromScene$fu_core_all_featureRelease(i, new int[]{bundleHandle});
                }
                AvatarController.this.destroyBundle(bundle.getPath());
            }
        });
    }

    public final void replaceSceneItemBundle(final long sceneId, final FUBundleData oldBundle, final FUBundleData newBundle) {
        Intrinsics.checkParameterIsNotNull(oldBundle, "oldBundle");
        Intrinsics.checkParameterIsNotNull(newBundle, "newBundle");
        doSceneActionBackground(sceneId, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                AvatarController avatarController = AvatarController.this;
                BaseAvatarController.addReferenceCount$default(avatarController, avatarController.getHandleReferenceCountMap(), newBundle.getPath(), 0, 4, (Object) null);
                AvatarController.this.createBundle(newBundle.getPath());
                AvatarController.this.doSceneActionGL(sceneId, new Function1<Integer, Unit>() {
                    {
                        super(1);
                    }

                    @Override
                    public Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i) {
                        int bundleHandle = AvatarController.this.getMBundleManager().getBundleHandle(oldBundle.getPath());
                        if (bundleHandle > 0) {
                            BaseAvatarController.removeReferenceCount$default(AvatarController.this, AvatarController.this.getHandleReferenceCountMap(), oldBundle.getPath(), 0, 4, (Object) null);
                            SDKController.INSTANCE.unbindItemsFromScene$fu_core_all_featureRelease(i, new int[]{bundleHandle});
                        }
                        int bundleHandle2 = AvatarController.this.getMBundleManager().getBundleHandle(newBundle.getPath());
                        if (bundleHandle2 > 0) {
                            SDKController.INSTANCE.bindItemsToScene$fu_core_all_featureRelease(i, new int[]{bundleHandle2});
                        }
                        AvatarController.this.destroyBundle(oldBundle.getPath());
                    }
                });
            }
        });
    }

    public final void replaceSceneItemBundleGL(long sceneId, final FUBundleData oldBundle, final FUBundleData newBundle) {
        Intrinsics.checkParameterIsNotNull(oldBundle, "oldBundle");
        Intrinsics.checkParameterIsNotNull(newBundle, "newBundle");
        BaseAvatarController.addReferenceCount$default(this, getHandleReferenceCountMap(), newBundle.getPath(), 0, 4, (Object) null);
        doSceneActionGL(sceneId, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                AvatarController.this.createBundle(newBundle.getPath());
                int bundleHandle = AvatarController.this.getMBundleManager().getBundleHandle(oldBundle.getPath());
                if (bundleHandle > 0) {
                    AvatarController avatarController = AvatarController.this;
                    BaseAvatarController.removeReferenceCount$default(avatarController, avatarController.getHandleReferenceCountMap(), oldBundle.getPath(), 0, 4, (Object) null);
                    SDKController.INSTANCE.unbindItemsFromScene$fu_core_all_featureRelease(i, new int[]{bundleHandle});
                }
                int bundleHandle2 = AvatarController.this.getMBundleManager().getBundleHandle(newBundle.getPath());
                if (bundleHandle2 > 0) {
                    SDKController.INSTANCE.bindItemsToScene$fu_core_all_featureRelease(i, new int[]{bundleHandle2});
                }
                AvatarController.this.destroyBundle(oldBundle.getPath());
            }
        });
    }

    public final void loadAvatarItemBundle(final long avatarId, final FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        doAvatarActionBackground(avatarId, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                AvatarController avatarController = AvatarController.this;
                BaseAvatarController.addReferenceCount$default(avatarController, avatarController.getHandleReferenceCountMap(), bundle.getPath(), 0, 4, (Object) null);
                AvatarController.this.createBundle(bundle.getPath());
                AvatarController.this.doAvatarActionGL(avatarId, new Function1<Integer, Unit>() {
                    {
                        super(1);
                    }

                    @Override
                    public Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i) {
                        int bundleHandle = AvatarController.this.getMBundleManager().getBundleHandle(bundle.getPath());
                        if (bundleHandle > 0) {
                            SDKController.INSTANCE.bindItemsToInstance$fu_core_all_featureRelease(i, new int[]{bundleHandle});
                        }
                    }
                });
            }
        });
    }

    public final void loadAvatarItemBundleGL(long avatarId, final FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        BaseAvatarController.addReferenceCount$default(this, getHandleReferenceCountMap(), bundle.getPath(), 0, 4, (Object) null);
        doAvatarActionGL(avatarId, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                AvatarController.this.createBundle(bundle.getPath());
                int bundleHandle = AvatarController.this.getMBundleManager().getBundleHandle(bundle.getPath());
                if (bundleHandle > 0) {
                    SDKController.INSTANCE.bindItemsToInstance$fu_core_all_featureRelease(i, new int[]{bundleHandle});
                }
            }
        });
    }

    public final void removeAvatarItemBundle(long avatarId, final FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        doAvatarActionBackgroundGL$default(this, avatarId, false, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                int bundleHandle = AvatarController.this.getMBundleManager().getBundleHandle(bundle.getPath());
                if (bundleHandle > 0) {
                    AvatarController avatarController = AvatarController.this;
                    BaseAvatarController.removeReferenceCount$default(avatarController, avatarController.getHandleReferenceCountMap(), bundle.getPath(), 0, 4, (Object) null);
                    SDKController.INSTANCE.unbindItemsFromInstance$fu_core_all_featureRelease(i, new int[]{bundleHandle});
                }
                AvatarController.this.destroyBundle(bundle.getPath());
            }
        }, 2, null);
    }

    public final void removeAvatarItemBundle(long avatarId, final ArrayList<FUBundleData> bundles) {
        Intrinsics.checkParameterIsNotNull(bundles, "bundles");
        doAvatarActionBackgroundGL$default(this, avatarId, false, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                ArrayList arrayList = new ArrayList();
                for (FUBundleData fUBundleData : bundles) {
                    int bundleHandle = AvatarController.this.getMBundleManager().getBundleHandle(fUBundleData.getPath());
                    if (bundleHandle > 0) {
                        AvatarController avatarController = AvatarController.this;
                        BaseAvatarController.removeReferenceCount$default(avatarController, avatarController.getHandleReferenceCountMap(), fUBundleData.getPath(), 0, 4, (Object) null);
                        arrayList.add(Integer.valueOf(bundleHandle));
                    }
                }
                ArrayList arrayList2 = arrayList;
                if (!arrayList2.isEmpty()) {
                    SDKController.INSTANCE.unbindItemsFromInstance$fu_core_all_featureRelease(i, CollectionsKt.toIntArray(arrayList2));
                }
                Iterator it = bundles.iterator();
                while (it.hasNext()) {
                    AvatarController.this.destroyBundle(((FUBundleData) it.next()).getPath());
                }
            }
        }, 2, null);
    }

    public final void replaceAvatarItemBundle(final long avatarId, final FUBundleData oldBundle, final FUBundleData newBundle) {
        Intrinsics.checkParameterIsNotNull(oldBundle, "oldBundle");
        Intrinsics.checkParameterIsNotNull(newBundle, "newBundle");
        doAvatarActionBackground(avatarId, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                AvatarController avatarController = AvatarController.this;
                BaseAvatarController.addReferenceCount$default(avatarController, avatarController.getHandleReferenceCountMap(), newBundle.getPath(), 0, 4, (Object) null);
                AvatarController.this.createBundle(newBundle.getPath());
                AvatarController.this.doAvatarActionGL(avatarId, new Function1<Integer, Unit>() {
                    {
                        super(1);
                    }

                    @Override
                    public Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i) {
                        int bundleHandle = AvatarController.this.getMBundleManager().getBundleHandle(oldBundle.getPath());
                        if (bundleHandle > 0) {
                            BaseAvatarController.removeReferenceCount$default(AvatarController.this, AvatarController.this.getHandleReferenceCountMap(), oldBundle.getPath(), 0, 4, (Object) null);
                            SDKController.INSTANCE.unbindItemsFromInstance$fu_core_all_featureRelease(i, new int[]{bundleHandle});
                        }
                        int bundleHandle2 = AvatarController.this.getMBundleManager().getBundleHandle(newBundle.getPath());
                        if (bundleHandle2 > 0) {
                            SDKController.INSTANCE.bindItemsToInstance$fu_core_all_featureRelease(i, new int[]{bundleHandle2});
                        }
                        AvatarController.this.destroyBundle(oldBundle.getPath());
                    }
                });
            }
        });
    }

    public final void replaceAvatarItemBundle(final long avatarId, final ArrayList<FUBundleData> oldBundles, final ArrayList<FUBundleData> newBundles) {
        Intrinsics.checkParameterIsNotNull(oldBundles, "oldBundles");
        Intrinsics.checkParameterIsNotNull(newBundles, "newBundles");
        doAvatarActionBackground(avatarId, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                for (FUBundleData fUBundleData : newBundles) {
                    AvatarController avatarController = AvatarController.this;
                    BaseAvatarController.addReferenceCount$default(avatarController, avatarController.getHandleReferenceCountMap(), fUBundleData.getPath(), 0, 4, (Object) null);
                }
                Iterator it = newBundles.iterator();
                while (it.hasNext()) {
                    AvatarController.this.createBundle(((FUBundleData) it.next()).getPath());
                }
                AvatarController.this.doAvatarActionGL(avatarId, new Function1<Integer, Unit>() {
                    {
                        super(1);
                    }

                    @Override
                    public Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i) {
                        ArrayList arrayList = new ArrayList();
                        for (FUBundleData fUBundleData2 : oldBundles) {
                            int bundleHandle = AvatarController.this.getMBundleManager().getBundleHandle(fUBundleData2.getPath());
                            if (bundleHandle > 0) {
                                BaseAvatarController.removeReferenceCount$default(AvatarController.this, AvatarController.this.getHandleReferenceCountMap(), fUBundleData2.getPath(), 0, 4, (Object) null);
                                arrayList.add(Integer.valueOf(bundleHandle));
                            }
                        }
                        if (oldBundles.size() > 0) {
                            SDKController.INSTANCE.unbindItemsFromInstance$fu_core_all_featureRelease(i, CollectionsKt.toIntArray(arrayList));
                        }
                        ArrayList arrayList2 = new ArrayList();
                        Iterator it2 = newBundles.iterator();
                        while (it2.hasNext()) {
                            int bundleHandle2 = AvatarController.this.getMBundleManager().getBundleHandle(((FUBundleData) it2.next()).getPath());
                            if (bundleHandle2 > 0) {
                                arrayList2.add(Integer.valueOf(bundleHandle2));
                            }
                        }
                        ArrayList arrayList3 = arrayList2;
                        if (!arrayList3.isEmpty()) {
                            SDKController.INSTANCE.bindItemsToInstance$fu_core_all_featureRelease(i, CollectionsKt.toIntArray(arrayList3));
                        }
                        Iterator it3 = oldBundles.iterator();
                        while (it3.hasNext()) {
                            AvatarController.this.destroyBundle(((FUBundleData) it3.next()).getPath());
                        }
                    }
                });
            }
        });
    }

    public final void replaceAvatarItemBundleGL(long avatarId, final ArrayList<FUBundleData> oldBundles, final ArrayList<FUBundleData> newBundles) {
        Intrinsics.checkParameterIsNotNull(oldBundles, "oldBundles");
        Intrinsics.checkParameterIsNotNull(newBundles, "newBundles");
        Iterator<T> it = newBundles.iterator();
        while (it.hasNext()) {
            BaseAvatarController.addReferenceCount$default(this, getHandleReferenceCountMap(), ((FUBundleData) it.next()).getPath(), 0, 4, (Object) null);
        }
        doAvatarActionGL(avatarId, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                Iterator it2 = newBundles.iterator();
                while (it2.hasNext()) {
                    AvatarController.this.createBundle(((FUBundleData) it2.next()).getPath());
                }
                ArrayList arrayList = new ArrayList();
                for (FUBundleData fUBundleData : oldBundles) {
                    int bundleHandle = AvatarController.this.getMBundleManager().getBundleHandle(fUBundleData.getPath());
                    if (bundleHandle > 0) {
                        AvatarController avatarController = AvatarController.this;
                        BaseAvatarController.removeReferenceCount$default(avatarController, avatarController.getHandleReferenceCountMap(), fUBundleData.getPath(), 0, 4, (Object) null);
                        arrayList.add(Integer.valueOf(bundleHandle));
                    }
                }
                if (oldBundles.size() > 0) {
                    SDKController.INSTANCE.unbindItemsFromInstance$fu_core_all_featureRelease(i, CollectionsKt.toIntArray(arrayList));
                }
                ArrayList arrayList2 = new ArrayList();
                Iterator it3 = newBundles.iterator();
                while (it3.hasNext()) {
                    int bundleHandle2 = AvatarController.this.getMBundleManager().getBundleHandle(((FUBundleData) it3.next()).getPath());
                    if (bundleHandle2 > 0) {
                        arrayList2.add(Integer.valueOf(bundleHandle2));
                    }
                }
                ArrayList arrayList3 = arrayList2;
                if (!arrayList3.isEmpty()) {
                    SDKController.INSTANCE.bindItemsToInstance$fu_core_all_featureRelease(i, CollectionsKt.toIntArray(arrayList3));
                }
                Iterator it4 = oldBundles.iterator();
                while (it4.hasNext()) {
                    AvatarController.this.destroyBundle(((FUBundleData) it4.next()).getPath());
                }
            }
        });
    }

    public static void setCurrentScene$default(AvatarController avatarController, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        avatarController.setCurrentScene(j, z);
    }

    public final void setCurrentScene(long sceneId, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setCurrentScene$fu_core_all_featureRelease(i);
            }
        });
    }

    public static void enableBackgroundColor$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableBackgroundColor(j, z, z2);
    }

    public final void enableBackgroundColor(long sceneId, final boolean enable, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableBackgroundColor$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public static void setBackgroundColor$default(AvatarController avatarController, long j, FUColorRGBData fUColorRGBData, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setBackgroundColor(j, fUColorRGBData, z);
    }

    public final void setBackgroundColor(long sceneId, final FUColorRGBData color, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(color, "color");
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setBackgroundColor$fu_core_all_featureRelease(i, (int) color.getRed(), (int) color.getGreen(), (int) color.getBlue(), (int) color.getAlpha());
            }
        });
    }

    public static void enableRenderCamera$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableRenderCamera(j, z, z2);
    }

    public final void enableRenderCamera(long sceneId, final boolean enable, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableRenderCamera$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public static void setProjectionMatrixFov$default(AvatarController avatarController, long j, float f, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setProjectionMatrixFov(j, f, z);
    }

    public final void setProjectionMatrixFov(long sceneId, final float fov, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setProjectionMatrixFov$fu_core_all_featureRelease(i, fov);
            }
        });
    }

    public static void setProjectionMatrixOrthoSize$default(AvatarController avatarController, long j, float f, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setProjectionMatrixOrthoSize(j, f, z);
    }

    public final void setProjectionMatrixOrthoSize(long sceneId, final float fov, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setProjectionMatrixOrthoSize$fu_core_all_featureRelease(i, fov);
            }
        });
    }

    public static void setProjectionMatrixZnear$default(AvatarController avatarController, long j, float f, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setProjectionMatrixZnear(j, f, z);
    }

    public final void setProjectionMatrixZnear(long sceneId, final float near, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setProjectionMatrixZnear$fu_core_all_featureRelease(i, near);
            }
        });
    }

    public static void setProjectionMatrixZfar$default(AvatarController avatarController, long j, float f, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setProjectionMatrixZfar(j, f, z);
    }

    public final void setProjectionMatrixZfar(long sceneId, final float far, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setProjectionMatrixZfar$fu_core_all_featureRelease(i, far);
            }
        });
    }

    public static void enableCameraAnimation$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableCameraAnimation(j, z, z2);
    }

    public final void enableCameraAnimation(long sceneId, final boolean enable, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableCameraAnimation$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public static void startCameraAnimation$default(AvatarController avatarController, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        avatarController.startCameraAnimation(j, z);
    }

    public final void startCameraAnimation(long sceneId, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.startCameraAnimation$fu_core_all_featureRelease(i);
            }
        });
    }

    public static void pauseCameraAnimation$default(AvatarController avatarController, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        avatarController.pauseCameraAnimation(j, z);
    }

    public final void pauseCameraAnimation(long sceneId, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.pauseCameraAnimation$fu_core_all_featureRelease(i);
            }
        });
    }

    public static void resetCameraAnimation$default(AvatarController avatarController, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        avatarController.resetCameraAnimation(j, z);
    }

    public final void resetCameraAnimation(long sceneId, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.resetCameraAnimation$fu_core_all_featureRelease(i);
            }
        });
    }

    public static void setCameraAnimationTransitionTime$default(AvatarController avatarController, long j, float f, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setCameraAnimationTransitionTime(j, f, z);
    }

    public final void setCameraAnimationTransitionTime(long sceneId, final float time, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setCameraAnimationTransitionTime$fu_core_all_featureRelease(i, time);
            }
        });
    }

    public static void enableCameraAnimationInternalLerp$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableCameraAnimationInternalLerp(j, z, z2);
    }

    public final void enableCameraAnimationInternalLerp(long sceneId, final boolean enable, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableCameraAnimationInternalLerp$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public final int getCameraAnimationFrameNumber(long sceneId, FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Integer num = getSceneIdMap().get(Long.valueOf(sceneId));
        int bundleHandle = getMBundleManager().getBundleHandle(bundle.getPath());
        if (num == null || bundleHandle <= 0) {
            return -1;
        }
        return SDKController.INSTANCE.getCameraAnimationFrameNumber$fu_core_all_featureRelease(num.intValue(), bundleHandle);
    }

    public final float getCameraAnimationProgress(long sceneId, FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Integer num = getSceneIdMap().get(Long.valueOf(sceneId));
        int bundleHandle = getMBundleManager().getBundleHandle(bundle.getPath());
        if (num == null || bundleHandle <= 0) {
            return -1.0f;
        }
        return SDKController.INSTANCE.getCameraAnimationProgress$fu_core_all_featureRelease(num.intValue(), bundleHandle);
    }

    public final float getCameraAnimationTransitionProgress(long sceneId) {
        Integer num = getSceneIdMap().get(Long.valueOf(sceneId));
        if (num != null) {
            return SDKController.INSTANCE.getCameraAnimationTransitionProgress$fu_core_all_featureRelease(num.intValue());
        }
        return -1.0f;
    }

    public final void loadCameraAnimationData(final long sceneId, final FUAnimationData animationData, final Boolean isLoop, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(animationData, "animationData");
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                final ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                AvatarController.this.analyzeAnimationData(animationData, arrayList, arrayList2);
                AvatarController.this.doCreateAnimationBundle(arrayList, arrayList2);
                AvatarController.this.doSceneActionGL(sceneId, new Function1<Integer, Unit>() {
                    {
                        super(1);
                    }

                    @Override
                    public Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i2) {
                        AvatarController.this.doAddCameraAnimation(i2, arrayList, arrayList2, isLoop);
                    }
                });
            }
        });
    }

    public final void removeCameraAnimationData(long sceneId, final FUAnimationData animationData, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(animationData, "animationData");
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                AvatarController.this.doRemoveCameraAnimation(i, animationData);
            }
        });
    }

    public final void replaceCameraAnimationData(final long sceneId, final FUAnimationData animationData, final FUAnimationData targetAnimationData, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(animationData, "animationData");
        Intrinsics.checkParameterIsNotNull(targetAnimationData, "targetAnimationData");
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                final ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                AvatarController.this.analyzeAnimationData(targetAnimationData, arrayList, arrayList2);
                AvatarController.this.doCreateAnimationBundle(arrayList, arrayList2);
                AvatarController.this.doSceneActionGL(sceneId, new Function1<Integer, Unit>() {
                    {
                        super(1);
                    }

                    @Override
                    public Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i2) {
                        AvatarController.this.doRemoveCameraAnimation(i2, animationData);
                        AvatarController.doAddCameraAnimation$default(AvatarController.this, i2, arrayList, arrayList2, null, 8, null);
                    }
                });
            }
        });
    }

    public static void playCameraAnimation$default(AvatarController avatarController, long j, FUAnimationData fUAnimationData, boolean z, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            z2 = true;
        }
        avatarController.playCameraAnimation(j, fUAnimationData, z, z2);
    }

    public final void playCameraAnimation(long sceneId, final FUAnimationData animationData, final boolean isLoop, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(animationData, "animationData");
        doAvatarActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                AvatarController.this.analyzeAnimationData(animationData, arrayList, arrayList2);
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    int bundleHandle = AvatarController.this.getMBundleManager().getBundleHandle(((FUBundleData) it.next()).getPath());
                    if (bundleHandle > 0) {
                        if (!isLoop) {
                            SDKController.INSTANCE.playCameraAnimationOnce$fu_core_all_featureRelease(i, bundleHandle);
                        } else {
                            SDKController.INSTANCE.playCameraAnimation$fu_core_all_featureRelease(i, bundleHandle);
                        }
                    }
                }
            }
        });
    }

    public final void doRemoveCameraAnimation(int sceneId, FUAnimationData animationData) {
        ArrayList<FUBundleData> arrayList = new ArrayList<>();
        analyzeAnimationData(animationData, arrayList, arrayList);
        ArrayList arrayList2 = new ArrayList();
        ArrayList<FUBundleData> arrayList3 = arrayList;
        for (FUBundleData fUBundleData : arrayList3) {
            int bundleHandle = getMBundleManager().getBundleHandle(fUBundleData.getPath());
            if (bundleHandle > 0) {
                BaseAvatarController.removeReferenceCount$default(this, getHandleReferenceCountMap(), fUBundleData.getPath(), 0, 4, (Object) null);
                arrayList2.add(Integer.valueOf(bundleHandle));
            }
        }
        ArrayList arrayList4 = arrayList2;
        if (!arrayList4.isEmpty()) {
            SDKController.INSTANCE.unbindItemsFromScene$fu_core_all_featureRelease(sceneId, CollectionsKt.toIntArray(arrayList4));
        }
        Iterator<T> it = arrayList3.iterator();
        while (it.hasNext()) {
            destroyBundle(((FUBundleData) it.next()).getPath());
        }
    }

    static void doAddCameraAnimation$default(AvatarController avatarController, int i, ArrayList arrayList, ArrayList arrayList2, Boolean bool, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            bool = null;
        }
        avatarController.doAddCameraAnimation(i, arrayList, arrayList2, bool);
    }

    public final void doAddCameraAnimation(int instanceId, ArrayList<FUBundleData> propList, ArrayList<FUBundleData> animationList, Boolean isLoop) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = animationList.iterator();
        while (it.hasNext()) {
            int bundleHandle = getMBundleManager().getBundleHandle(((FUBundleData) it.next()).getPath());
            if (bundleHandle > 0) {
                arrayList2.add(Integer.valueOf(bundleHandle));
                arrayList.add(Integer.valueOf(bundleHandle));
            }
        }
        Iterator<T> it2 = propList.iterator();
        while (it2.hasNext()) {
            int bundleHandle2 = getMBundleManager().getBundleHandle(((FUBundleData) it2.next()).getPath());
            if (bundleHandle2 > 0) {
                arrayList.add(Integer.valueOf(bundleHandle2));
            }
        }
        ArrayList arrayList3 = arrayList;
        if (!arrayList3.isEmpty()) {
            SDKController.INSTANCE.bindItemsToScene$fu_core_all_featureRelease(instanceId, CollectionsKt.toIntArray(arrayList3));
        }
        if (isLoop != null) {
            isLoop.booleanValue();
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                int iIntValue = ((Number) it3.next()).intValue();
                if (!isLoop.booleanValue()) {
                    SDKController.INSTANCE.playCameraAnimationOnce$fu_core_all_featureRelease(instanceId, iIntValue);
                } else {
                    SDKController.INSTANCE.playCameraAnimation$fu_core_all_featureRelease(instanceId, iIntValue);
                }
            }
        }
    }

    public static void enableShadow$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableShadow(j, z, z2);
    }

    public final void enableShadow(long sceneId, final boolean enable, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableShadow$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public static void setInstanceShadowPCFLevel$default(AvatarController avatarController, long j, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        avatarController.setInstanceShadowPCFLevel(j, i, z);
    }

    public final void setInstanceShadowPCFLevel(long sceneId, final int level, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceShadowPCFLevel$fu_core_all_featureRelease(i, level);
            }
        });
    }

    public static void enableLowQualityLighting$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableLowQualityLighting(j, z, z2);
    }

    public final void enableLowQualityLighting(long sceneId, final boolean enable, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableLowQualityLighting$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public static void enableARMode$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableARMode(j, z, z2);
    }

    public final void enableARMode(long sceneId, final boolean enable, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableARMode$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public static void enableHumanProcessor$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableHumanProcessor(j, z, z2);
    }

    public final void enableHumanProcessor(long sceneId, final boolean enable, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableHumanProcessor$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public static void enableFaceProcessor$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableFaceProcessor(j, z, z2);
    }

    public final void enableFaceProcessor(long sceneId, final boolean enable, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableFaceProcessor$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public static void humanProcessorSet3DScene$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.humanProcessorSet3DScene(j, z, z2);
    }

    public final void humanProcessorSet3DScene(long sceneId, final boolean isFull, boolean needBackgroundThread) {
        doSceneActionBackgroundGL(sceneId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.humanProcessorSet3DScene$fu_core_all_featureRelease(i, isFull);
            }
        });
    }

    public static void setInstanceBodyVisibleList$default(AvatarController avatarController, long j, int[] iArr, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setInstanceBodyVisibleList(j, iArr, z);
    }

    public final void setInstanceBodyVisibleList(long avatarId, final int[] visibleList, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(visibleList, "visibleList");
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceBodyVisibleList$fu_core_all_featureRelease(i, visibleList);
            }
        });
    }

    public static void setInstanceBodyInvisibleList$default(AvatarController avatarController, long j, int[] iArr, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setInstanceBodyInvisibleList(j, iArr, z);
    }

    public final void setInstanceBodyInvisibleList(long avatarId, final int[] visibleList, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(visibleList, "visibleList");
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.fuSetInstanceBodyInvisibleList$fu_core_all_featureRelease(i, visibleList);
            }
        });
    }

    public final void getInstanceFaceVertexScreenCoordinate(long avatarId, int index, float[] rect) {
        Intrinsics.checkParameterIsNotNull(rect, "rect");
        Integer num = getAvatarIdMap().get(Long.valueOf(avatarId));
        if (num != null) {
            SDKController.INSTANCE.getInstanceFaceVertexScreenCoordinate$fu_core_all_featureRelease(num.intValue(), index, rect);
        }
    }

    public static void setInstanceTargetPosition$default(AvatarController avatarController, long j, FUCoordinate3DData fUCoordinate3DData, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setInstanceTargetPosition(j, fUCoordinate3DData, z);
    }

    public final void setInstanceTargetPosition(long avatarId, final FUCoordinate3DData position, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(position, "position");
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceTargetPosition$fu_core_all_featureRelease(i, (float) position.getPositionX(), (float) position.getPositionY(), (float) position.getPositionZ());
            }
        });
    }

    public static void setInstanceTargetAngle$default(AvatarController avatarController, long j, float f, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setInstanceTargetAngle(j, f, z);
    }

    public final void setInstanceTargetAngle(long avatarId, final float value, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceTargetAngle$fu_core_all_featureRelease(i, value);
            }
        });
    }

    public static void setInstanceRotDelta$default(AvatarController avatarController, long j, float f, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setInstanceRotDelta(j, f, z);
    }

    public final void setInstanceRotDelta(long avatarId, final float value, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceRotDelta$fu_core_all_featureRelease(i, value);
            }
        });
    }

    public static void setInstanceScaleDelta$default(AvatarController avatarController, long j, float f, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setInstanceScaleDelta(j, f, z);
    }

    public final void setInstanceScaleDelta(long avatarId, final float value, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceScaleDelta$fu_core_all_featureRelease(i, value);
            }
        });
    }

    public static void setInstanceTranslateDelta$default(AvatarController avatarController, long j, float f, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setInstanceTranslateDelta(j, f, z);
    }

    public final void setInstanceTranslateDelta(long avatarId, final float value, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceTranslateDelta$fu_core_all_featureRelease(i, value);
            }
        });
    }

    public static void playInstanceAnimation$default(AvatarController avatarController, long j, FUAnimationData fUAnimationData, boolean z, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            z2 = true;
        }
        avatarController.playInstanceAnimation(j, fUAnimationData, z, z2);
    }

    public final void playInstanceAnimation(long avatarId, final FUAnimationData animationData, final boolean isLoop, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(animationData, "animationData");
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                ArrayList arrayList = new ArrayList();
                FUAnimationData fUAnimationData = animationData;
                if (fUAnimationData instanceof FUGroupAnimationData) {
                    arrayList.add(fUAnimationData.getAnimation());
                    arrayList.addAll(((FUGroupAnimationData) animationData).getSubAnimations());
                } else {
                    arrayList.add(fUAnimationData.getAnimation());
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    int bundleHandle = AvatarController.this.getMBundleManager().getBundleHandle(((FUBundleData) it.next()).getPath());
                    if (bundleHandle > 0) {
                        if (!isLoop) {
                            SDKController.INSTANCE.playInstanceAnimationOnce$fu_core_all_featureRelease(i, bundleHandle);
                        } else {
                            SDKController.INSTANCE.playInstanceAnimation$fu_core_all_featureRelease(i, bundleHandle);
                        }
                    }
                }
            }
        });
    }

    public static void startInstanceAnimation$default(AvatarController avatarController, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        avatarController.startInstanceAnimation(j, z);
    }

    public final void startInstanceAnimation(long avatarId, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.startInstanceAnimation$fu_core_all_featureRelease(i);
            }
        });
    }

    public static void pauseInstanceAnimation$default(AvatarController avatarController, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        avatarController.pauseInstanceAnimation(j, z);
    }

    public final void pauseInstanceAnimation(long avatarId, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.pauseInstanceAnimation$fu_core_all_featureRelease(i);
            }
        });
    }

    public static void stopInstanceAnimation$default(AvatarController avatarController, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        avatarController.stopInstanceAnimation(j, z);
    }

    public final void stopInstanceAnimation(long avatarId, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.stopInstanceAnimation$fu_core_all_featureRelease(i);
            }
        });
    }

    public static void resetInstanceAnimation$default(AvatarController avatarController, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        avatarController.resetInstanceAnimation(j, z);
    }

    public final void resetInstanceAnimation(long avatarId, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.resetInstanceAnimation$fu_core_all_featureRelease(i);
            }
        });
    }

    public static void setInstanceAnimationTransitionTime$default(AvatarController avatarController, long j, float f, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setInstanceAnimationTransitionTime(j, f, z);
    }

    public final void setInstanceAnimationTransitionTime(long avatarId, final float time, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceAnimationTransitionTime$fu_core_all_featureRelease(i, time);
            }
        });
    }

    public static void enableInstanceAnimationInternalLerp$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableInstanceAnimationInternalLerp(j, z, z2);
    }

    public final void enableInstanceAnimationInternalLerp(long avatarId, final boolean enable, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableInstanceAnimationInternalLerp$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public final int getInstanceAnimationFrameNumber(long avatarId, FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Integer num = getAvatarIdMap().get(Long.valueOf(avatarId));
        int bundleHandle = getMBundleManager().getBundleHandle(bundle.getPath());
        if (num == null || bundleHandle <= 0) {
            return -1;
        }
        return SDKController.INSTANCE.getInstanceAnimationFrameNumber$fu_core_all_featureRelease(num.intValue(), bundleHandle);
    }

    public final float getInstanceAnimationProgress(long avatarId, FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Integer num = getAvatarIdMap().get(Long.valueOf(avatarId));
        int bundleHandle = getMBundleManager().getBundleHandle(bundle.getPath());
        if (num == null || bundleHandle <= 0) {
            return -1.0f;
        }
        return SDKController.INSTANCE.getInstanceAnimationProgress$fu_core_all_featureRelease(num.intValue(), bundleHandle);
    }

    public final float getInstanceAnimationTransitionProgress(long avatarId, FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Integer num = getAvatarIdMap().get(Long.valueOf(avatarId));
        int bundleHandle = getMBundleManager().getBundleHandle(bundle.getPath());
        if (num == null || bundleHandle <= 0) {
            return -1.0f;
        }
        return SDKController.INSTANCE.m287xe80dadc8(num.intValue(), bundleHandle);
    }

    public final void loadAvatarAnimationData(final long avatarId, final FUAnimationData animationData, final Boolean isLoop) {
        Intrinsics.checkParameterIsNotNull(animationData, "animationData");
        doAvatarActionBackground(avatarId, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                final ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                AvatarController.this.analyzeAnimationData(animationData, arrayList, arrayList2);
                AvatarController.this.doCreateAnimationBundle(arrayList, arrayList2);
                AvatarController.this.doAvatarActionGL(avatarId, new Function1<Integer, Unit>() {
                    {
                        super(1);
                    }

                    @Override
                    public Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i) {
                        AvatarController.this.doAddAvatarAnimation(i, arrayList, arrayList2, isLoop);
                    }
                });
            }
        });
    }

    public final void removeAvatarAnimationData(long avatarId, final FUAnimationData animationData) {
        Intrinsics.checkParameterIsNotNull(animationData, "animationData");
        doAvatarActionBackgroundGL$default(this, avatarId, false, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                AvatarController.this.doRemoveAvatarAnimation(i, animationData);
            }
        }, 2, null);
    }

    public final void replaceAvatarAnimationData(final long avatarId, final FUAnimationData animationData, final FUAnimationData targetAnimationData) {
        Intrinsics.checkParameterIsNotNull(animationData, "animationData");
        Intrinsics.checkParameterIsNotNull(targetAnimationData, "targetAnimationData");
        doAvatarActionBackground(avatarId, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                final ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                AvatarController.this.analyzeAnimationData(targetAnimationData, arrayList, arrayList2);
                AvatarController.this.doCreateAnimationBundle(arrayList, arrayList2);
                AvatarController.this.doAvatarActionGL(avatarId, new Function1<Integer, Unit>() {
                    {
                        super(1);
                    }

                    @Override
                    public Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i) {
                        AvatarController.this.doRemoveAvatarAnimation(i, animationData);
                        AvatarController.doAddAvatarAnimation$default(AvatarController.this, i, arrayList, arrayList2, null, 8, null);
                    }
                });
            }
        });
    }

    public final void analyzeAnimationData(FUAnimationData animationData, ArrayList<FUBundleData> propList, ArrayList<FUBundleData> animationList) {
        if (animationData instanceof FUGroupAnimationData) {
            animationList.add(animationData.getAnimation());
            FUGroupAnimationData fUGroupAnimationData = (FUGroupAnimationData) animationData;
            animationList.addAll(fUGroupAnimationData.getSubAnimations());
            propList.addAll(fUGroupAnimationData.getSubProps());
            return;
        }
        animationList.add(animationData.getAnimation());
    }

    public final void doRemoveAvatarAnimation(int instanceId, FUAnimationData animationData) {
        ArrayList<FUBundleData> arrayList = new ArrayList<>();
        analyzeAnimationData(animationData, arrayList, arrayList);
        ArrayList arrayList2 = new ArrayList();
        ArrayList<FUBundleData> arrayList3 = arrayList;
        for (FUBundleData fUBundleData : arrayList3) {
            int bundleHandle = getMBundleManager().getBundleHandle(fUBundleData.getPath());
            if (bundleHandle > 0) {
                BaseAvatarController.removeReferenceCount$default(this, getHandleReferenceCountMap(), fUBundleData.getPath(), 0, 4, (Object) null);
                arrayList2.add(Integer.valueOf(bundleHandle));
            }
        }
        ArrayList arrayList4 = arrayList2;
        if (!arrayList4.isEmpty()) {
            SDKController.INSTANCE.unbindItemsFromInstance$fu_core_all_featureRelease(instanceId, CollectionsKt.toIntArray(arrayList4));
        }
        Iterator<T> it = arrayList3.iterator();
        while (it.hasNext()) {
            destroyBundle(((FUBundleData) it.next()).getPath());
        }
    }

    public final void doCreateAnimationBundle(ArrayList<FUBundleData> propList, ArrayList<FUBundleData> animationList) {
        for (FUBundleData fUBundleData : animationList) {
            BaseAvatarController.addReferenceCount$default(this, getHandleReferenceCountMap(), fUBundleData.getPath(), 0, 4, (Object) null);
            createBundle(fUBundleData.getPath());
        }
        for (FUBundleData fUBundleData2 : propList) {
            BaseAvatarController.addReferenceCount$default(this, getHandleReferenceCountMap(), fUBundleData2.getPath(), 0, 4, (Object) null);
            createBundle(fUBundleData2.getPath());
        }
    }

    static void doAddAvatarAnimation$default(AvatarController avatarController, int i, ArrayList arrayList, ArrayList arrayList2, Boolean bool, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            bool = null;
        }
        avatarController.doAddAvatarAnimation(i, arrayList, arrayList2, bool);
    }

    public final void doAddAvatarAnimation(int instanceId, ArrayList<FUBundleData> propList, ArrayList<FUBundleData> animationList, Boolean isLoop) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = animationList.iterator();
        while (it.hasNext()) {
            int bundleHandle = getMBundleManager().getBundleHandle(((FUBundleData) it.next()).getPath());
            if (bundleHandle > 0) {
                arrayList2.add(Integer.valueOf(bundleHandle));
                arrayList.add(Integer.valueOf(bundleHandle));
            }
        }
        Iterator<T> it2 = propList.iterator();
        while (it2.hasNext()) {
            int bundleHandle2 = getMBundleManager().getBundleHandle(((FUBundleData) it2.next()).getPath());
            if (bundleHandle2 > 0) {
                arrayList.add(Integer.valueOf(bundleHandle2));
            }
        }
        ArrayList arrayList3 = arrayList;
        if (!arrayList3.isEmpty()) {
            SDKController.INSTANCE.bindItemsToInstance$fu_core_all_featureRelease(instanceId, CollectionsKt.toIntArray(arrayList3));
        }
        if (isLoop != null) {
            isLoop.booleanValue();
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                int iIntValue = ((Number) it3.next()).intValue();
                if (!isLoop.booleanValue()) {
                    SDKController.INSTANCE.playInstanceAnimationOnce$fu_core_all_featureRelease(instanceId, iIntValue);
                } else {
                    SDKController.INSTANCE.playInstanceAnimation$fu_core_all_featureRelease(instanceId, iIntValue);
                }
            }
        }
    }

    public static void enableInstanceExpressionBlend$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableInstanceExpressionBlend(j, z, z2);
    }

    public final void enableInstanceExpressionBlend(long avatarId, final boolean enable, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableInstanceExpressionBlend$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public static void setInstanceBlendExpression$default(AvatarController avatarController, long j, float[] fArr, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setInstanceBlendExpression(j, fArr, z);
    }

    public final void setInstanceBlendExpression(long avatarId, final float[] data, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceBlendExpression$fu_core_all_featureRelease(i, data);
            }
        });
    }

    public static void setInstanceExpressionWeight0$default(AvatarController avatarController, long j, float[] fArr, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setInstanceExpressionWeight0(j, fArr, z);
    }

    public final void setInstanceExpressionWeight0(long avatarId, final float[] data, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceExpressionWeight0$fu_core_all_featureRelease(i, data);
            }
        });
    }

    public static void setInstanceExpressionWeight1$default(AvatarController avatarController, long j, float[] fArr, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        avatarController.setInstanceExpressionWeight1(j, fArr, z);
    }

    public final void setInstanceExpressionWeight1(long avatarId, final float[] data, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceExpressionWeight1$fu_core_all_featureRelease(i, data);
            }
        });
    }

    public static void setInstanceColor$default(AvatarController avatarController, long j, String str, FUColorRGBData fUColorRGBData, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        avatarController.setInstanceColor(j, str, fUColorRGBData, z);
    }

    public final void setInstanceColor(long avatarId, final String name, final FUColorRGBData color, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(color, "color");
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceColor$fu_core_all_featureRelease(i, name, (int) color.getRed(), (int) color.getGreen(), (int) color.getBlue());
            }
        });
    }

    public static void setInstanceColorIntensity$default(AvatarController avatarController, long j, String str, float f, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        avatarController.setInstanceColorIntensity(j, str, f, z);
    }

    public final void setInstanceColorIntensity(long avatarId, final String name, final float intensity, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceColorIntensity$fu_core_all_featureRelease(i, name, intensity);
            }
        });
    }

    public static void fuSetInstanceFaceBeautyColor$default(AvatarController avatarController, long j, FUBundleData fUBundleData, FUColorRGBData fUColorRGBData, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        avatarController.fuSetInstanceFaceBeautyColor(j, fUBundleData, fUColorRGBData, z);
    }

    public final void fuSetInstanceFaceBeautyColor(long avatarId, final FUBundleData bundle, final FUColorRGBData color, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Intrinsics.checkParameterIsNotNull(color, "color");
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                int bundleHandle = AvatarController.this.getMBundleManager().getBundleHandle(bundle.getPath());
                if (bundleHandle > 0) {
                    SDKController.INSTANCE.fuSetInstanceFaceBeautyColor$fu_core_all_featureRelease(i, bundleHandle, (int) color.getRed(), (int) color.getGreen(), (int) color.getBlue());
                } else {
                    FULogger.m297w(AvatarController.this.getTAG(), "fuSetInstanceFaceBeautyColor failed  bundle=" + bundle.getName() + " handle=" + bundleHandle);
                }
            }
        });
    }

    public final int getInstanceSkinColorIndex(long avatarId) {
        Integer num = getAvatarIdMap().get(Long.valueOf(avatarId));
        if (num != null) {
            return SDKController.INSTANCE.getInstanceSkinColorIndex$fu_core_all_featureRelease(num.intValue());
        }
        return -1;
    }

    public static void setInstanceDeformation$default(AvatarController avatarController, long j, String str, float f, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        avatarController.setInstanceDeformation(j, str, f, z);
    }

    public final void setInstanceDeformation(long avatarId, final String name, final float intensity, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceDeformation$fu_core_all_featureRelease(i, name, intensity);
            }
        });
    }

    public static void enableInstanceDynamicBone$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableInstanceDynamicBone(j, z, z2);
    }

    public final void enableInstanceDynamicBone(long avatarId, final boolean enable, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableInstanceDynamicBone$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public static void resetInstanceDynamicBone$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.resetInstanceDynamicBone(j, z, z2);
    }

    public final void resetInstanceDynamicBone(long avatarId, final boolean isImmediate, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.resetInstanceDynamicBone$fu_core_all_featureRelease(i, isImmediate ? 1 : 0);
            }
        });
    }

    public static void refreshInstanceDynamicBone$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.refreshInstanceDynamicBone(j, z, z2);
    }

    public final void refreshInstanceDynamicBone(long avatarId, final boolean isImmediate, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.refreshInstanceDynamicBone$fu_core_all_featureRelease(i, isImmediate ? 1 : 0);
            }
        });
    }

    public static void enableInstanceModelMatToBone$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableInstanceModelMatToBone(j, z, z2);
    }

    public final void enableInstanceModelMatToBone(long avatarId, final boolean enable, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableInstanceModelMatToBone$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public static void enableInstanceDynamicBoneTeleportMode$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableInstanceDynamicBoneTeleportMode(j, z, z2);
    }

    public final void enableInstanceDynamicBoneTeleportMode(long avatarId, final boolean enable, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableInstanceDynamicBoneTeleportMode$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public static void enableInstanceDynamicBoneRootRotationSpeedLimitMode$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableInstanceDynamicBoneRootRotationSpeedLimitMode(j, z, z2);
    }

    public final void enableInstanceDynamicBoneRootRotationSpeedLimitMode(long avatarId, final boolean enable, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.m284xaf221de1(i, enable);
            }
        });
    }

    public static void enableInstanceDynamicBoneRootTranslationSpeedLimitMode$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableInstanceDynamicBoneRootTranslationSpeedLimitMode(j, z, z2);
    }

    public final void enableInstanceDynamicBoneRootTranslationSpeedLimitMode(long avatarId, final boolean enable, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.m285x9b4fb1c8(i, enable);
            }
        });
    }

    public static void enableInstanceFocusEyeToCamera$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableInstanceFocusEyeToCamera(j, z, z2);
    }

    public final void enableInstanceFocusEyeToCamera(long avatarId, final boolean enable, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableInstanceFocusEyeToCamera$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public static void enableInstanceFaceUpMode$default(AvatarController avatarController, long j, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = true;
        }
        avatarController.enableInstanceFaceUpMode(j, z, z2);
    }

    public final void enableInstanceFaceUpMode(long avatarId, final boolean enable, boolean needBackgroundThread) {
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.enableInstanceFaceUpMode$fu_core_all_featureRelease(i, enable);
            }
        });
    }

    public static void setInstanceFaceUp$default(AvatarController avatarController, long j, String str, float f, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        avatarController.setInstanceFaceUp(j, str, f, z);
    }

    public final void setInstanceFaceUp(long avatarId, final String name, final float value, boolean needBackgroundThread) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        doAvatarActionBackgroundGL(avatarId, needBackgroundThread, new Function1<Integer, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SDKController.INSTANCE.setInstanceFaceUp$fu_core_all_featureRelease(i, name, value);
            }
        });
    }

    public final float getInstanceFaceUpOriginalValue(long avatarId, String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Integer num = getAvatarIdMap().get(Long.valueOf(avatarId));
        if (num != null) {
            return SDKController.INSTANCE.getInstanceFaceUpOriginalValue$fu_core_all_featureRelease(num.intValue(), name);
        }
        return 0.0f;
    }

    public final int getInstanceFaceUpArray(long avatarId, float[] rect) {
        Intrinsics.checkParameterIsNotNull(rect, "rect");
        Integer num = getAvatarIdMap().get(Long.valueOf(avatarId));
        if (num != null) {
            return SDKController.INSTANCE.getInstanceFaceUpArray$fu_core_all_featureRelease(num.intValue(), rect);
        }
        return 0;
    }

    public final void enableHumanFollowMode(final long avatarId, final boolean enable) {
        doBackgroundAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                Integer num = AvatarController.this.getAvatarIdMap().get(Long.valueOf(avatarId));
                if (num != null) {
                    num.intValue();
                    SDKController.INSTANCE.enableHumanFollowMode$fu_core_all_featureRelease(num.intValue(), enable);
                }
            }
        });
    }

    public final void fuSetInstanceRiggingRetargeterAvatarFollowMode(final long avatarId, final int mode) {
        doBackgroundAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                Integer num = AvatarController.this.getAvatarIdMap().get(Long.valueOf(avatarId));
                if (num != null) {
                    num.intValue();
                    SDKController.INSTANCE.m286x153dd222(num.intValue(), mode);
                }
            }
        });
    }

    public final void fuSetInstanceEnableHumanAnimDriver(final long avatarId, final boolean mode) {
        doBackgroundAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                Integer num = AvatarController.this.getAvatarIdMap().get(Long.valueOf(avatarId));
                if (num != null) {
                    num.intValue();
                    SDKController.INSTANCE.fuSetInstanceEnableHumanAnimDriver$fu_core_all_featureRelease(num.intValue(), mode);
                }
            }
        });
    }

    public final void humanProcessorSetAvatarAnimFilterParams(final int nBufferFrames, final float pos, final float angle) {
        doBackgroundAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                SDKController.INSTANCE.m288xb58577de(nBufferFrames, pos, angle);
            }
        });
    }

    public final void setInstanceRiggingRetargeterAvatarFixModeTransScale(final long avatarId, final float x, final float y, final float z) {
        doBackgroundAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                Integer num = AvatarController.this.getAvatarIdMap().get(Long.valueOf(avatarId));
                if (num != null) {
                    num.intValue();
                    SDKController.INSTANCE.m291x809d2ee7(num.intValue(), x, y, z);
                }
            }
        });
    }
}
