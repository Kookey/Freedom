package view.chenyu.com.freedom.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by wxl19 on 2017/6/23.
 */

public class WebAppInterface {

    private Context mContext;

    public WebAppInterface(Context mContext) {
        this.mContext = mContext;
    }

    //必须加这个注解，不然不会执行，showToast需要和页面上的方法名一致
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
}
