package view.chenyu.com.freedom.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.tencent.bugly.crashreport.BuglyLog;

import java.io.IOException;
import java.io.InputStream;

import view.chenyu.com.freedom.R;

import static view.chenyu.com.freedom.R.raw.demo;


/**
 * @author 王兴岭
 * @desc 加载执行js
 * @email wxlhdm@qq.com
 * @create 2017/6/23 17:25
 **/
public class WebViewJsActivity extends AppCompatActivity {

    private static final String TAG = WebViewJsActivity.class.getName();

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initWebView();
        loadUrl();
        setTitle(R.string.js_show_case);
    }

    private void initWebView() {
        webView = (WebView) findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();
        //关键点，默认式false,需要设置为true才能执行js
        settings.setJavaScriptEnabled(true);
        //需要和html页面上的方法的接口名一样，不然无法执行，
        //页面在raw/demo.html 此演示是用的本地html,远程加载html,原理是一样的
        /**
         * <script type="text/javascript">
         * function showAndroidToast(toast) {
         * //关键点
         * Android.showToast(toast);
         * }
         * </script>
         */                                                       //需要注意点
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
    }


    private void loadUrl() {
        InputStream inputStream = null;
        try {
            inputStream = getResources().openRawResource(demo);
            byte[] bytes = new byte[1024];
            int read;
            StringBuffer buffer = new StringBuffer();
            while ((read = inputStream.read(bytes)) != -1) {
                buffer.append(new String(bytes, 0, read));
            }
            BuglyLog.e(TAG, buffer.toString());
            webView.loadData(buffer.toString(), "text/html", "utf8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
