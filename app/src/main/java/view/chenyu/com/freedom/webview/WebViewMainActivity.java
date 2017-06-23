package view.chenyu.com.freedom.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import view.chenyu.com.freedom.R;


/**
 * @author 王兴岭
 * @desc 主页面
 * @email wxlhdm@qq.com
 * @create 2017/6/23 17:21
 **/
public class WebViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_main);
//        setTitle();

    }

    private void setTitle() {
        getActionBar().setTitle(R.string.webview_case_main);
    }

    public void showJsCase(View view) {
        Intent intent = new Intent(this, WebViewJsActivity.class);
        startActivity(intent);
    }

    public void showLoad(View view) {
        Intent intent = new Intent(this, WebViewActivity.class);
        startActivity(intent);
    }
}
