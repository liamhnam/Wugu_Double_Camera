package com.p020hp.printsdk;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelKt;
import androidx.room.migration.Migration;
import androidx.sqlite.p014db.SupportSQLiteDatabase;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.p020hp.open.printsdk.CoreManager;
import com.p020hp.open.printsdk.HpPrintJob;
import com.p020hp.open.printsdk.HpPrintRequest;
import com.p020hp.open.printsdk.HpPrinter;
import com.p020hp.open.printsdk.PrinterWithJobs;
import com.p020hp.open.printsdk.SdkViewModel;
import com.p020hp.printsdk.p021db.HpPrintSdkDb;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

public final class C1752q2 implements InterfaceC1737n2 {

    public static final C1752q2 f1624a;

    public static final Logger f1625b;

    public static HpPrintSdkDb f1626c;

    public static final Map<UUID, LiveData<HpPrintJob>> f1627d;

    public static final Migration f1628e;

    public static final Migration f1629f;

    public static final Migration f1630g;

    public static final Migration f1631h;

    public static final Migration f1632i;

    public static final class a extends Migration {
        public a() {
            super(1, 2);
        }

        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            C1752q2.f1625b.invoke("database migrate from 1 to 2");
            database.execSQL("CREATE TABLE history_job_new (\n    job_id          TEXT    NOT NULL,\n    job_name        TEXT    NOT NULL,\n    job_create_time INTEGER NOT NULL,\n    job_submit_time INTEGER NOT NULL,\n    job_status_info INTEGER NOT NULL,\n    printer_id      TEXT    NOT NULL,\n    job_done_time   INTEGER NOT NULL,\n    PRIMARY KEY (job_id)\n);");
            database.execSQL("INSERT INTO history_job_new (job_id, job_name, job_create_time, job_submit_time, job_status_info, printer_id, job_done_time) \nSELECT job_id, job_name, job_create_time, job_create_time, job_status_info, printer_id, job_create_time FROM history_job");
            database.execSQL("DROP TABLE history_job");
            database.execSQL("ALTER TABLE history_job_new RENAME TO history_job");
        }
    }

    public static final class b extends Migration {
        public b() {
            super(2, 3);
        }

        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            C1752q2.f1625b.invoke("database migrate from 2 to 3");
            database.execSQL("ALTER TABLE history_job ADD COLUMN job_type TEXT NOT NULL DEFAULT 'NA'");
            database.execSQL("ALTER TABLE history_job ADD COLUMN job_path TEXT NOT NULL DEFAULT 'NA'");
            database.execSQL("ALTER TABLE history_job ADD COLUMN job_size TEXT NOT NULL DEFAULT 'NA'");
            database.execSQL("ALTER TABLE history_job ADD COLUMN job_options_copies TEXT NOT NULL DEFAULT 'NA'");
            database.execSQL("ALTER TABLE history_job ADD COLUMN job_options_mediaSize TEXT NOT NULL DEFAULT 'NA'");
            database.execSQL("ALTER TABLE history_job ADD COLUMN job_options_color TEXT NOT NULL DEFAULT 'NA'");
            database.execSQL("ALTER TABLE history_job ADD COLUMN job_options_scale TEXT NOT NULL DEFAULT 'NA'");
            database.execSQL("ALTER TABLE history_job ADD COLUMN job_options_quality TEXT NOT NULL DEFAULT 'NA'");
            database.execSQL("ALTER TABLE history_job ADD COLUMN job_options_sides TEXT NOT NULL DEFAULT 'NA'");
            database.execSQL("ALTER TABLE history_job ADD COLUMN job_options_orientation TEXT NOT NULL DEFAULT 'NA'");
            database.execSQL("ALTER TABLE history_job ADD COLUMN job_options_pageRanges TEXT NOT NULL DEFAULT 'NA'");
        }
    }

    public static final class c extends Migration {
        public c() {
            super(3, 4);
        }

        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            C1752q2.f1625b.invoke("database migrate from 3 to 4");
            database.execSQL("ALTER TABLE history_job ADD COLUMN job_borderless INT NOT NULL DEFAULT 0");
        }
    }

    public static final class d extends Migration {
        public d() {
            super(4, 5);
        }

        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            C1752q2.f1625b.invoke("database migrate from 4 to 5");
            database.execSQL("CREATE TABLE history_job_new (\n    job_id          TEXT    NOT NULL,\n    job_name        TEXT    NOT NULL,\n    job_create_time INTEGER NOT NULL,\n    job_submit_time INTEGER NOT NULL,\n    job_status_info INTEGER NOT NULL,\n    printer_id      TEXT    NOT NULL,\n    job_done_time   INTEGER NOT NULL,\n    job_type   TEXT NOT NULL,\n    job_path   TEXT NOT NULL,\n    job_size   TEXT NOT NULL,\n    job_options_copies   TEXT NOT NULL,\n    job_options_mediaSize   TEXT NOT NULL,\n    job_options_color   TEXT NOT NULL,\n    job_options_scale   TEXT NOT NULL,\n    job_options_quality   TEXT NOT NULL,\n    job_options_sides   TEXT NOT NULL,\n    job_options_orientation   TEXT NOT NULL,\n    job_options_pageRanges   TEXT NOT NULL,\n    job_options_mediaMargin   INTEGER NOT NULL,\n    job_options_inputTray   TEXT NOT NULL,\n    job_options_paperType   TEXT NOT NULL,\n    PRIMARY KEY (job_id)\n);");
            database.execSQL("INSERT INTO history_job_new (job_id, job_name, job_create_time, job_submit_time, job_status_info, printer_id, job_done_time,\n                             job_type, job_path, job_size, job_options_copies, job_options_mediaSize, job_options_color, job_options_scale, job_options_quality, \n                             job_options_sides, job_options_orientation, job_options_pageRanges, job_options_mediaMargin, job_options_inputTray, job_options_paperType)\nSELECT job_id, job_name, job_create_time, job_submit_time, job_status_info, printer_id, job_done_time, job_type, job_path, \n        job_size, job_options_copies, job_options_mediaSize, job_options_color, job_options_scale, job_options_quality, job_options_sides,\n        job_options_orientation, job_options_pageRanges, job_borderless, job_borderless, job_borderless FROM history_job");
            database.execSQL("DROP TABLE history_job");
            database.execSQL("ALTER TABLE history_job_new RENAME TO history_job");
        }
    }

    public static final class e extends Migration {
        public e() {
            super(5, 6);
        }

        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            C1752q2.f1625b.invoke("database migrate from 5 to 6");
            database.execSQL("ALTER TABLE history_job ADD COLUMN job_options_dpi INT NOT NULL DEFAULT 300");
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.db.DbHelper$delDoneJobHistory$1", m1305f = "DbHelper.kt", m1306i = {1, 2}, m1307l = {211, 213, 217, 221, 229}, m1308m = "invokeSuspend", m1309n = {"dao", "dao"}, m1310s = {"L$0", "L$0"})
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public Object f1633a;

        public int f1634b;

        public final UUID[] f1635c;

        public final Function1<List<PrinterWithJobs>, Unit> f1636d;

        @DebugMetadata(m1304c = "com.hp.printsdk.db.DbHelper$delDoneJobHistory$1$2", m1305f = "DbHelper.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

            public final Function1<List<PrinterWithJobs>, Unit> f1637a;

            public final Map<UUID, PrinterWithJobs> f1638b;

            public a(Function1<? super List<PrinterWithJobs>, Unit> function1, Map<UUID, PrinterWithJobs> map, Continuation<? super a> continuation) {
                super(2, continuation);
                this.f1637a = function1;
                this.f1638b = map;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new a(this.f1637a, this.f1638b, continuation);
            }

            @Override
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new a(this.f1637a, this.f1638b, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                this.f1637a.invoke(CollectionsKt.toList(this.f1638b.values()));
                return Unit.INSTANCE;
            }
        }

        public f(UUID[] uuidArr, Function1<? super List<PrinterWithJobs>, Unit> function1, Continuation<? super f> continuation) {
            super(2, continuation);
            this.f1635c = uuidArr;
            this.f1636d = function1;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new f(this.f1635c, this.f1636d, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new f(this.f1635c, this.f1636d, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 361
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1752q2.f.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.db.DbHelper", m1305f = "DbHelper.kt", m1306i = {}, m1307l = {46}, m1308m = "getJobHistoryDbDao", m1309n = {}, m1310s = {})
    public static final class g extends ContinuationImpl {

        public Object f1639a;

        public Object f1640b;

        public int f1642d;

        public g(Continuation<? super g> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1640b = obj;
            this.f1642d |= Integer.MIN_VALUE;
            return C1752q2.this.m620a(this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.db.DbHelper$jobHistory$1", m1305f = "DbHelper.kt", m1306i = {}, m1307l = {182, 182, 185}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class h extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f1643a;

        public final UUID[] f1644b;

        public final Function1<List<PrinterWithJobs>, Unit> f1645c;

        @DebugMetadata(m1304c = "com.hp.printsdk.db.DbHelper$jobHistory$1$1", m1305f = "DbHelper.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

            public final Function1<List<PrinterWithJobs>, Unit> f1646a;

            public final List<PrinterWithJobs> f1647b;

            public a(Function1<? super List<PrinterWithJobs>, Unit> function1, List<PrinterWithJobs> list, Continuation<? super a> continuation) {
                super(2, continuation);
                this.f1646a = function1;
                this.f1647b = list;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new a(this.f1646a, this.f1647b, continuation);
            }

            @Override
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new a(this.f1646a, this.f1647b, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                this.f1646a.invoke(this.f1647b);
                return Unit.INSTANCE;
            }
        }

        public h(UUID[] uuidArr, Function1<? super List<PrinterWithJobs>, Unit> function1, Continuation<? super h> continuation) {
            super(2, continuation);
            this.f1644b = uuidArr;
            this.f1645c = function1;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new h(this.f1644b, this.f1645c, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new h(this.f1644b, this.f1645c, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.f1643a
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L25
                if (r1 == r4) goto L21
                if (r1 == r3) goto L1d
                if (r1 != r2) goto L15
                kotlin.ResultKt.throwOnFailure(r8)
                goto L64
            L15:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L1d:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L4d
            L21:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L33
            L25:
                kotlin.ResultKt.throwOnFailure(r8)
                com.hp.printsdk.q2 r8 = com.p020hp.printsdk.C1752q2.f1624a
                r7.f1643a = r4
                java.lang.Object r8 = r8.m620a(r7)
                if (r8 != r0) goto L33
                return r0
            L33:
                com.hp.printsdk.u2 r8 = (com.p020hp.printsdk.AbstractC1771u2) r8
                com.hp.open.printsdk.CoreManager r1 = com.p020hp.open.printsdk.CoreManager.INSTANCE
                long r4 = r1.getJobHistoryEarliestTime$print_core_thirdPartyRelease()
                java.util.UUID[] r1 = r7.f1644b
                int r6 = r1.length
                java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r6)
                java.util.UUID[] r1 = (java.util.UUID[]) r1
                r7.f1643a = r3
                java.lang.Object r8 = r8.m665a(r4, r1, r7)
                if (r8 != r0) goto L4d
                return r0
            L4d:
                java.util.List r8 = (java.util.List) r8
                kotlinx.coroutines.MainCoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getMain()
                com.hp.printsdk.q2$h$a r3 = new com.hp.printsdk.q2$h$a
                kotlin.jvm.functions.Function1<java.util.List<com.hp.open.printsdk.PrinterWithJobs>, kotlin.Unit> r4 = r7.f1645c
                r5 = 0
                r3.<init>(r4, r8, r5)
                r7.f1643a = r2
                java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r1, r3, r7)
                if (r8 != r0) goto L64
                return r0
            L64:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1752q2.h.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.db.DbHelper$jobs$1", m1305f = "DbHelper.kt", m1306i = {}, m1307l = {199, 199, 202}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class i extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f1648a;

        public final UUID[] f1649b;

        public final int f1650c;

        public final int f1651d;

        public final Function1<List<PrinterWithJobs>, Unit> f1652e;

        @DebugMetadata(m1304c = "com.hp.printsdk.db.DbHelper$jobs$1$1", m1305f = "DbHelper.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

            public final Function1<List<PrinterWithJobs>, Unit> f1653a;

            public final List<PrinterWithJobs> f1654b;

            public a(Function1<? super List<PrinterWithJobs>, Unit> function1, List<PrinterWithJobs> list, Continuation<? super a> continuation) {
                super(2, continuation);
                this.f1653a = function1;
                this.f1654b = list;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new a(this.f1653a, this.f1654b, continuation);
            }

            @Override
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new a(this.f1653a, this.f1654b, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                this.f1653a.invoke(this.f1654b);
                return Unit.INSTANCE;
            }
        }

        public i(UUID[] uuidArr, int i, int i2, Function1<? super List<PrinterWithJobs>, Unit> function1, Continuation<? super i> continuation) {
            super(2, continuation);
            this.f1649b = uuidArr;
            this.f1650c = i;
            this.f1651d = i2;
            this.f1652e = function1;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new i(this.f1649b, this.f1650c, this.f1651d, this.f1652e, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new i(this.f1649b, this.f1650c, this.f1651d, this.f1652e, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) throws java.lang.Throwable {
            /*
                r11 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r11.f1648a
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L25
                if (r1 == r4) goto L21
                if (r1 == r3) goto L1d
                if (r1 != r2) goto L15
                kotlin.ResultKt.throwOnFailure(r12)
                goto L6b
            L15:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r0)
                throw r12
            L1d:
                kotlin.ResultKt.throwOnFailure(r12)
                goto L54
            L21:
                kotlin.ResultKt.throwOnFailure(r12)
                goto L33
            L25:
                kotlin.ResultKt.throwOnFailure(r12)
                com.hp.printsdk.q2 r12 = com.p020hp.printsdk.C1752q2.f1624a
                r11.f1648a = r4
                java.lang.Object r12 = r12.m620a(r11)
                if (r12 != r0) goto L33
                return r0
            L33:
                r4 = r12
                com.hp.printsdk.u2 r4 = (com.p020hp.printsdk.AbstractC1771u2) r4
                com.hp.open.printsdk.CoreManager r12 = com.p020hp.open.printsdk.CoreManager.INSTANCE
                long r5 = r12.getJobHistoryEarliestTime$print_core_thirdPartyRelease()
                java.util.UUID[] r12 = r11.f1649b
                int r1 = r12.length
                java.lang.Object[] r12 = java.util.Arrays.copyOf(r12, r1)
                r7 = r12
                java.util.UUID[] r7 = (java.util.UUID[]) r7
                int r8 = r11.f1650c
                int r9 = r11.f1651d
                r11.f1648a = r3
                r10 = r11
                java.lang.Object r12 = r4.m664a(r5, r7, r8, r9, r10)
                if (r12 != r0) goto L54
                return r0
            L54:
                java.util.List r12 = (java.util.List) r12
                kotlinx.coroutines.MainCoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getMain()
                com.hp.printsdk.q2$i$a r3 = new com.hp.printsdk.q2$i$a
                kotlin.jvm.functions.Function1<java.util.List<com.hp.open.printsdk.PrinterWithJobs>, kotlin.Unit> r4 = r11.f1652e
                r5 = 0
                r3.<init>(r4, r12, r5)
                r11.f1648a = r2
                java.lang.Object r12 = kotlinx.coroutines.BuildersKt.withContext(r1, r3, r11)
                if (r12 != r0) goto L6b
                return r0
            L6b:
                kotlin.Unit r12 = kotlin.Unit.INSTANCE
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1752q2.i.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final class j implements Observer<HpPrintJob> {

        public final LiveData<HpPrintJob> f1655a;

        public final HpPrinter f1656b;

        public final HpPrintRequest f1657c;

        public j(LiveData<HpPrintJob> liveData, HpPrinter hpPrinter, HpPrintRequest hpPrintRequest) {
            this.f1655a = liveData;
            this.f1656b = hpPrinter;
            this.f1657c = hpPrintRequest;
        }

        @Override
        public void onChanged(HpPrintJob hpPrintJob) {
            HpPrintJob job = hpPrintJob;
            Intrinsics.checkNotNullParameter(job, "job");
            C1752q2.f1625b.m446d(new C1756r2(job));
            if (C1752q2.f1627d.get(job.getId()) == null && !C1752q2.f1627d.containsKey(job.getId())) {
                C1752q2.f1627d.put(job.getId(), this.f1655a);
            }
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope((SdkViewModel) CoreManager.INSTANCE.getAppViewModel$print_core_thirdPartyRelease(SdkViewModel.class)), Dispatchers.getIO(), null, new C1761s2(this.f1656b, job, this.f1657c, null), 2, null);
            if (job.isDone()) {
                C1752q2.f1627d.put(job.getId(), null);
                this.f1655a.removeObserver(this);
            }
        }
    }

    static {
        C1752q2 c1752q2 = new C1752q2();
        f1624a = c1752q2;
        f1625b = LoggerKt.logger(c1752q2);
        f1627d = new LinkedHashMap();
        f1628e = new a();
        f1629f = new b();
        f1630g = new c();
        f1631h = new d();
        f1632i = new e();
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m620a(kotlin.coroutines.Continuation<? super com.p020hp.printsdk.AbstractC1771u2> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.p020hp.printsdk.C1752q2.g
            if (r0 == 0) goto L13
            r0 = r9
            com.hp.printsdk.q2$g r0 = (com.p020hp.printsdk.C1752q2.g) r0
            int r1 = r0.f1642d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1642d = r1
            goto L18
        L13:
            com.hp.printsdk.q2$g r0 = new com.hp.printsdk.q2$g
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.f1640b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f1642d
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r0 = r0.f1639a
            com.hp.printsdk.u2 r0 = (com.p020hp.printsdk.AbstractC1771u2) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto Lb5
        L2e:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L36:
            kotlin.ResultKt.throwOnFailure(r9)
            monitor-enter(r8)
            com.hp.printsdk.db.HpPrintSdkDb r9 = com.p020hp.printsdk.C1752q2.f1626c     // Catch: java.lang.Throwable -> Lb9
            if (r9 != 0) goto L7f
            com.hp.open.printsdk.CoreManager r9 = com.p020hp.open.printsdk.CoreManager.INSTANCE     // Catch: java.lang.Throwable -> Lb9
            android.app.Application r9 = r9.getContext$print_core_thirdPartyRelease()     // Catch: java.lang.Throwable -> Lb9
            java.lang.Class<com.hp.printsdk.db.HpPrintSdkDb> r2 = com.p020hp.printsdk.p021db.HpPrintSdkDb.class
            java.lang.String r4 = "hpPrintSdk.db"
            androidx.room.RoomDatabase$Builder r9 = androidx.room.Room.databaseBuilder(r9, r2, r4)     // Catch: java.lang.Throwable -> Lb9
            androidx.room.RoomDatabase$Builder r9 = r9.fallbackToDestructiveMigrationOnDowngrade()     // Catch: java.lang.Throwable -> Lb9
            r2 = 5
            androidx.room.migration.Migration[] r2 = new androidx.room.migration.Migration[r2]     // Catch: java.lang.Throwable -> Lb9
            androidx.room.migration.Migration r4 = com.p020hp.printsdk.C1752q2.f1628e     // Catch: java.lang.Throwable -> Lb9
            r5 = 0
            r2[r5] = r4     // Catch: java.lang.Throwable -> Lb9
            androidx.room.migration.Migration r4 = com.p020hp.printsdk.C1752q2.f1629f     // Catch: java.lang.Throwable -> Lb9
            r2[r3] = r4     // Catch: java.lang.Throwable -> Lb9
            androidx.room.migration.Migration r4 = com.p020hp.printsdk.C1752q2.f1630g     // Catch: java.lang.Throwable -> Lb9
            r5 = 2
            r2[r5] = r4     // Catch: java.lang.Throwable -> Lb9
            androidx.room.migration.Migration r4 = com.p020hp.printsdk.C1752q2.f1631h     // Catch: java.lang.Throwable -> Lb9
            r5 = 3
            r2[r5] = r4     // Catch: java.lang.Throwable -> Lb9
            androidx.room.migration.Migration r4 = com.p020hp.printsdk.C1752q2.f1632i     // Catch: java.lang.Throwable -> Lb9
            r5 = 4
            r2[r5] = r4     // Catch: java.lang.Throwable -> Lb9
            androidx.room.RoomDatabase$Builder r9 = r9.addMigrations(r2)     // Catch: java.lang.Throwable -> Lb9
            androidx.room.RoomDatabase r9 = r9.build()     // Catch: java.lang.Throwable -> Lb9
            r2 = r9
            com.hp.printsdk.db.HpPrintSdkDb r2 = (com.p020hp.printsdk.p021db.HpPrintSdkDb) r2     // Catch: java.lang.Throwable -> Lb9
            com.p020hp.printsdk.C1752q2.f1626c = r2     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r2 = "databaseBuilder(context,…tSdkDb = it\n            }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r2)     // Catch: java.lang.Throwable -> Lb9
            com.hp.printsdk.db.HpPrintSdkDb r9 = (com.p020hp.printsdk.p021db.HpPrintSdkDb) r9     // Catch: java.lang.Throwable -> Lb9
        L7f:
            monitor-exit(r8)
            com.hp.printsdk.db.HpPrintSdkDb_Impl r9 = (com.p020hp.printsdk.p021db.HpPrintSdkDb_Impl) r9
            com.hp.printsdk.u2 r2 = r9.f1176a
            if (r2 == 0) goto L89
            com.hp.printsdk.u2 r9 = r9.f1176a
            goto L99
        L89:
            monitor-enter(r9)
            com.hp.printsdk.u2 r2 = r9.f1176a     // Catch: java.lang.Throwable -> Lb6
            if (r2 != 0) goto L95
            com.hp.printsdk.a3 r2 = new com.hp.printsdk.a3     // Catch: java.lang.Throwable -> Lb6
            r2.<init>(r9)     // Catch: java.lang.Throwable -> Lb6
            r9.f1176a = r2     // Catch: java.lang.Throwable -> Lb6
        L95:
            com.hp.printsdk.u2 r2 = r9.f1176a     // Catch: java.lang.Throwable -> Lb6
            monitor-exit(r9)     // Catch: java.lang.Throwable -> Lb6
            r9 = r2
        L99:
            com.hp.open.printsdk.CoreManager r2 = com.p020hp.open.printsdk.CoreManager.INSTANCE
            long r4 = r2.getJobHistoryEarliestTime$print_core_thirdPartyRelease()
            r0.f1639a = r9
            r0.f1642d = r3
            r2 = r9
            com.hp.printsdk.a3 r2 = (com.p020hp.printsdk.C1666a3) r2
            androidx.room.RoomDatabase r6 = r2.f884a
            com.hp.printsdk.d3 r7 = new com.hp.printsdk.d3
            r7.<init>(r2, r4)
            java.lang.Object r0 = androidx.room.CoroutinesRoom.execute(r6, r3, r7, r0)
            if (r0 != r1) goto Lb4
            return r1
        Lb4:
            r0 = r9
        Lb5:
            return r0
        Lb6:
            r0 = move-exception
            monitor-exit(r9)     // Catch: java.lang.Throwable -> Lb6
            throw r0
        Lb9:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1752q2.m620a(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override
    public void mo588a(HpPrinter printer, LiveData<HpPrintJob> jobs, HpPrintRequest request) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        Intrinsics.checkNotNullParameter(jobs, "jobs");
        Intrinsics.checkNotNullParameter(request, "request");
        Map<UUID, LiveData<HpPrintJob>> map = f1627d;
        HpPrintJob value = jobs.getValue();
        if (map.get(value != null ? value.getId() : null) != null) {
            return;
        }
        jobs.observeForever(new j(jobs, printer, request));
    }

    @Override
    public void delDoneJobHistory(UUID[] jobIds, Function1<? super List<PrinterWithJobs>, Unit> callback) {
        Intrinsics.checkNotNullParameter(jobIds, "jobIds");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope((SdkViewModel) CoreManager.INSTANCE.getAppViewModel$print_core_thirdPartyRelease(SdkViewModel.class)), Dispatchers.getIO(), null, new f(jobIds, callback, null), 2, null);
    }

    @Override
    public void jobHistory(UUID[] printerIds, Function1<? super List<PrinterWithJobs>, Unit> callback) {
        Intrinsics.checkNotNullParameter(printerIds, "printerIds");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope((SdkViewModel) CoreManager.INSTANCE.getAppViewModel$print_core_thirdPartyRelease(SdkViewModel.class)), Dispatchers.getIO(), null, new h(printerIds, callback, null), 2, null);
    }

    @Override
    public void jobs(UUID[] printerIds, int i2, int i3, Function1<? super List<PrinterWithJobs>, Unit> callback) {
        Intrinsics.checkNotNullParameter(printerIds, "printerIds");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope((SdkViewModel) CoreManager.INSTANCE.getAppViewModel$print_core_thirdPartyRelease(SdkViewModel.class)), Dispatchers.getIO(), null, new i(printerIds, i2, i3, callback, null), 2, null);
    }
}
