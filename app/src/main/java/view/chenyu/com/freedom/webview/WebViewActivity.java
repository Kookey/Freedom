package view.chenyu.com.freedom.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import view.chenyu.com.freedom.R;


/**
 * @author 王兴岭
 * @desc 使用 webview loadUrl 使用案例
 * @email wxlhdm@qq.com
 * @create 2017/6/23 13:25
 **/
public class WebViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = WebViewActivity.class.getName();
    private WebView webView;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        setTitle(R.string.webview_load_case);
        initWebView();
        initRefresh();
        loadUrl();
    }

    private void initWebView() {
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebClient());
    }

    private static class MyWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            //如果想让webview加载url,此处一定要返回false.true表示让系统自己去应用加载，那就和webview没有
            //任何鸟关系了，切记
            return false;
        }
    }

    private void initRefresh() {
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_light,
                android.R.color.holo_purple);
    }

    private void loadUrl() {
        //加载远程url
        webView.loadUrl("https://www.baidu.com/");
    }

    @Override
    public void onRefresh() {
        loadUrl();
        refreshLayout.setRefreshing(false);
    }

    //浏览网页历史记录
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        //交给系统处理
        return super.onKeyDown(keyCode, event);
    }
}
