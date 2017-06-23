package view.chenyu.com.freedom;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tencent.bugly.crashreport.BuglyLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 使用Glide 注意：
 * with(arg)
 * arg 如果是activity的话，activity必须存在，
 */

/**
 * @author 王兴岭
 * @desc 演示glide 异常问题
 * @email wxlhdm@qq.com
 * @create 2017/6/21 22:11
 **/
public class GlideExceptionActivity extends AppCompatActivity {

    private ImageView imageView;
    
    private static final String TAG = GlideExceptionActivity.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide_activity);
        imageView = (ImageView) findViewById(R.id.show);
        OkHttpUtils.get().url("http://172.16.1.253:8080/jinjiu/mcd/timeout")
                .tag(this).build().connTimeOut(5000000L).readTimeOut(5000000L).execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                BuglyLog.e(TAG,"网络请求",e);
            }



            @Override
            public void onResponse(String response, int id) {
                Glide.with(GlideExceptionActivity.this)
                        .load("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/0C/0E/ChMkJ1j1gQ6ILskaADPWcgfgSeYAAbvmQAADeEAM9aK508.jpg")
                        .into(imageView);
            }
        });
//        GlideAsyncTask task = new GlideAsyncTask();
//        task.execute();
    }

    private class GlideAsyncTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Glide.with(GlideExceptionActivity.this)
                    .load("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/0C/0E/ChMkJ1j1gQ6ILskaADPWcgfgSeYAAbvmQAADeEAM9aK508.jpg")
                    .into(imageView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        OkHttpUtils.getInstance().cancelTag(this);
    }
}
