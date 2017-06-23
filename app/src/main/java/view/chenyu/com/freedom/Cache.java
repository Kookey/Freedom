package view.chenyu.com.freedom;

import android.content.Context;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;

/**
 * Created by wxl19 on 2017/6/12.
 */

public class Cache {

    private static int appVersion = 100;

    private static int valueCount = 1;// 同一个key可以对应多少个缓存文件

    private static int maxSize = 10 * 1024 * 1024;// 一个缓存文件最大可以缓存10M

    public static final String CACHE_OBJECT = "object";// 对象缓存目录

    private DiskLruCache lruCache;

    public Cache(Context context) {
        File cacheDir = getDiskCacheDir(context, CACHE_OBJECT);
        try {
            lruCache = DiskLruCache.open(cacheDir, appVersion, valueCount, maxSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void set(String key, String value) {
        try {
            DiskLruCache.Editor editor = lruCache.edit(key);
            editor.set(0, value);
            editor.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
    }

    public String get(String key) {
        try {
            DiskLruCache.Snapshot snapshot = lruCache.get(key);
            String string = snapshot.getString(0);
            snapshot.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取相应的缓存目录
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }
}
