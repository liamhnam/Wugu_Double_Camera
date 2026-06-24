package kotlinx.coroutines;

import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationText;
import java.io.Closeable;
import java.util.concurrent.Executor;
import kotlin.Metadata;

@Metadata(m1292d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u0000 \n2\u00020\u00012\u00020\u0002:\u0001\nB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH&R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, m1293d2 = {"Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Ljava/io/Closeable;", "()V", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "close", "", PDAnnotationText.NAME_KEY, "kotlinx-coroutines-core"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class ExecutorCoroutineDispatcher extends CoroutineDispatcher implements Closeable {
    @Override
    public abstract void close();

    public abstract Executor getExecutor();
}
