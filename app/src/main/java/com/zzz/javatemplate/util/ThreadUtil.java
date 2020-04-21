package com.zzz.javatemplate.util;

import com.geek.thread.GeekThreadManager;
import com.geek.thread.ThreadPriority;
import com.geek.thread.ThreadType;
import com.geek.thread.task.GeekThread;

public class ThreadUtil {
    public static void runOnIOThread(final Runnable runnable){
        GeekThreadManager.getInstance().execute(new GeekThread(ThreadPriority.NORMAL) {
            @Override
            public void run() {
                runnable.run();
            }
        }, ThreadType.NORMAL_THREAD);
    }

}
