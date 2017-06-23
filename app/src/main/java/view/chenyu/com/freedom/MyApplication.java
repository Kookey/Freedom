package view.chenyu.com.freedom;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.tencent.bugly.crashreport.BuglyLog;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Description:
 * Author:hxy
 * Email:451757573@qq.com
 * Date:2017/5/3 0003.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initBugly();
//        initDefaultExceptionHandler();
    }

    private void initDefaultExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new AppExceptionHandler());
    }

    private void initBugly() {
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        strategy.setBuglyLogUpload(true);
        CrashReport.initCrashReport(context, "fe58d85136", true, strategy);
        BuglyLog.setCache(1*1024);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
