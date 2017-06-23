package view.chenyu.com.freedom;

import com.tencent.bugly.crashreport.BuglyLog;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by wxl19 on 2017/6/21.
 */

class AppExceptionHandler implements Thread.UncaughtExceptionHandler {
    
    private static final String TAG = AppExceptionHandler.class.getName();
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        CrashReport.postCatchedException(e, t);
        BuglyLog.e(TAG,"未捕获异常",e);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
