package view.chenyu.com.freedom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import view.chenyu.com.freedom.fragment.FirstFragment;
import view.chenyu.com.freedom.fragment.ThreeFragment;
import view.chenyu.com.freedom.fragment.TwoFragment;

/**
 * @author 王兴岭
 * @desc banner 广告
 * @email wxlhdm@qq.com
 * @create 2017/6/21 10:39
 **/
public class BannerActivity extends AppCompatActivity {

    private static final String TAG = BannerActivity.class.getName();
    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.real_content);
        View view1 = getLayoutInflater().inflate(R.layout.indicator, null);
        View view2 = getLayoutInflater().inflate(R.layout.indicator, null);
        View view3 = getLayoutInflater().inflate(R.layout.indicator, null);
        //setIndicator view 必须是不同的对象，否则报异常
        tabHost.addTab(tabHost.newTabSpec("simple").setIndicator(view1),
                FirstFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("contacts").setIndicator(view2),
                TwoFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("custom").setIndicator(view3),
                ThreeFragment.class, null);
    }

}
