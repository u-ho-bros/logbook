package logbook.thread;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * スレッドを管理します
 *
 */
public final class ThreadManager {

    /** Executor */
    private static final ScheduledExecutorService EXECUTOR = Executors.newScheduledThreadPool(4);

    private static List<Thread> threads = new ArrayList<Thread>();

    private static Map<Thread, ExceptionHandler> handlermap = new HashMap<Thread, ExceptionHandler>();

    /**
     * アプリケーションで共有するExecutorService
     * <p>
     * 長時間実行する必要のあるスレッドを登録する場合、割り込みされたかを検知して適切に終了するようにしてください。
     * </p>
     *
     * @return ExecutorService
     */
    public static ScheduledExecutorService getExecutorService() {
        return EXECUTOR;
    }

    /**
     * スレッドを管理下に置きます
     *
     * @param thread
     */
    @Deprecated
    public static void regist(Thread thread) {
        threads.add(thread);
    }

    /**
     * 管理しているスレッドを開始します
     */
    @Deprecated
    public static void start() {
        for (Thread thread : threads) {
            if (!thread.isAlive()) {
                thread.setDaemon(true);

                ExceptionHandler handler = new ExceptionHandler();
                handlermap.put(thread, handler);
                thread.setUncaughtExceptionHandler(handler);

                thread.start();
            }
        }
    }

    /**
     * 管理しているスレッドを取得します
     * @return
     */
    @Deprecated
    static List<Thread> getThreads() {
        return Collections.unmodifiableList(threads);
    }

    /**
     * 例外ハンドラを取得します
     *
     * @param thread
     * @return
     */
    static ExceptionHandler getUncaughtExceptionHandler(Thread thread) {
        return handlermap.get(thread);
    }

    /**
     * エラーハンドラ
     *
     */
    public static final class ExceptionHandler implements Thread.UncaughtExceptionHandler {

        private Throwable throwable;

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            this.throwable = e;
        }

        @Override
        public String toString() {
            if (this.throwable == null) {
                return "";
            }
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            this.throwable.printStackTrace(printWriter);
            printWriter.close();
            return stringWriter.toString();
        }
    }
}
